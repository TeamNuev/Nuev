package com.storycraft.example;

import ga.nullcraft.global.mod.NullMod;

import java.util.UUID;

public class ExampleMod extends NullMod {
    public ExampleMod() {
        super("ExampleMod", UUID.fromString("DDC52FC9-3C1E-065A-F948-9FA3D9957416"), "example", "0.0.1");
    }

    @Override
    public String getDescription() {
        return "This is exampleMod";
    }

    public static void main(String[] args){
        System.out.println("This should be launched from Nullcraft");
    }
}
