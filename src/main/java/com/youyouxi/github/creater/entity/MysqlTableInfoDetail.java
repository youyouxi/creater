package com.youyouxi.github.creater.entity;

import com.youyouxi.github.creater.Utils.Convert;

import java.io.Serializable;

/**
 * 数据库-详细信息
 * @author 内酷啦啦
 */
public class MysqlTableInfoDetail implements Serializable{

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
     * 表字段-小写
     */
    private String columnName_aB;

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
        this.columnName_aB = Convert.convert_ab(columnName);
    }

    public String getColumnName_aB() {
        return columnName_aB;
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

    @Override
    public String toString() {
        return "MysqlTableInfoDetail{" +
                "tableName='" + tableName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", columnName_aB='" + columnName_aB + '\'' +
                ", ordinalPosition='" + ordinalPosition + '\'' +
                ", isNullAble=" + isNullAble +
                ", columnType='" + columnType + '\'' +
                ", columnLength=" + columnLength +
                ", columnComment='" + columnComment + '\'' +
                ", columnKey='" + columnKey + '\'' +
                '}';
    }

    public static String selectSql(String table) {
        String str1 = "select * from information_schema.columns where table_schema = '";
        String str2 = "';";
        return str1 + table + str2;
    }

}
