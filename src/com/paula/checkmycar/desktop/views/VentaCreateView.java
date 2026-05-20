package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.paula.checkmc.model.ClienteCriteria;
import com.paula.checkmc.model.ClienteDTO;
import com.paula.checkmc.model.CocheCriteria;
import com.paula.checkmc.model.CocheDTO;
import com.paula.checkmc.model.EmpleadoCriteria;
import com.paula.checkmc.model.EmpleadoDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.model.VentaDTO;
import com.paula.checkmc.service.ClienteService;
import com.paula.checkmc.service.CocheService;
import com.paula.checkmc.service.EmpleadoService;
import com.paula.checkmc.service.VentaService;
import com.paula.checkmc.service.impl.ClienteServiceImpl;
import com.paula.checkmc.service.impl.CocheServiceImpl;
import com.paula.checkmc.service.impl.EmpleadoServiceImpl;
import com.paula.checkmc.service.impl.VentaServiceImpl;
import com.toedter.calendar.JDateChooser;

public class VentaCreateView extends View {

	private JDateChooser dateChooser;
	private JTextField precioClienteTF;
	private JTextField precioFinalTF;

	private JComboBox<ClienteDTO> compradorCB;
	private JComboBox<ClienteDTO> vendedorCB;
	private JComboBox<EmpleadoDTO> empleadoCB;
	private JComboBox<CocheDTO> cocheCB;

	private JButton guardarButton;
	private JButton cancelarButton;

	private Long ventaId;

	private ClienteService clienteService = new ClienteServiceImpl();
	private EmpleadoService empleadoService = new EmpleadoServiceImpl();
	private CocheService cocheService = new CocheServiceImpl();
	private VentaService ventaService = new VentaServiceImpl();

	public VentaCreateView() {
		initialize();
		postInitialize();
	}

