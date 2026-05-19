package com.paula.checkmycar.desktop.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class OrdenTrabajoOpenMenuController extends AbstractAction {

	private Component parent;

	public OrdenTrabajoOpenMenuController(Component parent) {

		super("Órdenes trabajo");

		this.parent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JPopupMenu menu = new JPopupMenu();

		JMenuItem nuevaOrdenItem = new JMenuItem("Nueva ");

		nuevaOrdenItem.addActionListener(new OrdenTrabajoOpenCreateController());

		menu.add(nuevaOrdenItem);

		JMenuItem buscarOrdenItem = new JMenuItem("Buscar ");

		buscarOrdenItem.addActionListener(new OrdenTrabajoOpenSearchController());

		menu.add(buscarOrdenItem);

		menu.show(parent, 0, parent.getHeight());
	}
}