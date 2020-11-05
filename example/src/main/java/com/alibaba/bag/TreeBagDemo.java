package com.alibaba.bag;

import org.apache.commons.collections.SortedBag;
import org.apache.commons.collections.bag.TreeBag;

/**
 * @author kq
 * @date 2020-11-02 8:48
 * @since 2020-0630
 */
public class TreeBagDemo {

    public static void main(String[] args) {
        SortedBag ips = new TreeBag();
        String one = "one";
        String two = "two";
        ips.add(one);
        ips.add(one);
        ips.add(one);
        ips.add(two);
        ips.add(two);

        int oneSize = ips.getCount(one);
        int twoSize = ips.getCount(two);

        System.out.println("oneSize:"+oneSize);
        System.out.println("twoSize:"+twoSize);
    }

}
