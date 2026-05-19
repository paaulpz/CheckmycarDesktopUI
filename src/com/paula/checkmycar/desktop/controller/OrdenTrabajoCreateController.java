package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.OrdenTrabajoDTO;
import com.paula.checkmc.service.OrdenTrabajoService;
import com.paula.checkmc.service.impl.OrdenTrabajoServiceImpl;
import com.paula.checkmycar.desktop.views.OrdenTrabajoCreateView;

public class OrdenTrabajoCreateController extends Controller {

	private static final Logger logger = LogManager.getLogger(OrdenTrabajoCreateController.class);

	private OrdenTrabajoCreateView view;

	private OrdenTrabajoService ordenTrabajoService;

	public OrdenTrabajoCreateController(OrdenTrabajoCreateView view) {

		super(view, "Guardar", null);

		this.view = view;

		this.ordenTrabajoService = new OrdenTrabajoServiceImpl();
	}

	@Override
	public void doAction() {

		try {

			OrdenTrabajoDTO dto = view.getModel();

			if (dto == null) {

				return;
			}

			Long id = ordenTrabajoService.create(dto);

			if (id != null) {

				JOptionPane.showMessageDialog(view, "Orden de trabajo creada correctamente.");

			} else {

				JOptionPane.showMessageDialog(view, "No se pudo crear la orden.", "Error", JOptionPane.ERROR_MESSAGE);
			}

		} catch (Exception e) {

			logger.error("Error creando orden trabajo", e);

			JOptionPane.showMessageDialog(view, "Error guardando la orden", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		doAction();
	}
}