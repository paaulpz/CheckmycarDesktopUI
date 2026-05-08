// PiezaOpenMenuController.java
package com.paula.checkmycar.desktop.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PiezaOpenMenuController extends AbstractAction {

    private Component parent;

    public PiezaOpenMenuController(Component parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem buscarItem = new JMenuItem("Buscar piezas");
        buscarItem.addActionListener(new PiezaOpenSearchController());

        menu.add(buscarItem);
        menu.show(parent, 0, parent.getHeight());
    }
}
