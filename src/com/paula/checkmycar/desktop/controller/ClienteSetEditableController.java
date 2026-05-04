package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import com.paula.checkmycar.desktop.views.ClienteCreateView;

public class ClienteSetEditableController  extends Controller {
	
	private ClienteCreateView view = null;
	
	public ClienteSetEditableController(ClienteCreateView view) {
		super(view, "Editar", null ); 
	}
	
	public void doAction() {
		ClienteCreateView view = (ClienteCreateView) getView();
		view.setEditable(true);
		view.setAgreeController(new ClienteUpdateController(view));
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		doAction();			
	}
	

}
