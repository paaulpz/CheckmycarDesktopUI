package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.ZoneId;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.paula.checkmc.model.ClienteCriteria;
import com.paula.checkmc.model.ClienteDTO;
import com.paula.checkmc.model.EstadoPresupuesto;
import com.paula.checkmc.model.LineaPresupuestoDTO;
import com.paula.checkmc.model.PresupuestoDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.ClienteService;
import com.paula.checkmc.service.EstadoPresupuestoService;
import com.paula.checkmc.service.impl.ClienteServiceImpl;
import com.paula.checkmc.service.impl.EstadoPresupuestoServiceImpl;
import com.paula.checkmycar.desktop.controller.Controller;
import com.paula.checkmycar.desktop.controller.PresupuestoCreateController;
import com.paula.checkmycar.desktop.controller.PresupuestoSetEditableController;
import com.paula.checkmycar.desktop.views.renderer.ClienteCBRenderer;
import com.paula.checkmycar.desktop.views.renderer.EstadoPresupuestoCBRender;
import com.paula.checkmycar.desktop.views.tableModel.LineaPresupuestoTableModel;
import com.toedter.calendar.JDateChooser;

public class PresupuestoCreateView extends View {

	private Long presupuestoId;

	private JTextField nombreTF;

	private JDateChooser fechaChooser;

	private JComboBox<ClienteDTO> clienteCB;

	private JComboBox<EstadoPresupuesto> estadoCB;

	private JTable lineasTable;

	private LineaPresupuestoTableModel lineasTableModel;

	private JButton añadirLineaButton;

	private JButton eliminarLineaButton;

	private JLabel totalLabel;

	private JButton guardarButton;

	private JButton cancelarButton;

	private ClienteService clienteService;

	private EstadoPresupuestoService estadoService;

	public PresupuestoCreateView() {

		clienteService = new ClienteServiceImpl();

		estadoService = new EstadoPresupuestoServiceImpl();

		initialize();

		postInitialize();

		setAgreeController(new PresupuestoCreateController(this));
	}

