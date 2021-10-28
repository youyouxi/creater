package com.youyouxi.github.creater.entity;

import com.mysql.cj.util.StringUtils;
import com.youyouxi.github.creater.Utils.Convert;

import java.io.Serializable;

/**
 * 数据库-表信息
 *
 * @author 内酷啦啦
 */
public class TableInfo implements Serializable {

    private static final long serialVersionUID = 4573451077890603384L;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表名 小驼峰
     */
    private String tableNameLowerCamelCase;

    /**
     * 表名 类名 大驼峰
     */
    private String tableNameUpperCamelCase;

    /**
     * 表描述
     */
    private String tableComment;

    /**
     * 表编码
     */
    private String tableCollation;

    /**
     * 数据库引擎
     */
    private String engine;

    /**
     * 表字段数
     */
    private Integer columnCount;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
        this.tableNameUpperCamelCase = Convert.convertUpperCamelCase(tableName);
        this.tableNameLowerCamelCase = Convert.convertLowerCamelCase(tableName);
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getTableCollation() {
        return tableCollation;
    }

    public void setTableCollation(String tableCollation) {
        this.tableCollation = tableCollation;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public Integer getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(Integer columnCount) {
        this.columnCount = columnCount;
    }

    public String getTableNameLowerCamelCase() {
        return tableNameLowerCamelCase;
    }

    public String getTableNameUpperCamelCase() {
        return tableNameUpperCamelCase;
    }

    @Override
    public String toString() {
        return "MysqlTableInfo{" +
                "tableName='" + tableName + '\'' +
                ", tableNameLowerCamelCase='" + tableNameLowerCamelCase + '\'' +
                ", tableNameUpperCamelCase='" + tableNameUpperCamelCase + '\'' +
                ", tableComment='" + tableComment + '\'' +
                ", tableCollation='" + tableCollation + '\'' +
                ", engine='" + engine + '\'' +
                ", columnCount=" + columnCount +
                '}';
    }

    /**
     * 获取 mysql 表信息
     * @param table 表名
     * @return sql
     */
    public static String selectSqlForMysql(String table) {
        String str1 = "SELECT table_name,table_comment,table_collation,ENGINE FROM information_schema.TABLES WHERE table_schema='";
        String str2 = "';";
        return str1 + table + str2;
    }

    /**
     * 获取 oracle 表信息
     * @param table 表名
     * @return sql
     */
    public static String selectSqlForOracle(String table) {
        if (!StringUtils.isNullOrEmpty(table)) {
            String str1 = "select * from user_tables WHERE TABLE_NAME = '";
            String str2 = "'";
            return str1 + table + str2;
        }
        return "select * from user_tables";
    }

    public static String selectSqlTableCommentForOracle(String table) {
        // todo table 操作
        return "select * from user_tab_comments";
    }
}
