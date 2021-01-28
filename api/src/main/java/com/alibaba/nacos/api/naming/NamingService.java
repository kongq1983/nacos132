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

package com.alibaba.nacos.api.naming;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.pojo.ListView;
import com.alibaba.nacos.api.naming.pojo.ServiceInfo;
import com.alibaba.nacos.api.selector.AbstractSelector;

import java.util.List;

/**
 * Naming Service.
 *
 * @author nkorange
 */
public interface NamingService {

    /** 注册实例
     * register a instance to service.
     *
     * @param serviceName name of service
     * @param ip          instance ip
     * @param port        instance port
     * @throws NacosException nacos exception
     */
    void registerInstance(String serviceName, String ip, int port) throws NacosException;

    /**
     * register a instance to service.
     *
     * @param serviceName name of service
     * @param groupName   group of service
     * @param ip          instance ip
     * @param port        instance port
     * @throws NacosException nacos exception
     */
    void registerInstance(String serviceName, String groupName, String ip, int port) throws NacosException;

    /**
     * register a instance to service with specified cluster name.
     *
     * @param serviceName name of service
     * @param ip          instance ip
     * @param port        instance port
     * @param clusterName instance cluster name
     * @throws NacosException nacos exception
     */
    void registerInstance(String serviceName, String ip, int port, String clusterName) throws NacosException;

    /**
     * register a instance to service with specified cluster name.
     *
     * @param serviceName name of service
     * @param groupName   group of service
     * @param ip          instance ip
     * @param port        instance port
     * @param clusterName instance cluster name
     * @throws NacosException nacos exception
     */
    void registerInstance(String serviceName, String groupName, String ip, int port, String clusterName)
            throws NacosException;

    /**
     * register a instance to service with specified instance properties.
     *
     * @param serviceName name of service
     * @param instance    instance to register
     * @throws NacosException nacos exception
     */
    void registerInstance(String serviceName, Instance instance) throws NacosException;

    /**
     * register a instance to service with specified instance properties.
     *
     * @param serviceName name of service
     * @param groupName   group of service
     * @param instance    instance to register
     * @throws NacosException nacos exception
     */
    void registerInstance(String serviceName, String groupName, Instance instance) throws NacosException;

    /** 注销实例
     * deregister instance from a service.
     *
     * @param serviceName name of service
     * @param ip          instance ip
     * @param port        instance port
     * @throws NacosException nacos exception
     */
    void deregisterInstance(String serviceName, String ip, int port) throws NacosException;

    /**
     * deregister instance from a service.
     *
     * @param serviceName name of service
     * @param groupName   group of service
     * @param ip          instance ip
     * @param port        instance port
     * @throws NacosException nacos exception
     */
    void deregisterInstance(String serviceName, String groupName, String ip, int port) throws NacosException;

    /**
     * deregister instance with specified cluster name from a service.
     *
     * @param serviceName name of service
     * @param ip          instance ip
     * @param port        instance port
     * @param clusterName instance cluster name
     * @throws NacosException nacos exception
     */
    void deregisterInstance(String serviceName, String ip, int port, String clusterName) throws NacosException;

    /**
     * deregister instance with specified cluster name from a service.
     *
     * @param serviceName name of service
     * @param groupName   group of service
     * @param ip          instance ip
     * @param port        instance port
     * @param clusterName instance cluster name
     * @throws NacosException nacos exception
     */
    void deregisterInstance(String serviceName, String groupName, String ip, int port, String clusterName)
            throws NacosException;

    /**
     * deregister instance with full instance information and default groupName.
     *
     * @param serviceName name of service
     * @param instance    instance
     * @throws NacosException nacos exception
     */
    void deregisterInstance(String serviceName, Instance instance) throws NacosException;

    /**
     * deregister instance with full instance information.
     *
     * @param serviceName name of service
     * @param groupName   group of service
     * @param instance    instance information
     * @throws NacosException nacos exception
     */
    void deregisterInstance(String serviceName, String groupName, Instance instance) throws NacosException;

    /** 获取某一服务的所有实例
     * get all instances of a service.
     *
     * @param serviceName name of service
     * @return A list of instance
     * @throws NacosException nacos exception
     */
    List<Instance> getAllInstances(String serviceName) throws NacosException;

