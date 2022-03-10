package com.cc.simba.k.mysqlJDBC;

import java.io.Serializable;

public class OrgUser implements Serializable {

    private long id;
    private String userName;
    private String password;
    private String mobile;


    public OrgUser(long id, String userName, String password, String mobile) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.mobile = mobile;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}