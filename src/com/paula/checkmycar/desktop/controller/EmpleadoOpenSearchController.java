package com.paula.checkmycar.desktop.controller;

import javax.swing.AbstractAction;

import com.paula.checkmycar.desktop.main.CheckmycarWindow;
import com.paula.checkmycar.desktop.views.EmpleadoSearchView;

public class EmpleadoOpenSearchController extends AbstractAction {

	public EmpleadoOpenSearchController() {	
	}

	private void doAction() {
	EmpleadoSearchView empleadoSearchView = new EmpleadoSearchView();	
	CheckmycarWindow.getInstance().addView(empleadoSearchView.getName(), empleadoSearchView);	
	}
	
	
	
	
	
	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
		doAction();

	}

}
