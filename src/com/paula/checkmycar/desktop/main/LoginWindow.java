package com.paula.checkmycar.desktop.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;

import com.paula.checkmc.model.Rol;
import com.paula.checkmc.service.RolService;
import com.paula.checkmc.service.impl.RolServiceImpl;
import com.paula.checkmycar.desktop.controller.LoginController;
import com.paula.checkmycar.desktop.views.renderer.RolCBRenderer;

public class LoginWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private static LoginWindow instance;

	private JPanel contentPane;

	private JTextField dniTF;
	private JPasswordField passwordTF;

	private JButton accederButton;

	private JRadioButton clienteRB;
	private JRadioButton empleadoRB;

	private JComboBox<Rol> rolCB;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {

					LoginWindow frame = LoginWindow.getInstance();

					frame.setVisible(true);

				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		});
	}

	public static LoginWindow getInstance() {

		if (instance == null) {

			instance = new LoginWindow();
		}

		return instance;
	}

	public LoginWindow() {

		initialize();

		postInitialize();

		setVisible(true);
	}

	private void initialize() {

		setTitle("CheckMyCar - Login");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(400, 350);

		setResizable(false);

		setLocationRelativeTo(null);

		contentPane = new JPanel();

		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		setContentPane(contentPane);

		GridBagLayout gbl = new GridBagLayout();

		gbl.columnWidths = new int[] { 0, 0, 0 };
		gbl.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };

		contentPane.setLayout(gbl);

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);

		JLabel titleLabel = new JLabel("CheckMyCar");

		gbc.gridx = 1;
		gbc.gridy = 0;

		contentPane.add(titleLabel, gbc);

		JLabel dniLabel = new JLabel("DNI/NIE");

		gbc.gridx = 1;
		gbc.gridy = 1;

		contentPane.add(dniLabel, gbc);

		dniTF = new JTextField();

		dniTF.setColumns(15);

		gbc.gridx = 1;
		gbc.gridy = 2;

		contentPane.add(dniTF, gbc);

		JLabel passwordLabel = new JLabel("Contraseña");

		gbc.gridx = 1;
		gbc.gridy = 3;

		contentPane.add(passwordLabel, gbc);

		passwordTF = new JPasswordField();

		passwordTF.setColumns(15);

		gbc.gridx = 1;
		gbc.gridy = 4;

		contentPane.add(passwordTF, gbc);

		JPanel tipoPanel = new JPanel(new BorderLayout());

		clienteRB = new JRadioButton("Cliente");
		empleadoRB = new JRadioButton("Empleado");

		clienteRB.setSelected(true);

		ButtonGroup group = new ButtonGroup();

		group.add(clienteRB);
		group.add(empleadoRB);

		tipoPanel.add(clienteRB, BorderLayout.WEST);
		tipoPanel.add(empleadoRB, BorderLayout.EAST);

		gbc.gridx = 1;
		gbc.gridy = 5;

		contentPane.add(tipoPanel, gbc);

		rolCB = new JComboBox<>();

		rolCB.setRenderer(new RolCBRenderer());

		RolService rolService = new RolServiceImpl();
		try {

			Rol placeholder = new Rol();

			placeholder.setId(null);

			placeholder.setNombre("Seleccionar");

			rolCB.addItem(placeholder);

			for (Rol r : rolService.findAll()) {
				rolCB.addItem(r);
			}

		} catch (Exception e) {

			e.printStackTrace();

			javax.swing.JOptionPane.showMessageDialog(this, "Error cargando roles", "Error",
					javax.swing.JOptionPane.ERROR_MESSAGE);
		}

		rolCB.setVisible(false);

		gbc.gridx = 1;
		gbc.gridy = 6;

		contentPane.add(rolCB, gbc);

		accederButton = new JButton("Acceder");

		gbc.gridx = 1;
		gbc.gridy = 7;

		contentPane.add(accederButton, gbc);

		empleadoRB.addActionListener(e -> {

			rolCB.setVisible(true);

			contentPane.revalidate();
			contentPane.repaint();
		});

		clienteRB.addActionListener(e -> {

			rolCB.setVisible(false);

			contentPane.revalidate();
			contentPane.repaint();
		});
	}

	private void postInitialize() {

		LoginController loginController = new LoginController(this);

		accederButton.addActionListener(loginController);
	}

	public String getDni() {

		return StringUtils.trimToNull(dniTF.getText());
	}

	public String getPassword() {

		return StringUtils.trimToNull(new String(passwordTF.getPassword()));
	}

	public boolean isClienteSelected() {

		return clienteRB.isSelected();
	}

	public boolean isEmpleadoSelected() {

		return empleadoRB.isSelected();
	}

	public Rol getRolSeleccionado() {

		Rol rol = (Rol) rolCB.getSelectedItem();

		if (rol == null || rol.getId() == null) {

			return null;
		}

		return rol;
	}
}