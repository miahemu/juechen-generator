package com.juechen.model;

import lombok.Data;

/**
 * @author Juechen
 * @version : MainTemplateConfig.java
 * @describe : 动态模版配置
 */
@Data
public class MainTemplateConfig {

    /**
     * 是否生成循环
     */
    private boolean loop;

    /**
     * 作者注释
     */
    private String author = "Juechen";

    /**
     * 输出信息
     */
    private String outputText = "Sum：";
}
