package com.juechen.maker;

import com.juechen.maker.generator.main.GenerateTemplate;
import com.juechen.maker.generator.main.ZipGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @author Juechen
 * @version : GlobalApplication.java
 */
public class GlobalApplication {

    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
//        GenerateTemplate mainGenerator = new MainGenerator();
        GenerateTemplate zipGenerator = new ZipGenerator();
        zipGenerator.doGenerate();
    }
}
