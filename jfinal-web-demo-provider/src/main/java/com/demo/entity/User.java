package com.demo.entity;

import com.jfinal.plugin.activerecord.Model;

public class User extends Model<User> {

    public static final User dao = new User().dao();

    private Long id;

    private String username;

    private String password;

    private Byte gender;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Byte getGender() {
        return gender;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }
}
