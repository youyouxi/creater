package com.youyouxi.github.creater.builder;

import com.youyouxi.github.creater.entity.MysqlTable;

import java.util.List;

/**
 * 数据库构造器
 * <p>
 * 主要用于获取数据库信息，表字段名，表信息，表字段类型
 *
 * @author youyouxi
 */
public interface DbBuilder {

    DbBuilder url(String url);

    DbBuilder port(String port);

    DbBuilder userName(String userName);

    DbBuilder password(String password);

    DbBuilder diver(String diver);

    DbBuilder type(String type);

    DbBuilder dataPool(String dataPool);

    DbBuilder table(String table);

    List<MysqlTable> execute();

}
