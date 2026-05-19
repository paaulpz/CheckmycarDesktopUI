package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.paula.checkmc.model.OrdenTrabajoCriteria;
import com.paula.checkmc.model.OrdenTrabajoDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.OrdenTrabajoService;
import com.paula.checkmc.service.impl.OrdenTrabajoServiceImpl;
import com.paula.checkmycar.desktop.views.renderer.PresupuestoOrdenTrabajoButtonRenderer;
import com.paula.checkmycar.desktop.views.tableModel.OrdenTrabajoTableModel;
import com.paula.checkmycar.desktop.views.tableModel.editor.OrdenTrabajoButtonEditor;
import com.toedter.calendar.JDateChooser;

public class OrdenTrabajoSearchView extends View {

	private JTable table;

	private OrdenTrabajoTableModel tableModel;

	private JTextField matriculaTF;

	private JDateChooser fechaDesdeChooser;

	private JDateChooser fechaHastaChooser;

	private JButton buscarButton;

	private JButton limpiarButton;

	private JButton anteriorButton;

	private JButton siguienteButton;

	private JLabel paginaLabel;

	private int paginaActual = 1;

	private static final int PAGE_SIZE = 10;

	private OrdenTrabajoService ordenTrabajoService = new OrdenTrabajoServiceImpl();

	public OrdenTrabajoSearchView() {

		initialize();

		postInitialize();
	}

	private void initialize() {

		setName("Búsqueda órdenes trabajo");

		setLayout(new BorderLayout());

		JPanel filtrosPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

		add(filtrosPanel, BorderLayout.NORTH);

		filtrosPanel.add(new JLabel("Matrícula:"));

		matriculaTF = new JTextField(15);

		filtrosPanel.add(matriculaTF);

		filtrosPanel.add(new JLabel("Fecha desde:"));

		fechaDesdeChooser = new JDateChooser();

		fechaDesdeChooser.setDateFormatString("dd/MM/yyyy");

		filtrosPanel.add(fechaDesdeChooser);

		filtrosPanel.add(new JLabel("Fecha hasta:"));

		fechaHastaChooser = new JDateChooser();

		fechaHastaChooser.setDateFormatString("dd/MM/yyyy");

		filtrosPanel.add(fechaHastaChooser);

		limpiarButton = new JButton("Limpiar");

		buscarButton = new JButton("Buscar");

		filtrosPanel.add(limpiarButton);

		filtrosPanel.add(buscarButton);

		table = new JTable();

		tableModel = new OrdenTrabajoTableModel();

		table.setModel(tableModel);

		table.setRowHeight(30);

		JScrollPane scrollPane = new JScrollPane(table);

		add(scrollPane, BorderLayout.CENTER);

		JPanel paginacionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		anteriorButton = new JButton("◀ Anterior");

		paginaLabel = new JLabel("Página 1");

		siguienteButton = new JButton("Siguiente ▶");

		paginacionPanel.add(anteriorButton);

		paginacionPanel.add(paginaLabel);

		paginacionPanel.add(siguienteButton);

		add(paginacionPanel, BorderLayout.SOUTH);
	}

	private void postInitialize() {

		buscarButton.addActionListener(e -> {

			buscar(1);
		});

		limpiarButton.addActionListener(e -> {

		    matriculaTF.setText("");

		    fechaDesdeChooser.setDate(null);

		    fechaHastaChooser.setDate(null);

		    tableModel.setOrdenes(new ArrayList<>());

		    paginaLabel.setText("Página 1");

		    anteriorButton.setEnabled(false);

		    siguienteButton.setEnabled(false);
		});

		anteriorButton.addActionListener(e -> {

			if (paginaActual > 1) {

				buscar(paginaActual - 1);
			}
		});

		siguienteButton.addActionListener(e -> {

			buscar(paginaActual + 1);
		});

		table.getColumn("Acciones").setCellRenderer(new PresupuestoOrdenTrabajoButtonRenderer());

		table.getColumn("Acciones").setCellEditor(new OrdenTrabajoButtonEditor(new JCheckBox()));

	}

	private void buscar() {

		buscar(1);
	}

	private void buscar(int pagina) {

		try {

			paginaActual = pagina;

			OrdenTrabajoCriteria criteria = new OrdenTrabajoCriteria();

			if (fechaDesdeChooser.getDate() != null) {

				criteria.setFechaInicioDesde(
						fechaDesdeChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			}

			if (fechaHastaChooser.getDate() != null) {

				criteria.setFechaInicioHasta(
						fechaHastaChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			}

			Results<OrdenTrabajoDTO> results = ordenTrabajoService.findByCriteria(criteria, pagina, PAGE_SIZE);

			tableModel.setOrdenes(results.getPage());

			actualizarPaginacion(pagina, PAGE_SIZE, results.getTotal());

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void actualizarPaginacion(int pagina, int pageSize, int total) {

		paginaActual = pagina;

		int totalPaginas = (int) Math.ceil((double) total / pageSize);

		if (totalPaginas < 1) {

			totalPaginas = 1;
		}

		paginaLabel.setText("Página " + paginaActual + " de " + totalPaginas);

		anteriorButton.setEnabled(paginaActual > 1);

		siguienteButton.setEnabled(paginaActual < totalPaginas);
	}
}