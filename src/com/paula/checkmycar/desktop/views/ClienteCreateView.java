package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;

import com.paula.checkmc.model.Cliente;
import com.paula.checkmc.model.ClienteDTO;
import com.paula.checkmc.model.Genero;
import com.paula.checkmc.model.Localidad;
import com.paula.checkmc.model.Pais;
import com.paula.checkmc.model.Provincia;
import com.paula.checkmc.service.LocalidadService;
import com.paula.checkmc.service.PaisService;
import com.paula.checkmc.service.ProvinciaService;
import com.paula.checkmc.service.impl.LocalidadServiceImpl;
import com.paula.checkmc.service.impl.PaisServiceImpl;
import com.paula.checkmc.service.impl.ProvinciaServiceImpl;
import com.paula.checkmycar.desktop.controller.ClienteCreateController;
import com.paula.checkmycar.desktop.controller.ClienteSetEditableController;
import com.paula.checkmycar.desktop.controller.Controller;
import com.paula.checkmycar.desktop.views.renderer.LocalidadCBRender;
import com.paula.checkmycar.desktop.views.renderer.PaisCBRenderer;
import com.paula.checkmycar.desktop.views.renderer.ProvinciaCBRenderer;

public class ClienteCreateView extends View {

	private JTextField nombreTF;
	private JTextField apellido1TF;
	private JTextField apellido2TF;
	private JTextField telefonoTF;
	private JTextField emailTf;
	private JTextField dniNieTF;
	private JTextField direccionTF;
	private JTextField contrasenaTF;

	private JComboBox<Pais> paisComboBox;
	private JComboBox<Provincia> provinciaComboBox;
	private JComboBox<Localidad> ciudadComboBox;

	private JRadioButton masculinoRB;
	private JRadioButton femeninoRB;
	private JRadioButton otroRB;

	private JButton agreeButton;
	private JButton cancelButton;

	private Long clienteId;

	private PaisService paisService;
	private ProvinciaService provinciaService;
	private LocalidadService localidadService;

	public ClienteCreateView() {

		initServices();

		initialize();

		postInitialize();
	}

	private void initServices() {

		paisService = new PaisServiceImpl();

		provinciaService = new ProvinciaServiceImpl();

		localidadService = new LocalidadServiceImpl();
	}

