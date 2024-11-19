package com.juechen.maker.template;

import cn.hutool.core.util.StrUtil;
import com.juechen.maker.meta.Meta;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Juechen
 * @version : TemplateMakerUtils.java
 * @describe : 模版制作工具类
 */
public class TemplateMakerUtils {

    /*
    * 从未分组文件中移除组内的同名文件
    */
    public static List<Meta.FileConfig.FileInfo> removeGroupFilesFromRoot(List<Meta.FileConfig.FileInfo> fileInfoList) {
        // 先获取到所有的分组
        List<Meta.FileConfig.FileInfo> groupFileInfoList = fileInfoList.stream()
                .filter(fileInfo -> StrUtil.isNotBlank(fileInfo.getGroupName()))
                .collect(Collectors.toList());

        // 获取所有分组内的文件列表
        List<Meta.FileConfig.FileInfo> groupInnerFileInfoList = groupFileInfoList.stream()
                .flatMap(fileInfo -> fileInfo.getFiles().stream())
                .collect(Collectors.toList());

        // 获取所有分组内文件输入路径集合
        List<String> fileInputPathSet = groupInnerFileInfoList.stream()
                .map(Meta.FileConfig.FileInfo::getInputPath)
                .collect(Collectors.toList());

        // 移除所有在set集合中的外层文件
        return fileInfoList.stream()
                .filter(fileInfo -> !fileInputPathSet.contains(fileInfo.getInputPath()))
                .collect(Collectors.toList());
    }
}
