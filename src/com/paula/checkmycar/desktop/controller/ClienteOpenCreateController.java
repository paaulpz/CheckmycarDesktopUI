package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.paula.checkmycar.desktop.main.CheckmycarWindow;
import com.paula.checkmycar.desktop.views.ClienteCreateView;

public class ClienteOpenCreateController extends AbstractAction {

    public ClienteOpenCreateController() {
        super("Añadir cliente");
    }

    public void doAction() {
        ClienteCreateView view = new ClienteCreateView();
        ClienteCreateController controller = new ClienteCreateController(view); 
        view.setAgreeController(controller); 
        CheckmycarWindow.getInstance().addView(view.getName(), view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}