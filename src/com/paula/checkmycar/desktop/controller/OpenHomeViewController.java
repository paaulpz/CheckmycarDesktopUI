package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.paula.checkmycar.desktop.main.CheckmycarWindow;
import com.paula.checkmycar.desktop.views.HomeView;

public class OpenHomeViewController extends AbstractAction {

    public OpenHomeViewController() {
        super("Inicio");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        HomeView view = new HomeView();
        CheckmycarWindow.getInstance().addView("Inicio", view);
    }
}