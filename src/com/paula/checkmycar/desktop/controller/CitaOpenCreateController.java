package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.paula.checkmycar.desktop.main.CheckmycarWindow;
import com.paula.checkmycar.desktop.views.CitaCreateView;

public class CitaOpenCreateController extends AbstractAction {

    public CitaOpenCreateController() {
        super("Nueva cita");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CitaCreateView view = new CitaCreateView();
        CheckmycarWindow.getInstance().addView(view.getName(), view);
    }
}