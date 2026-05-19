package com.paula.checkmycar.desktop.views.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.paula.checkmc.model.CocheDTO;

public class CocheCBRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		if (value instanceof CocheDTO) {

			CocheDTO coche = (CocheDTO) value;

			if (coche.getMatricula() == null) {

				setText("Seleccionar");

			} else {
				setText(coche.getMatricula());
			}
		}

		return this;
	}
}