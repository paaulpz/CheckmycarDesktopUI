package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.paula.checkmycar.desktop.views.tableModel.LineaPresupuestoTableModel;

public class LineaPresupuestoDeleteController implements ActionListener {

	private JTable table;

	public LineaPresupuestoDeleteController(JTable table) {

		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		int row = table.getSelectedRow();

		if (row < 0) {

			JOptionPane.showMessageDialog(table, "Seleccione una línea");

			return;
		}

		int opcion = JOptionPane.showConfirmDialog(table, "¿Eliminar línea?", "Confirmar", JOptionPane.YES_NO_OPTION);

		if (opcion == JOptionPane.YES_OPTION) {

			LineaPresupuestoTableModel model = (LineaPresupuestoTableModel) table.getModel();

			model.removeLinea(row);
		}
	}
}