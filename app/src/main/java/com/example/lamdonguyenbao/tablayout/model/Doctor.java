package com.example.lamdonguyenbao.tablayout.model;

public class Doctor {
    private String name;
    private String phone;
    private String avatar_url;

    public Doctor(String name, String phone, String avatar_url) {
        this.name = name;
        this.phone = phone;
        this.avatar_url = avatar_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

}
