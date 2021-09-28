package com.youyouxi.github.creater.entity;

import com.mysql.cj.util.StringUtils;

import java.io.Serializable;

/**
 * 个性化配置
 *
 * @author youyouxi
 */
public class PersonalConfig implements Serializable {

    private static final long serialVersionUID = -828296163069359380L;

    private String createCategory;

    private String createPath;

    private String packageName;

    private String author;

    public String getCreateCategory() {
        return createCategory;
    }

    public void setCreateCategory(String createCategory) {
        this.createCategory = createCategory;
    }

    public String getCreatePath() {
        return createPath;
    }

    public void setCreatePath(String createPath) {
        this.createPath = createPath;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void checkAndInit() {
        if (StringUtils.isNullOrEmpty(this.createPath)) {
            throw new NullPointerException("没有配置代码生成路径。PersonalConfig.setCreatePath");
        }
        if (StringUtils.isNullOrEmpty(this.createCategory)) {
            throw new NullPointerException("没有指定是 创建 或者 更新。PersonalConfig.CreateCategory");
        }
        this.author = this.author == null ? "example" : this.author;
        this.packageName = this.packageName == null ? "com.example" : this.packageName;
    }

    private enum CreateCategory {
        CREATE("create"),
        UPDATE("update"),
        ;

        private String category;

        CreateCategory(String category) {
            this.category = category;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }

}
