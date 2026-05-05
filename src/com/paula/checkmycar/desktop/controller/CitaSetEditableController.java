package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import com.paula.checkmycar.desktop.views.CitaCreateView;

public class CitaSetEditableController extends Controller {

    public CitaSetEditableController(CitaCreateView view) {
        super(view, "Editar", null);
    }

    @Override
    public void doAction() {
        CitaCreateView view = (CitaCreateView) getView();
        view.setEditable(true);
        view.setAgreeController(new CitaUpdateController(view));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}