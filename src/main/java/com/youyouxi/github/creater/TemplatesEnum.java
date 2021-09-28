package com.youyouxi.github.creater;

/**
 * 魔板信息
 *
 * @author youyouxi
 */
public enum TemplatesEnum {

    ENTITY("templates/entity.ftl", ".java"),
    MAPPER("templates/mapper.ftl", ".java"),
    MAPPER_BASE("templates/mapperBase.ftl", ".java"),
    MAPPER_BASE_XML("templates/mapperBaseXml.ftl", ".java"),
    ;

    /**
     * 模板地址
     */
    private String template;
    /**
     * 后缀
     */
    private String suffix;

    TemplatesEnum(String template, String suffix) {
        this.template = template;
        this.suffix = suffix;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

}
