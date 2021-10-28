package com.youyouxi.github.creater.entity;

import java.io.Serializable;
import java.util.List;

public class Table implements Serializable {

    private static final long serialVersionUID = -7741306487427192078L;

    /**
     * 表信息
     */
    private TableInfo tableInfo;

    /**
     * 表字段信息
     */
    private List<TableInfoDetail> tableInfoDetails;

    public TableInfo getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }

    public List<TableInfoDetail> getTableInfoDetails() {
        return tableInfoDetails;
    }

    public void setTableInfoDetails(List<TableInfoDetail> tableInfoDetail) {
        this.tableInfoDetails = tableInfoDetail;
    }

    @Override
    public String toString() {
        return "MysqlTable{" +
                "tableInfo=" + tableInfo +
                ", tableInfoDetail=" + tableInfoDetails +
                '}';
    }

}
