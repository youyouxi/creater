# create-code
代码生成工具

## 快速开始
```
<!-- https://mvnrepository.com/artifact/com.github.youyouxi/creater -->
<dependency>
    <groupId>com.github.youyouxi</groupId>
    <artifactId>creater</artifactId>
    <version>1.0.1</version>
</dependency>
```
```
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

    // 创建 和 更新 两种方式
    personalConfig.setCreateCategory(PersonalConfig.CreateCategory.CREATE.getCategory());

    List<MysqlTable> mysqlTables = new MysqlConcreteBuilder()
            .diver("com.mysql.cj.jdbc.Driver")
            .url("0.0.0.0")
            .port("3306")
            .dataPool("flybiner-user")
            .userName("root")
            .password("0000")
            .type("mysql")
            .personalConfig(personalConfig)
            .execute();

}
```
