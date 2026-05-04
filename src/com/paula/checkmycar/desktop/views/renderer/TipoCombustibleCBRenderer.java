package com.paula.checkmycar.desktop.views.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.paula.checkmc.model.TipoCombustible;

public class TipoCombustibleCBRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
  	  setText(value == null ? "" : ((TipoCombustible) value).getNombre());
        return this;
    }
}