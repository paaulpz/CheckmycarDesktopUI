package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.paula.checkmycar.desktop.main.CheckmycarWindow;
import com.paula.checkmycar.desktop.views.ClienteSearchView;

public class ClienteOpenSearchController extends AbstractAction {

	public ClienteOpenSearchController() {	
	}

	private void doAction() {
		ClienteSearchView userSearchView = new ClienteSearchView();
		CheckmycarWindow.getInstance().addView(userSearchView.getName(), userSearchView);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}

}
