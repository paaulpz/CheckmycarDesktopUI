package com.paula.checkmycar.desktop.views.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.paula.checkmc.model.Pais;

public class PaisCBRenderer extends DefaultListCellRenderer {

	public PaisCBRenderer() {
	}

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		setText(value==null?"":((Pais)value).getNombre());
		
		
		return this;
	}

}