	private void initialize() {

		setName("Crear presupuesto");

		setLayout(new BorderLayout());

		JPanel formPanel = new JPanel(new GridBagLayout());

		add(formPanel, BorderLayout.NORTH);

		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(5, 5, 5, 5);

		c.fill = GridBagConstraints.HORIZONTAL;

		int y = 0;

		c.gridx = 0;
		c.gridy = y;

		formPanel.add(new JLabel("Nombre *:"), c);

		nombreTF = new JTextField();

		c.gridx = 1;

		formPanel.add(nombreTF, c);

		c.gridx = 2;

		formPanel.add(new JLabel("Fecha *:"), c);

		fechaChooser = new JDateChooser();

		fechaChooser.setDateFormatString("dd/MM/yyyy");

		c.gridx = 3;

		formPanel.add(fechaChooser, c);

		y++;

		c.gridx = 0;
		c.gridy = y;

		formPanel.add(new JLabel("Cliente *:"), c);

		clienteCB = new JComboBox<>();

		c.gridx = 1;

		formPanel.add(clienteCB, c);

		c.gridx = 2;

		formPanel.add(new JLabel("Estado *:"), c);

		estadoCB = new JComboBox<>();

		c.gridx = 3;

		formPanel.add(estadoCB, c);

		lineasTableModel = new LineaPresupuestoTableModel();

		lineasTable = new JTable(lineasTableModel);

		lineasTable.setRowHeight(25);

		JScrollPane scrollPane = new JScrollPane(lineasTable);

		add(scrollPane, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel(new BorderLayout());

		add(bottomPanel, BorderLayout.SOUTH);

		JPanel lineasButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		añadirLineaButton = new JButton("Añadir línea");

		eliminarLineaButton = new JButton("Eliminar línea");

		lineasButtonsPanel.add(añadirLineaButton);

		lineasButtonsPanel.add(eliminarLineaButton);

		bottomPanel.add(lineasButtonsPanel, BorderLayout.WEST);

		JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		totalLabel = new JLabel("Total: 0.00 €");

		totalPanel.add(totalLabel);

		bottomPanel.add(totalPanel, BorderLayout.CENTER);

		JPanel actionButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		cancelarButton = new JButton("Cancelar");

		guardarButton = new JButton("Guardar");

		actionButtonsPanel.add(cancelarButton);

		actionButtonsPanel.add(guardarButton);

		bottomPanel.add(actionButtonsPanel, BorderLayout.SOUTH);

		añadirLineaButton.addActionListener(e -> abrirLinea());

		eliminarLineaButton.addActionListener(e -> eliminarLinea());
	}

	private void postInitialize() {

		try {

			clienteCB.setRenderer(new ClienteCBRenderer());

			estadoCB.setRenderer(new EstadoPresupuestoCBRender());

			cargarClientes();

			cargarEstados();

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

	private void cargarEstados() throws Exception {

		DefaultComboBoxModel<EstadoPresupuesto> model = new DefaultComboBoxModel<>();

		for (EstadoPresupuesto estado : estadoService.findAll()) {

			model.addElement(estado);
		}

		estadoCB.setModel(model);
	}

	private void abrirLinea() {

		LineaPresupuestoCreateView dialog = new LineaPresupuestoCreateView();

		dialog.setVisible(true);

		LineaPresupuestoDTO linea = dialog.getLinea();

		if (linea != null) {

			lineasTableModel.addLinea(linea);

			actualizarTotal();
		}
	}

	private void eliminarLinea() {

		int row = lineasTable.getSelectedRow();

		if (row < 0) {

			JOptionPane.showMessageDialog(this, "Seleccione una línea");

			return;
		}

		lineasTableModel.removeLinea(row);

		actualizarTotal();
	}

	private void actualizarTotal() {

		double total = lineasTableModel.getTotal();

		totalLabel.setText("Total: " + total + " €");
	}

	public PresupuestoDTO getModel() {

		PresupuestoDTO dto = new PresupuestoDTO();

		dto.setId(presupuestoId);

		dto.setNombre(nombreTF.getText().trim());

		if (fechaChooser.getDate() != null) {

			dto.setFecha(fechaChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		}

		ClienteDTO cliente = (ClienteDTO) clienteCB.getSelectedItem();

		if (cliente != null && cliente.getId() != null) {

			dto.setClienteId(cliente.getId());
		}

		EstadoPresupuesto estado = (EstadoPresupuesto) estadoCB.getSelectedItem();

		if (estado != null && estado.getId() != null) {

			dto.setEstadoPresupuestoId(estado.getId());
		}

		dto.setPrecioFinal(lineasTableModel.getTotal());

		return dto;
	}

	public void setPresupuestoDTO(PresupuestoDTO presupuesto) {

		this.presupuestoId = presupuesto.getId();

		nombreTF.setText(presupuesto.getNombre());

		if (presupuesto.getFecha() != null) {

			fechaChooser.setDate(java.sql.Date.valueOf(presupuesto.getFecha()));
		}

		for (int i = 0; i < clienteCB.getItemCount(); i++) {

			ClienteDTO cliente = clienteCB.getItemAt(i);

			if (cliente.getId() != null && cliente.getId().equals(presupuesto.getClienteId())) {

				clienteCB.setSelectedIndex(i);

				break;
			}
		}

		for (int i = 0; i < estadoCB.getItemCount(); i++) {

			EstadoPresupuesto estado = estadoCB.getItemAt(i);

			if (estado.getId() != null && estado.getId().equals(presupuesto.getEstadoPresupuestoId())) {

				estadoCB.setSelectedIndex(i);

				break;
			}
		}

		totalLabel.setText("Total: " + presupuesto.getPrecioFinal() + " €");

		setEditable(false);

		setAgreeController(new PresupuestoSetEditableController(this));
	}

	public LineaPresupuestoTableModel getLineasTableModel() {

		return lineasTableModel;
	}

	public void setAgreeController(Controller controller) {

		guardarButton.setAction(controller);
	}

	public void setCancelController(Controller controller) {

		cancelarButton.setAction(controller);
	}

	public void setEditable(boolean editable) {

		nombreTF.setEditable(editable);

		fechaChooser.setEnabled(editable);

		clienteCB.setEnabled(editable);

		estadoCB.setEnabled(editable);

		lineasTable.setEnabled(editable);

		añadirLineaButton.setEnabled(editable);

		eliminarLineaButton.setEnabled(editable);

		guardarButton.setEnabled(true);
	}
}