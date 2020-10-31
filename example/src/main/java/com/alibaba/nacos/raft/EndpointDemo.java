package com.alibaba.nacos.raft;

import com.alipay.sofa.jraft.util.Endpoint;

/**
 * @author kq
 * @date 2020-10-31 10:46
 * @since 2020-0630
 */
public class EndpointDemo {

    public static void main(String[] args) {
        Endpoint addr = new Endpoint("localhost", 8080);
        String s = addr.toString(); // 结果为 localhost:8080
        System.out.println(s);
//        boolean success = addr.parse(s);  // 可以从字符串解析出地址，结果为 true

    }

}
