package com.paula.checkmycar.desktop.views.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.paula.checkmc.model.Provincia;

public class ProvinciaCBRenderer extends DefaultListCellRenderer {

	public ProvinciaCBRenderer() {
	}

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		setText(value == null ? "" : ((Provincia) value).getNombre());
		
		return this;
	}

}
