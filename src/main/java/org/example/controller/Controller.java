package org.example.controller;

import org.example.dto.Member;

public class Controller {
    protected static Member loginedMember = null;

    public static boolean isLogined() {
        return loginedMember != null;
    }

    public void doAction(String cmd, String actionCommand) {}
}
