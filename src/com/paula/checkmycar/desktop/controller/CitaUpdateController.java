package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Cita;
import com.paula.checkmc.service.CitaService;
import com.paula.checkmc.service.impl.CitaServiceImpl;
import com.paula.checkmycar.desktop.views.CitaCreateView;

public class CitaUpdateController extends Controller {

	private static final Logger logger = LogManager.getLogger(CitaUpdateController.class);

	private CitaCreateView view = null;

	private CitaService citaService = null;

	public CitaUpdateController(CitaCreateView view) {

		super(view, "Guardar", null);

		this.view = view;

		this.citaService = new CitaServiceImpl();
	}

	public void doAction() {

		Cita cita = view.getModel();

		if (cita == null) {

			return;
		}

		if (cita.getId() == null) {

			JOptionPane.showMessageDialog(view, "Error: cita sin ID", "Error", JOptionPane.ERROR_MESSAGE);

			return;
		}

		try {

			logger.debug("Actualizando cita: {}", cita.getId());

			boolean updated = citaService.update(cita);

			if (updated) {

				JOptionPane.showMessageDialog(view, "Cita actualizada correctamente.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);

				view.setEditable(false);

				view.setAgreeController(new CitaSetEditableController(view));

			} else {

				JOptionPane.showMessageDialog(view, "Error al actualizar la cita", "Error", JOptionPane.ERROR_MESSAGE);
			}

		} catch (Exception e) {

			logger.error("Error actualizando cita {}: {}", cita.getId(), e.getMessage(), e);

			JOptionPane.showMessageDialog(view, "Error en base de datos", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		doAction();
	}
}