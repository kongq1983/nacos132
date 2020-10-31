package com.alibaba.nacos.raft;

import com.alipay.sofa.jraft.JRaftUtils;
import com.alipay.sofa.jraft.conf.Configuration;
import com.alipay.sofa.jraft.entity.PeerId;
import com.alipay.sofa.jraft.util.Endpoint;

/**
 * @author kq
 * @date 2020-10-31 11:05
 * @since 2020-0630
 */
public class JRaftUtilsDemo {

    public static void main(String[] args) { // getEndPoint
        Endpoint endpoint = JRaftUtils.getEndPoint("localhost:8080");
        PeerId peer = JRaftUtils.getPeerId("localhost:8080");
// 三个节点组成的 raft group 配置，注意节点之间用逗号隔开
        Configuration conf = JRaftUtils.getConfiguration("localhost:8081,localhost:8082,localhost:8083");

        System.out.printf("endpoint=%s \n",endpoint);
        System.out.printf("peer=%s \n",peer);
        System.out.printf("conf=%s \n",conf);
    }

}
