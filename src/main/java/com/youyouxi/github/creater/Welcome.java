package com.youyouxi.github.creater;

import com.youyouxi.github.creater.builder.MysqlConcreteBuilder;

/**
 * welcome
 *
 * @author youyouxi
 */
public class Welcome {

    public static void main(String[] args) {
        System.out.println("你好！");

        new MysqlConcreteBuilder()
                .diver("com.mysql.cj.jdbc.Driver")
                .url("47.95.235.86")
                .port("3306")
                .table("flybiner-user")
                .userName("root")
                .password("cjb123456")
                .type("mysql")
                .execute();

    }

}
