package com.juechen.maker.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.juechen.maker.generator.file.FileGenerator;
import com.juechen.maker.model.DataModel;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

/**
 * @author Juechen
 * @version : GenerateCommand.java
 * @describe 相当于遥控器的某个按钮
 */
@Command(name = "generate", description = "生成代码", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {

    @Option(
            names = {"-l", "--loop"},
            description = "是否循环",
            arity = "0..1",
            interactive = true,
            echo = true)
    private boolean loop;

    @Option(names = {"-a", "--author"},
            description = "作者",
            arity = "0..1",
            interactive = true,
            echo = true)
    private String author;

    @Option(
            names = {"-o", "--operation"},
            description = "运算类型：${COMPLETION-CANDIDATES}", // 允许自动补全并提示所有枚举值
            arity = "0..1",
            converter = OperationTypeConverter.class, // 指定转换器
            interactive = true,
            echo = true
    )
    private DataModel.OperationType operationType; // 设置默认值为SUM


    @Override
    public Integer call() throws Exception {
        DataModel dataModel = new DataModel();
        BeanUtil.copyProperties(this, dataModel);
        // 根据操作类型设置 outputText 默认值
        FileGenerator.setDefaultOutputText(dataModel);
        System.out.println("配置信息：" + dataModel);
        FileGenerator.doGenerator(dataModel);
        return 0;
    }
}
