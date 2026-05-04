package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.paula.checkmycar.desktop.main.CheckmycarWindow;
import com.paula.checkmycar.desktop.views.CocheCreateView;

public class CocheOpenCreateController extends AbstractAction {

    public CocheOpenCreateController() {
        super("Añadir coche");
    }

    public void doAction() {
        CocheCreateView view = new CocheCreateView();
        CheckmycarWindow.getInstance().addView(view.getName(), view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}
