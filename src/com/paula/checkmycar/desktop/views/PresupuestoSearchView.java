package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.paula.checkmc.model.ClienteCriteria;
import com.paula.checkmc.model.ClienteDTO;
import com.paula.checkmc.model.PresupuestoCriteria;
import com.paula.checkmc.model.PresupuestoDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.ClienteService;
import com.paula.checkmc.service.PresupuestoService;
import com.paula.checkmc.service.impl.ClienteServiceImpl;
import com.paula.checkmc.service.impl.PresupuestoServiceImpl;
import com.paula.checkmycar.desktop.views.renderer.ClienteCBRenderer;
import com.paula.checkmycar.desktop.views.renderer.PresupuestoOrdenTrabajoButtonRenderer;
import com.paula.checkmycar.desktop.views.tableModel.PresupuestoTableModel;
import com.paula.checkmycar.desktop.views.tableModel.editor.PresupuestoButtonEditor;
import com.toedter.calendar.JDateChooser;

public class PresupuestoSearchView extends View {

	private JComboBox<ClienteDTO> clienteCB;

	private JDateChooser fechaDesdeDC;

	private JDateChooser fechaHastaDC;

	private JTable tabla;

	private PresupuestoTableModel tableModel;

	private JLabel totalLabel;

	private JButton anteriorButton;

	private JButton siguienteButton;

	private JLabel paginaLabel;

	private int paginaActual = 1;

	private final int PAGE_SIZE = 10;

	private PresupuestoService presupuestoService;

	private ClienteService clienteService;

	public PresupuestoSearchView() {

		initServices();

		initialize();

		postInitialize();
	}

	private void initServices() {

		presupuestoService = new PresupuestoServiceImpl();

		clienteService = new ClienteServiceImpl();
	}

	private void initialize() {

		setName("Búsqueda de presupuestos");

		setLayout(new BorderLayout());

		JPanel filtrosPanel = new JPanel(new GridBagLayout());

		add(filtrosPanel, BorderLayout.NORTH);

		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(5, 5, 5, 5);

		c.fill = GridBagConstraints.HORIZONTAL;

		int y = 0;

		c.gridx = 0;
		c.gridy = y;

		filtrosPanel.add(new JLabel("Cliente:"), c);

		clienteCB = new JComboBox<>();

		c.gridx = 1;

		filtrosPanel.add(clienteCB, c);

		y++;

		c.gridx = 0;
		c.gridy = y;

		filtrosPanel.add(new JLabel("Fecha desde:"), c);

		fechaDesdeDC = new JDateChooser();

		fechaDesdeDC.setDateFormatString("dd/MM/yyyy");

		c.gridx = 1;

		filtrosPanel.add(fechaDesdeDC, c);

		c.gridx = 2;

		filtrosPanel.add(new JLabel("Fecha hasta:"), c);

		fechaHastaDC = new JDateChooser();

		fechaHastaDC.setDateFormatString("dd/MM/yyyy");

		c.gridx = 3;

		filtrosPanel.add(fechaHastaDC, c);

		y++;

		JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		c.gridx = 0;
		c.gridy = y;
		c.gridwidth = 4;

		filtrosPanel.add(botonesPanel, c);

		JButton limpiarButton = new JButton("Limpiar");

		JButton buscarButton = new JButton("Buscar");

		botonesPanel.add(limpiarButton);

		botonesPanel.add(buscarButton);

		limpiarButton.addActionListener(e -> limpiar());

		buscarButton.addActionListener(e -> {

			paginaActual = 1;

			buscar();
		});

		tableModel = new PresupuestoTableModel();

		tabla = new JTable(tableModel);


		tabla.getColumnModel().getColumn(5).setCellEditor(new PresupuestoButtonEditor(new javax.swing.JCheckBox()));

		tabla.setRowHeight(25);

		tabla.setFillsViewportHeight(true);

		add(new JScrollPane(tabla), BorderLayout.CENTER);

		JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		anteriorButton = new JButton("Anterior");

		paginaLabel = new JLabel("Página 1");

		siguienteButton = new JButton("Siguiente");

		totalLabel = new JLabel("0 resultados");

		footer.add(anteriorButton);

		footer.add(paginaLabel);

		footer.add(siguienteButton);

		footer.add(totalLabel);

		add(footer, BorderLayout.SOUTH);

		anteriorButton.setEnabled(false);

		siguienteButton.setEnabled(false);

		anteriorButton.addActionListener(e -> {

			if (paginaActual > 1) {

				paginaActual--;

				buscar();
			}
		});

		siguienteButton.addActionListener(e -> {

			paginaActual++;

			buscar();
		});
	}

