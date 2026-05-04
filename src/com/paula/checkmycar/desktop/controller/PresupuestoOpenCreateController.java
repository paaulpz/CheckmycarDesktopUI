package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.paula.checkmycar.desktop.main.CheckmycarWindow;
import com.paula.checkmycar.desktop.views.PresupuestoCreateView;

public class PresupuestoOpenCreateController extends AbstractAction {

	public PresupuestoOpenCreateController() {
		super("Nuevo presupuesto");
	}
	
	public void doAction() {
		PresupuestoCreateView view = new PresupuestoCreateView();
		CheckmycarWindow.getInstance().addView(view.getName(), view);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    doAction();
	}

}
