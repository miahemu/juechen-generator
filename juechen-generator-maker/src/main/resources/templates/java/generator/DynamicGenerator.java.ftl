package ${basePackage}.generator;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author ${author}
 * @version : ${version}
 */
public class DynamicGenerator {

    public static void doGenerator(String inputPath, String outputPath, Object model) throws IOException, TemplateException {

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        // 设置模板加载路径
        File inputFile = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(inputFile);
        configuration.setDefaultEncoding("UTF-8");

        // 获取模板文件名称
        String templateFile = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateFile);

        // 如果文件不存在则创建文件
        if(!FileUtil.exist(outputPath)){
            FileUtil.touch(outputPath);
        }

        // 使用 try-with-resources 自动管理资源
        try (FileWriter out = new FileWriter(outputPath)) {
            template.process(model, out);
        } catch (TemplateException e) {
            throw new TemplateException("模板处理失败: " + e.getMessage(), e, e.getEnvironment());
        } catch (IOException e) {
            throw new IOException("文件操作失败: " + e.getMessage(), e);
        }
    }
}
