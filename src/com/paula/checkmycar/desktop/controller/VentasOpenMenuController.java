package com.paula.checkmycar.desktop.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class VentasOpenMenuController extends AbstractAction {

	private Component parent;

	public VentasOpenMenuController(Component parent) {

		super("Ventas");

		this.parent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JPopupMenu menu = new JPopupMenu();

		JMenuItem nuevaVentaItem = new JMenuItem("Nueva venta");

		nuevaVentaItem.addActionListener(new VentaOpenCreateController());

		menu.add(nuevaVentaItem);

	
		menu.show(parent, 0, parent.getHeight());
	}
}