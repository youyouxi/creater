package com.youyouxi.github.creater.builder.impl;

import com.mysql.cj.util.StringUtils;
import com.youyouxi.github.creater.Creater;
import com.youyouxi.github.creater.builder.DbBuilder;
import com.youyouxi.github.creater.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * oracle 信息获取构造器
 *
 * @author youyouxi
 */
public class OracleConcreteBuilder implements DbBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(MysqlConcreteBuilder.class);

    private DbCreaterInfo dbCreaterInfo = new DbCreaterInfo();

    private PersonalConfig personalConfig;

    private static final String S1 = "jdbc:oracle:thin:@";

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
        dbCreaterInfo.setPort(":" + port + ":");
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
        String url = dbCreaterInfo.getUrl() + dbCreaterInfo.getPort() + dbCreaterInfo.getType();
        Connection connection = null;
        try {
            Class.forName(dbCreaterInfo.getDiver());
            connection = DriverManager.getConnection(url, dbCreaterInfo.getUserName(), dbCreaterInfo.getPassword());

            //创建预编译对象
            PreparedStatement ps = null;
            //创建结果集
            ResultSet rs = null;
            ps = connection.prepareStatement(TableInfo.selectSqlForOracle(dbCreaterInfo.getTable()));
            rs = ps.executeQuery();

            List<Table> tables = new ArrayList<>(32);
            // 查询表信息 表名
            List<TableInfo> tableInfos = new ArrayList<>(32);
            while (rs.next()) {
                String tableName = "";
                try {
                    tableName = rs.getObject(1).toString();
                    if (!StringUtils.isNullOrEmpty(tableName)) {
                        TableInfo tableInfo = new TableInfo();
                        tableInfo.setTableName(tableName);
                        tableInfos.add(tableInfo);
                    }
                } catch (SQLException e) {
                    LOGGER.error(tableName + "表 写入表信息错误", e);
                }
            }
            // 查询表信息 表注释
            Map<String, String> CommentMap = new HashMap<>(64);
            ps = connection.prepareStatement(TableInfo.selectSqlTableCommentForOracle(dbCreaterInfo.getTable()));
            rs = ps.executeQuery();
            while (rs.next()) {
                String tableName = "";
                String tableComments = "";
                try {
                    if (rs.getObject(1) != null && rs.getObject(3) != null) {
                        tableName = rs.getObject(1).toString();
                        tableComments = rs.getObject(3).toString();
                    }
                    if (!StringUtils.isNullOrEmpty(tableName)) {
                        CommentMap.put(tableName, tableComments);
                    }
                } catch (SQLException e) {
                    LOGGER.error(tableName + "表 写入表信息错误", e);
                }
            }
            for (TableInfo tableInfo : tableInfos) {
                tableInfo.setTableComment(CommentMap.get(tableInfo.getTableName()));
            }
            CommentMap.clear();

            // 查询表结构信息
            List<TableInfoDetail> tableInfoDetails = new ArrayList<>(64);
            ps = connection.prepareStatement(TableInfoDetail.selectSqlForOracle(dbCreaterInfo.getTable()));
            rs = ps.executeQuery();
            while (rs.next()) {
                String tableName = rs.getString(1);
                String columnName = rs.getString(2);
                String isNullable = rs.getString(9);
                String columnType = rs.getString(3);

                if (!StringUtils.isNullOrEmpty(tableName) && !StringUtils.isNullOrEmpty(columnName)
                        && !StringUtils.isNullOrEmpty(columnType) && !StringUtils.isNullOrEmpty(isNullable)) {
                    TableInfoDetail tableInfoDetail = new TableInfoDetail();
                    tableInfoDetail.setTableName(tableName);
                    tableInfoDetail.setColumnName(columnName);
                    tableInfoDetail.setIsNullAble(isNullable);
                    tableInfoDetail.setColumnType(columnType);
                    tableInfoDetails.add(tableInfoDetail);
                }
            }


            // 获取表结构注释
            ps = connection.prepareStatement(TableInfoDetail.selectSqlColumnsCommentForOracle(dbCreaterInfo.getTable()));
            rs = ps.executeQuery();
            while (rs.next()) {
                String tableName = rs.getString(1);
                String columnsName = rs.getString(2);
                String columnsComment = rs.getString(3);
                if (!StringUtils.isNullOrEmpty(tableName) && !StringUtils.isNullOrEmpty(columnsName)) {
                    CommentMap.put(tableName + columnsName, columnsComment);
                }
            }
            for (TableInfoDetail tableInfo : tableInfoDetails) {
                tableInfo.setColumnComment(CommentMap.get(tableInfo.getTableName() + tableInfo.getColumnName()));
            }
            CommentMap.clear();


            // 获取 MysqlTable 信息
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
                tables.add(mysqlTable);
            });

            rs.close();
            ps.close();
            connection.close();

            // 执行生成代码操作
            Creater.create(tables, personalConfig, dbCreaterInfo);
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("SQLException ClassNotFoundException", e);
        }
        return null;
    }
}
