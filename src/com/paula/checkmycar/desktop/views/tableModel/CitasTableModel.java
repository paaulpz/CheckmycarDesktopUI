package com.paula.checkmycar.desktop.views.tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.paula.checkmc.model.CitaDTO;

public class CitasTableModel extends AbstractTableModel {
	
	public static final String[] COLUMN_NAMES = new String[] {
			"Id", "Coche", "Cliente", "Empleado", "Fecha", "Hora" 
	};
	
	private List<CitaDTO> citas = null;
	
	public CitasTableModel() {
		setCitas(new ArrayList<CitaDTO>());
	}
	public CitasTableModel(List<CitaDTO> citas) {
		setCitas(citas);
	}
	
	
	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getRowCount() {
		return citas == null ? 0 : citas.size();
	}
	
	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}
	
	public List<CitaDTO> getCitas() {
		return citas;
	}
	
	public void setCitas(List<CitaDTO> citas) {
		this.citas = citas;
		fireTableDataChanged(); // Notificar a la tabla que los datos han cambiado
	}
	
	

}
