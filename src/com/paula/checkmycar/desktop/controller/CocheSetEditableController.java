package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import com.paula.checkmycar.desktop.views.CocheCreateView;

public class CocheSetEditableController extends Controller {

	private CocheCreateView view = null;

	public CocheSetEditableController(CocheCreateView view) {

		super(view, "Editar", null);

		this.view = view;
	}

	public void doAction() {

		CocheCreateView view = (CocheCreateView) getView();

		view.setEditable(true);

		view.setAgreeController(new CocheUpdateController(view));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		doAction();
	}
}