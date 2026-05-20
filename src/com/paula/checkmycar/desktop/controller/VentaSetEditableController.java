package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import com.paula.checkmycar.desktop.views.VentaCreateView;

public class VentaSetEditableController extends Controller {

	private VentaCreateView view;

	public VentaSetEditableController(VentaCreateView view) {

		super(view, "Editar");

		this.view = view;
	}

	@Override
	public void doAction() {

		view.setEditable(true);

		view.setAgreeController(new VentaUpdateController(view));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		doAction();
	}
}