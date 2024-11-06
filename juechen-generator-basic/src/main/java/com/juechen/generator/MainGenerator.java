package com.juechen.generator;

import com.juechen.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @author Juechen
 * @version : MainGenerator.java
 * @deseribe : 核心生成器
 */
public class MainGenerator {

    public static void doGenerator(Object model) throws IOException, TemplateException, TemplateException {
        String projectPath = System.getProperty("user.dir");
        String parentPath = projectPath + File.separator + "juechen-generator-basic" + File.separator;
        String inputPath = projectPath + File.separator + "juechen-generator-demo-projects/acm-template";
        String outputPath = projectPath + File.separator + "juechen-generator-basic";
        StaticGenerator.copyFilesByRecursive(inputPath, outputPath);

        String inputDynamicPath = parentPath + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicPath = parentPath + "MainTemplate.java";
        DynamicGenerator.doGenerator(inputDynamicPath, outputDynamicPath, model);
    }

    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig config = new MainTemplateConfig();
        config.setLoop(true);
        config.setAuthor("Juechen");
        config.setOutputText("求和结果：");
        doGenerator(config);
    }
}
