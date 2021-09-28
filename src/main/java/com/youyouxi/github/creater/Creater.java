package com.youyouxi.github.creater;

import com.youyouxi.github.creater.Utils.StringPool;
import com.youyouxi.github.creater.entity.MysqlTable;
import com.youyouxi.github.creater.entity.PersonalConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        

    }

    private static void createExecute() {
        try {
            Template template = CONFIGURATION.getTemplate("templates/entity.ftl");
            File dir = new File("/Users/chenjianbin/Documents/workspace_flybiner/creater/src/main/resources");
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    LOGGER.error("创建文件夹失败");
                    return;
                }
            }
            OutputStream fos = new FileOutputStream(new File(dir, "ceshi.java"));
            Writer out = new OutputStreamWriter(fos);
            Map<String, Object> root = new HashMap<>();
            root.put("packageName", "1111 ");
            template.process(root, out);
        } catch (IOException | TemplateException e) {
            LOGGER.error("", e);
        }
    }


}
