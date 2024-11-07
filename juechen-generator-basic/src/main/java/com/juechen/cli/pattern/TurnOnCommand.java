package com.juechen.cli.pattern;

/**
 * @author Juechen
 * @version : TurnOnCommand.java
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

