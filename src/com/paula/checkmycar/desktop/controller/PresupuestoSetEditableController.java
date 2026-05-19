package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import com.paula.checkmycar.desktop.views.PresupuestoCreateView;

public class PresupuestoSetEditableController extends Controller {

	private PresupuestoCreateView view;

	public PresupuestoSetEditableController(PresupuestoCreateView view) {

		super(view, "Editar", null);

		this.view = view;
	}

	@Override
	public void doAction() {

		view.setEditable(true);

		view.setAgreeController(new PresupuestoUpdateController(view));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		doAction();
	}
}