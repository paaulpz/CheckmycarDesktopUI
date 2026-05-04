package com.paula.checkmycar.desktop.views.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.paula.checkmc.model.EstadoPresupuesto;

public class EstadoPresupuestoCBRender extends DefaultListCellRenderer {

	
	
	public EstadoPresupuestoCBRender() {
		
	}
	
	public Component getListCellRendererComponent(JList<?> list,Object value, int index,boolean isSelected, boolean cellHasFocus) {
		setText(value == null ? "" : ((EstadoPresupuesto) value).getNombre());
	   
		return this;
	}
  
}