	private void postInitialize() {

		try {

			clienteCB.setRenderer(new ClienteCBRenderer());

			cargarClientes();

		} catch (Exception e) {

			e.printStackTrace();

			javax.swing.JOptionPane.showMessageDialog(this, "Error cargando datos");
		}

		tableModel = new PresupuestoTableModel();

		tabla.setModel(tableModel);

		tabla.setRowHeight(30);

		tabla.getColumnModel().getColumn(5).setCellRenderer(new PresupuestoOrdenTrabajoButtonRenderer());

		tabla.getColumnModel().getColumn(5).setCellEditor(new PresupuestoButtonEditor(new JCheckBox()));

		tabla.setSurrendersFocusOnKeystroke(true);

		tabla.setCellSelectionEnabled(true);

		tabla.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {

				int row = tabla.rowAtPoint(e.getPoint());

				int col = tabla.columnAtPoint(e.getPoint());

				if (col == 5) {

					tabla.editCellAt(row, col);

					Component editor = tabla.getEditorComponent();

					if (editor != null) {

						editor.requestFocus();
					}
				}
			}
		});
	}

	private void cargarClientes() throws Exception {

		Results<ClienteDTO> clientes = clienteService.findByCriteria(new ClienteCriteria(), 1, 1000);

		DefaultComboBoxModel<ClienteDTO> model = new DefaultComboBoxModel<>();

		ClienteDTO placeholder = new ClienteDTO();

		placeholder.setId(null);

		placeholder.setNombre("Seleccionar");

		model.addElement(placeholder);

		for (ClienteDTO cliente : clientes.getPage()) {

			model.addElement(cliente);
		}

		clienteCB.setModel(model);

		clienteCB.setRenderer(new ClienteCBRenderer());
	}

	public void buscar() {

		try {

			PresupuestoCriteria criteria = new PresupuestoCriteria();

			ClienteDTO cliente = (ClienteDTO) clienteCB.getSelectedItem();

			if (cliente != null && cliente.getId() != null) {

				criteria.setClienteId(cliente.getId());
			}

			if (fechaDesdeDC.getDate() != null) {

				criteria.setFechaDesde(fechaDesdeDC.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			}

			if (fechaHastaDC.getDate() != null) {

				criteria.setFechaHasta(fechaHastaDC.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			}

			Results<PresupuestoDTO> results = presupuestoService.findByCriteria(criteria, paginaActual, PAGE_SIZE);

			tableModel.setData(results.getPage());

			totalLabel.setText(results.getTotal() + " resultados");

			actualizarPaginacion(results.getTotal());

		} catch (Exception e) {

			e.printStackTrace();

			javax.swing.JOptionPane.showMessageDialog(this, "Error buscando presupuestos", "Error",
					javax.swing.JOptionPane.ERROR_MESSAGE);
		}
	}

	private void actualizarPaginacion(int total) {

		int totalPaginas = total / PAGE_SIZE;

		if (total % PAGE_SIZE != 0) {

			totalPaginas++;
		}

		if (totalPaginas < 1) {

			totalPaginas = 1;
		}

		paginaLabel.setText("Página " + paginaActual + " de " + totalPaginas);

		anteriorButton.setEnabled(paginaActual > 1);

		siguienteButton.setEnabled(paginaActual < totalPaginas);
	}

	private void limpiar() {

		clienteCB.setSelectedIndex(0);

		fechaDesdeDC.setDate(null);

		fechaHastaDC.setDate(null);

		tableModel.setData(new ArrayList<>());

		totalLabel.setText("0 resultados");

		paginaActual = 1;

		paginaLabel.setText("Página 1");

		anteriorButton.setEnabled(false);

		siguienteButton.setEnabled(false);
	}
}