package com.paula.checkmycar.desktop.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class VentaOpenMenuController extends AbstractAction {

    private Component parent;

    public VentaOpenMenuController(Component parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem buscarItem = new JMenuItem("Buscar ");
        buscarItem.addActionListener(new VentaOpenSearchController());

        JMenuItem nuevaItem = new JMenuItem("Nueva ");
        nuevaItem.addActionListener(new VentaOpenCreateController());

        menu.add(buscarItem);
        menu.add(nuevaItem);
        menu.show(parent, 0, parent.getHeight());
    }
}