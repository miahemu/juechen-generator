package com.juechen.cli.pattern;

/**
 * @author Juechen
 * @version : TurnOffCommand.java
 * @describe 相当于遥控器的某个按钮
 */
public class TurnOffCommand implements Command {
    private Device device;

    public TurnOffCommand(Device device) {
        this.device = device;
    }

    public void execute() {
        device.turnOff();
    }
}

