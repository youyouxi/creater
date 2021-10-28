package com.youyouxi.github.creater;

/**
 * 魔板信息
 *
 * @author youyouxi
 */
public enum TemplatesEnum {

    /**
     * 类别 通用
     */
    ENTITY("templates/entity.ftl", ".java"),
    MAPPER("templates/mapper.ftl", "Mapper.java"),
    MAPPER_BASE("templates/mapperBase.ftl", "MapperBase.java"),
    MAPPER_BASE_XML("templates/mapperBaseXml.ftl", ".xml"),
    /**
     *
     */
    ENTITY_BR("templates/entityBr.ftl", ".java"),
    ;

    /**
     * 模板地址
     */
    private String templatePath;
    /**
     * 后缀
     */
    private String suffix;

    TemplatesEnum(String templatePath, String suffix) {
        this.templatePath = templatePath;
        this.suffix = suffix;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
