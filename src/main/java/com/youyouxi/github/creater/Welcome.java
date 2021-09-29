package com.youyouxi.github.creater;

import com.youyouxi.github.creater.builder.impl.MysqlConcreteBuilder;
import com.youyouxi.github.creater.entity.MysqlTable;
import com.youyouxi.github.creater.entity.PersonalConfig;

import java.util.List;

/**
 * welcome
 *
 * @author youyouxi
 */
public class Welcome {


    public static void main(String[] args) {
        System.out.println("你好！");

        PersonalConfig personalConfig = new PersonalConfig();
        personalConfig.setCreatePath("/Users/chenjianbin/Documents/workspace_flybiner/creater/src/main/resources");
        personalConfig.setCreateCategory(PersonalConfig.CreateCategory.CREATE.getCategory());

        List<MysqlTable> mysqlTables = new MysqlConcreteBuilder()
                .diver("com.mysql.cj.jdbc.Driver")
                .url("47.95.235.86")
                .port("3306")
                .dataPool("flybiner-user")
                .userName("root")
                .password("cjb123456")
                .type("mysql")
                .personalConfig(personalConfig)
                .execute();

    }

}
