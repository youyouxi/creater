package com.youyouxi.github.creater.entity;

import java.io.Serializable;

/**
 * 数据库连接信息
 *
 * @author youyouxi
 */
public class DbConnectInfo implements Serializable {

    private static final long serialVersionUID = 3781316901299744393L;

    private String url;

    private String port;

    private String userName;

    private String password;

    private String diver;

    private String type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
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

    public String getDiver() {
        return diver;
    }

    public void setDiver(String diver) {
        this.diver = diver;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DbConnectInfo{" +
                "url='" + url + '\'' +
                ", port='" + port + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", diver='" + diver + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
