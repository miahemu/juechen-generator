package com.juechen.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Juechen
 * @version : DynamicGenerator.java
 */
public class DynamicGenerator {

    public static void doGenerator(String inputPath, String outputPath,Object model) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        File inputFile = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(inputFile);
        configuration.setDefaultEncoding("UTF-8");

        String templateFile = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateFile);

        FileWriter out = new FileWriter(outputPath);
        template.process(model,out);

        out.close();
    }
}
