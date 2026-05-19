package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.OrdenTrabajoDTO;
import com.paula.checkmc.service.OrdenTrabajoService;
import com.paula.checkmc.service.impl.OrdenTrabajoServiceImpl;
import com.paula.checkmycar.desktop.views.OrdenTrabajoCreateView;

public class OrdenTrabajoUpdateController extends Controller {

	private static final Logger logger = LogManager.getLogger(OrdenTrabajoUpdateController.class);

	private OrdenTrabajoCreateView view;

	private OrdenTrabajoService ordenTrabajoService;

	public OrdenTrabajoUpdateController(OrdenTrabajoCreateView view) {

		super(view, "Guardar", null);

		this.view = view;

		this.ordenTrabajoService = new OrdenTrabajoServiceImpl();
	}

	@Override
	public void doAction() {

		try {

			OrdenTrabajoDTO dto = view.getModel();

			if (dto == null || dto.getId() == null) {

				return;
			}

			boolean updated = ordenTrabajoService.update(dto);

			if (updated) {

				JOptionPane.showMessageDialog(view, "Orden actualizada correctamente.");

				view.setEditable(false);

				view.setAgreeController(new OrdenTrabajoSetEditableController(view));

			} else {

				JOptionPane.showMessageDialog(view, "No se pudo actualizar la orden.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (Exception e) {

			logger.error("Error actualizando orden", e);

			JOptionPane.showMessageDialog(view, "Error actualizando orden", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		doAction();
	}
}