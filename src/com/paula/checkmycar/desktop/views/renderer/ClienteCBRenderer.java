package com.paula.checkmycar.desktop.views.renderer;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.paula.checkmc.model.ClienteDTO;

public class ClienteCBRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof ClienteDTO) {
            ClienteDTO c = (ClienteDTO) value;
            String nombre = c.getNombre() != null ? c.getNombre() : "";
            String apellido = c.getPrimerApellido() != null ? c.getPrimerApellido() : "";
            setText(nombre + " " + apellido);
        }
        return this;
    }
}