package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.paula.checkmc.model.LineaPresupuestoDTO;
import com.paula.checkmycar.desktop.views.LineaPresupuestoCreateView;
import com.paula.checkmycar.desktop.views.tableModel.LineaPresupuestoTableModel;

public class LineaPresupuestoOpenCreateController implements ActionListener {

	private LineaPresupuestoTableModel tableModel;

	public LineaPresupuestoOpenCreateController(LineaPresupuestoTableModel tableModel) {

		this.tableModel = tableModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		LineaPresupuestoCreateView view = new LineaPresupuestoCreateView();

		view.setVisible(true);

		LineaPresupuestoDTO linea = view.getLinea();

		if (linea != null) {

			tableModel.addLinea(linea);
		}
	}
}