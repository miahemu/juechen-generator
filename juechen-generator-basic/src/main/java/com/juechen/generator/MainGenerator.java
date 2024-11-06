package com.juechen.generator;

import com.juechen.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author Juechen
 * @version : MainGenerator.java
 * @deseribe : 核心生成器
 */
public class MainGenerator {

    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig config = new MainTemplateConfig();
        config.setLoop(true);
        config.setAuthor("Juechen");
        config.setOperationType(MainTemplateConfig.OperationType.SUM);
//        config.setOperationType(MainTemplateConfig.OperationType.PRODUCT);
//        config.setOperationType(MainTemplateConfig.OperationType.MAX);
//        config.setOperationType(MainTemplateConfig.OperationType.MIN);

        // 根据操作类型自动设置默认输出文本
        setDefaultOutputText(config);

        doGenerator(config);
    }

    public static void doGenerator(Object model) throws IOException, TemplateException {
        // 获取项目根目录路径
        String projectPath = System.getProperty("user.dir");
        String parentPath = Paths.get(projectPath, "juechen-generator-basic").toString();
        String inputPath = Paths.get(projectPath, "juechen-generator-demo-projects", "acm-template").toString();
        String outputPath = parentPath;

        // 递归复制静态文件
        StaticGenerator.copyFilesByRecursive(inputPath, outputPath);

        // 动态模板路径设置
        String inputDynamicPath = Paths.get(parentPath, "src", "main", "resources", "templates", "MainTemplate.java.ftl").toString();
        String outputDynamicPath = Paths.get(parentPath, "MainTemplate.java").toString();

        // 执行模板生成
        DynamicGenerator.doGenerator(inputDynamicPath, outputDynamicPath, model);
    }

    /**
     * 根据操作类型自动设置输出文本的默认值
     * @param config 模板配置
     */
    private static void setDefaultOutputText(MainTemplateConfig config) {
        if (config.getOutputText() == null || config.getOutputText().isEmpty()) {
            switch (config.getOperationType()) {
                case SUM:
                    config.setOutputText("SUM:");
                    break;
                case PRODUCT:
                    config.setOutputText("PRODUCT:");
                    break;
                case MAX:
                    config.setOutputText("MAX:");
                    break;
                case MIN:
                    config.setOutputText("MIN:");
                    break;
                default:
                    config.setOutputText("RESULT:");
            }
        }
    }

}
