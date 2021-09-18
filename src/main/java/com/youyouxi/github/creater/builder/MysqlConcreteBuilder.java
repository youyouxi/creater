package com.youyouxi.github.creater.builder;

import com.mysql.cj.util.StringUtils;
import com.youyouxi.github.creater.entity.DbConnectInfo;
import com.youyouxi.github.creater.entity.MysqlTable;
import com.youyouxi.github.creater.entity.MysqlTableInfo;
import com.youyouxi.github.creater.entity.MysqlTableInfoDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * mysql 信息获取构造器
 *
 * @author youyouxi
 */
public class MysqlConcreteBuilder implements DbBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(MysqlConcreteBuilder.class);

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

    public DbBuilder dataPool(String dataPool) {
        if (StringUtils.isNullOrEmpty(dataPool)) {
            throw new NullPointerException("数据库连接库名不能为空");
        }
        dbConnectInfo.setDataPool(dataPool);
        return this;
    }

    public List<MysqlTable> execute() {
        dbConnectInfo.check();
        try {
            String URL = dbConnectInfo.getUrl() + dbConnectInfo.getPort() + dbConnectInfo.getDataPool() + S2;
            String USER = dbConnectInfo.getUserName();
            String PASSWORD = dbConnectInfo.getPassword();
            // 加载驱动程序
            Class.forName(dbConnectInfo.getDiver());
            // 获得数据库链接
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            // 查询数据库表信息
            Statement tableSt = conn.createStatement();
            ResultSet tableRe = tableSt.executeQuery(MysqlTableInfo.selectSql(dbConnectInfo.getDataPool()));

            List<MysqlTableInfo> tableInfos = new ArrayList<MysqlTableInfo>(32);
            while (tableRe.next()) {
                MysqlTableInfo tableInfo = new MysqlTableInfo();
                tableInfo.setTableName(tableRe.getString("table_name"));
                tableInfo.setTableComment(tableRe.getString("table_comment"));
                tableInfo.setTableCollation(tableRe.getString("table_collation"));
                tableInfo.setEngine(tableRe.getString("engine"));
                tableInfos.add(tableInfo);
            }

            Statement tableDetailSt = conn.createStatement();
            ResultSet tableDetailRe = tableSt.executeQuery(MysqlTableInfoDetail.selectSql(dbConnectInfo.getDataPool()));

            List<MysqlTableInfoDetail> tableInfoDetails = new ArrayList<MysqlTableInfoDetail>(32);
            while (tableDetailRe.next()) {
                MysqlTableInfoDetail tableInfoDetail = new MysqlTableInfoDetail();
                tableInfoDetail.setTableName(tableDetailRe.getString("table_name"));
                tableInfoDetail.setColumnName(tableDetailRe.getString("column_name"));
                tableInfoDetail.setOrdinalPosition(tableDetailRe.getString("ordinal_position"));
                tableInfoDetail.setIsNullAble(tableDetailRe.getString("is_nullable"));
                tableInfoDetail.setColumnType(tableDetailRe.getString("column_type"));
                tableInfoDetail.setColumnComment(tableDetailRe.getString("column_comment"));
                tableInfoDetails.add(tableInfoDetail);
            }

            List<MysqlTable> mysqlTables = new ArrayList<MysqlTable>(32);
            tableInfos.forEach(e -> {
                MysqlTable mysqlTable = new MysqlTable();
                tableInfoDetails.forEach(x -> {
                    List<MysqlTableInfoDetail> ary = new ArrayList<>(32);
                    if (e.getTableName().equals(x.getTableName())) {
                        ary.add(x);
                    }
                    mysqlTable.setTableInfoDetails(ary);
                });
                mysqlTable.setTableInfo(e);
                mysqlTables.add(mysqlTable);
            });

            LOGGER.info("查询数据库信息===" + mysqlTables);

            tableSt.close();
            tableDetailSt.close();
            conn.close();
            return mysqlTables;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("数据库表信息为空");
    }

}
