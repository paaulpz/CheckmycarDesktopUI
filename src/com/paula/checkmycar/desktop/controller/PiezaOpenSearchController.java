
package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.paula.checkmycar.desktop.main.CheckmycarWindow;
import com.paula.checkmycar.desktop.views.PiezaSearchView;

public class PiezaOpenSearchController extends AbstractAction {

    public PiezaOpenSearchController() {
        super("Buscar piezas");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PiezaSearchView view = new PiezaSearchView();
        CheckmycarWindow.getInstance().addView(view.getName(), view);
    }
}