package com.paula.checkmycar.desktop.views.tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.paula.checkmc.model.LineaPresupuestoDTO;

public class LineaPresupuestoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private static final String[] COLUMN_NAMES = { "Pieza", "Unidades", "Precio unidad", "Total" };

	private List<LineaPresupuestoDTO> lineas;

	public LineaPresupuestoTableModel() {

		lineas = new ArrayList<>();
	}

	@Override
	public int getRowCount() {

		return lineas.size();
	}

	@Override
	public int getColumnCount() {

		return COLUMN_NAMES.length;
	}

	@Override
	public String getColumnName(int column) {

		return COLUMN_NAMES[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		LineaPresupuestoDTO linea = lineas.get(rowIndex);

		switch (columnIndex) {

		case 0:
			return linea.getNombrePieza();

		case 1:
			return linea.getUnidades();

		case 2:
			return linea.getPrecioUnitario() + " €";

		case 3:
			return linea.getPrecioFinal() + " €";

		default:
			return null;
		}
	}

	public void addLinea(LineaPresupuestoDTO linea) {

		lineas.add(linea);

		fireTableDataChanged();
	}

	public void removeLinea(int row) {

		lineas.remove(row);

		fireTableDataChanged();
	}

	public List<LineaPresupuestoDTO> getLineas() {

		return lineas;
	}

	public Double getTotal() {

		double total = 0;

		for (LineaPresupuestoDTO linea : lineas) {

			if (linea.getPrecioFinal() != null) {

				total += linea.getPrecioFinal();
			}
		}

		return total;
	}
}