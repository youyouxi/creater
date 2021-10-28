package com.youyouxi.github.creater;

import com.youyouxi.github.creater.builder.impl.OracleConcreteBuilder;
import com.youyouxi.github.creater.entity.Table;
import com.youyouxi.github.creater.entity.PersonalConfig;

import java.util.List;

/**
 * welcome
 *
 * @author youyouxi
 */
public class WelcomeOracle {


    public static void main(String[] args) {

        PersonalConfig personalConfig = new PersonalConfig();
        personalConfig.setCreatePath("/Users/chenjianbin/Documents/workspace_flybiner/creater/src/main/java/com/youyouxi/github/creater/temp");
        // 设置生成路径
        personalConfig.setCreatePathOfEntity("/Users/chenjianbin/Documents/workspace_flybiner/creater/src/main/java/com/youyouxi/github/creater/temp/entity");
        personalConfig.setCreatePathOfMapper("/Users/chenjianbin/Documents/workspace_flybiner/creater/src/main/java/com/youyouxi/github/creater/temp/mapper");
        personalConfig.setCreatePathOfMapperBase("/Users/chenjianbin/Documents/workspace_flybiner/creater/src/main/java/com/youyouxi/github/creater/temp/mapper");
        personalConfig.setCreatePathOfMapperBaseXml("/Users/chenjianbin/Documents/workspace_flybiner/creater/src/main/java/com/youyouxi/github/creater/temp/xml");

        personalConfig.setPackageName("com.youyouxi.github.creater.temp");
        personalConfig.setPackageNameOfEntity("com.youyouxi.github.creater.temp.entity");
        personalConfig.setPackageNameOfMapper("com.youyouxi.github.creater.temp.mapper");
        personalConfig.setPackageNameOfMapperBase("com.youyouxi.github.creater.temp.mapper");

        personalConfig.setCreateCategory(PersonalConfig.CreateCategory.CREATE.getCategory());

        List<Table> oracleTable = new OracleConcreteBuilder()
                .diver("oracle.jdbc.OracleDriver")
                .url("10.60.44.70")
                .port("1521")
                .dataPool("flybiner-user")
                .userName("ms_ats_dev")
                .password("ms_ats_dev")
                .type("ORCL")
                .table("T_EI_BUSINESSES")
                .personalConfig(personalConfig)
                .execute();

    }

}
