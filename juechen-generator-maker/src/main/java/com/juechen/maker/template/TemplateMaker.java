package com.juechen.maker.template;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.juechen.maker.meta.Meta;
import com.juechen.maker.meta.enums.FileGenerateTypeEnum;
import com.juechen.maker.meta.enums.FileTypeEnum;
import com.juechen.maker.template.enums.FileFilterRangeEnum;
import com.juechen.maker.template.enums.FileFilterRuleEnum;
import com.juechen.maker.template.model.FileFilterConfig;
import com.juechen.maker.template.model.TemplateMakerFileConfig;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Juechen
 * @version : TemplateMaker.java
 * @describe : 模板生成器
 */
public class TemplateMaker {

    public static void main(String[] args) {
        Meta meta = new Meta();
        meta.setName("acm-template-generator");
        meta.setDescription("ACM 示例模板生成器");

        String projectPath = System.getProperty("user.dir");
        String originProjectPath = new File(projectPath).getParent() + File.separator + "juechen-generator-demo-projects/springboot-init";
        String inputFilePath1 = "src/main/java/com/juechen/springbootinit/common";
        String inputFilePath2 = "src/main/java/com/juechen/springbootinit/controller";
        List<String> inputFilePath = Arrays.asList(inputFilePath1, inputFilePath2);

        // 模型参数信息（首次）
//        Meta.ModelConfig.ModelInfo modelInfo = new Meta.ModelConfig.ModelInfo();
//        modelInfo.setFieldName("outputText");
//        modelInfo.setType("String");
//        modelInfo.setDefaultValue("sum = ");

        // 模型参数信息（第二次）
        Meta.ModelConfig.ModelInfo modelInfo = new Meta.ModelConfig.ModelInfo();
        modelInfo.setFieldName("className");
        modelInfo.setType("String");

        // 替换变量（首次）
//        String searchStr = "求和结果:";
        // 替换变量（第二次）
        String searchStr = "BaseResponse";

        // 追加文件过滤
        TemplateMakerFileConfig templateMakerFileConfig = new TemplateMakerFileConfig();

        TemplateMakerFileConfig.FileInfoConfig fileInfoConfig1 = new TemplateMakerFileConfig.FileInfoConfig();
        List<FileFilterConfig> fileFilterConfigList = new ArrayList<>();
        FileFilterConfig fileFilterConfig = FileFilterConfig.builder()
                .range(FileFilterRangeEnum.FILE_NAME.getValue())
                .rule(FileFilterRuleEnum.CONTAINS.getValue())
                .value("Base")
                .build();
        fileFilterConfigList.add(fileFilterConfig);
        fileInfoConfig1.setPath(inputFilePath1);
        fileInfoConfig1.setFilterConfigList(fileFilterConfigList);


        TemplateMakerFileConfig.FileInfoConfig fileInfoConfig2 = new TemplateMakerFileConfig.FileInfoConfig();
        fileInfoConfig2.setPath(inputFilePath2);

        templateMakerFileConfig.setFiles(Arrays.asList(fileInfoConfig1,fileInfoConfig2));
        long id = makeTemplate(meta, originProjectPath, templateMakerFileConfig, modelInfo, searchStr, null);
        System.out.println(id);
    }


    /**
     * 制作模板
     *
     * @param newMeta
     * @param originProjectPath
     * @param templateMakerFileConfig
     * @param modelInfo
     * @param searchStr
     * @param id
     * @return
     */
    public static long makeTemplate(Meta newMeta, String originProjectPath, TemplateMakerFileConfig templateMakerFileConfig, Meta.ModelConfig.ModelInfo modelInfo, String searchStr, Long id) {
        // 没有 id 则生成
        if (id == null) {
            id = IdUtil.getSnowflakeNextId();
        }

        // 复制目录
        String projectPath = System.getProperty("user.dir");
        String tempDirPath = projectPath + File.separator + ".temp";
        String templatePath = tempDirPath + File.separator + id;

        // 是否为首次制作模板
        // 目录不存在，则是首次制作
        if (!FileUtil.exist(templatePath)) {
            FileUtil.mkdir(templatePath);
            FileUtil.copy(originProjectPath, templatePath, true);
        }

        // 一、输入信息
        // 输入文件信息
        String sourceRootPath = templatePath + File.separator + FileUtil.getLastPathEle(Paths.get(originProjectPath)).toString();
        List<TemplateMakerFileConfig.FileInfoConfig> filConfigInfoList = templateMakerFileConfig.getFiles();

        // 二、生成文件模版
        // 输入文件为目录
        List<Meta.FileConfig.FileInfo> newFileInfoList = new ArrayList<>();

        for(TemplateMakerFileConfig.FileInfoConfig fileInfoConfig : filConfigInfoList) {
            String inputFilePath = fileInfoConfig.getPath();
            // 如果填的是相对路径，要改为绝对路径
            if (!inputFilePath.startsWith(sourceRootPath)){
                inputFilePath = sourceRootPath + File.separator + inputFilePath;
            }

            // 获取过滤后的文件列表
            List<File> fileList = FileFilter.doMultiFileFilter(inputFilePath, fileInfoConfig.getFilterConfigList());
            for(File file : fileList){
                Meta.FileConfig.FileInfo newFileInfo = makeFileTemplate(modelInfo, searchStr, sourceRootPath, file);
                newFileInfoList.add(newFileInfo);
            }
        }

        // 三、生成配置文件
        String metaOutputPath = sourceRootPath + File.separator + "meta.json";

        // 如果已有 meta 文件，说明不是第一次制作，则在 meta 基础上进行修改
        if (FileUtil.exist(metaOutputPath)) {
            Meta oldMeta = JSONUtil.toBean(FileUtil.readUtf8String(metaOutputPath), Meta.class);
            BeanUtil.copyProperties(newMeta, oldMeta, CopyOptions.create().ignoreNullValue());
            newMeta = oldMeta;

            // 1. 追加配置参数
            List<Meta.FileConfig.FileInfo> fileInfoList = newMeta.getFileConfig().getFiles();
            fileInfoList.addAll(newFileInfoList);
            List<Meta.ModelConfig.ModelInfo> modelInfoList = newMeta.getModelConfig().getModels();
            modelInfoList.add(modelInfo);

            // 配置去重
            newMeta.getFileConfig().setFiles(distinctFiles(fileInfoList));
            newMeta.getModelConfig().setModels(distinctModels(modelInfoList));
        } else {
            // 构造配置参数
            Meta.FileConfig fileConfig = new Meta.FileConfig();
            newMeta.setFileConfig(fileConfig);
            fileConfig.setSourceRootPath(sourceRootPath);
            List<Meta.FileConfig.FileInfo> fileInfoList = new ArrayList<>();
            fileConfig.setFiles(fileInfoList);
            fileInfoList.addAll(newFileInfoList);

            Meta.ModelConfig modelConfig = new Meta.ModelConfig();
            newMeta.setModelConfig(modelConfig);
            List<Meta.ModelConfig.ModelInfo> modelInfoList = new ArrayList<>();
            modelConfig.setModels(modelInfoList);
            modelInfoList.add(modelInfo);
        }

        // 输出元信息文件
        FileUtil.writeUtf8String(JSONUtil.toJsonPrettyStr(newMeta), metaOutputPath);
        return id;
    }


