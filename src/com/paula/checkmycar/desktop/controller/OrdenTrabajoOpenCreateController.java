package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import com.paula.checkmycar.desktop.main.CheckmycarWindow;
import com.paula.checkmycar.desktop.views.OrdenTrabajoCreateView;

public class OrdenTrabajoOpenCreateController extends Controller {

	public OrdenTrabajoOpenCreateController() {

		super(null, "Nueva orden", null);
	}

	@Override
	public void doAction() {

		OrdenTrabajoCreateView view = new OrdenTrabajoCreateView();

		view.setAgreeController(new OrdenTrabajoCreateController(view));

		CheckmycarWindow.getInstance().addView(view.getName(), view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		doAction();
	}
}