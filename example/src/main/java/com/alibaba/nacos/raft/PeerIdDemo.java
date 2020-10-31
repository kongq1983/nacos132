package com.alibaba.nacos.raft;

import com.alipay.sofa.jraft.entity.PeerId;
import com.alipay.sofa.jraft.util.Endpoint;

/**
 * @author kq
 * @date 2020-10-31 10:50
 * @since 2020-0630
 */
public class PeerIdDemo {

    public static void main(String[] args) {
        PeerId peer = new PeerId("localhost", 8080);
        Endpoint addr = peer.getEndpoint(); // 获取节点地址
        int index = peer.getIdx(); // 获取节点序号，目前一直为 0
        String s = peer.toString(); // 结果为 localhost:8080
        boolean success = peer.parse(s);  // 可以从字符串解析出 PeerId，结果为 true
        System.out.printf("addr=%s , index = %s ,success=%s\n",addr,index,success);
    }

}