    /**
     * 文件去重
     *
     * @param fileInfoList
     * @return
     */
    private static List<Meta.FileConfig.FileInfo> distinctFiles(List<Meta.FileConfig.FileInfo> fileInfoList) {
        List<Meta.FileConfig.FileInfo> newFileInfoList = new ArrayList<>(
                fileInfoList.stream()
                        .collect(
                                Collectors.toMap(Meta.FileConfig.FileInfo::getInputPath, o -> o, (e, r) -> r)
                        ).values()
        );
        return newFileInfoList;
    }

    /**
     * 模型去重
     *
     * @param modelInfoList
     * @return
     */
    private static List<Meta.ModelConfig.ModelInfo> distinctModels(List<Meta.ModelConfig.ModelInfo> modelInfoList) {
        List<Meta.ModelConfig.ModelInfo> newModelInfoList = new ArrayList<>(
                modelInfoList.stream()
                        .collect(
                                Collectors.toMap(Meta.ModelConfig.ModelInfo::getFieldName, o -> o, (e, r) -> r)
                        ).values()
        );
        return newModelInfoList;
    }

    /**
     * 制作文件模板
     *
     * @param modelInfo
     * @param searchStr
     * @param sourceRootPath
     * @param inputFile
     * @return
     */
    private static Meta.FileConfig.FileInfo makeFileTemplate(Meta.ModelConfig.ModelInfo modelInfo, String searchStr, String sourceRootPath, File inputFile) {
        // 文件输入输出相对路径（用于生成配置）
        String fileInputPath = inputFile.getAbsolutePath().replace(sourceRootPath + "/", "");
        String fileOutputPath = fileInputPath + ".ftl";

        // 要挖坑的文件绝对路径（用于制作模板）
        String fileInputAbsolutePath = inputFile.getAbsolutePath();
        String fileOutputAbsolutePath = fileInputAbsolutePath + ".ftl";

        // 使用字符串替换，生成模板文件
        String fileContent;
        // 如果已有模板文件，说明不是第一次制作，则在模板基础上再次挖坑
        if (FileUtil.exist(fileOutputAbsolutePath)) {
            fileContent = FileUtil.readUtf8String(fileOutputAbsolutePath);
        } else {
            fileContent = FileUtil.readUtf8String(fileInputAbsolutePath);
        }
        String replacement = String.format("${%s}", modelInfo.getFieldName());
        String newFileContent = StrUtil.replace(fileContent, searchStr, replacement);

        // 文件配置信息
        Meta.FileConfig.FileInfo fileInfo = new Meta.FileConfig.FileInfo();
        fileInfo.setInputPath(fileInputPath);
        fileInfo.setOutputPath(fileOutputPath);
        fileInfo.setType(FileTypeEnum.FILE.getValue());

        if(newFileContent.equals(fileContent)){
            // 输入路径 = 输出路径
            fileInfo.setOutputPath(fileInputPath);
            fileInfo.setGenerateType(FileGenerateTypeEnum.STATIC.getValue());
        } else {
            fileInfo.setGenerateType(FileGenerateTypeEnum.DYNAMIC.getValue());
            // 输出模板文件
            FileUtil.writeUtf8String(newFileContent, fileOutputAbsolutePath);
        }
        return fileInfo;
    }


}
