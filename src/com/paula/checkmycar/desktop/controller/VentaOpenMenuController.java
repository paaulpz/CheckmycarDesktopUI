package com.paula.checkmycar.desktop.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class VentaOpenMenuController extends AbstractAction {

	private Component parent = null;

	public VentaOpenMenuController(Component parent) {

		this.parent = parent;
	}

	private void doAction() {

		JPopupMenu ventaPopup = new JPopupMenu();

		JMenuItem buscarVentaItem = new JMenuItem("Buscar");

		buscarVentaItem.addActionListener(new VentaOpenSearchController());

		JMenuItem nuevaVentaItem = new JMenuItem("Nuevo");

		nuevaVentaItem.addActionListener(new VentaOpenCreateController());

		ventaPopup.add(buscarVentaItem);

		ventaPopup.add(nuevaVentaItem);

		ventaPopup.show(parent, 0, parent.getHeight());
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		doAction();
	}
}