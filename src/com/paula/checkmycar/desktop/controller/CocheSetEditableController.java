package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import com.paula.checkmycar.desktop.views.CocheCreateView;

public class CocheSetEditableController extends Controller {

    public CocheSetEditableController(CocheCreateView view) {
        super(view, "Editar", null);
    }

    @Override
    public void doAction() {
        CocheCreateView view = (CocheCreateView) getView();
        view.setEditable(true);
        view.setAgreeController(new CocheUpdateController(view));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}