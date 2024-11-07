package com.juechen.cli.pattern;

/**
 * @author Juechen
 * @version : TurnOnCommand.java
 * @describe 相当于遥控器的某个按钮
 */
public class TurnOnCommand implements Command {
    private Device device;

    public TurnOnCommand(Device device) {
        this.device = device;
    }

    public void execute() {
        device.turnOn();
    }
}