	private void initialize() {

		setName("Registro de cliente");

		setLayout(new BorderLayout(0, 0));

		JPanel contentPanel = new JPanel();

		add(contentPanel, BorderLayout.WEST);

		GridBagLayout gbl_contentPanel = new GridBagLayout();

		gbl_contentPanel.columnWidths = new int[] { 64, 123, 0, 51, 0, 71, 0 };

		gbl_contentPanel.rowHeights = new int[] { 13, 19, 0, 0, 0, 0, 0, 0, 0 };

		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };

		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };

		contentPanel.setLayout(gbl_contentPanel);

		JLabel titulo = new JLabel("Datos personales del cliente : ");

		GridBagConstraints gbc_titulo = new GridBagConstraints();

		gbc_titulo.gridwidth = 3;
		gbc_titulo.anchor = GridBagConstraints.NORTHWEST;
		gbc_titulo.insets = new Insets(0, 0, 5, 5);
		gbc_titulo.gridx = 0;
		gbc_titulo.gridy = 0;

		contentPanel.add(titulo, gbc_titulo);

		JLabel nombreLabel = new JLabel("Nombre: ");

		GridBagConstraints gbc_nombreLabel = new GridBagConstraints();

		gbc_nombreLabel.anchor = GridBagConstraints.EAST;
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

		nombreTF.setColumns(10);

		JLabel apellido1Label = new JLabel("Primer apellido: ");

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

		apellido1TF.setColumns(10);

		JLabel apellido2Label = new JLabel("Segundo apellido: ");

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

		apellido2TF.setColumns(10);

		JLabel telefonoLabel = new JLabel("Telefono: ");

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

		telefonoTF.setColumns(10);

		JLabel emailLabel = new JLabel("Email: ");

		GridBagConstraints gbc_emailLabel = new GridBagConstraints();

		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);

		gbc_emailLabel.gridx = 2;
		gbc_emailLabel.gridy = 2;

		contentPanel.add(emailLabel, gbc_emailLabel);

		emailTf = new JTextField();

		GridBagConstraints gbc_emailTF = new GridBagConstraints();

		gbc_emailTF.insets = new Insets(0, 0, 5, 5);

		gbc_emailTF.fill = GridBagConstraints.HORIZONTAL;

		gbc_emailTF.gridx = 3;
		gbc_emailTF.gridy = 2;

		contentPanel.add(emailTf, gbc_emailTF);

		emailTf.setColumns(10);

		JLabel dniLabel = new JLabel("DNI/NIE: ");

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

		dniNieTF.setColumns(10);

		JLabel direccionLabel = new JLabel("Direccion: ");

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

		direccionTF.setColumns(10);

		paisComboBox = new JComboBox<>();

		paisComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() != ItemEvent.SELECTED) {

					return;
				}

				Pais pais = (Pais) paisComboBox.getSelectedItem();

				DefaultComboBoxModel<Provincia> model = new DefaultComboBoxModel<>();

				Provincia provinciaDefault = new Provincia();

				provinciaDefault.setId(null);

				provinciaDefault.setNombre("Seleccionar");

				model.addElement(provinciaDefault);

				if (pais != null && pais.getId() != null) {

					try {

						List<Provincia> provincias = provinciaService.findByPais(pais.getId());

						for (Provincia p : provincias) {

							model.addElement(p);
						}

					} catch (Exception ex) {

						ex.printStackTrace();

						JOptionPane.showMessageDialog(null, "Error cargando provincias");
					}
				}

				provinciaComboBox.setModel(model);

				provinciaComboBox.setRenderer(new ProvinciaCBRenderer());
			}
		});

		provinciaComboBox = new JComboBox<>();

		provinciaComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() != ItemEvent.SELECTED) {

					return;
				}

				Provincia provincia = (Provincia) provinciaComboBox.getSelectedItem();

				DefaultComboBoxModel<Localidad> model = new DefaultComboBoxModel<>();

				Localidad localidadDefault = new Localidad();

				localidadDefault.setId(null);

				localidadDefault.setNombre("Seleccionar");

				model.addElement(localidadDefault);

				if (provincia != null && provincia.getId() != null) {

					try {

						List<Localidad> localidades = localidadService.findByProvincia(provincia.getId());

						for (Localidad l : localidades) {

							model.addElement(l);
						}

					} catch (Exception ex) {

						ex.printStackTrace();

						JOptionPane.showMessageDialog(null, "Error cargando localidades");
					}
				}

				ciudadComboBox.setModel(model);

				ciudadComboBox.setRenderer(new LocalidadCBRender());

				ciudadComboBox.setSelectedIndex(0);
			}
		});

		ciudadComboBox = new JComboBox<>();
		JLabel paisLabel = new JLabel("Pais: ");

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

		JLabel provinciaLabel = new JLabel("Provincia: ");

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

		JLabel ciudadLabel = new JLabel("Ciudad: ");

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

		JLabel generoLabel = new JLabel("Genero: ");

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

		GridBagConstraints gbc_masculino = new GridBagConstraints();

		gbc_masculino.insets = new Insets(0, 0, 5, 5);

		gbc_masculino.gridx = 1;
		gbc_masculino.gridy = 5;

		contentPanel.add(masculinoRB, gbc_masculino);

		GridBagConstraints gbc_femenino = new GridBagConstraints();

		gbc_femenino.insets = new Insets(0, 0, 5, 5);

		gbc_femenino.gridx = 2;
		gbc_femenino.gridy = 5;

		contentPanel.add(femeninoRB, gbc_femenino);

		GridBagConstraints gbc_otro = new GridBagConstraints();

		gbc_otro.insets = new Insets(0, 0, 5, 5);

		gbc_otro.gridx = 3;
		gbc_otro.gridy = 5;

		contentPanel.add(otroRB, gbc_otro);

		JLabel passwordLabel = new JLabel("Contraseña: ");

		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();

		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);

		gbc_passwordLabel.gridx = 0;
		gbc_passwordLabel.gridy = 6;

		contentPanel.add(passwordLabel, gbc_passwordLabel);

		contrasenaTF = new JTextField();

		GridBagConstraints gbc_passwordTF = new GridBagConstraints();

		gbc_passwordTF.insets = new Insets(0, 0, 5, 5);

		gbc_passwordTF.fill = GridBagConstraints.HORIZONTAL;

		gbc_passwordTF.gridx = 1;
		gbc_passwordTF.gridy = 6;

		contentPanel.add(contrasenaTF, gbc_passwordTF);

		contrasenaTF.setColumns(10);

		JPanel buttons = new JPanel();

		add(buttons, BorderLayout.SOUTH);

		agreeButton = new JButton("Guardar");

		cancelButton = new JButton("Cancelar");

		buttons.add(agreeButton);

		buttons.add(cancelButton);
	}

	private void postInitialize() {

		try {

			List<Pais> paises = paisService.findAll();

			DefaultComboBoxModel<Pais> model = new DefaultComboBoxModel<>();

			Pais paisDefault = new Pais();

			paisDefault.setId(null);

			paisDefault.setNombre("Seleccionar");

			model.addElement(paisDefault);

			for (Pais pais : paises) {

				model.addElement(pais);
			}

			paisComboBox.setModel(model);

			paisComboBox.setRenderer(new PaisCBRenderer());

			setAgreeController(new ClienteCreateController(this));

		} catch (Exception e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	public Cliente getModel() {

		if (dniNieTF.getText().trim().isEmpty()) {

			JOptionPane.showMessageDialog(this, "El DNI es obligatorio");

			return null;
		}

		Cliente cliente = new Cliente();

		cliente.setId(clienteId);

		cliente.setNombre(nombreTF.getText());

		cliente.setPrimerApellido(apellido1TF.getText());

		cliente.setSegundoApellido(apellido2TF.getText());

		cliente.setTelefono(telefonoTF.getText());

		cliente.setEmail(emailTf.getText());

		cliente.setDniNie(dniNieTF.getText());

		cliente.setDireccion(direccionTF.getText());

		cliente.setPassword(contrasenaTF.getText());

		Localidad localidad = (Localidad) ciudadComboBox.getSelectedItem();

		if (localidad != null) {

			cliente.setLocalidadId(localidad.getId());
		}

		if (masculinoRB.isSelected()) {

			cliente.setGeneroId(Genero.MASCULINO.longValue());

		} else if (femeninoRB.isSelected()) {

			cliente.setGeneroId(Genero.FEMENINO.longValue());

		} else if (otroRB.isSelected()) {

			cliente.setGeneroId(Genero.OTRO_LONG);
		}

		return cliente;
	}

	public void setAgreeController(Controller controller) {

		agreeButton.setAction(controller);
	}

	public void setCancelController(Controller controller) {

		cancelButton.setAction(controller);
	}

	public void setEditable(boolean editable) {

		nombreTF.setEditable(editable);
		apellido1TF.setEditable(editable);
		apellido2TF.setEditable(editable);
		telefonoTF.setEditable(editable);
		emailTf.setEditable(editable);
		dniNieTF.setEditable(editable);
		direccionTF.setEditable(editable);
		contrasenaTF.setEditable(editable);

		paisComboBox.setEnabled(editable);
		provinciaComboBox.setEnabled(editable);
		ciudadComboBox.setEnabled(editable);

		masculinoRB.setEnabled(editable);
		femeninoRB.setEnabled(editable);
		otroRB.setEnabled(editable);
	}

	public void setClienteDTO(ClienteDTO cliente) {

		try {

			nombreTF.setText(cliente.getNombre());

			apellido1TF.setText(cliente.getPrimerApellido());

			apellido2TF.setText(cliente.getSegundoApellido());

			telefonoTF.setText(cliente.getTelefono());

			emailTf.setText(cliente.getEmail());

			dniNieTF.setText(cliente.getDniNie());

			direccionTF.setText(cliente.getDireccion());

			clienteId = cliente.getId();

			if (cliente.getGeneroId() != null) {

				if (cliente.getGeneroId().equals(Genero.MASCULINO.longValue())) {

					masculinoRB.setSelected(true);

				} else if (cliente.getGeneroId().equals(Genero.FEMENINO.longValue())) {

					femeninoRB.setSelected(true);

				} else if (cliente.getGeneroId().equals(Genero.OTRO_LONG)) {

					otroRB.setSelected(true);
				}
			}

			if (cliente.getLocalidadId() != null) {

				Localidad localidad = localidadService.findById(cliente.getLocalidadId());

				if (localidad != null) {

					Provincia provincia = provinciaService.findById(localidad.getProvinceId());

					if (provincia != null) {

						Pais pais = paisService.findById(provincia.getPaisId());

						if (pais != null) {

							for (int i = 0; i < paisComboBox.getItemCount(); i++) {

								Pais p = paisComboBox.getItemAt(i);

								if (p.getId() != null && p.getId().equals(pais.getId())) {

									paisComboBox.setSelectedIndex(i);

									break;
								}
							}
						}

						for (int i = 0; i < provinciaComboBox.getItemCount(); i++) {

							Provincia p = provinciaComboBox.getItemAt(i);

							if (p.getId() != null && p.getId().equals(provincia.getId())) {

								provinciaComboBox.setSelectedIndex(i);

								break;
							}
						}
					}

					for (int i = 0; i < ciudadComboBox.getItemCount(); i++) {

						Localidad l = ciudadComboBox.getItemAt(i);

						if (l.getId() != null && l.getId().equals(cliente.getLocalidadId())) {

							ciudadComboBox.setSelectedIndex(i);

							break;
						}
					}
				}
			}

			setEditable(false);

			setAgreeController(new ClienteSetEditableController(this));

		} catch (Exception e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
}