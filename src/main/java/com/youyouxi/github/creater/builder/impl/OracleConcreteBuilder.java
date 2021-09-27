package com.youyouxi.github.creater.builder.impl;

import com.mysql.cj.util.StringUtils;
import com.youyouxi.github.creater.builder.DbBuilder;
import com.youyouxi.github.creater.entity.DbCreaterInfo;
import com.youyouxi.github.creater.entity.MysqlTable;

import java.util.List;

/**
 * oracle 信息获取构造器
 *
 * @author youyouxi
 */
public class OracleConcreteBuilder implements DbBuilder {

    private DbCreaterInfo dbConnectInfo = new DbCreaterInfo();

    @Override
    public DbBuilder url(String url) {
        if (StringUtils.isNullOrEmpty(url)) {
            throw new NullPointerException("数据库连接地址不能为空");
        }
        dbConnectInfo.setUrl(url);
        return this;
    }

    @Override
    public DbBuilder port(String port) {
        if (StringUtils.isNullOrEmpty(port)) {
            throw new NullPointerException("数据库连接端口不能为空");
        }
        dbConnectInfo.setPort(port);
        return this;
    }

    @Override
    public DbBuilder userName(String userName) {
        if (StringUtils.isNullOrEmpty(userName)) {
            throw new NullPointerException("数据库连接端口不能为空");
        }
        dbConnectInfo.setUserName(userName);
        return this;
    }

    @Override
    public DbBuilder password(String password) {
        if (StringUtils.isNullOrEmpty(password)) {
            throw new NullPointerException("数据库连接端口不能为空");
        }
        dbConnectInfo.setPassword(password);
        return this;
    }

    @Override
    public DbBuilder diver(String diver) {
        if (StringUtils.isNullOrEmpty(diver)) {
            throw new NullPointerException("数据库连接端口不能为空");
        }
        dbConnectInfo.setDiver(diver);
        return this;
    }

    @Override
    public DbBuilder type(String type) {
        if (StringUtils.isNullOrEmpty(type)) {
            throw new NullPointerException("数据库连接端口不能为空");
        }
        dbConnectInfo.setType(type);
        return this;
    }

    @Override
    public DbBuilder dataPool(String dataPool) {
        return null;
    }

    @Override
    public DbBuilder table(String table) {
        return null;
    }

    public List<MysqlTable> execute() {
        dbConnectInfo.check();
        System.out.println("获取数据库信息");
        return null;
    }
}
