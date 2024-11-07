package com.juechen;

import com.juechen.cli.CommandExecutor;

/**
 * @author Juechen
 * @version : GlobalApplication.java
 * @describe 在命令模式中，GlobalApplication 类似于客户端（Client），它负责触发命令执行。
 *           客户端通过调用 CommandExecutor（相当于遥控器）来调用和执行具体的命令，
 *           而每一个子命令类（如 GenerateCommand）都属于具体命令（相当于遥控器的按钮）的实现。
 *           这些子命令类实现了 Runnable 或 Callable 接口，负责执行特定功能的逻辑。
 *           MainGenerator 类似于命令的接收者（电视），真正执行具体逻辑。
 */
public class GlobalApplication {

    public static void main(String[] args) {
//        args = new String[]{"generate", "-l", "-a", "-o"};
//        args = new String[]{"config"};
//        args = new String[]{"list"};

        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }
}



