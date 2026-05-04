package com.paula.checkmycar.desktop.views.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.paula.checkmc.model.TipoTransmision;

public class TipoTransmisionCBRender extends DefaultListCellRenderer {

	public Component getListCellRendererComponent(JList<?> list,Object value, int index,boolean isSelected, boolean cellHasFocus) {
		 setText(value == null ? "" : ((TipoTransmision) value).getNombre());
			   
			    return this;
			}
}