package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.paula.checkmc.model.LineaPresupuestoDTO;
import com.paula.checkmc.model.PiezaCriteria;
import com.paula.checkmc.model.PiezaDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.PiezaService;
import com.paula.checkmc.service.impl.PiezaServiceImpl;
import com.paula.checkmycar.desktop.views.renderer.PiezaCBRenderer;

public class LineaPresupuestoCreateView extends JDialog {

	private JComboBox<PiezaDTO> piezaCB;

	private JSpinner unidadesSpinner;

	private JLabel precioLabel;

	private JLabel totalLabel;

	private JButton añadirButton;

	private JButton cancelarButton;

	private LineaPresupuestoDTO linea;

	private PiezaService piezaService;

	public LineaPresupuestoCreateView() {

		piezaService = new PiezaServiceImpl();

		initialize();

		postInitialize();
	}

	private void initialize() {

		setTitle("Añadir línea");

		setModal(true);

		setSize(450, 250);

		setLocationRelativeTo(null);

		setLayout(new BorderLayout());

		JPanel formPanel = new JPanel(new GridBagLayout());

		add(formPanel, BorderLayout.CENTER);

		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(5, 5, 5, 5);

		c.fill = GridBagConstraints.HORIZONTAL;

		int y = 0;

		c.gridx = 0;
		c.gridy = y;

		formPanel.add(new JLabel("Pieza:"), c);

		piezaCB = new JComboBox<>();

		c.gridx = 1;

		formPanel.add(piezaCB, c);

		y++;

		c.gridx = 0;
		c.gridy = y;

		formPanel.add(new JLabel("Unidades:"), c);

		unidadesSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));

		c.gridx = 1;

		formPanel.add(unidadesSpinner, c);

		y++;

		c.gridx = 0;
		c.gridy = y;

		formPanel.add(new JLabel("Precio unidad:"), c);

		precioLabel = new JLabel("0.00 €");

		c.gridx = 1;

		formPanel.add(precioLabel, c);

		y++;

		c.gridx = 0;
		c.gridy = y;

		formPanel.add(new JLabel("Total línea:"), c);

		totalLabel = new JLabel("0.00 €");

		c.gridx = 1;

		formPanel.add(totalLabel, c);

		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		add(buttonsPanel, BorderLayout.SOUTH);

		cancelarButton = new JButton("Cancelar");

		añadirButton = new JButton("Añadir");

		buttonsPanel.add(cancelarButton);

		buttonsPanel.add(añadirButton);

		cancelarButton.addActionListener(e -> dispose());

		añadirButton.addActionListener(e -> guardarLinea());

		piezaCB.addActionListener(e -> actualizarTotales());

		unidadesSpinner.addChangeListener(e -> actualizarTotales());
	}

	private void postInitialize() {

		try {

			piezaCB.setRenderer(new PiezaCBRenderer());

			cargarPiezas();

		} catch (Exception e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(this, "Error cargando piezas");
		}
	}

	private void cargarPiezas() throws Exception {

		Results<PiezaDTO> results = piezaService.findByCriteria(new PiezaCriteria(), 1, 10);

		List<PiezaDTO> piezas = results.getPage();

		DefaultComboBoxModel<PiezaDTO> model = new DefaultComboBoxModel<>();

		for (PiezaDTO pieza : piezas) {

			model.addElement(pieza);
		}

		piezaCB.setModel(model);

		actualizarTotales();
	}

	private void actualizarTotales() {

		PiezaDTO pieza = (PiezaDTO) piezaCB.getSelectedItem();

		if (pieza == null) {

			return;
		}

		double unidades = ((Number) unidadesSpinner.getValue()).doubleValue();

		double precio = pieza.getPrecio();

		double total = precio * unidades;

		precioLabel.setText(precio + " €");

		totalLabel.setText(total + " €");
	}

	private void guardarLinea() {

		PiezaDTO pieza = (PiezaDTO) piezaCB.getSelectedItem();

		if (pieza == null) {

			JOptionPane.showMessageDialog(this, "Debe seleccionar una pieza");

			return;
		}

		double unidades = ((Number) unidadesSpinner.getValue()).doubleValue();

		double total = unidades * pieza.getPrecio();

		linea = new LineaPresupuestoDTO();

		linea.setPiezaId(pieza.getId());

		linea.setNombrePieza(pieza.getNombre());

		linea.setUnidades(unidades);

		linea.setPrecioUnitario(pieza.getPrecio());

		linea.setPrecioFinal(total);

		dispose();
	}

	public LineaPresupuestoDTO getLinea() {

		return linea;
	}
}