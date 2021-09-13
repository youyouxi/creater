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
                .url("10.211.55.10")
                .port("3306")
                .table("flybiner-user")
                .userName("root")
                .password("123456")
                .type("mysql")
                .execute();

    }

}
