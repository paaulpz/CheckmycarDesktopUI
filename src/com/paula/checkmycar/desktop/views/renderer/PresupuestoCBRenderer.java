package com.paula.checkmycar.desktop.views.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.paula.checkmc.model.PresupuestoDTO;

public class PresupuestoCBRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		if (value instanceof PresupuestoDTO) {

			PresupuestoDTO presupuesto = (PresupuestoDTO) value;

			if (presupuesto.getId() == null) {

				setText("Seleccionar");

			} else {

				String texto = presupuesto.getNombre();

				if (presupuesto.getNombreCliente() != null && !presupuesto.getNombreCliente().trim().isEmpty()) {

					texto += " - " + presupuesto.getNombreCliente();
				}

				setText(texto);
			}
		}

		return this;
	}
}