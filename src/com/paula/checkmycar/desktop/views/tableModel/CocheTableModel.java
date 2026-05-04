package com.paula.checkmycar.desktop.views.tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.paula.checkmc.model.CocheDTO;

public class CocheTableModel extends AbstractTableModel {

    public static final String[] COLUMN_NAMES = {
        "Id", "Matrícula", "Marca", "Modelo", "Bastidor", "Kilómetros", "Precio Final (€)"
    };

    private List<CocheDTO> data;

    public CocheTableModel() {
        this.data = new ArrayList<>();
    }

    public CocheTableModel(List<CocheDTO> data) {
        this.data = data;
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
            case 0: return coche.getId();
            case 1: return coche.getMatricula();
            case 2: return coche.getNombreMarca();
            case 3: return coche.getNombreModelo();
            case 4: return coche.getNumeroBastidor();
            case 5: return coche.getKilometros();
            case 6: return coche.getPrecioFinal();
            default: return null;
        }
    }

    public List<CocheDTO> getData() {
        return data;
    }

    public void setData(List<CocheDTO> resultados) {
        this.data = resultados;
        fireTableDataChanged();
    }
}