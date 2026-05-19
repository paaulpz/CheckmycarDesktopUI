package com.paula.checkmycar.desktop.views.tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.paula.checkmc.model.PiezaDTO;

public class PiezaTableModel extends AbstractTableModel {

	public static final String[] COLUMN_NAMES = { "Id", "Referencia", "Nombre", "Stock", "Precio (€)", "Estado" };

	private List<PiezaDTO> data = new ArrayList<>();

	@Override
	public int getRowCount() {

		return data.size();
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

		PiezaDTO p = data.get(rowIndex);

		switch (columnIndex) {

		case 0:
			return p.getId();

		case 1:
			return p.getNumeroReferencia();

		case 2:
			return p.getNombre();

		case 3:
			return p.getStock();

		case 4:
			return p.getPrecio();

		case 5:
			return p.getNombreEstado();

		default:
			return null;
		}
	}

	public void setData(List<PiezaDTO> data) {

		this.data = data;

		fireTableDataChanged();
	}

	public PiezaDTO getPiezaAt(int row) {

		return data.get(row);
	}

	public void setRows(List<PiezaDTO> page) {

		this.data = page;

		fireTableDataChanged();
	}

	public void setPiezas(List<PiezaDTO> piezas) {

		this.data = piezas;

		fireTableDataChanged();
	}

	public List<PiezaDTO> getPiezas() {

		return data;
	}
}