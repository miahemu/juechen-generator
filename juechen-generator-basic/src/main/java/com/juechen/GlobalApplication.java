package com.juechen;

import com.juechen.cli.CommandExecutor;

/**
 * @author Juechen
 * @version : Main.java
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



