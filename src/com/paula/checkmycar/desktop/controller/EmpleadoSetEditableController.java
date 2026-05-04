package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import com.paula.checkmycar.desktop.views.EmpleadoCreateView;

public class EmpleadoSetEditableController extends Controller {

    public EmpleadoSetEditableController(EmpleadoCreateView view) {
        super(view, "Editar", null);
    }

    @Override
    public void doAction() {
        EmpleadoCreateView view = (EmpleadoCreateView) getView();
        view.setEditable(true);
        view.setAgreeController(new EmpleadoUpdateController(view));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}