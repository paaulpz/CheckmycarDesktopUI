package com.paula.checkmycar.desktop.views.tableModel;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.paula.checkmc.model.PresupuestoDTO;

public class PresupuestoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private static final String[] COLUMN_NAMES = { "Nombre", "Fecha", "Cliente", "Estado", "Precio final", "Acciones" };

	private List<PresupuestoDTO> presupuestos;

	private DateTimeFormatter formatter;

	public PresupuestoTableModel() {

		presupuestos = new ArrayList<>();

		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	}

	@Override
	public int getRowCount() {

		return presupuestos.size();
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
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		return columnIndex == 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		PresupuestoDTO presupuesto = presupuestos.get(rowIndex);

		switch (columnIndex) {

		case 0:

			return presupuesto.getNombre();

		case 1:

			if (presupuesto.getFecha() != null) {

				return presupuesto.getFecha().format(formatter);
			}

			return "";

		case 2:

			if (presupuesto.getNombreCliente() != null) {

				return presupuesto.getNombreCliente();
			}

			return "";

		case 3:

			if (presupuesto.getEstadoPresupuesto() != null) {

				return presupuesto.getEstadoPresupuesto();
			}

			return "";

		case 4:

			if (presupuesto.getPrecioFinal() != null) {

				return presupuesto.getPrecioFinal() + " €";
			}

			return "";

		case 5:

			return "";

		default:

			return null;
		}
	}

	public void setData(List<PresupuestoDTO> presupuestos) {

		this.presupuestos = presupuestos;

		fireTableDataChanged();
	}

	public List<PresupuestoDTO> getData() {

		return presupuestos;
	}

	public PresupuestoDTO getPresupuestoAt(int row) {

		return presupuestos.get(row);
	}
}