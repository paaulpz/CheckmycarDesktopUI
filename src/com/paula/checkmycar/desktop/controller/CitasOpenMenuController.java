package com.paula.checkmycar.desktop.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class CitasOpenMenuController extends AbstractAction {

    private Component parent;

    public CitasOpenMenuController(Component parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPopupMenu menu = new JPopupMenu();


        JMenuItem nuevaItem = new JMenuItem("Nueva ");
        nuevaItem.addActionListener(new CitaOpenCreateController());

        menu.add(nuevaItem);

        menu.show(parent, 0, parent.getHeight());
    }
}