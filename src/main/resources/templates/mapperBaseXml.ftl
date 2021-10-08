<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="<#if !personalConfig.packageNameOfMapper??>${package}<#else>${personalConfig.packageNameOfMapper}</#if>.${mysqlTable.tableInfo.tableNameUpperCamelCase}Mapper">
    <!-- 通用查询映射结果 -->
    <resultMap id="${mysqlTable.tableInfo.tableNameLowerCamelCase}ResultMap" type="<#if !personalConfig.packageNameOfEntity??>${package}<#else>${personalConfig.packageNameOfEntity}</#if>.${mysqlTable.tableInfo.tableNameUpperCamelCase}">
<#list mysqlTable.tableInfoDetails as tid>
    <#if tid.columnName == "id">
        <id column="${tid.columnName}" property="${tid.columnNameLowerCamelCase}"/>
    <#else>
        <result column="${tid.columnName}" property="${tid.columnNameLowerCamelCase}"/>
    </#if>
</#list>
    </resultMap>
</mapper>