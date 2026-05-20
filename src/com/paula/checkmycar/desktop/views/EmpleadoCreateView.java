package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.paula.checkmc.model.Empleado;
import com.paula.checkmc.model.EmpleadoDTO;
import com.paula.checkmc.model.Genero;
import com.paula.checkmc.model.Localidad;
import com.paula.checkmc.model.Pais;
import com.paula.checkmc.model.Provincia;
import com.paula.checkmc.model.Rol;
import com.paula.checkmc.service.LocalidadService;
import com.paula.checkmc.service.PaisService;
import com.paula.checkmc.service.ProvinciaService;
import com.paula.checkmc.service.RolService;
import com.paula.checkmc.service.impl.LocalidadServiceImpl;
import com.paula.checkmc.service.impl.PaisServiceImpl;
import com.paula.checkmc.service.impl.ProvinciaServiceImpl;
import com.paula.checkmc.service.impl.RolServiceImpl;
import com.paula.checkmycar.desktop.controller.Controller;
import com.paula.checkmycar.desktop.controller.EmpleadoCreateController;
import com.paula.checkmycar.desktop.views.renderer.LocalidadCBRender;
import com.paula.checkmycar.desktop.views.renderer.PaisCBRenderer;
import com.paula.checkmycar.desktop.views.renderer.ProvinciaCBRenderer;
import com.paula.checkmycar.desktop.views.renderer.RolCBRenderer;

public class EmpleadoCreateView extends View {

	private JTextField nombreTF;
	private JTextField apellido1TF;
	private JTextField apellido2TF;
	private JTextField telefonoTF;
	private JTextField emailTF;
	private JTextField dniNieTF;
	private JTextField direccionTF;
	private JTextField contrasenaTF;

	private JComboBox<Pais> paisComboBox;
	private JComboBox<Provincia> provinciaComboBox;
	private JComboBox<Localidad> ciudadComboBox;
	private JComboBox<Rol> rolComboBox;

	private JRadioButton masculinoRB;
	private JRadioButton femeninoRB;
	private JRadioButton otroRB;

	private JButton agreeButton;
	private JButton cancelButton;

	private Long empleadoId;

	private PaisService paisService;
	private ProvinciaService provinciaService;
	private LocalidadService localidadService;
	private RolService rolService;

	public EmpleadoCreateView() {

		initServices();

		initialize();

		postInitialize();
	}

	private void initServices() {

		paisService = new PaisServiceImpl();

		provinciaService = new ProvinciaServiceImpl();

		localidadService = new LocalidadServiceImpl();

		rolService = new RolServiceImpl();
	}

