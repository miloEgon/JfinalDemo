package com.demo.entity;

public class User {

    public User() {
    }

    public User(Long id, String userName, String trueName, String phone) {
        this.id = id;
        this.userName = userName;
        this.trueName = trueName;
        this.phone = phone;
    }

    private Long id;

    private String userName;

    private String trueName;

    private String phone;

    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getTrueName() {
        return trueName;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
