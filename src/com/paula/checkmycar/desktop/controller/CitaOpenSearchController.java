package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.paula.checkmycar.desktop.main.CheckmycarWindow;
import com.paula.checkmycar.desktop.views.CitaSearchView;

public class CitaOpenSearchController extends AbstractAction {

    public CitaOpenSearchController() {

        super("Buscar citas");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        CitaSearchView view =
                new CitaSearchView();

        CheckmycarWindow.getInstance()
                .addView(view.getName(), view);
    }
}