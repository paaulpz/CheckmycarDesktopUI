package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.paula.checkmycar.desktop.main.CheckmycarWindow;
import com.paula.checkmycar.desktop.views.HomeView;


public class OpenHomeViewController extends AbstractAction  {

	public OpenHomeViewController() {
	}

	public void doAction() {
		HomeView homeView = new HomeView();
		CheckmycarWindow.getInstance().addView(homeView.getName(), homeView);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}

}
