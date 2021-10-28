package com.youyouxi.github.creater.builder.impl;

import com.mysql.cj.util.StringUtils;
import com.youyouxi.github.creater.Creater;
import com.youyouxi.github.creater.builder.DbBuilder;
import com.youyouxi.github.creater.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * mysql 代码生成器 构造器
 *
 * @author youyouxi
 */
public class MysqlConcreteBuilder implements DbBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(MysqlConcreteBuilder.class);

    private DbCreaterInfo dbCreaterInfo = new DbCreaterInfo();

    private PersonalConfig personalConfig;

    private static final String S1 = "jdbc:mysql://";
    private static final String S2 = "?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";

    @Override
    public DbBuilder url(String url) {
        if (StringUtils.isNullOrEmpty(url)) {
            throw new NullPointerException("数据库连接地址不能为空");
        }
        dbCreaterInfo.setUrl(S1 + url);
        return this;
    }

    @Override
    public DbBuilder port(String port) {
        if (StringUtils.isNullOrEmpty(port)) {
            throw new NullPointerException("数据库连接端口不能为空");
        }
        dbCreaterInfo.setPort(":" + port + "/");
        return this;
    }

    @Override
    public DbBuilder userName(String userName) {
        if (StringUtils.isNullOrEmpty(userName)) {
            throw new NullPointerException("数据库连接用户不能为空");
        }
        dbCreaterInfo.setUserName(userName);
        return this;
    }

    @Override
    public DbBuilder password(String password) {
        if (StringUtils.isNullOrEmpty(password)) {
            throw new NullPointerException("数据库连接密码不能为空");
        }
        dbCreaterInfo.setPassword(password);
        return this;
    }

    @Override
    public DbBuilder diver(String diver) {
        if (StringUtils.isNullOrEmpty(diver)) {
            throw new NullPointerException("数据库连接diver不能为空");
        }
        dbCreaterInfo.setDiver(diver);
        return this;
    }

    @Override
    public DbBuilder type(String type) {
        if (StringUtils.isNullOrEmpty(type)) {
            throw new NullPointerException("数据库连接类型不能为空");
        }
        dbCreaterInfo.setType(type);
        return this;
    }

    @Override
    public DbBuilder dataPool(String dataPool) {
        if (StringUtils.isNullOrEmpty(dataPool)) {
            throw new NullPointerException("数据库连接库名不能为空");
        }
        dbCreaterInfo.setDataPool(dataPool);
        return this;
    }

    @Override
    public DbBuilder table(String table) {
        dbCreaterInfo.setTable(table);
        return this;
    }

    @Override
    public DbBuilder personalConfig(PersonalConfig personalConfig) {
        this.personalConfig = personalConfig;
        return this;
    }

    @Override
    public List<Table> execute() {
        // 数据库 连接参数校验
        dbCreaterInfo.check();
        // 初始化 个性化配置
        personalConfig.checkAndInit();
        try {
            String url = dbCreaterInfo.getUrl() + dbCreaterInfo.getPort() + dbCreaterInfo.getDataPool() + S2;
            String user = dbCreaterInfo.getUserName();
            String password = dbCreaterInfo.getPassword();
            // 加载驱动程序
            Class.forName(dbCreaterInfo.getDiver());
            // 获得数据库链接
            Connection conn = DriverManager.getConnection(url, user, password);
            // 查询数据库表信息 表信息
            List<TableInfo> tableInfos = new ArrayList<>(32);
            getTableInfos(conn, tableInfos);

            // 查询数据库表信息 表字段信息
            List<TableInfoDetail> tableInfoDetails = new ArrayList<>(32);
            getTableInfoDetails(conn, tableInfoDetails);

            // 获取 MysqlTable 信息
            List<Table> mysqlTables = new ArrayList<>(32);
            tableInfos.forEach(e -> {
                Table mysqlTable = new Table();
                List<TableInfoDetail> ary = new ArrayList<>(32);
                tableInfoDetails.forEach(x -> {
                    if (e.getTableName().equals(x.getTableName())) {
                        ary.add(x);
                    }
                    mysqlTable.setTableInfoDetails(ary);
                });
                mysqlTable.setTableInfo(e);
                mysqlTables.add(mysqlTable);
            });

            // 过滤table信息
            if (!StringUtils.isNullOrEmpty(dbCreaterInfo.getTable())) {
                for (Table e : mysqlTables) {
                    if (e.getTableInfo().getTableName().equals(dbCreaterInfo.getTable())) {
                        mysqlTables.clear();
                        mysqlTables.add(e);
                        break;
                    }
                }
            }

            // 关闭数据库连接
            conn.close();
            // 执行生成代码操作
            Creater.create(mysqlTables, personalConfig, dbCreaterInfo);

            return mysqlTables;
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("ClassNotFoundException SQLException", e);
        }
        throw new NullPointerException("数据库表信息为空");
    }

    private void getTableInfoDetails(Connection conn, List<TableInfoDetail> tableInfoDetails) throws SQLException {
        Statement tableDetailSt = conn.createStatement();
        ResultSet tableDetailRe = tableDetailSt.executeQuery(TableInfoDetail.selectSqlForMysql(dbCreaterInfo.getDataPool()));
        while (tableDetailRe.next()) {
            TableInfoDetail tableInfoDetail = new TableInfoDetail();
            tableInfoDetail.setTableName(tableDetailRe.getString("table_name"));
            tableInfoDetail.setColumnName(tableDetailRe.getString("column_name"));
            tableInfoDetail.setOrdinalPosition(tableDetailRe.getString("ordinal_position"));
            tableInfoDetail.setIsNullAble(tableDetailRe.getString("is_nullable"));
            tableInfoDetail.setColumnType(tableDetailRe.getString("column_type"));
            tableInfoDetail.setColumnComment(tableDetailRe.getString("column_comment"));
            tableInfoDetails.add(tableInfoDetail);
        }
        tableDetailSt.close();
        tableDetailRe.close();
    }

    private void getTableInfos(Connection conn, List<TableInfo> tableInfos) throws SQLException {
        Statement tableSt = conn.createStatement();
        ResultSet tableRe = tableSt.executeQuery(TableInfo.selectSqlForMysql(dbCreaterInfo.getDataPool()));
        while (tableRe.next()) {
            TableInfo tableInfo = new TableInfo();
            tableInfo.setTableName(tableRe.getString("table_name"));
            tableInfo.setTableComment(tableRe.getString("table_comment"));
            tableInfo.setTableCollation(tableRe.getString("table_collation"));
            tableInfo.setEngine(tableRe.getString("engine"));
            tableInfos.add(tableInfo);
        }
        tableSt.close();
        tableRe.close();
    }

}
