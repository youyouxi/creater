package <#if !personalConfig.packageNameOfMapperBase??>${package}<#else>${personalConfig.packageNameOfMapperBase}</#if>;

import <#if !personalConfig.packageNameOfEntity??>${package}<#else>${personalConfig.packageNameOfEntity}</#if>.${mysqlTable.tableInfo.tableNameUpperCamelCase};
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * ${mysqlTable.tableInfo.tableComment}
 *
 * @author ${personalConfig.author}
 */
public interface ${mysqlTable.tableInfo.tableNameUpperCamelCase}MapperBase {

    /**
     * 查询${mysqlTable.tableInfo.tableNameUpperCamelCase}
     *
     * @param id 唯一id
     * @return ${mysqlTable.tableInfo.tableNameUpperCamelCase} ${mysqlTable.tableInfo.tableNameUpperCamelCase}信息
     */
    @Select("SELECT * FROM ${mysqlTable.tableInfo.tableName} WHERE id = ${r'#{id}'}")
    ${mysqlTable.tableInfo.tableNameUpperCamelCase} selectOne(@Param("id") Long id);

    /**
     * 查询 ${mysqlTable.tableInfo.tableNameUpperCamelCase}
     *
     * @param ${mysqlTable.tableInfo.tableNameLowerCamelCase} ${mysqlTable.tableInfo.tableNameLowerCamelCase}
     * @return ${mysqlTable.tableInfo.tableNameUpperCamelCase} ${mysqlTable.tableInfo.tableNameUpperCamelCase} 信息
     */
    @Select({"<script>",
            "SELECT * FROM ${mysqlTable.tableInfo.tableName} " +
                    "WHERE 1 = 1 " +
                    "<#list mysqlTable.tableInfoDetails as tid> " +
                    "<if test=\"${tid.columnNameLowerCamelCase} != null\"> " +
                    "AND `${tid.columnName}` = ${r'#'}{${tid.columnNameLowerCamelCase}} " +
                    "</if> " +
                    "</#list> " +
                    "LIMIT 1 ",
            "</script>"})
    ${mysqlTable.tableInfo.tableNameUpperCamelCase} selectOne${mysqlTable.tableInfo.tableNameUpperCamelCase}(${mysqlTable.tableInfo.tableNameUpperCamelCase} ${mysqlTable.tableInfo.tableNameLowerCamelCase});

    /**
     * 查询 ${mysqlTable.tableInfo.tableNameUpperCamelCase} 列表
     *
     * @param ${mysqlTable.tableInfo.tableNameLowerCamelCase} ${mysqlTable.tableInfo.tableNameLowerCamelCase}
     * @return List<${mysqlTable.tableInfo.tableNameLowerCamelCase}> ${mysqlTable.tableInfo.tableNameLowerCamelCase} 列表
     */
    @Select({"<script>",
            "SELECT * FROM ${mysqlTable.tableInfo.tableName} " +
                    "WHERE 1 = 1 " +
                    "<#list mysqlTable.tableInfoDetails as tid> " +
                    "<if test=\"${tid.columnNameLowerCamelCase} != null\"> " +
                    "AND `${tid.columnName}` = ${r'#'}{${tid.columnNameLowerCamelCase}} " +
                    "</if> " +
                    "</#list> " +
                    "LIMIT 100 ",
            "</script>"})
    List<${mysqlTable.tableInfo.tableNameUpperCamelCase}> selectList(${mysqlTable.tableInfo.tableNameUpperCamelCase} ${mysqlTable.tableInfo.tableNameLowerCamelCase});


