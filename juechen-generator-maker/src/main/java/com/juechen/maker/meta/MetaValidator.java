package com.juechen.maker.meta;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.juechen.maker.meta.enums.FileGenerateTypeEnum;
import com.juechen.maker.meta.enums.FileTypeEnum;
import com.juechen.maker.meta.enums.ModelTypeEnum;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Juechen
 * @version : MetaValidator.java
 */
public class MetaValidator {
    public static void doValidAndFill(Meta meta) {
        validAndFillMetaRoot(meta);
        validAndFillFileConfig(meta);
        validAndFillModelConfig(meta);
    }

    private static void validAndFillModelConfig(Meta meta) {
        // modelConfig 校验和默认值填充
        Meta.ModelConfig modelConfig = meta.getModelConfig();
        if (modelConfig != null) {
            List<Meta.ModelConfig.ModelInfo> modelInfoList = modelConfig.getModels();
            if (CollectionUtil.isNotEmpty(modelInfoList)) {
                for (Meta.ModelConfig.ModelInfo modelInfo : modelInfoList) {
                    String fieldName = modelInfo.getFieldName();
                    if (StrUtil.isBlank(fieldName)) {
                        throw new MetaException("fieldName不能为空");
                    }

                    String modelInfoType = modelInfo.getType();
                    if (StrUtil.isBlank(modelInfoType)) {
                        modelInfo.setType(ModelTypeEnum.STRING.getValue());
                    }

                }
            }
        }
    }

    private static void validAndFillFileConfig(Meta meta) {
        // fileConfig校验和默认值填充
        Meta.FileConfig fileConfig = meta.getFileConfig();
        if (fileConfig != null) {
            String sourceRootPath = fileConfig.getSourceRootPath();
            if (StrUtil.isBlank(sourceRootPath)) {
                throw new MetaException("sourceRootPath不能为空");
            }

            // inputRootPath : .source + sourceRootPath 的最后一个层级路径
            String inputRootPath = fileConfig.getInputRootPath();
            String defaultInputRootPath = ".source" + File.separator +
                    FileUtil.getLastPathEle(Paths.get(sourceRootPath)).getFileName().toString();
            if (StrUtil.isEmpty(inputRootPath)) {
                fileConfig.setInputRootPath(defaultInputRootPath);
            }

            // outputRootPath : 当前路径下的generated
            String outputRootPath = fileConfig.getOutputRootPath();
            String defaultOutputRootPath = "generated";
            if (StrUtil.isEmpty(outputRootPath)) {
                fileConfig.setOutputRootPath(defaultOutputRootPath);
            }

            String configType = fileConfig.getType();
            String defaultConfigType = FileTypeEnum.DIR.getValue();
            if (StrUtil.isEmpty(configType)) {
                fileConfig.setType(defaultConfigType);
            }

            // fileInfo 默认值
            List<Meta.FileConfig.FileInfo> fileInfoList = fileConfig.getFiles();
            if (CollectionUtil.isNotEmpty(fileInfoList)) {
                for (Meta.FileConfig.FileInfo fileInfo : fileInfoList) {
                    // type: 默认 inputPath 有文件后缀（如 .java）为file，否则为 dir
                    String type = fileInfo.getType();
                    // 类型为group，不校验
                    if (FileTypeEnum.GROUP.getValue().equals(type)){
                        continue;
                    }

                    String inputPath = fileInfo.getInputPath();
                    if (StrUtil.isBlank(inputPath)) {
                        throw new MetaException("inputPath不能为空");
                    }

                    String outputPath = fileInfo.getOutputPath();
                    if (StrUtil.isBlank(outputPath)) {
                        fileInfo.setOutputPath(inputPath);
                    }

                    if (StrUtil.isBlank(type)) {
                        // 获取文件后缀
                        if (StrUtil.isBlank(FileUtil.getSuffix(inputPath))) {
                            fileInfo.setType(FileTypeEnum.DIR.getValue());
                        } else {
                            fileInfo.setType(FileTypeEnum.FILE.getValue());
                        }
                    }

//                    FileUtil.getSuffix()和inputPath.endsWith()区别：
//                    假设 inputPath = "example.tar.gz"。
//                    FileUtil.getSuffix(inputPath) 会返回 "gz"。
//                    inputPath.endsWith(".gz") 会返回 true，但 inputPath.endsWith(".tar.gz") 也会返回 true，具体取决于传入的后缀

                    // generateType: 如果文件结尾不为.ftl，则generateType 默认为static，否则为dynamic
                    String generateType = fileInfo.getGenerateType();
                    if (StrUtil.isBlank(generateType)) {
                        if (inputPath.endsWith(".ftl")) {
                            fileInfo.setGenerateType(FileGenerateTypeEnum.DYNAMIC.getValue());
                        } else {
                            fileInfo.setGenerateType(FileGenerateTypeEnum.STATIC.getValue());
                        }
                    }
                }
            }
        }
    }

    private static void validAndFillMetaRoot(Meta meta) {
        // 基础信息校验和默认值填充
        // blankToDefault 会将空白字符串（包括空格）替换为默认值
        String name = StrUtil.blankToDefault(meta.getName(), "my-generator");
        // emptyToDefault 只会将 null 或完全空的字符串 "" 替换为默认值
        String description = StrUtil.emptyToDefault(meta.getDescription(), "我的代码模版生成器");
        String author = StrUtil.emptyToDefault(meta.getAuthor(), "juechen");
        String basePackage = StrUtil.blankToDefault(meta.getBasePackage(), "com.juechen");
        String version = StrUtil.emptyToDefault(meta.getVersion(), "1.0");
        String createTime = StrUtil.emptyToDefault(meta.getCreateTime(), DateUtil.now());
        meta.setName(name);
        meta.setDescription(description);
        meta.setAuthor(author);
        meta.setBasePackage(basePackage);
        meta.setVersion(version);
        meta.setCreateTime(createTime);
    }
}
