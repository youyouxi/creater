package com.youyouxi.github.creater.entity;

import com.youyouxi.github.creater.Utils.Convert;

import java.io.Serializable;

/**
 * 数据库-表信息
 *
 * @author 内酷啦啦
 */
public class MysqlTableInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表名 驼峰
     */
    private String tableName_aB;

    /**
     * 表名 类名
     */
    private String tableName_AB;

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
        this.tableName_AB = Convert.convert_AB(tableName);
        this.tableName_aB = Convert.convert_ab(tableName);
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

    public String getTableNameSmall_aB() {
        return tableName_aB;
    }

    public String getTableNameSmall_AB() {
        return tableName_AB;
    }

    @Override
    public String toString() {
        return "MysqlTableInfo{" +
                "tableName='" + tableName + '\'' +
                ", tableName_aB='" + tableName_aB + '\'' +
                ", tableName_AB='" + tableName_AB + '\'' +
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
