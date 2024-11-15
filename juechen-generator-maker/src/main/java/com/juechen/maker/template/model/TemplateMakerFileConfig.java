package com.juechen.maker.template.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Juechen
 * @version : TemplateMakerFileConfig.java
 */

@Data
public class TemplateMakerFileConfig {

    private List<FileInfoConfig> files;

    @NoArgsConstructor
    @Data
    public static class FileInfoConfig {

        private String path;

        private List<FileFilterConfig> filterConfigList;
    }
}

