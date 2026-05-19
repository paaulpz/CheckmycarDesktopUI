package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.paula.checkmc.model.Cita;
import com.paula.checkmc.model.CitaDTO;
import com.paula.checkmc.model.ClienteCriteria;
import com.paula.checkmc.model.ClienteDTO;
import com.paula.checkmc.model.CocheCriteria;
import com.paula.checkmc.model.CocheDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.ClienteService;
import com.paula.checkmc.service.CocheService;
import com.paula.checkmc.service.impl.ClienteServiceImpl;
import com.paula.checkmc.service.impl.CocheServiceImpl;
import com.paula.checkmycar.desktop.controller.CitaCreateController;
import com.paula.checkmycar.desktop.controller.CitaSetEditableController;
import com.paula.checkmycar.desktop.controller.Controller;
import com.paula.checkmycar.desktop.views.renderer.ClienteCBRenderer;
import com.paula.checkmycar.desktop.views.renderer.CocheCBRenderer;
import com.toedter.calendar.JDateChooser;

public class CitaCreateView extends View {

	private JDateChooser dateChooser;

	private JTextField descripcionTF;

	private JComboBox<ClienteDTO> clienteCB;

	private JComboBox<CocheDTO> cocheCB;

	private JButton guardarButton;

	private JButton cancelarButton;

	private Long citaId;

	private ClienteService clienteService;

	private CocheService cocheService;

	public CitaCreateView() {

		clienteService = new ClienteServiceImpl();

		cocheService = new CocheServiceImpl();

		initialize();

		postInitialize();
	}

	private void initialize() {

		setName("Crear cita");

		setLayout(new BorderLayout());

		JPanel contentPanel = new JPanel(new GridBagLayout());

		add(contentPanel, BorderLayout.NORTH);

		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(10, 10, 5, 5);

		c.fill = GridBagConstraints.HORIZONTAL;

		int y = 0;

		c.gridx = 0;
		c.gridy = y;

		contentPanel.add(new JLabel("Fecha *:"), c);

		dateChooser = new JDateChooser();

		dateChooser.setDateFormatString("dd/MM/yyyy");

		c.gridx = 1;

		contentPanel.add(dateChooser, c);

		y++;

		c.gridx = 0;
		c.gridy = y;

		contentPanel.add(new JLabel("Cliente *:"), c);

		clienteCB = new JComboBox<>();

		c.gridx = 1;

		contentPanel.add(clienteCB, c);

		c.gridx = 2;

		contentPanel.add(new JLabel("Coche *:"), c);

		cocheCB = new JComboBox<>();

		c.gridx = 3;

		contentPanel.add(cocheCB, c);

		y++;

		c.gridx = 0;
		c.gridy = y;

		contentPanel.add(new JLabel("Descripción:"), c);

		descripcionTF = new JTextField();

		c.gridx = 1;

		c.gridwidth = 3;

		contentPanel.add(descripcionTF, c);

		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		add(buttonsPanel, BorderLayout.SOUTH);

		cancelarButton = new JButton("Cancelar");

		guardarButton = new JButton("Guardar");

		buttonsPanel.add(cancelarButton);

		buttonsPanel.add(guardarButton);

		guardarButton.addActionListener(new CitaCreateController(this));

		clienteCB.addActionListener(e -> cargarCoches());
	}

	private void postInitialize() {

		try {

			clienteCB.setRenderer(new ClienteCBRenderer());

			cocheCB.setRenderer(new CocheCBRenderer());

			cargarClientes();

			inicializarCoches();

		} catch (Exception e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(this, "Error cargando datos", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cargarClientes() throws Exception {

		Results<ClienteDTO> results = clienteService.findByCriteria(new ClienteCriteria(), 1, 1000);

		DefaultComboBoxModel<ClienteDTO> model = new DefaultComboBoxModel<>();

		ClienteDTO placeholder = new ClienteDTO();

		placeholder.setId(null);

		model.addElement(placeholder);

		for (ClienteDTO cliente : results.getPage()) {

			model.addElement(cliente);
		}

		clienteCB.setModel(model);
	}

	private void inicializarCoches() {

		DefaultComboBoxModel<CocheDTO> model = new DefaultComboBoxModel<>();

		CocheDTO placeholder = new CocheDTO();

		placeholder.setId(null);

		model.addElement(placeholder);

		cocheCB.setModel(model);
	}

	private void cargarCoches() {

		ClienteDTO cliente = (ClienteDTO) clienteCB.getSelectedItem();

		DefaultComboBoxModel<CocheDTO> model = new DefaultComboBoxModel<>();

		CocheDTO placeholder = new CocheDTO();

		placeholder.setId(null);

		model.addElement(placeholder);

		try {

			if (cliente != null && cliente.getId() != null) {

				CocheCriteria criteria = new CocheCriteria();

				criteria.setClienteId(cliente.getId());

				Results<CocheDTO> results = cocheService.findByCriteria(criteria, 1, 1000);

				for (CocheDTO coche : results.getPage()) {

					model.addElement(coche);
				}
			}

			cocheCB.setModel(model);

		} catch (Exception e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(this, "Error cargando coches", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public Cita getModel() {

		if (dateChooser.getDate() == null) {

			JOptionPane.showMessageDialog(this, "La fecha es obligatoria.", "Error", JOptionPane.ERROR_MESSAGE);

			return null;
		}

		ClienteDTO cliente = (ClienteDTO) clienteCB.getSelectedItem();

		if (cliente == null || cliente.getId() == null) {

			JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente.", "Error", JOptionPane.ERROR_MESSAGE);

			return null;
		}

		CocheDTO coche = (CocheDTO) cocheCB.getSelectedItem();

		if (coche == null || coche.getId() == null) {

			JOptionPane.showMessageDialog(this, "Debe seleccionar un coche.", "Error", JOptionPane.ERROR_MESSAGE);

			return null;
		}

		LocalDateTime fecha = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		Cita cita = new Cita();

		cita.setId(citaId);

		cita.setFecha(fecha);

		cita.setDescripcion(descripcionTF.getText().trim());

		cita.setClienteId(cliente.getId());

		cita.setCocheId(coche.getId());

		return cita;
	}

	public void setCitaDTO(CitaDTO cita) {

		citaId = cita.getId();

		if (cita.getFecha() != null) {

			Date fecha = Date.from(cita.getFecha().atZone(ZoneId.systemDefault()).toInstant());

			dateChooser.setDate(fecha);
		}

		descripcionTF.setText(cita.getDescripcion());

		for (int i = 0; i < clienteCB.getItemCount(); i++) {

			ClienteDTO cliente = clienteCB.getItemAt(i);

			if (cliente.getId() != null && cliente.getId().equals(cita.getClienteId())) {

				clienteCB.setSelectedIndex(i);

				break;
			}
		}

		cargarCoches();

		for (int i = 0; i < cocheCB.getItemCount(); i++) {

			CocheDTO coche = cocheCB.getItemAt(i);

			if (coche.getId() != null && coche.getId().equals(cita.getCocheId())) {

				cocheCB.setSelectedIndex(i);

				break;
			}
		}

		setEditable(false);

		setAgreeController(new CitaSetEditableController(this));
	}

	public void setEditable(boolean editable) {

		dateChooser.setEnabled(editable);

		descripcionTF.setEditable(editable);

		clienteCB.setEnabled(editable);

		cocheCB.setEnabled(editable);
	}

	public void setAgreeController(Controller controller) {

		guardarButton.setAction(controller);
	}

	public void setCancelController(Controller controller) {

		cancelarButton.setAction(controller);
	}
}