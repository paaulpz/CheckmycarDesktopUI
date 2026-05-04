package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.paula.checkmycar.desktop.main.CheckmycarWindow;
import com.paula.checkmycar.desktop.views.CocheSearchView;

public class CocheOpenSearchController extends AbstractAction  {
	
	public CocheOpenSearchController() {	
	}

	private void doAction() {
		CocheSearchView cocheSearchView = new CocheSearchView();
		CheckmycarWindow.getInstance().addView(cocheSearchView.getName(), cocheSearchView);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}

}
