package com.paula.checkmycar.desktop.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class ClienteOpenMenuController extends AbstractAction {

	private Component parent = null;
	
	
	public ClienteOpenMenuController( Component parent) {
		this.parent = parent;		
	}	

	private void doAction() {
		
		JPopupMenu userPopup = new JPopupMenu();
		
		JMenuItem buscarClienteItem = new JMenuItem("Buscar");
		buscarClienteItem.addActionListener(new ClienteOpenSearchController());
		
		JMenuItem nuevoClienteItem = new JMenuItem("Nuevo");
		nuevoClienteItem.addActionListener(new ClienteOpenCreateController());
		
		
		userPopup.add(buscarClienteItem);
		userPopup.add(nuevoClienteItem);
		
		userPopup.show(parent, 0, parent.getHeight());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}


}