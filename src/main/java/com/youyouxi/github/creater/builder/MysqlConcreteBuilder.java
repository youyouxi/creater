package com.youyouxi.github.creater.builder;

import com.mysql.cj.util.StringUtils;
import com.youyouxi.github.creater.entity.DbConnectInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * mysql 信息获取构造器
 *
 * @author youyouxi
 */
public class MysqlConcreteBuilder implements DbBuilder {

    private DbConnectInfo dbConnectInfo = new DbConnectInfo();

    private static final String S1 = "jdbc:mysql://";
    private static final String S2 = "?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";

    public DbBuilder url(String url) {
        if (StringUtils.isNullOrEmpty(url)) {
            throw new NullPointerException("数据库连接地址不能为空");
        }
        dbConnectInfo.setUrl(S1 + url);
        return this;
    }

    public DbBuilder port(String port) {
        if (StringUtils.isNullOrEmpty(port)) {
            throw new NullPointerException("数据库连接端口不能为空");
        }
        dbConnectInfo.setPort(":" + port + "/");
        return this;
    }

    public DbBuilder userName(String userName) {
        if (StringUtils.isNullOrEmpty(userName)) {
            throw new NullPointerException("数据库连接用户不能为空");
        }
        dbConnectInfo.setUserName(userName);
        return this;
    }

    public DbBuilder password(String password) {
        if (StringUtils.isNullOrEmpty(password)) {
            throw new NullPointerException("数据库连接密码不能为空");
        }
        dbConnectInfo.setPassword(password);
        return this;
    }

    public DbBuilder diver(String diver) {
        if (StringUtils.isNullOrEmpty(diver)) {
            throw new NullPointerException("数据库连接diver不能为空");
        }
        dbConnectInfo.setDiver(diver);
        return this;
    }

    public DbBuilder type(String type) {
        if (StringUtils.isNullOrEmpty(type)) {
            throw new NullPointerException("数据库连接类型不能为空");
        }
        dbConnectInfo.setType(type);
        return this;
    }

    public DbBuilder table(String table) {
        if (StringUtils.isNullOrEmpty(table)) {
            throw new NullPointerException("数据库连接库名不能为空");
        }
        dbConnectInfo.setTable(table);
        return this;
    }

    public void execute() {
        dbConnectInfo.check();
        try {
            String URL = dbConnectInfo.getUrl() + dbConnectInfo.getPort() + dbConnectInfo.getTable() + S2;
            String USER = dbConnectInfo.getUserName();
            String PASSWORD = dbConnectInfo.getPassword();
            // 加载驱动程序
            Class.forName(dbConnectInfo.getDiver());
            // 获得数据库链接
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwAbles) {
            throwAbles.printStackTrace();
        }
    }
}
