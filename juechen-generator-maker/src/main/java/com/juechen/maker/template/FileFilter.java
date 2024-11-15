package com.juechen.maker.template;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import com.juechen.maker.template.enums.FileFilterRangeEnum;
import com.juechen.maker.template.enums.FileFilterRuleEnum;
import com.juechen.maker.template.model.FileFilterConfig;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Juechen
 * @version : FileFilter.java
 */
public class FileFilter {

    /**
     * 多个文件过滤，返回文件列表
     */
    public static List<File> doMultiFileFilter(String filePath, List<FileFilterConfig> fileFilterConfigList) {
        // 根据所有路径获取所有文件
        List<File> fileList = FileUtil.loopFiles(filePath);
        return fileList.stream()
                .filter(file -> doSingleFileFilter(fileFilterConfigList, file))
                .collect(Collectors.toList());
    }


    /**
     * 单个文件过滤
     */
    public static boolean doSingleFileFilter(List<FileFilterConfig> fileFilterConfigList, File file) {
        String fileName = file.getName();
        String fileContent = FileUtil.readUtf8String(file);

        // 所有过滤器校验结束的结果
        boolean result = true;

        if (CollUtil.isEmpty(fileFilterConfigList)) {
            return true;
        }

        for (FileFilterConfig fileFilterConfig : fileFilterConfigList) {
            String range = fileFilterConfig.getRange();
            String rule = fileFilterConfig.getRule();
            String value = fileFilterConfig.getValue();

            // 调用getEnumByValue()方法看调用的是哪一个枚举类型
            FileFilterRangeEnum fileFilterRangeEnum = FileFilterRangeEnum.getEnumByValue(range);
            if (fileFilterRangeEnum == null) {
                continue;
            }

            // 要过滤的内容
            String content = fileName;
            switch (fileFilterRangeEnum) {
                case FILE_NAME:
                    content = fileName;
                    break;
                case FILE_CONTENT:
                    content = fileContent;
                    break;
            }

            FileFilterRuleEnum fileFilterRuleEnum = FileFilterRuleEnum.getEnumByValue(rule);
            if (fileFilterRuleEnum == null) {
                continue;
            }

            switch (fileFilterRuleEnum) {
                case CONTAINS:
                    result = content.contains(value);
                    break;
                case STARTS_WITH:
                    result = content.startsWith(value);
                    break;
                case ENDS_WITH:
                    result = content.endsWith(value);
                    break;
                case REGEX:
                    result = content.matches(value);
                    break;
                case EQUALS:
                    result = content.equals(value);
                    break;
                default:
            }

            // 如果有一个不满足，就直接返回
            if (!result) return false;
        }

        // 都满足
        return true;
    }

}


