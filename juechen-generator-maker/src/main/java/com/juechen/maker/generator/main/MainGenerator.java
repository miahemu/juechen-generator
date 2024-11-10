package com.juechen.maker.generator.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.juechen.maker.generator.file.DynamicFileGenerator;
import com.juechen.maker.meta.Meta;
import com.juechen.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @author Juechen
 * @version : MainGenerator.java
 */
public class MainGenerator extends GenerateTemplate{

    // 这里子类重写了父类的buildDist方法，所以主方法直接在执行doGenerate()时，会走下面这个buildDist()这个方法
    @Override
    protected void buildDist(String outputPath, String sourceCopyDestPath, String shellOutputFilePath, String jarPath) {
        System.out.println("不用给我生成精简代码模版了~~~");
    }

    public static void main(String[] args) throws IOException, TemplateException, InterruptedException {
        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.doGenerate();

    }

}

