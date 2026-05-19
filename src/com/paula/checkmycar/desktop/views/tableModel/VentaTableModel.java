package com.paula.checkmycar.desktop.views.tableModel;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.paula.checkmc.model.VentaDTO;

public class VentaTableModel extends AbstractTableModel {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static final String[] COLUMN_NAMES = {
        "Fecha", "Matrícula", "Comprador", "Vendedor", "Empleado", "Precio Cliente", "Precio Final", "Detalles"
    };

    private List<VentaDTO> data = new ArrayList<>();

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
    public boolean isCellEditable(int row, int column) {
        return column == 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        VentaDTO v = data.get(rowIndex);
        switch (columnIndex) {
            case 0: return v.getFechaVenta() != null ? v.getFechaVenta().format(FORMATTER) : "";
            case 1: return v.getMatriculaCoche();
            case 2: return v.getClienteCompradorNombre();
            case 3: return v.getClienteVendedorNombre();
            case 4: return v.getEmpleadoNombre();
            case 5: return v.getPrecioCliente();
            case 6: return v.getPrecioFinal();
            case 7: return "Detalles";
            default: return null;
        }
    }

    public void setData(List<VentaDTO> data) {
        this.data = data;
        fireTableDataChanged();
    }

    public List<VentaDTO> getData() {
        return data;
    }

    public VentaDTO getVentaAt(int row) {
        return data.get(row);
    }

	public void setVentas(List<VentaDTO> ventas) {
		// TODO Auto-generated method stub
		
	}
}