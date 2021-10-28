package com.youyouxi.github.creater.entity;

import com.mysql.cj.util.StringUtils;
import com.youyouxi.github.creater.Utils.Convert;

import java.io.Serializable;

/**
 * 数据库-详细信息
 *
 * @author 内酷啦啦
 */
public class TableInfoDetail implements Serializable {

    private static final long serialVersionUID = -1679093642870165313L;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表字段
     */
    private String columnName;

    /**
     * 表字段-小驼峰
     */
    private String columnNameLowerCamelCase;

    /**
     * 表字段-大驼峰
     */
    private String columnNameUpperCamelCase;

    /**
     * 表字段序号
     */
    private String ordinalPosition;

    /**
     * 是否必填
     */
    private String isNullAble;

    /**
     * 字段类型
     */
    private String columnType;

    /**
     * 字段类型
     */
    private String columnJavaType;

    /**
     * 字段最大长度
     */
    private Integer columnLength;

    /**
     * 表字段注释
     */
    private String columnComment;

    /**
     * 等于 PRI 为主键
     */
    private String columnKey;

    /**
     * 是否显示 0-不显示 1-显示
     */
    private String isShow;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
        this.columnNameLowerCamelCase = Convert.convertLowerCamelCase(columnName);
        this.columnNameUpperCamelCase = Convert.convertUpperCamelCase(columnName);
    }

    public void setColumnNameLowerCamelCase(String columnNameLowerCamelCase) {
        this.columnNameLowerCamelCase = columnNameLowerCamelCase;
    }

    public void setColumnNameUpperCamelCase(String columnNameUpperCamelCase) {
        this.columnNameUpperCamelCase = columnNameUpperCamelCase;
    }

    public String getColumnNameLowerCamelCase() {
        return columnNameLowerCamelCase;
    }

    public String getColumnNameUpperCamelCase() {
        return columnNameUpperCamelCase;
    }

    public String getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(String ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public String getIsNullAble() {
        return isNullAble;
    }

    public void setIsNullAble(String isNullAble) {
        this.isNullAble = isNullAble;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public Integer getColumnLength() {
        return columnLength;
    }

    public void setColumnLength(Integer columnLength) {
        this.columnLength = columnLength;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getColumnJavaType() {
        return columnJavaType;
    }

    public void setColumnJavaType(String columnJavaType) {
        this.columnJavaType = columnJavaType;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    @Override
    public String toString() {
        return "MysqlTableInfoDetail{" +
                "tableName='" + tableName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", columnNameLowerCamelCase='" + columnNameLowerCamelCase + '\'' +
                ", columnNameUpperCamelCase='" + columnNameUpperCamelCase + '\'' +
                ", ordinalPosition='" + ordinalPosition + '\'' +
                ", isNullAble='" + isNullAble + '\'' +
                ", columnType='" + columnType + '\'' +
                ", columnJavaType='" + columnJavaType + '\'' +
                ", columnLength=" + columnLength +
                ", columnComment='" + columnComment + '\'' +
                ", columnKey='" + columnKey + '\'' +
                '}';
    }

    public static String selectSqlForMysql(String table) {
        String str1 = "select * from information_schema.columns where table_schema = '";
        String str2 = "';";
        return str1 + table + str2;
    }

    public static String selectSqlForOracle(String table){
        if (!StringUtils.isNullOrEmpty(table)) {
            String str1 = "select * from user_tab_columns where Table_Name='";
            String str2 = "'";
            return str1 + table + str2;
        }
        return "select * from user_tab_columns";
    }

    public static String selectSqlColumnsCommentForOracle(String table){
        if (!StringUtils.isNullOrEmpty(table)) {
            String str1 = "select * from user_col_comments where Table_Name='";
            String str2 = "'";
            return str1 + table + str2;
        }
        return "select * from user_col_comments";
    }

}
