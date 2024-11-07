package com.juechen.cli.pattern;

/**
 * @author Juechen
 * @version Device.java
 * @describe 相当于被遥控的设备
 */
public class Device {
    private String name;

    public Device(String name) {
        this.name = name;
    }

    public void turnOn() {
        System.out.println(name + " 设备打开");
    }

    public void turnOff() {
        System.out.println(name + " 设备关闭");
    }
}

