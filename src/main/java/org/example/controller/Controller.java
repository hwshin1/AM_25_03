package org.example.controller;

import org.example.dto.Member;

public class Controller {
    protected Member loginedMember = null;

    protected boolean isLogined() {
        return loginedMember != null;
    }

    public void doAction(String cmd, String actionCommand) {}
}
