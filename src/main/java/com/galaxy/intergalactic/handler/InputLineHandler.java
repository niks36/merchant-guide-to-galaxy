package com.galaxy.intergalactic.handler;

public interface InputLineHandler {
    boolean canHandle(String line);

    default void handle(String line) {
        System.out.println("I have no idea what you are talking about");
    }
}
