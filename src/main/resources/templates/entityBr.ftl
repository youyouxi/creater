package <#if !personalConfig.packageNameOfEntity??>${package}<#else>${personalConfig.packageNameOfEntity}</#if>;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fingard.ats.entity.AtsCommonObject;
<#list javaPackage as pk>
${pk}
</#list>

/**
 * ${mysqlTable.tableInfo.tableComment}
 *
 * @author ${personalConfig.author}
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("${mysqlTable.tableInfo.tableName}")
public class ${mysqlTable.tableInfo.tableNameUpperCamelCase} extends AtsCommonObject {

    private static final long serialVersionUID = 1L;

<#list mysqlTable.tableInfoDetails as tid>
    <#if tid.isShow == '1'>
    /**
     * ${tid.columnComment}
     */
    @TableField("${tid.columnName}")
    private ${tid.columnJavaType} ${tid.columnNameLowerCamelCase};
    </#if>
</#list>

<#list mysqlTable.tableInfoDetails as tid>
    public static final String ${tid.columnName} = "${tid.columnName}";

</#list>
}