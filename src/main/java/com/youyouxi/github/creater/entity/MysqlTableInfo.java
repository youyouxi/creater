package com.youyouxi.github.creater.entity;

import com.youyouxi.github.creater.Utils.Convert;

import java.io.Serializable;

/**
 * 数据库-表信息
 *
 * @author 内酷啦啦
 */
public class MysqlTableInfo implements Serializable {

    private static final long serialVersionUID = 4573451077890603384L;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表名 驼峰
     */
    private String tableNameHump;

    /**
     * 表名 类名 Capitalize
     */
    private String tableNameCapitalize;

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
        this.tableNameHump = Convert.convert_AB(tableName);
        this.tableNameCapitalize = Convert.convert_ab(tableName);
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

    public String getTableNameHump() {
        return tableNameHump;
    }

    public void setTableNameHump(String tableNameHump) {
        this.tableNameHump = tableNameHump;
    }

    public String getTableNameCapitalize() {
        return tableNameCapitalize;
    }

    public void setTableNameCapitalize(String tableNameCapitalize) {
        this.tableNameCapitalize = tableNameCapitalize;
    }

    @Override
    public String toString() {
        return "MysqlTableInfo{" +
                "tableName='" + tableName + '\'' +
                ", tableNameHump='" + tableNameHump + '\'' +
                ", tableNameCapitalize='" + tableNameCapitalize + '\'' +
                ", tableComment='" + tableComment + '\'' +
                ", tableCollation='" + tableCollation + '\'' +
                ", engine='" + engine + '\'' +
                ", columnCount=" + columnCount +
                '}';
    }

    public static String selectSql(String table) {
        String str1 = "SELECT table_name,table_comment,table_collation,ENGINE FROM information_schema.TABLES WHERE table_schema='";
        String str2 = "';";
        return str1 + table + str2;
    }
}
