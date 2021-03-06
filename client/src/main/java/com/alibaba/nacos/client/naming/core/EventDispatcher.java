/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.client.naming.core;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.pojo.ServiceInfo;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.alibaba.nacos.common.lifecycle.Closeable;
import com.alibaba.nacos.common.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import static com.alibaba.nacos.client.utils.LogUtils.NAMING_LOGGER;

/** 事件调度者  单线程处理 比如通知服务实例变更
 * Event dispatcher.
 * 比如ServiceInfo有变动(新增、修改、删除)，会通知过来  springcloud的NacosWatch会注册EventDispatcher事件
 * @author xuanyin
 */
@SuppressWarnings("PMD.ThreadPoolCreationRule")
public class EventDispatcher implements Closeable {

    private ExecutorService executor = null;
    /** 变更服务对象 */
    private final BlockingQueue<ServiceInfo> changedServices = new LinkedBlockingQueue<ServiceInfo>();
    /** 事件监听注册者  */
    private final ConcurrentMap<String, List<EventListener>> observerMap = new ConcurrentHashMap<String, List<EventListener>>();

    private volatile boolean closed = false;

    public EventDispatcher() {
        // 单线程
        this.executor = Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "com.alibaba.nacos.naming.client.listener");
                thread.setDaemon(true);

                return thread;
            }
        });

        this.executor.execute(new Notifier()); // Notifier一直循环   直到closed=true结束
    }

    /** subscribe调用本方法
     * Add listener.
     *
     * @param serviceInfo service info
     * @param clusters    clusters
     * @param listener    listener
     */
    public void addListener(ServiceInfo serviceInfo, String clusters, EventListener listener) {

        NAMING_LOGGER.info("[LISTENER] adding " + serviceInfo.getName() + " with " + clusters + " to listener map");
        List<EventListener> observers = Collections.synchronizedList(new ArrayList<EventListener>());
        observers.add(listener);

        observers = observerMap.putIfAbsent(ServiceInfo.getKey(serviceInfo.getName(), clusters), observers);
        if (observers != null) {
            observers.add(listener);
        }

        serviceChanged(serviceInfo); // changedServices.add(serviceInfo);
    }

    /**
     * Remove listener.
     *
     * @param serviceName service name
     * @param clusters    clusters
     * @param listener    listener
     */
    public void removeListener(String serviceName, String clusters, EventListener listener) {

        NAMING_LOGGER.info("[LISTENER] removing " + serviceName + " with " + clusters + " from listener map");

        List<EventListener> observers = observerMap.get(ServiceInfo.getKey(serviceName, clusters));
        if (observers != null) {
            Iterator<EventListener> iter = observers.iterator();
            while (iter.hasNext()) {
                EventListener oldListener = iter.next();
                if (oldListener.equals(listener)) {
                    iter.remove();
                }
            }
            if (observers.isEmpty()) {
                observerMap.remove(ServiceInfo.getKey(serviceName, clusters));
            }
        }
    }

    public boolean isSubscribed(String serviceName, String clusters) {
        return observerMap.containsKey(ServiceInfo.getKey(serviceName, clusters));
    }

    public List<ServiceInfo> getSubscribeServices() {
        List<ServiceInfo> serviceInfos = new ArrayList<ServiceInfo>();
        for (String key : observerMap.keySet()) {
            serviceInfos.add(ServiceInfo.fromKey(key));
        }
        return serviceInfos;
    }

    /**
     * Service changed.
     *
     * @param serviceInfo service info
     */
    public void serviceChanged(ServiceInfo serviceInfo) {
        if (serviceInfo == null) {
            return;
        }

        changedServices.add(serviceInfo);
    }

    @Override
    public void shutdown() throws NacosException {
        String className = this.getClass().getName();
        NAMING_LOGGER.info("{} do shutdown begin", className);
        ThreadUtils.shutdownThreadPool(executor, NAMING_LOGGER);
        closed = true;
        NAMING_LOGGER.info("{} do shutdown stop", className);
    }
    /** 线程池只有1个线程在处理  */
    private class Notifier implements Runnable {

        @Override
        public void run() {
            while (!closed) {

                ServiceInfo serviceInfo = null;
                try {
                    serviceInfo = changedServices.poll(5, TimeUnit.MINUTES); //  Retrieves and removes the head of this queue
                } catch (Exception ignore) {
                }

                if (serviceInfo == null) {
                    continue;
                }

                try {
                    List<EventListener> listeners = observerMap.get(serviceInfo.getKey()); // 只通知某个服务的监听者

                    if (!CollectionUtils.isEmpty(listeners)) {
                        for (EventListener listener : listeners) {
                            List<Instance> hosts = Collections.unmodifiableList(serviceInfo.getHosts()); // 具体的服务实例
                            listener.onEvent(new NamingEvent(serviceInfo.getName(), serviceInfo.getGroupName(),
                                    serviceInfo.getClusters(), hosts)); // 逐个通知 比如SpringCloud Alibaba的NacosWatch类
                        }
                    }

                } catch (Exception e) {
                    NAMING_LOGGER.error("[NA] notify error for service: " + serviceInfo.getName() + ", clusters: "
                            + serviceInfo.getClusters(), e);
                }
            }
        }
    }
}
