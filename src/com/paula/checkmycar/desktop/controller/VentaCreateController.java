package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.VentaDTO;
import com.paula.checkmc.service.VentaService;
import com.paula.checkmc.service.impl.VentaServiceImpl;
import com.paula.checkmycar.desktop.views.VentaCreateView;

public class VentaCreateController extends Controller {

	private static final Logger logger = LogManager.getLogger(VentaCreateController.class);

	private VentaCreateView view;

	private VentaService ventaService;

	public VentaCreateController(VentaCreateView view) {

		super(view, "Guardar", null);

		this.view = view;

		this.ventaService = new VentaServiceImpl();
	}

	@Override
	public void doAction() {

		try {

			VentaDTO venta = view.getModel();

			if (venta == null) {

				return;
			}

			Long id = ventaService.create(venta);

			if (id != null) {

				JOptionPane.showMessageDialog(view, "Venta creada correctamente.");

			} else {

				JOptionPane.showMessageDialog(view, "No se pudo crear la venta.", "Error", JOptionPane.ERROR_MESSAGE);
			}

		} catch (Exception e) {

			logger.error("Error creando venta", e);

			JOptionPane.showMessageDialog(view, "Error guardando la venta", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		doAction();
	}
}