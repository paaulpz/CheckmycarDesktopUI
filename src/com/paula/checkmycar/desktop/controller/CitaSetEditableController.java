package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import com.paula.checkmycar.desktop.views.CitaCreateView;

public class CitaSetEditableController extends Controller {

	private CitaCreateView view = null;

	public CitaSetEditableController(CitaCreateView view) {

		super(view, "Editar", null);
	}

	public void doAction() {

		CitaCreateView view = (CitaCreateView) getView();

		view.setEditable(true);

		view.setAgreeController(new CitaUpdateController(view));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		doAction();
	}
}