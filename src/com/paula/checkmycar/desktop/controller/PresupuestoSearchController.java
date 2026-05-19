package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.paula.checkmycar.desktop.views.PresupuestoSearchView;

public class PresupuestoSearchController extends Controller {

	private PresupuestoSearchView view;

	public PresupuestoSearchController(PresupuestoSearchView view) {

		super(view, "Buscar");

		this.view = view;
	}

	@Override
	public void doAction() {

		try {

			view.buscar();

		} catch (Exception e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(view, "Error buscando presupuestos", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		doAction();
	}
}