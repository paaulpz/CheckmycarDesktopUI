package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.paula.checkmycar.desktop.main.CheckmycarWindow;
import com.paula.checkmycar.desktop.views.EmpleadoCreateView;

public class EmpleadoOpenCreateController extends AbstractAction {

	
	public EmpleadoOpenCreateController() {
		super("Añadir empleado");
	}
	
	public void doAction() {
	    EmpleadoCreateView view = new EmpleadoCreateView();
	    EmpleadoCreateController controller = new EmpleadoCreateController(view); 
	    view.setAgreeController(controller); 
	    CheckmycarWindow.getInstance().addView(view.getName(), view);
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();

	}

}
