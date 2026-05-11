package com.paula.checkmycar.desktop.views.tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.paula.checkmc.model.ClienteDTO;

public class ClienteTableModel extends AbstractTableModel {

	public static final String[] COLUMN_NAMES = {
		   "DNI","Nombre", "Email", "Teléfono", "Detalles"
		};

    private List<ClienteDTO> clientes;

    public ClienteTableModel() {
        this.clientes = new ArrayList<>();
    }

    public ClienteTableModel(List<ClienteDTO> clientes) {
        this.clientes = clientes;
    }

    @Override
    public int getRowCount() {
        return clientes.size();
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
        ClienteDTO cliente = clientes.get(rowIndex);
        switch (columnIndex) {
            case 0: return cliente.getDniNie();
            case 1: return cliente.getNombre();
            case 2: return cliente.getEmail();
            case 3: return cliente.getTelefono();
            case 4: return "Detalles"; 
            default: return null;
        }
    }

    public List<ClienteDTO> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteDTO> clientes) {
        this.clientes = clientes;
        fireTableDataChanged();
    }

    
    public ClienteDTO getClienteAt(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= clientes.size()) {
            return null;
        }
        return clientes.get(rowIndex);
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 4;
    }
	
}