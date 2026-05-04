package com.paula.checkmycar.desktop.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class CocheOpenMenuController extends AbstractAction {

	private Component parent = null;
	
	public CocheOpenMenuController( Component parent) {
		this.parent = parent;		
	}
	
	private void doAction() {
		
JPopupMenu userPopup = new JPopupMenu();
		
		JMenuItem buscarCocheItem = new JMenuItem("Buscar");
		buscarCocheItem.addActionListener(new CocheOpenSearchController());
		
		JMenuItem nuevoCocheItem = new JMenuItem("Nuevo");
		nuevoCocheItem.addActionListener(new CocheOpenCreateController());
		
		
		userPopup.add(buscarCocheItem);
		userPopup.add(nuevoCocheItem);
		
		userPopup.show(parent, 0, parent.getHeight());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}

		
	}

