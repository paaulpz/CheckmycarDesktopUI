package com.paula.checkmycar.desktop.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PresupuestoOpenMenuController extends AbstractAction {

	private Component parent = null;
	public PresupuestoOpenMenuController( Component parent) {
		this.parent = parent;		
	}
	
	private void doAction() {
		JPopupMenu userPopup = new JPopupMenu();
		JMenuItem buscarPresupuestoItem = new JMenuItem("Buscar");
		buscarPresupuestoItem.addActionListener(new PresupuestoOpenSearchController());
		
		JMenuItem nuevoPresupuestoItem = new JMenuItem("Nuevo");
		nuevoPresupuestoItem.addActionListener(new PresupuestoOpenCreateController());
		
		userPopup.add(buscarPresupuestoItem);
		userPopup.add(nuevoPresupuestoItem);
		
		userPopup.show(parent, 0, parent.getHeight());
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();

	}

}
