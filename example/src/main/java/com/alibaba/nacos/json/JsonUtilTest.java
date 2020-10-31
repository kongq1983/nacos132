package com.alibaba.nacos.json;

import com.alibaba.nacos.common.utils.JacksonUtils;
import com.alibaba.nacos.entity.Account;

/**
 * @author kq
 * @date 2020-10-31 15:46
 * @since 2020-0630
 */
public class JsonUtilTest {

    public static void main(String[] args) {
        Account account = new Account();
        account.setId(1L);
        account.setUsername("admin");

        String json = MyJacksonUtils.toJson(account);
        System.out.println(json);

    }

}
