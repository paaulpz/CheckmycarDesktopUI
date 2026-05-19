package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.paula.checkmc.model.EstadoPieza;
import com.paula.checkmc.model.PiezaCriteria;
import com.paula.checkmc.model.PiezaDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.EstadoPiezaService;
import com.paula.checkmc.service.PiezaService;
import com.paula.checkmc.service.impl.EstadoPiezaServiceImpl;
import com.paula.checkmc.service.impl.PiezaServiceImpl;
import com.paula.checkmycar.desktop.controller.PiezaSearchController;
import com.paula.checkmycar.desktop.views.renderer.EstadoPiezaCBRenderer;
import com.paula.checkmycar.desktop.views.tableModel.PiezaTableModel;

public class PiezaSearchView extends View {

	private JTextField nombreTF;

	private JTextField referenciaTF;

	private JComboBox<EstadoPieza> estadoCombo;

	private JButton buscarButton;

	private JButton anteriorButton;

	private JButton siguienteButton;

	private JLabel paginaLabel;

	private JTable table;

	private PiezaTableModel tableModel;

	private PiezaService piezaService;

	private EstadoPiezaService estadoPiezaService;

	private PiezaSearchController searchController;

	private int paginaActual = 1;

	private static final int PAGE_SIZE = 10;

	public PiezaSearchView() {

		piezaService = new PiezaServiceImpl();

		estadoPiezaService = new EstadoPiezaServiceImpl();

		initialize();

		postInitialize();
	}

	private void initialize() {

		setLayout(new BorderLayout());

		setName("Búsqueda de piezas");

		JPanel filtrosPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		filtrosPanel.add(new JLabel("Nombre:"));

		nombreTF = new JTextField(15);

		filtrosPanel.add(nombreTF);

		filtrosPanel.add(new JLabel("Referencia:"));

		referenciaTF = new JTextField(15);

		filtrosPanel.add(referenciaTF);

		filtrosPanel.add(new JLabel("Estado:"));

		estadoCombo = new JComboBox<>();

		filtrosPanel.add(estadoCombo);

		buscarButton = new JButton("Buscar");

		filtrosPanel.add(buscarButton);

		JButton limpiarButton = new JButton("Limpiar");

		filtrosPanel.add(limpiarButton);

		add(filtrosPanel, BorderLayout.NORTH);

		tableModel = new PiezaTableModel();

		table = new JTable(tableModel);

		JScrollPane scrollPane = new JScrollPane(table);

		add(scrollPane, BorderLayout.CENTER);

		JPanel paginacionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		anteriorButton = new JButton("Anterior");

		siguienteButton = new JButton("Siguiente");

		paginaLabel = new JLabel("Página 1");

		paginacionPanel.add(anteriorButton);

		paginacionPanel.add(paginaLabel);

		paginacionPanel.add(siguienteButton);

		add(paginacionPanel, BorderLayout.SOUTH);
	}

	private void postInitialize() {

		estadoCombo.setRenderer(new EstadoPiezaCBRenderer());

		cargarEstados();

		searchController = new PiezaSearchController(this);

		buscarButton.addActionListener(searchController);

		anteriorButton.addActionListener(e -> {

			if (paginaActual > 1) {

				paginaActual--;

				searchController.buscar(paginaActual);
			}
		});

		siguienteButton.addActionListener(e -> {

			paginaActual++;

			searchController.buscar(paginaActual);
		});

		try {

			buscar(1);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void cargarEstados() {

		try {

			DefaultComboBoxModel<EstadoPieza> model = new DefaultComboBoxModel<>();

			EstadoPieza estadoDefault = new EstadoPieza();

			estadoDefault.setId(null);

			estadoDefault.setNombre("Todos");

			model.addElement(estadoDefault);

			List<EstadoPieza> estados = estadoPiezaService.findAll();

			for (EstadoPieza estado : estados) {

				model.addElement(estado);
			}

			estadoCombo.setModel(model);

		} catch (Exception e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(this, "Error cargando estados");
		}
	}

	public void buscar() throws Exception {

		buscar(1);
	}

	public void buscar(int pagina) throws Exception {

		paginaActual = pagina;

		PiezaCriteria criteria = new PiezaCriteria();

		criteria.setNombre(nombreTF.getText());

		criteria.setNumeroReferencia(referenciaTF.getText());

		EstadoPieza estado = (EstadoPieza) estadoCombo.getSelectedItem();

		if (estado != null && estado.getId() != null) {

			criteria.setEstadoId(estado.getId());
		}

		Results<PiezaDTO> results = piezaService.findByCriteria(criteria, pagina, PAGE_SIZE);

		List<PiezaDTO> piezas = results.getPage();

		tableModel.setPiezas(piezas);

		tableModel.fireTableDataChanged();

		actualizarPaginacion(pagina, PAGE_SIZE, results.getTotal());
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
}