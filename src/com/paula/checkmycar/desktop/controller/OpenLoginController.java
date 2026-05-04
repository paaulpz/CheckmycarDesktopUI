package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.paula.checkmycar.desktop.main.LoginWindow;

public class OpenLoginController extends AbstractAction {

    public OpenLoginController() {
        super("Login");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new LoginWindow();
    }
}