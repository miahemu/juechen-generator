package ${basePackage};

import ${basePackage}.cli.CommandExecutor;

/**
 * @author ${author}
 * @version : ${version}
 */
public class GlobalApplication {

    public static void main(String[] args) {
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }
}



