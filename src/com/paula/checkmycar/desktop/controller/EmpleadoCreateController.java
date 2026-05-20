package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.paula.checkmc.model.Empleado;
import com.paula.checkmc.service.EmpleadoService;
import com.paula.checkmc.service.impl.EmpleadoServiceImpl;
import com.paula.checkmycar.desktop.views.EmpleadoCreateView;

public class EmpleadoCreateController extends Controller {

	private EmpleadoCreateView view;
	private EmpleadoService empleadoService;

	public EmpleadoCreateController(EmpleadoCreateView view) {

		super(view, "Guardar",
				new ImageIcon(EmpleadoCreateController.class.getResource("/icons/16x16/agregarusuario.png")));

		this.view = view;
		this.empleadoService = new EmpleadoServiceImpl();
	}

	@Override
	public void doAction() {

		Empleado empleado = (Empleado) view.getModel();

		if (empleado == null)
			return;

		try {

		

			empleadoService.create(empleado);
			JOptionPane.showMessageDialog(view, "Empleado creado correctamente", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);

			view.setEditable(false);

			view.setAgreeController(new EmpleadoSetEditableController(view));

		} catch (Exception e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(view, "Error al crear el empleado", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}
}