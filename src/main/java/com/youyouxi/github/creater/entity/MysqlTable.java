package com.youyouxi.github.creater.entity;

import java.io.Serializable;
import java.util.List;

public class MysqlTable implements Serializable {

    private static final long serialVersionUID = -7741306487427192078L;

    /**
     * 表信息
     */
    private MysqlTableInfo tableInfo;

    /**
     * 表字段信息
     */
    private List<MysqlTableInfoDetail> tableInfoDetails;

    public MysqlTableInfo getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(MysqlTableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }

    public List<MysqlTableInfoDetail> getTableInfoDetails() {
        return tableInfoDetails;
    }

    public void setTableInfoDetails(List<MysqlTableInfoDetail> tableInfoDetail) {
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
