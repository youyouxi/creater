package com.youyouxi.github.creater.builder;

import com.mysql.cj.util.StringUtils;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import com.youyouxi.github.creater.entity.DbConnectInfo;

/**
 * mysql 信息获取构造器
 *
 * @author youyouxi
 */
public class MysqlConcreteBuilder implements DbBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(MysqlConcreteBuilder.class);

    private DbConnectInfo dbConnectInfo;

    public DbBuilder url(String url) {
        if (StringUtils.isNullOrEmpty(url)) {
            throw new NullPointerException("数据库连接地址不能为空");
        }
        dbConnectInfo.setUrl(url);
        return this;
    }

    public DbBuilder port(String port) {
        if (StringUtils.isNullOrEmpty(port)) {
            throw new NullPointerException("数据库连接端口不能为空");
        }
        dbConnectInfo.setUrl(port);
        return this;
    }

    public DbBuilder userName(String userName) {
        if (StringUtils.isNullOrEmpty(userName)) {
            throw new NullPointerException("数据库连接端口不能为空");
        }
        dbConnectInfo.setUrl(userName);
        return this;
    }

    public DbBuilder password(String password) {
        if (StringUtils.isNullOrEmpty(password)) {
            throw new NullPointerException("数据库连接端口不能为空");
        }
        dbConnectInfo.setUrl(password);
        return this;
    }

    public DbBuilder diver(String diver) {
        if (StringUtils.isNullOrEmpty(diver)) {
            throw new NullPointerException("数据库连接端口不能为空");
        }
        dbConnectInfo.setUrl(diver);
        return this;
    }

    public DbBuilder type(String type) {
        if (StringUtils.isNullOrEmpty(type)) {
            throw new NullPointerException("数据库连接端口不能为空");
        }
        dbConnectInfo.setUrl(type);
        return this;
    }

    public void execute() {

    }
}
