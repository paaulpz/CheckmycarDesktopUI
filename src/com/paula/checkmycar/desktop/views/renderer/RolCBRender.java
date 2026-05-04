package com.paula.checkmycar.desktop.views.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.paula.checkmc.model.Rol;

public class RolCBRender  extends DefaultListCellRenderer{
	
	public RolCBRender() {
	}
	public Component getListCellRendererComponent(JList<?> list,Object value, int index,boolean isSelected, boolean cellHasFocus) {
		 setText(value == null ? "" : ((Rol) value).getNombre());
			   
			    return this;
			}

}