	private void initialize() {

		setName("Registro de empleados");

		setLayout(new BorderLayout(0, 0));

		JPanel contentPanel = new JPanel();

		add(contentPanel, BorderLayout.NORTH);

		GridBagLayout gbl_contentPanel = new GridBagLayout();

		gbl_contentPanel.columnWidths = new int[] { 64, 123, 0, 51, 0, 71, 0 };

		gbl_contentPanel.rowHeights = new int[] { 13, 19, 0, 0, 0, 0, 0, 0, 0 };

		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };

		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };

		contentPanel.setLayout(gbl_contentPanel);

		JLabel titulo = new JLabel("Datos personales del empleado:");

		GridBagConstraints gbc_titulo = new GridBagConstraints();

		gbc_titulo.gridwidth = 3;
		gbc_titulo.anchor = GridBagConstraints.NORTHWEST;
		gbc_titulo.insets = new Insets(0, 0, 5, 5);
		gbc_titulo.gridx = 0;
		gbc_titulo.gridy = 0;

		contentPanel.add(titulo, gbc_titulo);

		JLabel nombreLabel = new JLabel("Nombre:");

		GridBagConstraints gbc_nombreLabel = new GridBagConstraints();

		gbc_nombreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nombreLabel.gridx = 0;
		gbc_nombreLabel.gridy = 1;

		contentPanel.add(nombreLabel, gbc_nombreLabel);

		nombreTF = new JTextField();

		GridBagConstraints gbc_nombreTF = new GridBagConstraints();

		gbc_nombreTF.insets = new Insets(0, 0, 5, 5);
		gbc_nombreTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreTF.gridx = 1;
		gbc_nombreTF.gridy = 1;

		contentPanel.add(nombreTF, gbc_nombreTF);

		JLabel apellido1Label = new JLabel("Primer apellido:");

		GridBagConstraints gbc_apellido1Label = new GridBagConstraints();

		gbc_apellido1Label.insets = new Insets(0, 0, 5, 5);
		gbc_apellido1Label.gridx = 2;
		gbc_apellido1Label.gridy = 1;

		contentPanel.add(apellido1Label, gbc_apellido1Label);

		apellido1TF = new JTextField();

		GridBagConstraints gbc_apellido1TF = new GridBagConstraints();

		gbc_apellido1TF.insets = new Insets(0, 0, 5, 5);
		gbc_apellido1TF.fill = GridBagConstraints.HORIZONTAL;
		gbc_apellido1TF.gridx = 3;
		gbc_apellido1TF.gridy = 1;

		contentPanel.add(apellido1TF, gbc_apellido1TF);

		JLabel apellido2Label = new JLabel("Segundo apellido:");

		GridBagConstraints gbc_apellido2Label = new GridBagConstraints();

		gbc_apellido2Label.insets = new Insets(0, 0, 5, 5);
		gbc_apellido2Label.gridx = 4;
		gbc_apellido2Label.gridy = 1;

		contentPanel.add(apellido2Label, gbc_apellido2Label);

		apellido2TF = new JTextField();

		GridBagConstraints gbc_apellido2TF = new GridBagConstraints();

		gbc_apellido2TF.insets = new Insets(0, 0, 5, 0);
		gbc_apellido2TF.fill = GridBagConstraints.HORIZONTAL;
		gbc_apellido2TF.gridx = 5;
		gbc_apellido2TF.gridy = 1;

		contentPanel.add(apellido2TF, gbc_apellido2TF);

		JLabel telefonoLabel = new JLabel("Telefono:");

		GridBagConstraints gbc_telefonoLabel = new GridBagConstraints();

		gbc_telefonoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_telefonoLabel.gridx = 0;
		gbc_telefonoLabel.gridy = 2;

		contentPanel.add(telefonoLabel, gbc_telefonoLabel);

		telefonoTF = new JTextField();

		GridBagConstraints gbc_telefonoTF = new GridBagConstraints();

		gbc_telefonoTF.insets = new Insets(0, 0, 5, 5);
		gbc_telefonoTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_telefonoTF.gridx = 1;
		gbc_telefonoTF.gridy = 2;

		contentPanel.add(telefonoTF, gbc_telefonoTF);

		JLabel emailLabel = new JLabel("Email:");

		GridBagConstraints gbc_emailLabel = new GridBagConstraints();

		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.gridx = 2;
		gbc_emailLabel.gridy = 2;

		contentPanel.add(emailLabel, gbc_emailLabel);

		emailTF = new JTextField();

		GridBagConstraints gbc_emailTF = new GridBagConstraints();

		gbc_emailTF.insets = new Insets(0, 0, 5, 5);
		gbc_emailTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTF.gridx = 3;
		gbc_emailTF.gridy = 2;

		contentPanel.add(emailTF, gbc_emailTF);

		JLabel dniLabel = new JLabel("DNI/NIE:");

		GridBagConstraints gbc_dniLabel = new GridBagConstraints();

		gbc_dniLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dniLabel.gridx = 4;
		gbc_dniLabel.gridy = 2;

		contentPanel.add(dniLabel, gbc_dniLabel);

		dniNieTF = new JTextField();

		GridBagConstraints gbc_dniTF = new GridBagConstraints();

		gbc_dniTF.insets = new Insets(0, 0, 5, 0);
		gbc_dniTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_dniTF.gridx = 5;
		gbc_dniTF.gridy = 2;

		contentPanel.add(dniNieTF, gbc_dniTF);

		JLabel direccionLabel = new JLabel("Direccion:");

		GridBagConstraints gbc_direccionLabel = new GridBagConstraints();

		gbc_direccionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_direccionLabel.gridx = 0;
		gbc_direccionLabel.gridy = 3;

		contentPanel.add(direccionLabel, gbc_direccionLabel);

		direccionTF = new JTextField();

		GridBagConstraints gbc_direccionTF = new GridBagConstraints();

		gbc_direccionTF.gridwidth = 5;
		gbc_direccionTF.insets = new Insets(0, 0, 5, 0);
		gbc_direccionTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_direccionTF.gridx = 1;
		gbc_direccionTF.gridy = 3;

		contentPanel.add(direccionTF, gbc_direccionTF);

		paisComboBox = new JComboBox<>();

		provinciaComboBox = new JComboBox<>();

		ciudadComboBox = new JComboBox<>();

		rolComboBox = new JComboBox<>();

		paisComboBox.setRenderer(new PaisCBRenderer());

		provinciaComboBox.setRenderer(new ProvinciaCBRenderer());

		ciudadComboBox.setRenderer(new LocalidadCBRender());

		rolComboBox.setRenderer(new RolCBRenderer());

		paisComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() != ItemEvent.SELECTED) {

					return;
				}

				Pais pais = (Pais) paisComboBox.getSelectedItem();

				DefaultComboBoxModel<Provincia> model = new DefaultComboBoxModel<>();

				Provincia placeholder = new Provincia();

				placeholder.setId(null);

				placeholder.setNombre("Seleccionar");

				model.addElement(placeholder);

				if (pais != null && pais.getId() != null) {

					try {

						List<Provincia> provincias = provinciaService.findByPais(pais.getId());

						for (Provincia p : provincias) {

							model.addElement(p);
						}

					} catch (Exception ex) {

						ex.printStackTrace();
					}
				}

				provinciaComboBox.setModel(model);

				provinciaComboBox.setSelectedIndex(0);
			}
		});

		provinciaComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() != ItemEvent.SELECTED) {

					return;
				}

				Provincia provincia = (Provincia) provinciaComboBox.getSelectedItem();

				DefaultComboBoxModel<Localidad> model = new DefaultComboBoxModel<>();

				Localidad placeholder = new Localidad();

				placeholder.setId(null);

				placeholder.setNombre("Seleccionar");

				model.addElement(placeholder);

				if (provincia != null && provincia.getId() != null) {

					try {

						List<Localidad> localidades = localidadService.findByProvincia(provincia.getId());

						for (Localidad l : localidades) {

							model.addElement(l);
						}

					} catch (Exception ex) {

						ex.printStackTrace();
					}
				}

				ciudadComboBox.setModel(model);

				ciudadComboBox.setSelectedIndex(0);
			}
		});

		JLabel paisLabel = new JLabel("Pais:");

		GridBagConstraints gbc_paisLabel = new GridBagConstraints();

		gbc_paisLabel.insets = new Insets(0, 0, 5, 5);
		gbc_paisLabel.gridx = 0;
		gbc_paisLabel.gridy = 4;

		contentPanel.add(paisLabel, gbc_paisLabel);

		GridBagConstraints gbc_paisCombo = new GridBagConstraints();

		gbc_paisCombo.insets = new Insets(0, 0, 5, 5);
		gbc_paisCombo.fill = GridBagConstraints.HORIZONTAL;
		gbc_paisCombo.gridx = 1;
		gbc_paisCombo.gridy = 4;

		contentPanel.add(paisComboBox, gbc_paisCombo);

		JLabel provinciaLabel = new JLabel("Provincia:");

		GridBagConstraints gbc_provinciaLabel = new GridBagConstraints();

		gbc_provinciaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_provinciaLabel.gridx = 2;
		gbc_provinciaLabel.gridy = 4;

		contentPanel.add(provinciaLabel, gbc_provinciaLabel);

		GridBagConstraints gbc_provinciaCombo = new GridBagConstraints();

		gbc_provinciaCombo.insets = new Insets(0, 0, 5, 5);
		gbc_provinciaCombo.fill = GridBagConstraints.HORIZONTAL;
		gbc_provinciaCombo.gridx = 3;
		gbc_provinciaCombo.gridy = 4;

		contentPanel.add(provinciaComboBox, gbc_provinciaCombo);

		JLabel ciudadLabel = new JLabel("Ciudad:");

		GridBagConstraints gbc_ciudadLabel = new GridBagConstraints();

		gbc_ciudadLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ciudadLabel.gridx = 4;
		gbc_ciudadLabel.gridy = 4;

		contentPanel.add(ciudadLabel, gbc_ciudadLabel);

		GridBagConstraints gbc_ciudadCombo = new GridBagConstraints();

		gbc_ciudadCombo.insets = new Insets(0, 0, 5, 0);
		gbc_ciudadCombo.fill = GridBagConstraints.HORIZONTAL;
		gbc_ciudadCombo.gridx = 5;
		gbc_ciudadCombo.gridy = 4;

		contentPanel.add(ciudadComboBox, gbc_ciudadCombo);

		JLabel generoLabel = new JLabel("Genero:");

		GridBagConstraints gbc_generoLabel = new GridBagConstraints();

		gbc_generoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_generoLabel.gridx = 0;
		gbc_generoLabel.gridy = 5;

		contentPanel.add(generoLabel, gbc_generoLabel);

		masculinoRB = new JRadioButton("Masculino");

		femeninoRB = new JRadioButton("Femenino");

		otroRB = new JRadioButton("Otro");

		ButtonGroup generoGroup = new ButtonGroup();

		generoGroup.add(masculinoRB);

		generoGroup.add(femeninoRB);

		generoGroup.add(otroRB);

		GridBagConstraints gbc_m = new GridBagConstraints();

		gbc_m.insets = new Insets(0, 0, 5, 5);

		gbc_m.gridx = 1;

		gbc_m.gridy = 5;

		contentPanel.add(masculinoRB, gbc_m);

		GridBagConstraints gbc_f = new GridBagConstraints();

		gbc_f.insets = new Insets(0, 0, 5, 5);

		gbc_f.gridx = 2;

		gbc_f.gridy = 5;

		contentPanel.add(femeninoRB, gbc_f);

		GridBagConstraints gbc_o = new GridBagConstraints();

		gbc_o.insets = new Insets(0, 0, 5, 5);

		gbc_o.gridx = 3;

		gbc_o.gridy = 5;

		contentPanel.add(otroRB, gbc_o);

		JLabel passLabel = new JLabel("Contraseña:");

		GridBagConstraints gbc_passLabel = new GridBagConstraints();

		gbc_passLabel.insets = new Insets(0, 0, 5, 5);

		gbc_passLabel.gridx = 0;

		gbc_passLabel.gridy = 6;

		contentPanel.add(passLabel, gbc_passLabel);

		contrasenaTF = new JTextField();

		GridBagConstraints gbc_passTF = new GridBagConstraints();

		gbc_passTF.insets = new Insets(0, 0, 5, 5);

		gbc_passTF.fill = GridBagConstraints.HORIZONTAL;

		gbc_passTF.gridx = 1;

		gbc_passTF.gridy = 6;

		contentPanel.add(contrasenaTF, gbc_passTF);

		JLabel rolLabel = new JLabel("Rol:");

		GridBagConstraints gbc_rolLabel = new GridBagConstraints();

		gbc_rolLabel.insets = new Insets(0, 0, 5, 5);

		gbc_rolLabel.gridx = 2;

		gbc_rolLabel.gridy = 6;

		contentPanel.add(rolLabel, gbc_rolLabel);

		GridBagConstraints gbc_rolCombo = new GridBagConstraints();

		gbc_rolCombo.insets = new Insets(0, 0, 5, 5);

		gbc_rolCombo.fill = GridBagConstraints.HORIZONTAL;

		gbc_rolCombo.gridx = 3;

		gbc_rolCombo.gridy = 6;

		contentPanel.add(rolComboBox, gbc_rolCombo);

		JPanel buttons = new JPanel();

		add(buttons, BorderLayout.SOUTH);

		agreeButton = new JButton("Guardar");

		cancelButton = new JButton("Cancelar");

		buttons.add(agreeButton);

		buttons.add(cancelButton);
	}

	private void postInitialize() {

		try {

			DefaultComboBoxModel<Pais> paisModel = new DefaultComboBoxModel<>();

			Pais placeholder = new Pais();

			placeholder.setId(null);

			placeholder.setNombre("Seleccionar");

			paisModel.addElement(placeholder);

			for (Pais p : paisService.findAll()) {

				paisModel.addElement(p);
			}

			paisComboBox.setModel(paisModel);

			DefaultComboBoxModel<Rol> rolModel = new DefaultComboBoxModel<>();

			Rol rolPlaceholder = new Rol();

			rolPlaceholder.setId(null);

			rolPlaceholder.setNombre("Seleccionar");

			rolModel.addElement(rolPlaceholder);

			for (Rol r : rolService.findAll()) {

				rolModel.addElement(r);
			}

			rolComboBox.setModel(rolModel);

			setAgreeController(new EmpleadoCreateController(this));

		} catch (Exception e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	public void setAgreeController(Controller controller) {

		for (java.awt.event.ActionListener al : agreeButton.getActionListeners()) {

			agreeButton.removeActionListener(al);
		}

		agreeButton.addActionListener(controller);

		agreeButton.setText(controller.getValue(javax.swing.Action.NAME).toString());
	}

	public Empleado getModel() {

		Empleado empleado = new Empleado();

		empleado.setId(empleadoId);

		empleado.setNombre(nombreTF.getText());

		empleado.setPrimerApellido(apellido1TF.getText());

		empleado.setSegundoApellido(apellido2TF.getText());

		empleado.setTelefono(telefonoTF.getText());

		empleado.setEmail(emailTF.getText());

		empleado.setDniNie(dniNieTF.getText());

		empleado.setDireccion(direccionTF.getText());

		empleado.setPassword(contrasenaTF.getText());

		Localidad localidad = (Localidad) ciudadComboBox.getSelectedItem();

		if (localidad != null) {

			empleado.setLocalidadId(localidad.getId());
		}

		Rol rol = (Rol) rolComboBox.getSelectedItem();

		if (rol != null) {

			empleado.setRolId(rol.getId());
		}

		if (masculinoRB.isSelected()) {

			empleado.setGeneroId(Genero.MASCULINO.longValue());

		} else if (femeninoRB.isSelected()) {

			empleado.setGeneroId(Genero.FEMENINO.longValue());

		} else if (otroRB.isSelected()) {

			empleado.setGeneroId(Genero.OTRO_LONG);
		}

		return empleado;
	}

	public void setEditable(boolean editable) {

		nombreTF.setEditable(editable);

		apellido1TF.setEditable(editable);

		apellido2TF.setEditable(editable);

		telefonoTF.setEditable(editable);

		emailTF.setEditable(editable);

		dniNieTF.setEditable(editable);

		direccionTF.setEditable(editable);

		contrasenaTF.setEditable(editable);

		paisComboBox.setEnabled(editable);

		provinciaComboBox.setEnabled(editable);

		ciudadComboBox.setEnabled(editable);

		rolComboBox.setEnabled(editable);

		masculinoRB.setEnabled(editable);

		femeninoRB.setEnabled(editable);

		otroRB.setEnabled(editable);
	}

	public void setCancelController(Controller controller) {

		for (java.awt.event.ActionListener al : cancelButton.getActionListeners()) {

			cancelButton.removeActionListener(al);
		}

		cancelButton.addActionListener(controller);

		cancelButton.setText(controller.getValue(javax.swing.Action.NAME).toString());
	}

	public JButton getAgreeButton() {

		return agreeButton;
	}

	public void setEmpleadoDTO(EmpleadoDTO empleado) {

		this.empleadoId = empleado.getId();

		nombreTF.setText(empleado.getNombre());

		apellido1TF.setText(empleado.getPrimerApellido());

		apellido2TF.setText(empleado.getSegundoApellido());

		telefonoTF.setText(empleado.getTelefono());

		emailTF.setText(empleado.getEmail());

		dniNieTF.setText(empleado.getDniNie());

		direccionTF.setText(empleado.getDireccion());

		contrasenaTF.setText(empleado.getPassword());

		if (empleado.getGeneroId() != null) {

			if (empleado.getGeneroId().equals(Genero.MASCULINO.longValue())) {

				masculinoRB.setSelected(true);

			} else if (empleado.getGeneroId().equals(Genero.FEMENINO.longValue())) {

				femeninoRB.setSelected(true);

			} else {

				otroRB.setSelected(true);
			}
		}

		for (int i = 0; i < rolComboBox.getItemCount(); i++) {

			Rol rol = rolComboBox.getItemAt(i);

			if (rol.getId() != null && rol.getId().equals(empleado.getRolId())) {

				rolComboBox.setSelectedIndex(i);

				break;
			}
		}

		try {

			if (empleado.getLocalidadId() != null) {

				Localidad localidad = localidadService.findById(empleado.getLocalidadId());

				if (localidad != null) {

					for (int i = 0; i < provinciaComboBox.getItemCount(); i++) {

						Provincia provincia = provinciaComboBox.getItemAt(i);

						if (provincia.getId() != null && provincia.getId().equals(localidad.getProvinceId())) {

							provinciaComboBox.setSelectedIndex(i);

							break;
						}
					}

					for (int i = 0; i < ciudadComboBox.getItemCount(); i++) {

						Localidad loc = ciudadComboBox.getItemAt(i);

						if (loc.getId() != null && loc.getId().equals(localidad.getId())) {

							ciudadComboBox.setSelectedIndex(i);

							break;
						}
					}
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}