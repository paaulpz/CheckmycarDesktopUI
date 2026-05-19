package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.ClienteDTO;
import com.paula.checkmc.model.EmpleadoDTO;
import com.paula.checkmc.model.Rol;
import com.paula.checkmc.service.ClienteService;
import com.paula.checkmc.service.EmpleadoService;
import com.paula.checkmc.service.impl.ClienteServiceImpl;
import com.paula.checkmc.service.impl.EmpleadoServiceImpl;
import com.paula.checkmycar.desktop.main.CheckmycarWindow;
import com.paula.checkmycar.desktop.main.LoginWindow;

public class LoginController implements ActionListener {

	private static final Logger logger = LogManager.getLogger(LoginController.class);

	private LoginWindow view;

	private ClienteService clienteService;

	private EmpleadoService empleadoService;

	public LoginController(LoginWindow view) {

		this.view = view;

		clienteService = new ClienteServiceImpl();

		empleadoService = new EmpleadoServiceImpl();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		login();
	}

	private void login() {

		try {

			String dni = view.getDni();

			String password = view.getPassword();

			if (dni == null || dni.trim().isEmpty() || password == null || password.trim().isEmpty()) {

				JOptionPane.showMessageDialog(view, "Rellena todos los campos");

				return;
			}

			if (view.isClienteSelected()) {

				ClienteDTO cliente = clienteService.login(dni, password);

				if (cliente == null) {

					JOptionPane.showMessageDialog(view, "DNI o contraseña incorrectos");

					return;
				}

				logger.info("Login cliente correcto: {}", cliente.getNombre());

				abrirAplicacion();
			}

			else if (view.isEmpleadoSelected()) {

				Rol rol = view.getRolSeleccionado();

				if (rol == null || rol.getId() == null) {

					JOptionPane.showMessageDialog(view, "Selecciona un rol");

					return;
				}

				EmpleadoDTO empleado = empleadoService.login(dni, password, rol.getId());

				if (empleado == null) {

					JOptionPane.showMessageDialog(view, "Credenciales incorrectas");

					return;
				}

				logger.info("Login empleado correcto: {}", empleado.getNombre());

				abrirAplicacion();
			}

		} catch (Exception e) {

			logger.error("Error en login: {}", e.getMessage(), e);

			JOptionPane.showMessageDialog(view, "Error iniciando sesión", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void abrirAplicacion() {

		JOptionPane.showMessageDialog(view, "¡Bienvenido!");

		view.dispose();

		CheckmycarWindow.getInstance().frame.setVisible(true);
	}
}