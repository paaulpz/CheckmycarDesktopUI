package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.paula.checkmc.service.VentaService;
import com.paula.checkmc.service.impl.VentaServiceImpl;
import com.paula.checkmycar.desktop.views.VentaCreateView;

public class VentaUpdateController extends Controller {

	private VentaCreateView view;

	private VentaService ventaService = new VentaServiceImpl();

	public VentaUpdateController(VentaCreateView view) {

		super(view, "Guardar");

		this.view = view;
	}

	@Override
	public void doAction() {

		try {

			ventaService.update(view.getModel());

			JOptionPane.showMessageDialog(view, "Venta actualizada correctamente");

		} catch (Exception e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(view, "Error actualizando venta", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		doAction();
	}
}