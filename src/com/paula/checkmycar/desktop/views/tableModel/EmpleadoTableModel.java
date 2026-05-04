package com.paula.checkmycar.desktop.views.tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.paula.checkmc.model.EmpleadoDTO;

public class EmpleadoTableModel extends AbstractTableModel {

    private static final String[] COLUMNAS = {
         "Nombre", "Primer Apellido", "Segundo Apellido",
        "DNI/NIE", "Rol", "Editar"
    };

    private List<EmpleadoDTO> datos;

    public void setEmpleados(List<EmpleadoDTO> datos) {
        this.datos = datos;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return datos == null ? 0 : datos.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNAS.length;
    }

    @Override
    public String getColumnName(int col) {
        return COLUMNAS[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        EmpleadoDTO e = datos.get(row);
        switch (col) {
            
            case 0: return e.getNombre();
            case 1: return e.getPrimerApellido();
            case 2: return e.getSegundoApellido();
            case 3: return e.getDniNie();
            case 4: return e.getNombreRol();
            case 5: return "editar";
            default: return null;
        }
    }

    public EmpleadoDTO getEmpleadoAt(int row) {
        return datos.get(row);
    }
}