    /**
     * get all instances of a service.
     *
     * @param serviceName name of service
     * @param groupName   group of service
     * @return A list of instance
     * @throws NacosException nacos exception
     */
    List<Instance> getAllInstances(String serviceName, String groupName) throws NacosException;

    /**
     * Get all instances of a service.
     *
     * @param serviceName name of service
     * @param subscribe   if subscribe the service
     * @return A list of instance
     * @throws NacosException nacos exception
     */
    List<Instance> getAllInstances(String serviceName, boolean subscribe) throws NacosException;

    /**
     * Get all instances of a service.
     *
     * @param serviceName name of service
     * @param groupName   group of service
     * @param subscribe   if subscribe the service
     * @return A list of instance
     * @throws NacosException nacos exception
     */
    List<Instance> getAllInstances(String serviceName, String groupName, boolean subscribe) throws NacosException;

    /**
     * Get all instances within specified clusters of a service.
     *
     * @param serviceName name of service
     * @param clusters    list of cluster
     * @return A list of qualified instance
     * @throws NacosException nacos exception
     */
    List<Instance> getAllInstances(String serviceName, List<String> clusters) throws NacosException;

    /**
     * Get all instances within specified clusters of a service.
     *
     * @param serviceName name of service
     * @param groupName   group of service
     * @param clusters    list of cluster
     * @return A list of qualified instance
     * @throws NacosException nacos exception
     */
    List<Instance> getAllInstances(String serviceName, String groupName, List<String> clusters) throws NacosException;

    /**
     * Get all instances within specified clusters of a service.
     *
     * @param serviceName name of service
     * @param clusters    list of cluster
     * @param subscribe   if subscribe the service
     * @return A list of qualified instance
     * @throws NacosException nacos exception
     */
    List<Instance> getAllInstances(String serviceName, List<String> clusters, boolean subscribe) throws NacosException;

    /**
     * Get all instances within specified clusters of a service.
     *
     * @param serviceName name of service
     * @param groupName   group of service
     * @param clusters    list of cluster
     * @param subscribe   if subscribe the service
     * @return A list of qualified instance
     * @throws NacosException nacos exception
     */
    List<Instance> getAllInstances(String serviceName, String groupName, List<String> clusters, boolean subscribe)
            throws NacosException;

    /** 获取某一服务健康或不健康的实例
     * Get qualified instances of service.
     *
     * @param serviceName name of service.
     * @param healthy     a flag to indicate returning healthy or unhealthy instances
     * @return A qualified list of instance
     * @throws NacosException nacos exception
     */
    List<Instance> selectInstances(String serviceName, boolean healthy) throws NacosException;

    /**
     * Get qualified instances of service.
     *
     * @param serviceName name of service
     * @param groupName   group of service
     * @param healthy     a flag to indicate returning healthy or unhealthy instances
     * @return A qualified list of instance
     * @throws NacosException nacos exception
     */
    List<Instance> selectInstances(String serviceName, String groupName, boolean healthy) throws NacosException;

    /**
     * Get qualified instances of service.
     *
     * @param serviceName name of service
     * @param healthy     a flag to indicate returning healthy or unhealthy instances
     * @param subscribe   if subscribe the service
     * @return A qualified list of instance
     * @throws NacosException nacos exception
     */
    List<Instance> selectInstances(String serviceName, boolean healthy, boolean subscribe) throws NacosException;

    /**
     * Get qualified instances of service.
     *
     * @param serviceName name of service
     * @param groupName   group of service
     * @param healthy     a flag to indicate returning healthy or unhealthy instances
     * @param subscribe   if subscribe the service
     * @return A qualified list of instance
     * @throws NacosException nacos exception
     */
    List<Instance> selectInstances(String serviceName, String groupName, boolean healthy, boolean subscribe)
            throws NacosException;

    /**
     * Get qualified instances within specified clusters of service.
     *
     * @param serviceName name of service
     * @param clusters    list of cluster
     * @param healthy     a flag to indicate returning healthy or unhealthy instances
     * @return A qualified list of instance
     * @throws NacosException nacos exception
     */
    List<Instance> selectInstances(String serviceName, List<String> clusters, boolean healthy) throws NacosException;

