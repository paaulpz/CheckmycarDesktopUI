package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.paula.checkmc.model.LineaPresupuestoDTO;
import com.paula.checkmc.model.PresupuestoDTO;
import com.paula.checkmc.service.LineaPresupuestoService;
import com.paula.checkmc.service.PresupuestoService;
import com.paula.checkmc.service.impl.LineaPresupuestoServiceImpl;
import com.paula.checkmc.service.impl.PresupuestoServiceImpl;
import com.paula.checkmycar.desktop.views.PresupuestoCreateView;

public class PresupuestoCreateController extends Controller {

	private PresupuestoCreateView view;

	private PresupuestoService presupuestoService;

	private LineaPresupuestoService lineaService;

	public PresupuestoCreateController(PresupuestoCreateView view) {

		super(view, "Guardar");

		this.view = view;

		presupuestoService = new PresupuestoServiceImpl();

		lineaService = new LineaPresupuestoServiceImpl();
	}

	@Override
	public void doAction() {

		try {

			PresupuestoDTO presupuesto = view.getModel();

			if (presupuesto == null) {

				return;
			}

			Long presupuestoId = presupuestoService.create(presupuesto);

			if (presupuestoId == null) {

				JOptionPane.showMessageDialog(view, "No se pudo crear el presupuesto", "Error",
						JOptionPane.ERROR_MESSAGE);

				return;
			}

			for (LineaPresupuestoDTO linea : view.getLineasTableModel().getLineas()) {

				linea.setPresupuestoId(presupuestoId);

				lineaService.create(linea);
			}

			JOptionPane.showMessageDialog(view, "Presupuesto creado correctamente");

		} catch (Exception e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(view, "Error guardando presupuesto", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		doAction();
	}
}