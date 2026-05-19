package com.paula.checkmycar.desktop.views.tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.paula.checkmc.model.CitaDTO;

public class CitasTableModel extends AbstractTableModel {

	public static final String[] COLUMN_NAMES = { "Id", "Cliente", "Coche", "Fecha", "Descripción", "Estado",
			"Acciones" };

	private List<CitaDTO> citas;

	public CitasTableModel() {

		this.citas = new ArrayList<>();
	}

	@Override
	public int getRowCount() {

		return citas.size();
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

		CitaDTO cita = citas.get(rowIndex);

		switch (columnIndex) {

		case 0:
			return cita.getId();

		case 1:
			return cita.getNombreCliente();

		case 2:
			return cita.getMatriculaCoche();

		case 3:
			return cita.getFecha();

		case 4:
			return cita.getDescripcion();
			
		case 5:
			return cita.getEstadoCita();

		case 6:
			return "";


		default:
			return null;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		return columnIndex == 6;
	}

	public void setCitas(List<CitaDTO> citas) {

		this.citas = citas;

		fireTableDataChanged();
	}

	public List<CitaDTO> getCitas() {

		return citas;
	}

	public CitaDTO getRow(int row) {

		return citas.get(row);
	}

	public CitaDTO getCitaAt(int row) {

		return citas.get(row);
	}
}