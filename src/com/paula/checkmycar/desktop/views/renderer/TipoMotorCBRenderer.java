package com.paula.checkmycar.desktop.views.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.paula.checkmc.model.TipoMotor;

public class TipoMotorCBRenderer extends DefaultListCellRenderer {

	
	public Component getListCellRendererComponent(JList<?> list,Object value, int index,boolean isSelected, boolean cellHasFocus) {
		 setText(value == null ? "" : ((	TipoMotor) value).getNombre());
			   
			    return this;
			}
}