package com.juechen.maker.generator.file;

import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author Juechen
 * @version : FileGenerator.java
 * @deseribe : 核心生成器
 */
public class FileGenerator {

    public static void doGenerator(Object model) throws IOException, TemplateException {
        // 获取项目根目录路径
        String projectPath = System.getProperty("user.dir");
        String parentPath = new File(projectPath).getParentFile().getAbsolutePath();
        String inputPath = Paths.get(parentPath, "juechen-generator-demo-projects", "acm-template").toString();
        String outputPath = projectPath;

        // 递归复制静态文件
        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);

        // 动态模板路径设置
        String inputDynamicPath = Paths.get(projectPath, "src", "main", "resources", "templates", "MainTemplate.java.ftl").toString();
        String outputDynamicPath = Paths.get(projectPath, "acm-template", "src", "com", "juechen", "acm", "MainTemplate.java").toString();

        // 执行模板生成
        DynamicFileGenerator.doGenerator(inputDynamicPath, outputDynamicPath, model);
    }

}
