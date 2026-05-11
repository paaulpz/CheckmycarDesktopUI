package com.paula.checkmycar.desktop.views.tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.paula.checkmc.model.PresupuestoDTO;

public class PresupuestoTableModel extends AbstractTableModel {

	
	public static final String[] COLUMN_NAMES = new String[] {
			"Id", "Coche", "Cliente", "Empleado", "Fecha", "Importe" 
	};
	
	private List<PresupuestoDTO> presupuestos = null;
	
	public PresupuestoTableModel() {
		setPresupuestos(new ArrayList<PresupuestoDTO>());
	}
	
	public PresupuestoTableModel(List<PresupuestoDTO> presupuestos) {
		setPresupuestos(presupuestos);
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		PresupuestoDTO presupuesto = presupuestos.get(row);
		
		return presupuesto;
	}
	
	@Override
	public int getRowCount() {
		return presupuestos == null ? 0 : presupuestos.size();
	}
	
	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}
	
		public List<PresupuestoDTO> getPresupuestos() {
		return presupuestos;
	}
		
		public void setPresupuestos(List<PresupuestoDTO> presupuestos) {
		this.presupuestos = presupuestos;
		fireTableDataChanged(); 
	}
	
	
	
}
