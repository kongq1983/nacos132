package com.alibaba.nacos.raft;

import com.alipay.sofa.jraft.JRaftUtils;
import com.alipay.sofa.jraft.conf.Configuration;
import com.alipay.sofa.jraft.entity.PeerId;

/**
 * @author kq
 * @date 2020-10-31 10:56
 * @since 2020-0630
 */
public class ConfigurationDemo {

    public static void main(String[] args) {

        PeerId peer1 = new PeerId("localhost", 8080);
        PeerId peer2 = new PeerId("localhost", 8081);
        PeerId peer3 = new PeerId("localhost", 8082);

        // 由 3 个节点组成的 raft group
        Configuration conf = new Configuration();
        conf.addPeer(peer1);
        conf.addPeer(peer2);
        conf.addPeer(peer3);

        System.out.println(conf.getPeers());
        //  jraft 提供了 JRaftUtils 来快捷地从字符串创建出所需要的对象
        Configuration conf1 = JRaftUtils.getConfiguration("localhost:8081,localhost:8082,localhost:8083");
        System.out.println(conf1.getPeers());

    }

}
