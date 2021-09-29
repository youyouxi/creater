package ${package}

<#list javaPackage as pk>
    ${pk}
</#list>

/**
* ${mysqlTable.tableInfo.tableComment}
*
* @author ${personalConfig.author}
*/
public class ${mysqlTable.tableInfo.tableNameHump} implements Serializable{

    private static final long serialVersionUID = 1L;

<#list mysqlTable.tableInfoDetails as tid>
    /**${tid.columnComment}*/
    private ${tid.columnType} ${tid.columnName_aB};
</#list>

}