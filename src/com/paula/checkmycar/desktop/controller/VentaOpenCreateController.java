package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.paula.checkmycar.desktop.main.CheckmycarWindow;
import com.paula.checkmycar.desktop.views.VentaCreateView;

public class VentaOpenCreateController extends AbstractAction {

	public VentaOpenCreateController() {

		super("Nueva venta");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		VentaCreateView view = new VentaCreateView();

		CheckmycarWindow.getInstance().addView(view.getName(), view);
	}
}