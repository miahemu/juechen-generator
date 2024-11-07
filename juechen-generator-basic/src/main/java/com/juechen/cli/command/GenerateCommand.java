package com.juechen.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.juechen.generator.MainGenerator;
import com.juechen.model.MainTemplateConfig;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

import static com.juechen.generator.MainGenerator.setDefaultOutputText;

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
    private MainTemplateConfig.OperationType operationType; // 设置默认值为SUM


    @Override
    public Integer call() throws Exception {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        BeanUtil.copyProperties(this, mainTemplateConfig);
        // 根据操作类型设置 outputText 默认值
        setDefaultOutputText(mainTemplateConfig);
        System.out.println("配置信息：" + mainTemplateConfig);
        MainGenerator.doGenerator(mainTemplateConfig);
        return 0;
    }
}
