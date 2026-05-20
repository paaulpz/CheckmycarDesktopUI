package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.paula.checkmycar.desktop.main.CheckmycarWindow;
import com.paula.checkmycar.desktop.views.VentaSearchView;

public class VentaOpenSearchController extends AbstractAction {

	public VentaOpenSearchController() {
	}

	private void doAction() {
		VentaSearchView ventaSearchView = new VentaSearchView();
		CheckmycarWindow.getInstance().addView(ventaSearchView.getName(), ventaSearchView);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}

}