package ${basePackage}.cli.command;

import cn.hutool.core.bean.BeanUtil;
import ${basePackage}.generator.MainGenerator;
import ${basePackage}.model.DataModel;
import lombok.Data;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

<#macro generateOption indent modelInfo>
${indent}@Option(names = {<#if modelInfo.abbr??>"-${modelInfo.abbr}", </#if>"--${modelInfo.fieldName}"}, arity = "0..1", <#if modelInfo.description??>description = "${modelInfo.description}", </#if>interactive = true, echo = true)
${indent}private ${modelInfo.type} ${modelInfo.fieldName}<#if modelInfo.defaultValue??> = ${modelInfo.defaultValue?c}</#if>;
</#macro>

/**
 * @author ${author}
 * @version : ${version}
 */
@Command(name = "generate", description = "生成代码", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {
    <#list modelConfig.models as modelInfo>

    <#if modelInfo.groupKey??>
    /**
     * ${modelInfo.groupName}
     */
    static DataModel.${modelInfo.type} ${modelInfo.groupKey} = new DataModel.${modelInfo.type}();

    @Command(name = "${modelInfo.groupName}", description = "${modelInfo.description}")
    @Data
    public static class ${modelInfo.type}Command implements Runnable {

        <#list modelInfo.models as subModelInfo>
        /**
         * ${subModelInfo.fieldName}
         */
        <@generateOption indent="        " modelInfo=subModelInfo/>
        </#list>

        @Override
        public void run() {
            <#list modelInfo.models as subModelInfo>
            ${modelInfo.groupKey}.${subModelInfo.fieldName} = ${subModelInfo.fieldName};
            </#list>
        }
    }
    <#else>

    <@generateOption indent="    " modelInfo=modelInfo/>
    </#if>
    </#list>

    public Integer call() throws Exception {
        <#list modelConfig.models as modelInfo>
        <#if modelInfo.groupKey??>
        <#if modelInfo.condition??>
        if(${modelInfo.condition}){
            System.out.println("输入${modelInfo.groupName}配置:");
            CommandLine commandLine = new CommandLine(${modelInfo.type}Command.class);
            commandLine.execute(${modelInfo.allArgsStr});
        }
        <#else>
        System.out.println("输入 ${modelInfo.groupName}配置:");
        CommandLine commandLine = new CommandLine(${modelInfo.type}Command.class);
        commandLine.execute(${modelInfo.allArgsStr});
        </#if>
        </#if>
        </#list>
        DataModel dataModel = new DataModel();
        BeanUtil.copyProperties(this, dataModel);
        <#--这里要考虑如果有多个组的情况-->
        <#list modelConfig.models as modelInfo>
        <#if modelInfo.groupKey??>
        dataModel.${modelInfo.groupKey} = ${modelInfo.groupKey};
        </#if>
        </#list>
        MainGenerator.doGenerate(dataModel);
        System.out.println("代码生成完毕！");
        return 0;
    }
}