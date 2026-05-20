package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.paula.checkmc.model.ClienteCriteria;
import com.paula.checkmc.model.ClienteDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.model.VentaCriteria;
import com.paula.checkmc.model.VentaDTO;
import com.paula.checkmc.service.ClienteService;
import com.paula.checkmc.service.VentaService;
import com.paula.checkmc.service.impl.ClienteServiceImpl;
import com.paula.checkmc.service.impl.VentaServiceImpl;
import com.paula.checkmycar.desktop.controller.VentaSearchController;
import com.paula.checkmycar.desktop.views.renderer.VentaButtonRenderer;
import com.paula.checkmycar.desktop.views.tableModel.VentaTableModel;
import com.paula.checkmycar.desktop.views.tableModel.editor.VentaButtonEditor;

public class VentaSearchView extends View {

	private JTable table;

	private VentaTableModel tableModel;

	private JComboBox<ClienteDTO> vendedorCB;

	private JComboBox<ClienteDTO> compradorCB;

	private JButton buscarButton;

	private JButton limpiarButton;

	private JButton anteriorButton;

	private JButton siguienteButton;

	private JLabel paginaLabel;

	private int paginaActual = 1;

	private int pageSize = 10;

	private List<VentaDTO> ventasTotales = new ArrayList<>();

	private VentaService ventaService = new VentaServiceImpl();

	private ClienteService clienteService = new ClienteServiceImpl();

	private VentaSearchController searchController;

	public VentaSearchView() {

		initialize();

		postInitialize();
	}

	private void initialize() {

		setName("Búsqueda ventas");

		setLayout(new BorderLayout());

		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		add(topPanel, BorderLayout.NORTH);

		topPanel.add(new JLabel("Vendedor:"));

		vendedorCB = new JComboBox<>();

		topPanel.add(vendedorCB);

		topPanel.add(new JLabel("Comprador:"));

		compradorCB = new JComboBox<>();

		topPanel.add(compradorCB);

		buscarButton = new JButton("Buscar");

		limpiarButton = new JButton("Limpiar");

		topPanel.add(buscarButton);

		topPanel.add(limpiarButton);

		tableModel = new VentaTableModel();

		table = new JTable(tableModel);

		table.setRowHeight(30);

		JScrollPane scrollPane = new JScrollPane(table);

		add(scrollPane, BorderLayout.CENTER);

		JPanel paginacionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		add(paginacionPanel, BorderLayout.SOUTH);

		anteriorButton = new JButton("◀ Anterior");

		paginaLabel = new JLabel("Página 1");

		siguienteButton = new JButton("Siguiente ▶");

		paginacionPanel.add(anteriorButton);

		paginacionPanel.add(paginaLabel);

		paginacionPanel.add(siguienteButton);

		anteriorButton.setEnabled(false);

		siguienteButton.setEnabled(false);
	}

	private void postInitialize() {

		searchController = new VentaSearchController(this);

		cargarClientes(vendedorCB);

		cargarClientes(compradorCB);

		buscarButton.addActionListener(e -> buscar(1));

		limpiarButton.addActionListener(e -> {

			vendedorCB.setSelectedIndex(0);

			compradorCB.setSelectedIndex(0);

			ventasTotales = new ArrayList<>();

			tableModel.setData(new ArrayList<>());

			paginaActual = 1;

			paginaLabel.setText("Página 1");

			anteriorButton.setEnabled(false);

			siguienteButton.setEnabled(false);
		});

		anteriorButton.addActionListener(e -> {

			if (paginaActual > 1) {

				mostrarPagina(paginaActual - 1);
			}
		});

		siguienteButton.addActionListener(e -> {

			int totalPaginas = (int) Math.ceil((double) ventasTotales.size() / pageSize);

			if (paginaActual < totalPaginas) {

				mostrarPagina(paginaActual + 1);
			}
		});

		table.getColumn("Detalles").setCellRenderer(new VentaButtonRenderer());

		table.getColumn("Detalles").setCellEditor(new VentaButtonEditor(new JCheckBox()));
	}

	private void cargarClientes(JComboBox<ClienteDTO> combo) {

		try {

			ClienteCriteria criteria = new ClienteCriteria();

			Results<ClienteDTO> results = clienteService.findByCriteria(criteria, 1, 1000);

			DefaultComboBoxModel<ClienteDTO> model = new DefaultComboBoxModel<>();

			ClienteDTO vacio = new ClienteDTO();

			vacio.setId(null);

			vacio.setNombre("Todos");

			model.addElement(vacio);

			for (ClienteDTO cliente : results.getPage()) {

				model.addElement(cliente);
			}

			combo.setModel(model);

			combo.setRenderer(new DefaultListCellRenderer() {

				@Override
				public Component getListCellRendererComponent(JList<?> list, Object value, int index,
						boolean isSelected, boolean cellHasFocus) {

					super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

					if (value instanceof ClienteDTO) {

						ClienteDTO cliente = (ClienteDTO) value;

						if (cliente.getId() == null) {

							setText("Todos");

						} else {

							setText(cliente.getNombre() + " " + cliente.getPrimerApellido());
						}
					}

					return this;
				}
			});

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void buscar(int pagina) {

		try {

			VentaCriteria criteria = new VentaCriteria();

			ClienteDTO vendedor = (ClienteDTO) vendedorCB.getSelectedItem();

			if (vendedor != null && vendedor.getId() != null) {

				criteria.setClienteVendedorId(vendedor.getId());
			}

			ClienteDTO comprador = (ClienteDTO) compradorCB.getSelectedItem();

			if (comprador != null && comprador.getId() != null) {

				criteria.setClienteCompradorId(comprador.getId());
			}

			Results<VentaDTO> results = ventaService.findByCriteria(criteria, 1, 1000);

			ventasTotales = results.getPage();

			mostrarPagina(pagina);

		} catch (Exception e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(this, "Error buscando ventas", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void mostrarPagina(int pagina) {

		paginaActual = pagina;

		int from = (pagina - 1) * pageSize;

		int to = Math.min(from + pageSize, ventasTotales.size());

		tableModel.setData(ventasTotales.subList(from, to));

		actualizarPaginacion();
	}

	private void actualizarPaginacion() {

		int totalPaginas = (int) Math.ceil((double) ventasTotales.size() / pageSize);

		if (totalPaginas == 0) {

			totalPaginas = 1;
		}

		paginaLabel.setText("Página " + paginaActual + " de " + totalPaginas);

		anteriorButton.setEnabled(paginaActual > 1);

		siguienteButton.setEnabled(paginaActual < totalPaginas);
	}
}