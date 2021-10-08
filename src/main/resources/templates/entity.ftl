package <#if !personalConfig.packageNameOfEntity??>${package}<#else>${personalConfig.packageNameOfEntity}</#if>;

import java.io.Serializable;
<#list javaPackage as pk>
${pk}
</#list>

/**
 * ${mysqlTable.tableInfo.tableComment}
 *
 * @author ${personalConfig.author}
 */
public class ${mysqlTable.tableInfo.tableNameUpperCamelCase} implements Serializable {

    private static final long serialVersionUID = 1L;

<#list mysqlTable.tableInfoDetails as tid>
    /**
     * ${tid.columnComment}
     */
    private ${tid.columnJavaType} ${tid.columnNameLowerCamelCase};
</#list>

<#list mysqlTable.tableInfoDetails as tid>
    public ${tid.columnJavaType} get${tid.columnNameUpperCamelCase}() {
        return ${tid.columnNameLowerCamelCase};
    }

    public void set${tid.columnNameUpperCamelCase}(${tid.columnJavaType} ${tid.columnNameLowerCamelCase}) {
        this.${tid.columnNameLowerCamelCase} = ${tid.columnNameLowerCamelCase};
    }

</#list>
}