    /**
     * 查询 ${mysqlTable.tableInfo.tableNameUpperCamelCase} 列表-分页
     *
     * @param ${mysqlTable.tableInfo.tableNameLowerCamelCase} ${mysqlTable.tableInfo.tableNameLowerCamelCase}
     * @param offset 开始条数
     * @param pageSize 结束条数
     * @return List<${mysqlTable.tableInfo.tableNameLowerCamelCase}> ${mysqlTable.tableInfo.tableNameLowerCamelCase} 列表
     */
    @Select({"<script>",
            "SELECT * FROM ${mysqlTable.tableInfo.tableName} " +
                    "WHERE 1 = 1 " +
                    "<#list mysqlTable.tableInfoDetails as tid> " +
                    "<if test=\"${mysqlTable.tableInfo.tableNameLowerCamelCase}.${tid.columnNameLowerCamelCase} != null\"> " +
                    "<#if tid.columnType == 'String'>" +
                    "AND `${tid.columnName}` LIKE '%${r'$'}{${mysqlTable.tableInfo.tableNameLowerCamelCase}.${tid.columnNameLowerCamelCase}}%' " +
                    "<#else>" +
                    "AND `${tid.columnName}` = ${r'#'}{${mysqlTable.tableInfo.tableNameLowerCamelCase}.${tid.columnNameLowerCamelCase}} " +
                    "</#if>" +
                    "</if> " +
                    "</#list> " +
                    "LIMIT ${r'#{offset}'},${r'#{pageSize}'} ",
            "</script>"})
    List<${mysqlTable.tableInfo.tableNameUpperCamelCase}> selectPage(@Param("${mysqlTable.tableInfo.tableNameLowerCamelCase}") ${mysqlTable.tableInfo.tableNameUpperCamelCase} ${mysqlTable.tableInfo.tableNameLowerCamelCase}, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    /**
     * 查询 ${mysqlTable.tableInfo.tableNameUpperCamelCase} 总数-分页
     *
     * @param ${mysqlTable.tableInfo.tableNameLowerCamelCase} ${mysqlTable.tableInfo.tableNameLowerCamelCase}
     * @return Integer 总数
     */
    @Select({"<script>",
            "SELECT count(*) FROM ${mysqlTable.tableInfo.tableName} " +
                    "WHERE 1 = 1 " +
                    "<#list mysqlTable.tableInfoDetails as tid> " +
                    "<if test=\"${mysqlTable.tableInfo.tableNameLowerCamelCase}.${tid.columnNameLowerCamelCase} != null\"> " +
                    "<#if tid.columnType == 'String'>" +
                    "AND `${tid.columnName}` LIKE '%${r'$'}{${mysqlTable.tableInfo.tableNameLowerCamelCase}.${tid.columnNameLowerCamelCase}}%' " +
                    "<#else>" +
                    "AND `${tid.columnName}` = ${r'#'}{${mysqlTable.tableInfo.tableNameLowerCamelCase}.${tid.columnNameLowerCamelCase}} " +
                    "</#if>" +
                    "</if> " +
                    "</#list> ",
            "</script>"})
    Integer selectCount(@Param("${mysqlTable.tableInfo.tableNameLowerCamelCase}") ${mysqlTable.tableInfo.tableNameUpperCamelCase} ${mysqlTable.tableInfo.tableNameLowerCamelCase});

    /**
     * 插入 ${mysqlTable.tableInfo.tableNameUpperCamelCase}
     *
     * @param ${mysqlTable.tableInfo.tableNameLowerCamelCase} ${mysqlTable.tableInfo.tableNameLowerCamelCase}
     * @return Integer 插入条数
     */
    @Insert({"<script>",
            "INSERT INTO `${mysqlTable.tableInfo.tableName}` " +
                    "<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\"> " +
                    "<#list mysqlTable.tableInfoDetails as tid> " +
                    "<if test=\"${tid.columnNameLowerCamelCase} != null\"> " +
                    "`${tid.columnName}`, " +
                    "</if> " +
                    "</#list> " +
                    "</trim> " +
                    "VALUES " +
                    "<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\"> " +
                    "<#list mysqlTable.tableInfoDetails as tid> " +
                    "<if test=\"${tid.columnNameLowerCamelCase} != null\"> " +
                    "${r'#'}{${tid.columnNameLowerCamelCase}}, " +
                    "</if> " +
                    "</#list> " +
                    "</trim> ",
            "</script>"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insert(${mysqlTable.tableInfo.tableNameUpperCamelCase} ${mysqlTable.tableInfo.tableNameLowerCamelCase});

    /**
     * 批量插入 ${mysqlTable.tableInfo.tableNameUpperCamelCase}
     *
     * @param ${mysqlTable.tableInfo.tableNameLowerCamelCase}s 列表
     * @return Integer 插入条数
     */
    @Insert({"<script>",
            "INSERT INTO `${mysqlTable.tableInfo.tableName}` " +
                    "<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\"> " +
                    "<#list mysqlTable.tableInfoDetails as tid> " +
                    "`${tid.columnName}`, " +
                    "</#list> " +
                    "</trim> " +
                    "VALUES " +
                    "<foreach collection=\"list\" item=\"obj\" open=\"\" close=\"\" separator=\",\"> " +
                    "<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\"> " +
                    "<#list mysqlTable.tableInfoDetails as tid> " +
                    "${r'#'}{obj.${tid.columnNameLowerCamelCase}}, " +
                    "</#list> " +
                    "</trim> " +
                    "</foreach> ",
            "</script>"})
    Integer insertBatch(List<${mysqlTable.tableInfo.tableNameUpperCamelCase}> ${mysqlTable.tableInfo.tableNameLowerCamelCase}s);

    /**
     * 更新 ${mysqlTable.tableInfo.tableNameUpperCamelCase}
     *
     * @param ${mysqlTable.tableInfo.tableNameLowerCamelCase} ${mysqlTable.tableInfo.tableNameLowerCamelCase}
     * @return Integer 更新条数
     */
    @Update({"<script>",
            "UPDATE ${mysqlTable.tableInfo.tableName} " +
                    "<set> " +
                    "    <#list mysqlTable.tableInfoDetails as tid> " +
                    "        <if test=\"${tid.columnNameLowerCamelCase} != null\"> " +
                    "            `${tid.columnName}` = ${r'#'}{${tid.columnNameLowerCamelCase}}, " +
                    "        </if> " +
                    "    </#list> " +
                    "</set> " +
                    "WHERE id = ${r'#{id}'} ",
            "</script>"})
    Integer update(${mysqlTable.tableInfo.tableNameUpperCamelCase} ${mysqlTable.tableInfo.tableNameLowerCamelCase});

    /**
     * 删除 ${mysqlTable.tableInfo.tableNameUpperCamelCase}
     *
     * @param id 唯一id
     * @return Integer 删除条数
     */
    @Delete("DELETE FROM ${mysqlTable.tableInfo.tableName} WHERE id = ${r'#{id}'} ")
    Integer delete(@Param("id") Long id);

}
