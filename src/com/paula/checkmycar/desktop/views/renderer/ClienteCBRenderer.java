package com.paula.checkmycar.desktop.views.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.paula.checkmc.model.ClienteDTO;

public class ClienteCBRenderer extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		if (value instanceof ClienteDTO) {

			ClienteDTO cliente = (ClienteDTO) value;

			if (cliente.getId() == null) {

				setText("Seleccionar");

			} else {

				String dni = cliente.getDniNie() != null ? cliente.getDniNie() : "";

				String nombre = cliente.getNombre() != null ? cliente.getNombre() : "";

				setText(dni + " - " + nombre);
			}
		}

		return this;
	}
}