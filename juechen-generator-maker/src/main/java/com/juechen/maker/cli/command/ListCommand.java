package com.juechen.maker.cli.command;

import cn.hutool.core.io.FileUtil;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.List;

/**
 * @author Juechen
 * @version : ListCommand.java
 * @describe 相当于遥控器的某个按钮
 */
@Command(name = "list", description = "查看文件列表", mixinStandardHelpOptions = true)
public class ListCommand implements Runnable {

    public void run() {
        String projectPath = System.getProperty("user.dir");
        File parentFile = new File(projectPath).getParentFile();
        // 输入路径
        String inputPath = new File(parentFile, "juechen-generator-demo-projects/acm-template").getAbsolutePath();
        List<File> files = FileUtil.loopFiles(inputPath);
        for (File file : files) {
            // 跳过输出包含 .DS_Store 的文件 （只针对MacOS）
            if (!file.getName().equals(".DS_Store")) {
                System.out.println(file);
            }
        }
    }

}

