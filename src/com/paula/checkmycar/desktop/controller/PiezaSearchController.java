package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.paula.checkmycar.desktop.views.PiezaSearchView;

public class PiezaSearchController extends Controller {

	private PiezaSearchView view;

	public PiezaSearchController(PiezaSearchView view) {

		super(view, "Buscar");

		this.view = view;
	}

	@Override
	public void doAction() {

		try {

			view.buscar();

		} catch (Exception e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(view, "Error buscando piezas", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void buscar(int pagina) {

		try {

			view.buscar(pagina);

		} catch (Exception e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(view, "Error buscando piezas", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		doAction();
	}
}