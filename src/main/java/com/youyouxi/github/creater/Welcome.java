package com.youyouxi.github.creater;

import com.youyouxi.github.creater.builder.MysqlConcreteBuilder;
import com.youyouxi.github.creater.entity.MysqlTable;

import java.util.List;

/**
 * welcome
 *
 * @author youyouxi
 */
public class Welcome {

    public static void main(String[] args) {
        System.out.println("你好！");

        List<MysqlTable> mysqlTables = new MysqlConcreteBuilder()
                .diver("com.mysql.cj.jdbc.Driver")
                .url("47.95.235.86")
                .port("3306")
                .dataPool("flybiner-user")
                .userName("root")
                .password("cjb123456")
                .type("mysql")
                .execute();

    }

}
