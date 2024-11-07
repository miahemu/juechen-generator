package com.juechen.maker.model;

import lombok.Data;

/**
 * 动态模板配置类
 * 用于设置模板生成的配置参数
 *
 * @author Juechen
 * @version MainTemplateConfig.java
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

    /**
     * 运算类型（sum, product, max, min等）
     */
    private OperationType operationType = OperationType.SUM;

    /**
     * 操作类型的枚举定义
     */
    public enum OperationType {
        SUM,       // 求和
        PRODUCT,   // 乘积
        MAX,       // 最大值
        MIN        // 最小值
    }
}
