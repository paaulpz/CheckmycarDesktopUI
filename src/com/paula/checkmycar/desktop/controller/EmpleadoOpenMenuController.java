package com.paula.checkmycar.desktop.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class EmpleadoOpenMenuController extends AbstractAction {

	private Component parent = null;
	
	public EmpleadoOpenMenuController( Component parent) {
		this.parent = parent;		
	}
	
	private void doAction() {
		
		JPopupMenu userPopup = new JPopupMenu();
		
		JMenuItem buscarEmpleadoItem = new JMenuItem("Buscar");
		buscarEmpleadoItem.addActionListener(new EmpleadoOpenSearchController());
		
		
		JMenuItem nuevoEmpleadoItem = new JMenuItem("Nuevo");
		nuevoEmpleadoItem.addActionListener(new EmpleadoOpenCreateController());
		
		userPopup.add(buscarEmpleadoItem);
		userPopup.add(nuevoEmpleadoItem);
		
		userPopup.show(parent, 0, parent.getHeight());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();

	}

}
