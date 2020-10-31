package com.alibaba.nacos.entity;

/**
 * @author kq
 * @date 2020-10-31 15:45
 * @since 2020-0630
 */
public class Account {

    private Long id;
    private String username;
    private int age;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", age=" + age +
            ", name='" + name + '\'' +
            '}';
    }
}
