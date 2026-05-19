package com.paula.checkmycar.desktop.views.tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.paula.checkmc.model.OrdenTrabajoDTO;

public class OrdenTrabajoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	public static final String[] COLUMN_NAMES = { 
			"Id", "Diagnóstico", "Fecha inicio", "Fecha fin", "Coche", "Acciones" };

	private List<OrdenTrabajoDTO> ordenes;

	public OrdenTrabajoTableModel() {

		ordenes = new ArrayList<>();
	}

	@Override
	public int getRowCount() {

		return ordenes.size();
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
	public boolean isCellEditable(
	        int rowIndex,
	        int columnIndex) {

	    return columnIndex == 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		OrdenTrabajoDTO ot = ordenes.get(rowIndex);

		switch (columnIndex) {

		case 0:
			return ot.getId();

		case 1:
			return ot.getDiagnostico();

		case 2:
			return ot.getFechaInicio();

		case 3:
			return ot.getFechaFin();

		case 4:
			return ot.getMatriculaCoche();

		case 5:
			return "";

		default:
			return null;
		}
	}

	public void setOrdenes(List<OrdenTrabajoDTO> ordenes) {

		this.ordenes = ordenes;

		fireTableDataChanged();
	}

	public List<OrdenTrabajoDTO> getOrdenes() {

		return ordenes;
	}

	public OrdenTrabajoDTO getRow(int row) {

		return ordenes.get(row);
	}
}