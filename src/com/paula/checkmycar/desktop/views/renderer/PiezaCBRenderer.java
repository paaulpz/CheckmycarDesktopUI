package com.paula.checkmycar.desktop.views.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.paula.checkmc.model.PiezaDTO;

public class PiezaCBRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {

        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof PiezaDTO) {
            PiezaDTO p = (PiezaDTO) value;
            String texto = p.getId() == null ? "Seleccionar"
                    : p.getNombre() + " (" + p.getPrecio() + "€)";
            setText(texto);
        }

        return this;
    }
}
