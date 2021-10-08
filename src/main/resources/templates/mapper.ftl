package <#if !personalConfig.packageNameOfMapper??>${package}<#else>${personalConfig.packageNameOfMapper}</#if>;

/**
 * ${mysqlTable.tableInfo.tableComment}
 *
 * @author ${personalConfig.author}
 */
public interface ${mysqlTable.tableInfo.tableNameUpperCamelCase}Mapper extends ${mysqlTable.tableInfo.tableNameUpperCamelCase}MapperBase{

}