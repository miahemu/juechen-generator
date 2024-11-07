package com.juechen.maker.cli.command;

import com.juechen.maker.model.DataModel;
import picocli.CommandLine;
import picocli.CommandLine.ITypeConverter;

/**
 * @author Juechen
 * @version : OperationTypeConverter.java
 * @describe 使用 ITypeConverter 实现一个自定义转换器，将输入的字符串转换为 OperationType 枚举
 */
public class OperationTypeConverter implements ITypeConverter<DataModel.OperationType> {
    @Override
    public DataModel.OperationType convert(String value) {
        try {
            // 将用户输入的字符串转换为大写并匹配 Enum
            return DataModel.OperationType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CommandLine.TypeConversionException("Invalid operation type. Choose from: SUM, PRODUCT, MAX, MIN.");
        }
    }
}
