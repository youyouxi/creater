package com.youyouxi.github.creater.entity;

import java.io.Serializable;

/**
 * 数据库连接信息
 *
 * @author youyouxi
 */
public class DbCreaterInfo implements Serializable {

    private static final long serialVersionUID = 3781316901299744393L;

    private String url;

    private String port;

    private String userName;

    private String password;

    private String dataPool;

    private String diver;

    private String type;

    private String table;

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

    public String getDataPool() {
        return dataPool;
    }

    public void setDataPool(String dataPool) {
        this.dataPool = dataPool;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return "DbCreaterInfo{" +
                "url='" + url + '\'' +
                ", port='" + port + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", dataPool='" + dataPool + '\'' +
                ", diver='" + diver + '\'' +
                ", type='" + type + '\'' +
                ", table='" + table + '\'' +
                '}';
    }

    public void check() {
        if (this.url == null || this.diver == null || this.password == null
                || this.port == null || this.type == null || this.userName == null
                || this.dataPool == null) {
            throw new NullPointerException("数据库连接信息为空或者不完善！");
        }
    }
}
