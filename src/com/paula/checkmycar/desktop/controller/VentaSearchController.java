package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.paula.checkmycar.desktop.views.VentaSearchView;

public class VentaSearchController extends Controller {

	private VentaSearchView view;

	public VentaSearchController(VentaSearchView view) {

		super(view, "Buscar");

		this.view = view;
	}

	@Override
	public void doAction() {

		try {

			view.buscar(1);

		} catch (Exception e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(view, "Error buscando ventas", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		doAction();
	}

	public void buscar(int pagina) {

		view.buscar(pagina);
	}
}