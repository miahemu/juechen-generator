package com.juechen.maker.generator.main;

/**
 * @author Juechen
 * @version : ZipGenerator.java
 */
public class ZipGenerator extends GenerateTemplate{

    @Override
    protected String buildDist(String outputPath, String sourceCopyDestPath, String shellOutputFilePath, String jarPath) {
        String distPath = super.buildDist(outputPath, sourceCopyDestPath, shellOutputFilePath, jarPath);
        return super.buildZip(distPath);
    }

}

