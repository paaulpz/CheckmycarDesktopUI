package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import com.paula.checkmycar.desktop.views.OrdenTrabajoCreateView;

public class OrdenTrabajoSetEditableController extends Controller {

	private OrdenTrabajoCreateView view;

	public OrdenTrabajoSetEditableController(OrdenTrabajoCreateView view) {

		super(view, "Editar", null);

		this.view = view;
	}

	@Override
	public void doAction() {

		view.setEditable(true);

		view.setAgreeController(new OrdenTrabajoUpdateController(view));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		doAction();
	}
}