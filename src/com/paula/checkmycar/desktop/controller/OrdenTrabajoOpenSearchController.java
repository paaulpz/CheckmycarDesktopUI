package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import com.paula.checkmycar.desktop.main.CheckmycarWindow;
import com.paula.checkmycar.desktop.views.OrdenTrabajoSearchView;

public class OrdenTrabajoOpenSearchController
		extends Controller {

	public OrdenTrabajoOpenSearchController() {

		super(null, "Buscar órdenes", null);
	}

	@Override
	public void doAction() {

		OrdenTrabajoSearchView view =
				new OrdenTrabajoSearchView();

		CheckmycarWindow.getInstance()
				.addView(view.getName(), view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		doAction();
	}
}