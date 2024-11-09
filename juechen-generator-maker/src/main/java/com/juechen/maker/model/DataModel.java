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
     * 作者注释
     */
    private String author;

    /**
     * 输出信息的前缀
     */
    private String outputText;

}
