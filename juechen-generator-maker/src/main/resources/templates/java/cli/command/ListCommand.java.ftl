package ${basePackage}.cli.command;

import cn.hutool.core.io.FileUtil;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.List;

/**
 * @author ${author}
 * @version ${version}
 */
@Command(name = "list", description = "查看文件列表", mixinStandardHelpOptions = true)
public class ListCommand implements Runnable {

    public void run() {
        String inputPath = "${fileConfig.inputRootPath}";
        List<File> files = FileUtil.loopFiles(inputPath);
        for (File file : files) {
            // 跳过输出包含 .DS_Store 的文件 （只针对MacOS）
            if (!file.getName().equals(".DS_Store")) {
                System.out.println(file);
            }
        }
    }

}

