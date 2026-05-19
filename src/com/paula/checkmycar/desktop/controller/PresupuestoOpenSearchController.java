package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.paula.checkmycar.desktop.main.CheckmycarWindow;
import com.paula.checkmycar.desktop.views.PresupuestoSearchView;

public class PresupuestoOpenSearchController extends AbstractAction {

	
	public PresupuestoOpenSearchController() {	
	}
	
	private void doAction() {
		PresupuestoSearchView presupuestoSearchView = new PresupuestoSearchView();
		CheckmycarWindow.getInstance().addView(presupuestoSearchView.getName(), presupuestoSearchView);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}

}
