package com.youyouxi.github.creater.builder;

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

    void execute();

}
