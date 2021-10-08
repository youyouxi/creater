package com.youyouxi.github.creater;

import com.mysql.cj.util.StringUtils;
import com.youyouxi.github.creater.Utils.StringPool;
import com.youyouxi.github.creater.entity.MysqlTable;
import com.youyouxi.github.creater.entity.MysqlTableInfoDetail;
import com.youyouxi.github.creater.entity.PersonalConfig;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 生成器
 *
 * @author youyouxi
 */
public class Creater {

    private static final Logger LOGGER = LoggerFactory.getLogger(Creater.class);

    private static Configuration CONFIGURATION;

    static {
        if (CONFIGURATION == null) {
            CONFIGURATION = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
            CONFIGURATION.setDefaultEncoding(StandardCharsets.UTF_8.name());
            CONFIGURATION.setClassForTemplateLoading(Creater.class, StringPool.SLASH);
        }
    }

    public static void create(List<MysqlTable> mysqlTables, PersonalConfig personalConfig) {
        for (MysqlTable mysqlTable : mysqlTables) {
            if (personalConfig.getCreateCategory().equals(PersonalConfig.CreateCategory.CREATE.getCategory())) {
                createExecute(mysqlTable, TemplatesEnum.MAPPER.getTemplatePath(), TemplatesEnum.MAPPER.getSuffix(), personalConfig, personalConfig.getCreatePathOfMapper());
                createExecute(mysqlTable, TemplatesEnum.MAPPER_BASE_XML.getTemplatePath(), TemplatesEnum.MAPPER_BASE_XML.getSuffix(), personalConfig, personalConfig.getCreatePathOfMapperBaseXml());
            }
            createExecute(mysqlTable, TemplatesEnum.ENTITY.getTemplatePath(), TemplatesEnum.ENTITY.getSuffix(), personalConfig, personalConfig.getCreatePathOfEntity());
            createExecute(mysqlTable, TemplatesEnum.MAPPER_BASE.getTemplatePath(), TemplatesEnum.MAPPER_BASE.getSuffix(), personalConfig, personalConfig.getCreatePathOfMapperBase());
        }
    }

    private static void createExecute(MysqlTable mysqlTable,
                                      String templatePath,
                                      String suffix,
                                      PersonalConfig personalConfig,
                                      String personalCreatePath) {
        try {
            Template template = CONFIGURATION.getTemplate(templatePath);
            File dir = new File(StringUtils.isNullOrEmpty(personalCreatePath) ? personalConfig.getCreatePath() : personalCreatePath);
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    LOGGER.error("创建文件夹失败");
                    return;
                }
            }
            // javaPackage
            Set<String> javaPackage = new HashSet<String>(16);
            extractColumnType(mysqlTable, javaPackage);
            OutputStream fos = new FileOutputStream(new File(dir, mysqlTable.getTableInfo().getTableNameUpperCamelCase() + suffix));
            Writer out = new OutputStreamWriter(fos);
            Map<String, Object> root = new HashMap<String, Object>();
            root.put("mysqlTable", mysqlTable);
            root.put("package", getPackage(personalConfig));
            root.put("javaPackage", javaPackage);
            root.put("personalConfig", personalConfig);
            template.process(root, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (TemplateNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (MalformedTemplateNameException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getPackage(PersonalConfig personalConfig) {
        return personalConfig.getPackageName();
    }

    /**
     * 提取表字段类型名称
     *
     * @param mysqlTable  字段类型名称
     * @param javaPackage javaPackage
     */
    private static void extractColumnType(MysqlTable mysqlTable, Set<String> javaPackage) {
        for (MysqlTableInfoDetail tableInfoDetail : mysqlTable.getTableInfoDetails()) {
            String columnType = tableInfoDetail.getColumnType();
            String str = "";
            if ((columnType.contains("int") || columnType.contains("tinyint")) && !"bigint".equals(columnType)) {
                str = "Integer";
            } else if (columnType.contains("bigint")) {
                str = "Long";
            } else if ((columnType.contains("varchar") || columnType.contains("char") || columnType.contains("text"))) {
                str = "String";
            } else if (columnType.contains("decimal")) {
                str = "BigDecimal";
                javaPackage.add("import java.math.BigDecimal;");
            } else if (columnType.contains("datetime")) {
                str = "Date";
                javaPackage.add("import java.util.Date;");
            }
            tableInfoDetail.setColumnJavaType(str);
        }

    }


}