    /**
     * Get qualified instances within specified clusters of service.
     *
     * @param serviceName name of service
     * @param groupName   group of service
     * @param clusters    list of cluster
     * @param healthy     a flag to indicate returning healthy or unhealthy instances
     * @return A qualified list of instance
     * @throws NacosException nacos exception
     */
    List<Instance> selectInstances(String serviceName, String groupName, List<String> clusters, boolean healthy)
            throws NacosException;

    /**
     * Get qualified instances within specified clusters of service.
     *
     * @param serviceName name of service
     * @param clusters    list of cluster
     * @param healthy     a flag to indicate returning healthy or unhealthy instances
     * @param subscribe   if subscribe the service
     * @return A qualified list of instance
     * @throws NacosException nacos exception
     */
    List<Instance> selectInstances(String serviceName, List<String> clusters, boolean healthy, boolean subscribe)
            throws NacosException;

    /**
     * Get qualified instances within specified clusters of service.
     *
     * @param serviceName name of service
     * @param groupName   group of service
     * @param clusters    list of cluster
     * @param healthy     a flag to indicate returning healthy or unhealthy instances
     * @param subscribe   if subscribe the service
     * @return A qualified list of instance
     * @throws NacosException nacos exception
     */
    List<Instance> selectInstances(String serviceName, String groupName, List<String> clusters, boolean healthy,
            boolean subscribe) throws NacosException;

    /** 根据权重选择一个健康的实例
     * Select one healthy instance of service using predefined load balance strategy.
     *
     * @param serviceName name of service
     * @return qualified instance
     * @throws NacosException nacos exception
     */
    Instance selectOneHealthyInstance(String serviceName) throws NacosException;

    /**
     * Select one healthy instance of service using predefined load balance strategy.
     *
     * @param serviceName name of service
     * @param groupName   group of service
     * @return qualified instance
     * @throws NacosException nacos exception
     */
    Instance selectOneHealthyInstance(String serviceName, String groupName) throws NacosException;

    /**
     * select one healthy instance of service using predefined load balance strategy.
     *
     * @param serviceName name of service
     * @param subscribe   if subscribe the service
     * @return qualified instance
     * @throws NacosException nacos exception
     */
    Instance selectOneHealthyInstance(String serviceName, boolean subscribe) throws NacosException;

    /**
     * select one healthy instance of service using predefined load balance strategy.
     *
     * @param serviceName name of service
     * @param groupName   group of service
     * @param subscribe   if subscribe the service
     * @return qualified instance
     * @throws NacosException nacos exception
     */
    Instance selectOneHealthyInstance(String serviceName, String groupName, boolean subscribe) throws NacosException;

    /**
     * Select one healthy instance of service using predefined load balance strategy.
     *
     * @param serviceName name of service
     * @param clusters    a list of clusters should the instance belongs to
     * @return qualified instance
     * @throws NacosException nacos exception
     */
    Instance selectOneHealthyInstance(String serviceName, List<String> clusters) throws NacosException;

    /**
     * Select one healthy instance of service using predefined load balance strategy.
     *
     * @param serviceName name of service
     * @param groupName   group of service
     * @param clusters    a list of clusters should the instance belongs to
     * @return qualified instance
     * @throws NacosException nacos exception
     */
    Instance selectOneHealthyInstance(String serviceName, String groupName, List<String> clusters)
            throws NacosException;

    /**
     * Select one healthy instance of service using predefined load balance strategy.
     *
     * @param serviceName name of service
     * @param clusters    a list of clusters should the instance belongs to
     * @param subscribe   if subscribe the service
     * @return qualified instance
     * @throws NacosException nacos exception
     */
    Instance selectOneHealthyInstance(String serviceName, List<String> clusters, boolean subscribe)
            throws NacosException;

    /**
     * Select one healthy instance of service using predefined load balance strategy.
     *
     * @param serviceName name of service
     * @param groupName   group of service
     * @param clusters    a list of clusters should the instance belongs to
     * @param subscribe   if subscribe the service
     * @return qualified instance
     * @throws NacosException nacos exception
     */
    Instance selectOneHealthyInstance(String serviceName, String groupName, List<String> clusters, boolean subscribe)
            throws NacosException;

