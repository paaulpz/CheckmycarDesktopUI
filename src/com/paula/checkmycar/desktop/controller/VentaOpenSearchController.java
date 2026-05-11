
package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.paula.checkmycar.desktop.main.CheckmycarWindow;
import com.paula.checkmycar.desktop.views.VentaSearchView;

public class VentaOpenSearchController extends AbstractAction {

    public VentaOpenSearchController() {
        super("Buscar ventas");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VentaSearchView view = new VentaSearchView();
        CheckmycarWindow.getInstance().addView(view.getName(), view);
    }
}