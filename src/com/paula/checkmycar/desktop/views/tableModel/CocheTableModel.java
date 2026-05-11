package com.paula.checkmycar.desktop.views.tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.paula.checkmc.model.CocheDTO;

public class CocheTableModel extends AbstractTableModel {

    public static final String[] COLUMN_NAMES = {
            "Matrícula", "Marca", "Modelo", "Propietario", "Detalles"
    };

    private List<CocheDTO> data;

    public CocheTableModel() {
        this.data = new ArrayList<>();
    }

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

        CocheDTO coche = data.get(rowIndex);

        switch (columnIndex) {

        case 0:
            return coche.getMatricula();

        case 1:
            return coche.getNombreMarca();

        case 2:
            return coche.getNombreModelo();

        case 3:
            return coche.getNombreCliente();

        case 4:
            return "Detalles";

        default:
            return null;
        }
    }

    public void setData(List<CocheDTO> data) {
        this.data = data;
        fireTableDataChanged();
    }

    public List<CocheDTO> getData() {
        return data;
    }

    public CocheDTO getCocheAt(int row) {
        return data.get(row);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 4;
    }
}