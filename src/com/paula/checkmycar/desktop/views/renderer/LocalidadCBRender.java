package com.paula.checkmycar.desktop.views.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.paula.checkmc.model.Localidad;

public class LocalidadCBRender extends DefaultListCellRenderer {
	
	public LocalidadCBRender() {		
	}
	
	public Component getListCellRendererComponent(JList<?> list,Object value, int index,boolean isSelected, boolean cellHasFocus) {
 setText(value == null ? "" : ((Localidad) value).getNombre());
	   
	    return this;
	}
}
