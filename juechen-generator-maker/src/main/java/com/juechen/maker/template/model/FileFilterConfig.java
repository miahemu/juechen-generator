package com.juechen.maker.template.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author Juechen
 * @version : FileFilterConfig.java
 * @sescribe : 文件过滤配置
 */
@Data
@Builder
public class FileFilterConfig {

    /**
     * 过滤范围
     */
    private String range;

    /**
     * 过滤规则
     */
    private String rule;

    /**
     * 过滤值
     */
    private String value;
}