	private void initialize() {

		setName("Crear venta");

		setLayout(new BorderLayout());

		JPanel formPanel = new JPanel(new GridBagLayout());

		add(formPanel, BorderLayout.NORTH);

		int y = 0;

		y++;

		y++;

		y++;

		GridBagConstraints gbcFechaLabel = new GridBagConstraints();

		gbcFechaLabel.insets = new Insets(5, 5, 5, 5);

		gbcFechaLabel.anchor = GridBagConstraints.EAST;

		gbcFechaLabel.gridx = 0;

		gbcFechaLabel.gridy = 1;

		JLabel label_5 = new JLabel("Fecha *:");
		formPanel.add(label_5, gbcFechaLabel);

		dateChooser = new JDateChooser();

		dateChooser.setDateFormatString("dd/MM/yyyy");

		GridBagConstraints gbcFechaChooser = new GridBagConstraints();

		gbcFechaChooser.insets = new Insets(5, 5, 5, 5);

		gbcFechaChooser.fill = GridBagConstraints.HORIZONTAL;

		gbcFechaChooser.weightx = 1.0;

		gbcFechaChooser.gridx = 1;

		gbcFechaChooser.gridy = 1;

		formPanel.add(dateChooser, gbcFechaChooser);

		GridBagConstraints gbcEmpleadoLabel = new GridBagConstraints();

		gbcEmpleadoLabel.insets = new Insets(5, 5, 5, 5);

		gbcEmpleadoLabel.anchor = GridBagConstraints.EAST;

		gbcEmpleadoLabel.gridx = 2;

		gbcEmpleadoLabel.gridy = 1;

		JLabel label_4 = new JLabel("Empleado *:");
		formPanel.add(label_4, gbcEmpleadoLabel);

		empleadoCB = new JComboBox<>();

		GridBagConstraints gbcEmpleadoCB = new GridBagConstraints();

		gbcEmpleadoCB.insets = new Insets(5, 5, 5, 0);

		gbcEmpleadoCB.fill = GridBagConstraints.HORIZONTAL;

		gbcEmpleadoCB.weightx = 1.0;

		gbcEmpleadoCB.gridx = 3;

		gbcEmpleadoCB.gridy = 1;

		formPanel.add(empleadoCB, gbcEmpleadoCB);

		GridBagConstraints gbcVendedorLabel = new GridBagConstraints();

		gbcVendedorLabel.insets = new Insets(5, 15, 5, 5);

		gbcVendedorLabel.anchor = GridBagConstraints.EAST;

		gbcVendedorLabel.gridx = 0;

		gbcVendedorLabel.gridy = 3;

		JLabel label_2 = new JLabel("Vendedor *:");
		formPanel.add(label_2, gbcVendedorLabel);

		vendedorCB = new JComboBox<>();

		GridBagConstraints gbcVendedorCB = new GridBagConstraints();

		gbcVendedorCB.insets = new Insets(5, 5, 5, 5);

		gbcVendedorCB.fill = GridBagConstraints.HORIZONTAL;

		gbcVendedorCB.weightx = 1.0;

		gbcVendedorCB.gridx = 1;

		gbcVendedorCB.gridy = 3;

		formPanel.add(vendedorCB, gbcVendedorCB);

		GridBagConstraints gbcCocheLabel = new GridBagConstraints();

		gbcCocheLabel.insets = new Insets(5, 15, 5, 5);

		gbcCocheLabel.anchor = GridBagConstraints.EAST;

		gbcCocheLabel.gridx = 2;

		gbcCocheLabel.gridy = 3;

		JLabel label_3 = new JLabel("Matrícula coche *:");
		formPanel.add(label_3, gbcCocheLabel);

		cocheCB = new JComboBox<>();

		GridBagConstraints gbcCocheCB = new GridBagConstraints();

		gbcCocheCB.insets = new Insets(5, 5, 5, 0);

		gbcCocheCB.fill = GridBagConstraints.HORIZONTAL;

		gbcCocheCB.weightx = 1.0;

		gbcCocheCB.gridx = 3;

		gbcCocheCB.gridy = 3;

		formPanel.add(cocheCB, gbcCocheCB);

		GridBagConstraints gbcCompradorLabel = new GridBagConstraints();

		gbcCompradorLabel.insets = new Insets(5, 5, 5, 5);

		gbcCompradorLabel.anchor = GridBagConstraints.EAST;

		gbcCompradorLabel.gridx = 0;

		gbcCompradorLabel.gridy = 4;

		JLabel label_1 = new JLabel("Comprador:");
		formPanel.add(label_1, gbcCompradorLabel);

		compradorCB = new JComboBox<>();

		GridBagConstraints gbcCompradorCB = new GridBagConstraints();

		gbcCompradorCB.insets = new Insets(5, 5, 5, 5);

		gbcCompradorCB.fill = GridBagConstraints.HORIZONTAL;

		gbcCompradorCB.weightx = 1.0;

		gbcCompradorCB.gridx = 1;

		gbcCompradorCB.gridy = 4;

		formPanel.add(compradorCB, gbcCompradorCB);

		GridBagConstraints gbcPrecioFinalLabel = new GridBagConstraints();

		gbcPrecioFinalLabel.insets = new Insets(5, 5, 0, 5);

		gbcPrecioFinalLabel.anchor = GridBagConstraints.EAST;

		gbcPrecioFinalLabel.gridx = 0;

		gbcPrecioFinalLabel.gridy = 5;

		formPanel.add(new JLabel("Precio final (€):"), gbcPrecioFinalLabel);

		precioFinalTF = new JTextField();

		GridBagConstraints gbcPrecioFinalTF = new GridBagConstraints();

		gbcPrecioFinalTF.insets = new Insets(5, 5, 0, 5);

		gbcPrecioFinalTF.fill = GridBagConstraints.HORIZONTAL;

		gbcPrecioFinalTF.weightx = 1.0;

		gbcPrecioFinalTF.gridx = 1;

		gbcPrecioFinalTF.gridy = 5;

		formPanel.add(precioFinalTF, gbcPrecioFinalTF);

		GridBagConstraints gbcPrecioClienteLabel = new GridBagConstraints();

		gbcPrecioClienteLabel.insets = new Insets(5, 15, 0, 5);

		gbcPrecioClienteLabel.anchor = GridBagConstraints.EAST;

		gbcPrecioClienteLabel.gridx = 2;

		gbcPrecioClienteLabel.gridy = 5;

		JLabel label = new JLabel("Precio cliente (€):");
		formPanel.add(label, gbcPrecioClienteLabel);

		precioClienteTF = new JTextField();

		GridBagConstraints gbcPrecioClienteTF = new GridBagConstraints();

		gbcPrecioClienteTF.insets = new Insets(5, 5, 0, 0);

		gbcPrecioClienteTF.fill = GridBagConstraints.HORIZONTAL;

		gbcPrecioClienteTF.weightx = 1.0;

		gbcPrecioClienteTF.gridx = 3;

		gbcPrecioClienteTF.gridy = 5;

		formPanel.add(precioClienteTF, gbcPrecioClienteTF);

		JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		add(botonesPanel, BorderLayout.SOUTH);

		cancelarButton = new JButton("Cancelar");

		guardarButton = new JButton("Guardar");

		botonesPanel.add(cancelarButton);

		botonesPanel.add(guardarButton);

		guardarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				guardar();
			}
		});
	}

	private void postInitialize() {

		try {

			DefaultListCellRenderer clienteRenderer = new DefaultListCellRenderer() {

				@Override
				public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index,
						boolean isSelected, boolean cellHasFocus) {

					super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

					if (value instanceof ClienteDTO) {

						ClienteDTO cliente = (ClienteDTO) value;

						if (cliente.getId() == null) {

							setText("Seleccionar");

						} else {

							setText(cliente.getDniNie() + " - " + cliente.getNombre());
						}
					}

					return this;
				}
			};

			compradorCB.setRenderer(clienteRenderer);

			vendedorCB.setRenderer(clienteRenderer);

			empleadoCB.setRenderer(new DefaultListCellRenderer() {

				@Override
				public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index,
						boolean isSelected, boolean cellHasFocus) {

					super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

					if (value instanceof EmpleadoDTO) {

						EmpleadoDTO empleado = (EmpleadoDTO) value;

						if (empleado.getId() == null) {

							setText("Seleccionar");

						} else {

							setText(empleado.getNombre() + " " + empleado.getPrimerApellido());
						}
					}

					return this;
				}
			});

			cocheCB.setRenderer(new DefaultListCellRenderer() {

				@Override
				public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index,
						boolean isSelected, boolean cellHasFocus) {

					super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

					if (value instanceof CocheDTO) {

						CocheDTO coche = (CocheDTO) value;

						if (coche.getId() == null) {

							setText("Seleccionar");

						} else {

							setText(coche.getMatricula());
						}
					}

					return this;
				}
			});

			cargarClientes();

			cargarEmpleados();

			vendedorCB.addActionListener(e -> {

				ClienteDTO vendedor = (ClienteDTO) vendedorCB.getSelectedItem();

				if (vendedor != null && vendedor.getId() != null) {

					cargarCochesCliente(vendedor.getId());

				} else {

					limpiarCoches();
				}
			});
		} catch (Exception e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(this, "Error cargando datos", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cargarClientes() throws Exception {

		Results<ClienteDTO> results = clienteService.findByCriteria(new ClienteCriteria(), 1, 1000);

		DefaultComboBoxModel<ClienteDTO> modelComprador = new DefaultComboBoxModel<>();

		DefaultComboBoxModel<ClienteDTO> modelVendedor = new DefaultComboBoxModel<>();

		ClienteDTO vacio = new ClienteDTO();

		vacio.setId(null);

		vacio.setNombre("Seleccionar");

		modelComprador.addElement(vacio);

		modelVendedor.addElement(vacio);

		for (ClienteDTO cliente : results.getPage()) {

			modelComprador.addElement(cliente);

			modelVendedor.addElement(cliente);
		}

		compradorCB.setModel(modelComprador);

		vendedorCB.setModel(modelVendedor);
	}

	private void cargarEmpleados() throws Exception {

		EmpleadoCriteria criteria = new EmpleadoCriteria();

		Results<EmpleadoDTO> results = empleadoService.findByCriteria(criteria, 1, 1000);

		DefaultComboBoxModel<EmpleadoDTO> model = new DefaultComboBoxModel<>();

		EmpleadoDTO vacio = new EmpleadoDTO();

		vacio.setId(null);

		vacio.setNombre("Seleccionar");

		model.addElement(vacio);

		for (EmpleadoDTO empleado : results.getPage()) {

			if (empleado.getId() != null && empleado.getId() == 4L) {

				model.addElement(empleado);
			}
		}

		empleadoCB.setModel(model);
	}

	private void cargarCochesCliente(Long clienteId) {

		try {

			CocheCriteria criteria = new CocheCriteria();

			criteria.setClienteId(clienteId);

			Results<CocheDTO> results = cocheService.findByCriteria(criteria, 1, 1000);

			DefaultComboBoxModel<CocheDTO> model = new DefaultComboBoxModel<>();

			CocheDTO vacio = new CocheDTO();

			vacio.setId(null);

			vacio.setMatricula("Seleccionar");

			model.addElement(vacio);

			for (CocheDTO coche : results.getPage()) {

				model.addElement(coche);
			}

			cocheCB.setModel(model);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void limpiarCoches() {

		DefaultComboBoxModel<CocheDTO> model = new DefaultComboBoxModel<>();

		CocheDTO vacio = new CocheDTO();

		vacio.setId(null);

		vacio.setMatricula("Seleccionar");

		model.addElement(vacio);

		cocheCB.setModel(model);
	}

	private void guardar() {
		if (dateChooser.getDate() == null) {
			JOptionPane.showMessageDialog(this, "La fecha es obligatoria.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		CocheDTO coche = (CocheDTO) cocheCB.getSelectedItem();
		if (coche == null || coche.getId() == null) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un coche.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		ClienteDTO vendedor = (ClienteDTO) vendedorCB.getSelectedItem();
		if (vendedor == null || vendedor.getId() == null) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un vendedor.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		EmpleadoDTO empleado = (EmpleadoDTO) empleadoCB.getSelectedItem();
		if (empleado == null || empleado.getId() == null) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un empleado.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			VentaDTO venta = new VentaDTO();
			venta.setId(ventaId);
			venta.setFechaVenta(dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			venta.setCocheId(coche.getId());
			venta.setMatriculaCoche(coche.getMatricula());
			venta.setClienteVendedorId(vendedor.getId());
			venta.setEmpleadoId(empleado.getId());

			ClienteDTO comprador = (ClienteDTO) compradorCB.getSelectedItem();
			if (comprador != null && comprador.getId() != null) {
				venta.setClienteCompradorId(comprador.getId());
			}

			if (!precioClienteTF.getText().trim().isEmpty())
				venta.setPrecioCliente(Double.parseDouble(precioClienteTF.getText().trim()));

			if (!precioFinalTF.getText().trim().isEmpty())
				venta.setPrecioFinal(Double.parseDouble(precioFinalTF.getText().trim()));

			ventaService.create(venta);
			JOptionPane.showMessageDialog(this, "Venta guardada correctamente.", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Precio inválido.", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al guardar la venta: " + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setCancelController(com.paula.checkmycar.desktop.controller.Controller controller) {
		cancelarButton.setAction(controller);
	}

	public void setVentaDTO(VentaDTO venta) {

		this.ventaId = venta.getId();

		if (venta.getFechaVenta() != null) {

			java.util.Date date = java.util.Date
					.from(venta.getFechaVenta().atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());

			dateChooser.setDate(date);
		}

		if (venta.getPrecioCliente() != null) {

			precioClienteTF.setText(String.valueOf(venta.getPrecioCliente()));
		}

		if (venta.getPrecioFinal() != null) {

			precioFinalTF.setText(String.valueOf(venta.getPrecioFinal()));
		}

		for (int i = 0; i < compradorCB.getItemCount(); i++) {

			ClienteDTO cl = compradorCB.getItemAt(i);

			if (cl.getId() != null && cl.getId().equals(venta.getClienteCompradorId())) {

				compradorCB.setSelectedIndex(i);

				break;
			}
		}

		for (int i = 0; i < vendedorCB.getItemCount(); i++) {

			ClienteDTO cl = vendedorCB.getItemAt(i);

			if (cl.getId() != null && cl.getId().equals(venta.getClienteVendedorId())) {

				vendedorCB.setSelectedIndex(i);

				break;
			}
		}

		if (venta.getClienteVendedorId() != null) {

			cargarCochesCliente(venta.getClienteVendedorId());
		}

		for (int i = 0; i < cocheCB.getItemCount(); i++) {

			CocheDTO co = cocheCB.getItemAt(i);

			if (co.getId() != null && co.getId().equals(venta.getCocheId())) {

				cocheCB.setSelectedIndex(i);

				break;
			}
		}

		for (int i = 0; i < empleadoCB.getItemCount(); i++) {

			EmpleadoDTO emp = empleadoCB.getItemAt(i);

			if (emp.getId() != null && emp.getId().equals(venta.getEmpleadoId())) {

				empleadoCB.setSelectedIndex(i);

				break;
			}
		}
	}

	public VentaDTO getModel() {

		VentaDTO venta = new VentaDTO();

		venta.setId(ventaId);

		if (dateChooser.getDate() != null) {

			venta.setFechaVenta(dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		}

		CocheDTO coche = (CocheDTO) cocheCB.getSelectedItem();

		if (coche != null) {

			venta.setCocheId(coche.getId());
		}

		ClienteDTO comprador = (ClienteDTO) compradorCB.getSelectedItem();

		if (comprador != null) {

			venta.setClienteCompradorId(comprador.getId());
		}

		ClienteDTO vendedor = (ClienteDTO) vendedorCB.getSelectedItem();

		if (vendedor != null) {

			venta.setClienteVendedorId(vendedor.getId());
		}

		EmpleadoDTO empleado = (EmpleadoDTO) empleadoCB.getSelectedItem();

		if (empleado != null) {

			venta.setEmpleadoId(empleado.getId());
		}

		if (!precioClienteTF.getText().trim().isEmpty()) {

			venta.setPrecioCliente(Double.parseDouble(precioClienteTF.getText().trim()));
		}

		if (!precioFinalTF.getText().trim().isEmpty()) {

			venta.setPrecioFinal(Double.parseDouble(precioFinalTF.getText().trim()));
		}

		return venta;
	}

	public void setEditable(boolean editable) {

		dateChooser.setEnabled(editable);

		compradorCB.setEnabled(editable);

		vendedorCB.setEnabled(editable);

		empleadoCB.setEnabled(editable);

		cocheCB.setEnabled(editable);

		precioClienteTF.setEditable(editable);

		precioFinalTF.setEditable(editable);
	}

	public void setAgreeController(ActionListener controller) {

		for (ActionListener listener : guardarButton.getActionListeners()) {

			guardarButton.removeActionListener(listener);
		}

		guardarButton.addActionListener(controller);
	}

	public JButton getGuardarButton() {

		return guardarButton;
	}

}