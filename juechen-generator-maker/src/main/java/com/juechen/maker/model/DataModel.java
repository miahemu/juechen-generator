package com.juechen.maker.model;

import lombok.Data;

/**
 * 动态模板配置类
 * 用于设置模板生成的配置参数
 *
 * @author Juechen
 * @version DataModel.java
 */
@Data
public class DataModel {

    /**
     * 是否生成循环
     */
    private boolean loop;

    /**
     * 核心模版
     */
    public MainTemplate mainTemplate = new MainTemplate();

    /**
     * 用于生成模版核心文件
     */
    @Data
    public static class MainTemplate {
        /**
         * 作者注释
         */
        public String author = "juechen";

        /**
         * 输出信息的前缀
         */
        public String outputText = "sum = ";
    }

}
