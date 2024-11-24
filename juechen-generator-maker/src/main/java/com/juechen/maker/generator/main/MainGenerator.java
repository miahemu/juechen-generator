package com.juechen.maker.generator.main;

/**
 * @author Juechen
 * @version : MainGenerator.java
 */
public class MainGenerator extends GenerateTemplate{

    // 这里子类重写了父类的buildDist方法，所以主方法直接在执行doGenerate()时，会走下面这个buildDist()这个方法
    @Override
    protected String buildDist(String outputPath, String sourceCopyDestPath, String shellOutputFilePath, String jarPath) {
        System.out.println("不用给我生成精简代码模版了~~~");
        return "";
    }

}

