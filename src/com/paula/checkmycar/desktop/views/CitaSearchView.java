package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.paula.checkmc.model.CitaCriteria;
import com.paula.checkmc.model.CitaDTO;
import com.paula.checkmc.model.ClienteCriteria;
import com.paula.checkmc.model.ClienteDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.CitaService;
import com.paula.checkmc.service.ClienteService;
import com.paula.checkmc.service.impl.CitaServiceImpl;
import com.paula.checkmc.service.impl.ClienteServiceImpl;
import com.paula.checkmycar.desktop.views.renderer.CitasButtonRenderer;
import com.paula.checkmycar.desktop.views.renderer.ClienteCBRenderer;
import com.paula.checkmycar.desktop.views.tableModel.CitasTableModel;
import com.paula.checkmycar.desktop.views.tableModel.editor.CitaButtonEditor;
import com.toedter.calendar.JDateChooser;

public class CitaSearchView extends View {

	private JDateChooser fechaDesdeChooser;

	private JDateChooser fechaHastaChooser;

	private JComboBox<ClienteDTO> clienteCB;

	private JButton buscarButton;

	private JButton limpiarButton;

	private JTable table;

	private CitasTableModel tableModel;

	private JLabel paginaLabel;

	private JButton anteriorButton;

	private JButton siguienteButton;

	private int paginaActual = 1;
	private int totalPaginas = 1;

	private final int PAGE_SIZE = 10;

	private CitaService citaService;

	private ClienteService clienteService;

	public CitaSearchView() {

		citaService = new CitaServiceImpl();

		clienteService = new ClienteServiceImpl();

		initialize();

		postInitialize();
	}

	private void initialize() {

		setName("Búsqueda de citas");

		setLayout(new BorderLayout());

		JPanel filtrosPanel = new JPanel(new GridBagLayout());

		add(filtrosPanel, BorderLayout.NORTH);

		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(5, 5, 5, 5);

		c.fill = GridBagConstraints.HORIZONTAL;

		int y = 0;

		c.gridx = 0;
		c.gridy = y;

		filtrosPanel.add(new JLabel("Fecha desde:"), c);

		fechaDesdeChooser = new JDateChooser();

		fechaDesdeChooser.setDateFormatString("dd/MM/yyyy");

		c.gridx = 1;

		filtrosPanel.add(fechaDesdeChooser, c);

		c.gridx = 2;

		filtrosPanel.add(new JLabel("Fecha hasta:"), c);

		fechaHastaChooser = new JDateChooser();

		fechaHastaChooser.setDateFormatString("dd/MM/yyyy");

		c.gridx = 3;

		filtrosPanel.add(fechaHastaChooser, c);

		y++;

		c.gridx = 0;
		c.gridy = y;

		filtrosPanel.add(new JLabel("Cliente:"), c);

		clienteCB = new JComboBox<>();

		c.gridx = 1;

		filtrosPanel.add(clienteCB, c);

		JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		limpiarButton = new JButton("Limpiar");

		buscarButton = new JButton("Buscar");

		botonesPanel.add(limpiarButton);

		botonesPanel.add(buscarButton);

		c.gridx = 2;
		c.gridwidth = 2;

		filtrosPanel.add(botonesPanel, c);

		table = new JTable();

		JScrollPane scrollPane = new JScrollPane(table);

		add(scrollPane, BorderLayout.CENTER);

		JPanel paginacionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		anteriorButton = new JButton("Anterior");

		paginaLabel = new JLabel("Página 1");

		siguienteButton = new JButton("Siguiente");

		paginacionPanel.add(anteriorButton);

		paginacionPanel.add(paginaLabel);

		paginacionPanel.add(siguienteButton);

		add(paginacionPanel, BorderLayout.SOUTH);

		anteriorButton.setEnabled(false);

		siguienteButton.setEnabled(false);

		buscarButton.addActionListener(e -> {

			paginaActual = 1;

			buscar(paginaActual);
		});

		limpiarButton.addActionListener(e -> limpiar());

		anteriorButton.addActionListener(e -> {

			if (paginaActual > 1) {

				paginaActual--;

				buscar(paginaActual);
			}
		});

		siguienteButton.addActionListener(e -> {

			paginaActual++;

			buscar(paginaActual);
		});
	}

	private void postInitialize() {

		try {

			clienteCB.setRenderer(new ClienteCBRenderer());

			cargarClientes();

			tableModel = new CitasTableModel();

			table.setModel(tableModel);

			table.setRowHeight(30);

			table.getColumnModel().getColumn(6).setCellRenderer(new CitasButtonRenderer());

			table.getColumnModel().getColumn(6).setCellEditor(new CitaButtonEditor(new javax.swing.JCheckBox()));

			table.setSurrendersFocusOnKeystroke(true);

			table.setCellSelectionEnabled(true);

			table.addMouseListener(new java.awt.event.MouseAdapter() {

				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {

					int row = table.rowAtPoint(e.getPoint());

					int col = table.columnAtPoint(e.getPoint());

					if (col == 6) {

						table.editCellAt(row, col);

						Component editor = table.getEditorComponent();

						if (editor != null) {

							editor.requestFocus();
						}
					}
				}
			});

		} catch (Exception e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(this, "Error cargando clientes", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cargarClientes() throws Exception {

		Results<ClienteDTO> results = clienteService.findByCriteria(new ClienteCriteria(), 1, 1000);

		DefaultComboBoxModel<ClienteDTO> model = new DefaultComboBoxModel<>();

		ClienteDTO todos = new ClienteDTO();

		todos.setId(null);

		model.addElement(todos);

		for (ClienteDTO cliente : results.getPage()) {

			model.addElement(cliente);
		}

		clienteCB.setModel(model);
	}

	private void buscar(int pagina) {

		try {

			CitaCriteria criteria = new CitaCriteria();

			Date fechaDesde = fechaDesdeChooser.getDate();

			if (fechaDesde != null) {

				criteria.setFechaDesde(fechaDesde.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			}

			Date fechaHasta = fechaHastaChooser.getDate();

			if (fechaHasta != null) {

				criteria.setFechaHasta(fechaHasta.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			}

			ClienteDTO cliente = (ClienteDTO) clienteCB.getSelectedItem();

			if (cliente != null && cliente.getId() != null) {

				criteria.setClienteId(cliente.getId());
			}

			Results<CitaDTO> results = citaService.findByCriteria(criteria, pagina, PAGE_SIZE);

			System.out.println(results.getTotal());

			List<CitaDTO> citas = results.getPage();

			tableModel.setCitas(citas);

			actualizarPaginacion(pagina, PAGE_SIZE, results.getTotal());

		} catch (Exception e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(this, "Error buscando citas", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiar() {

		fechaDesdeChooser.setDate(null);

		fechaHastaChooser.setDate(null);

		clienteCB.setSelectedIndex(0);

		tableModel.setCitas(new ArrayList<>());

		paginaActual = 1;

		actualizarPaginacion(1, PAGE_SIZE, 0);
	}

	public void actualizarPaginacion(int pagina, int pageSize, int total) {

		paginaActual = pagina;

		int totalPaginas = total / pageSize;

		if (total % pageSize != 0) {

			totalPaginas++;
		}

		if (totalPaginas < 1) {

			totalPaginas = 1;
		}

		paginaLabel.setText("Página " + paginaActual + " de " + totalPaginas);

		anteriorButton.setEnabled(paginaActual > 1);

		siguienteButton.setEnabled(paginaActual < totalPaginas);
	}

	public void setTableData(List<CitaDTO> citas) {

		tableModel.setCitas(citas);
	}
}