    /** 注册对某个服务的监听
     * Subscribe service to receive events of instances alteration.
     *
     * @param serviceName name of service
     * @param listener    event listener
     * @throws NacosException nacos exception
     */
    void subscribe(String serviceName, EventListener listener) throws NacosException;

    /**
     * Subscribe service to receive events of instances alteration.
     *
     * @param serviceName name of service
     * @param groupName   group of service
     * @param listener    event listener
     * @throws NacosException nacos exception
     */
    void subscribe(String serviceName, String groupName, EventListener listener) throws NacosException;

    /**
     * Subscribe service to receive events of instances alteration.
     *
     * @param serviceName name of service
     * @param clusters    list of cluster
     * @param listener    event listener
     * @throws NacosException nacos exception
     */
    void subscribe(String serviceName, List<String> clusters, EventListener listener) throws NacosException;

    /**
     * Subscribe service to receive events of instances alteration.
     *
     * @param serviceName name of service
     * @param groupName   group of service
     * @param clusters    list of cluster
     * @param listener    event listener
     * @throws NacosException nacos exception
     */
    void subscribe(String serviceName, String groupName, List<String> clusters, EventListener listener)
            throws NacosException;

    /** 注销对某个服务的监听
     * Unsubscribe event listener of service.
     *
     * @param serviceName name of service
     * @param listener    event listener
     * @throws NacosException nacos exception
     */
    void unsubscribe(String serviceName, EventListener listener) throws NacosException;

    /**
     * unsubscribe event listener of service.
     *
     * @param serviceName name of service
     * @param groupName   group of service
     * @param listener    event listener
     * @throws NacosException nacos exception
     */
    void unsubscribe(String serviceName, String groupName, EventListener listener) throws NacosException;

    /**
     * Unsubscribe event listener of service.
     *
     * @param serviceName name of service
     * @param clusters    list of cluster
     * @param listener    event listener
     * @throws NacosException nacos exception
     */
    void unsubscribe(String serviceName, List<String> clusters, EventListener listener) throws NacosException;

    /**
     * Unsubscribe event listener of service.
     *
     * @param serviceName name of service
     * @param groupName   group of service
     * @param clusters    list of cluster
     * @param listener    event listener
     * @throws NacosException nacos exception
     */
    void unsubscribe(String serviceName, String groupName, List<String> clusters, EventListener listener)
            throws NacosException;

    /**
     * Get all service names from server.
     *
     * @param pageNo   page index
     * @param pageSize page size
     * @return list of service names
     * @throws NacosException nacos exception
     */
    ListView<String> getServicesOfServer(int pageNo, int pageSize) throws NacosException;

    /**
     * Get all service names from server.
     *
     * @param pageNo    page index
     * @param pageSize  page size
     * @param groupName group name
     * @return list of service names
     * @throws NacosException nacos exception
     */
    ListView<String> getServicesOfServer(int pageNo, int pageSize, String groupName) throws NacosException;

    /**
     * Get all service names from server with selector.
     *
     * @param pageNo   page index
     * @param pageSize page size
     * @param selector selector to filter the resource
     * @return list of service names
     * @throws NacosException nacos exception
     * @since 0.7.0
     */
    ListView<String> getServicesOfServer(int pageNo, int pageSize, AbstractSelector selector) throws NacosException;

    /** 获取命名空间（namespace)下的所有服务名。【注：此方法有个小坑，参数pageNo要从1开始】
     * Get all service names from server with selector.
     *
     * @param pageNo    page index
     * @param pageSize  page size
     * @param groupName group name
     * @param selector  selector to filter the resource
     * @return list of service names
     * @throws NacosException nacos exception
     */
    ListView<String> getServicesOfServer(int pageNo, int pageSize, String groupName, AbstractSelector selector)
            throws NacosException;

    /** 获取被监听的服务
     * Get all subscribed services of current client.
     *
     * @return subscribed services
     * @throws NacosException nacos exception
     */
    List<ServiceInfo> getSubscribeServices() throws NacosException;

    /** 检测服务端健康状态
     * get server health status.
     *
     * @return is server healthy
     */
    String getServerStatus();

    /**
     * Shutdown the resource service.
     *
     * @throws NacosException exception.
     */
    void shutDown() throws NacosException;
}
