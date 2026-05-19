package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.paula.checkmc.model.EmpleadoDTO;
import com.paula.checkmc.model.Rol;
import com.paula.checkmc.service.RolService;
import com.paula.checkmc.service.impl.RolServiceImpl;
import com.paula.checkmycar.desktop.controller.EmpleadoSearchController;
import com.paula.checkmycar.desktop.views.renderer.ClienteEmpleadoButtonRenderer;
import com.paula.checkmycar.desktop.views.tableModel.EmpleadoTableModel;
import com.paula.checkmycar.desktop.views.tableModel.editor.ClienteEmpleadoButtonEditor;

public class EmpleadoSearchView extends View {

	private JTextField nombreTF;
	private JTextField dniTF;
	private JTextField emailTF;
	private JComboBox<Rol> rolComboBox;

	private JButton buscarButton;
	private JButton limpiarButton;

	private JTable tabla;
	private EmpleadoTableModel tableModel;

	private RolService rolService;

	private JButton anteriorButton;
	private JButton siguienteButton;
	private JLabel paginaLabel;
	private int paginaActual = 1;
	private EmpleadoSearchController searchController;

	public EmpleadoSearchView() {
		setName("Búsqueda de empleados");
		rolService = new RolServiceImpl();
		initialize();
		postInitialize();
	}

	private void initialize() {
		setLayout(new BorderLayout(0, 0));

		JPanel criteriosPanel = new JPanel();
		add(criteriosPanel, BorderLayout.NORTH);

		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		criteriosPanel.setLayout(gbl);

		JLabel rolLabel = new JLabel("Rol: ");
		GridBagConstraints gbc_rolLabel = new GridBagConstraints();
		gbc_rolLabel.anchor = GridBagConstraints.EAST;
		gbc_rolLabel.insets = new Insets(10, 10, 5, 5);
		gbc_rolLabel.gridx = 0;
		gbc_rolLabel.gridy = 1;
		criteriosPanel.add(rolLabel, gbc_rolLabel);

		rolComboBox = new JComboBox<>();
		GridBagConstraints gbc_rolComboBox = new GridBagConstraints();
		gbc_rolComboBox.insets = new Insets(10, 0, 5, 10);
		gbc_rolComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_rolComboBox.gridx = 1;
		gbc_rolComboBox.gridy = 1;
		criteriosPanel.add(rolComboBox, gbc_rolComboBox);

		JLabel nombreLabel = new JLabel("Nombre: ");
		GridBagConstraints gbc_nombreLabel = new GridBagConstraints();
		gbc_nombreLabel.anchor = GridBagConstraints.EAST;
		gbc_nombreLabel.insets = new Insets(10, 5, 5, 5);
		gbc_nombreLabel.gridx = 2;
		gbc_nombreLabel.gridy = 1;
		criteriosPanel.add(nombreLabel, gbc_nombreLabel);

		nombreTF = new JTextField();
		GridBagConstraints gbc_nombreTF = new GridBagConstraints();
		gbc_nombreTF.insets = new Insets(10, 0, 5, 10);
		gbc_nombreTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreTF.gridx = 3;
		gbc_nombreTF.gridy = 1;
		nombreTF.setColumns(10);
		criteriosPanel.add(nombreTF, gbc_nombreTF);

		JLabel dniLabel = new JLabel("DNI / NIE: ");
		GridBagConstraints gbc_dniLabel = new GridBagConstraints();
		gbc_dniLabel.anchor = GridBagConstraints.EAST;
		gbc_dniLabel.insets = new Insets(10, 5, 5, 5);
		gbc_dniLabel.gridx = 4;
		gbc_dniLabel.gridy = 1;
		criteriosPanel.add(dniLabel, gbc_dniLabel);

		dniTF = new JTextField();
		GridBagConstraints gbc_dniTF = new GridBagConstraints();
		gbc_dniTF.insets = new Insets(10, 0, 5, 10);
		gbc_dniTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_dniTF.gridx = 5;
		gbc_dniTF.gridy = 1;
		dniTF.setColumns(10);
		criteriosPanel.add(dniTF, gbc_dniTF);

		JLabel emailLabel = new JLabel("Email: ");
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.anchor = GridBagConstraints.EAST;
		gbc_emailLabel.insets = new Insets(10, 5, 5, 5);
		gbc_emailLabel.gridx = 6;
		gbc_emailLabel.gridy = 1;
		criteriosPanel.add(emailLabel, gbc_emailLabel);

		emailTF = new JTextField();
		GridBagConstraints gbc_emailTF = new GridBagConstraints();
		gbc_emailTF.insets = new Insets(10, 0, 5, 10);
		gbc_emailTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTF.gridx = 7;
		gbc_emailTF.gridy = 1;
		emailTF.setColumns(10);
		criteriosPanel.add(emailTF, gbc_emailTF);

		limpiarButton = new JButton("Limpiar");
		limpiarButton.setIcon(new ImageIcon(EmpleadoSearchView.class.getResource("/icons/16x16/basura.png")));
		GridBagConstraints gbc_limpiarButton = new GridBagConstraints();
		gbc_limpiarButton.insets = new Insets(5, 5, 10, 5);
		gbc_limpiarButton.gridx = 6;
		gbc_limpiarButton.gridy = 3;
		criteriosPanel.add(limpiarButton, gbc_limpiarButton);

		buscarButton = new JButton("Buscar");
		buscarButton.setIcon(new ImageIcon(EmpleadoSearchView.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));
		GridBagConstraints gbc_buscarButton = new GridBagConstraints();
		gbc_buscarButton.insets = new Insets(5, 0, 10, 10);
		gbc_buscarButton.gridx = 7;
		gbc_buscarButton.gridy = 3;
		criteriosPanel.add(buscarButton, gbc_buscarButton);

		JPanel resultadosPanel = new JPanel(new BorderLayout());
		add(resultadosPanel, BorderLayout.CENTER);

		tableModel = new EmpleadoTableModel();
		tabla = new JTable(tableModel);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tabla.setFillsViewportHeight(true);
		resultadosPanel.add(new JScrollPane(tabla), BorderLayout.CENTER);

		limpiarButton.addActionListener(e -> clearFields());

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

		try {

			DefaultComboBoxModel<Rol> rolModel = new DefaultComboBoxModel<>();

			Rol placeholder = new Rol();

			placeholder.setNombre("Todos");

			rolModel.addElement(placeholder);

			for (Rol r : rolService.findAll()) {

				rolModel.addElement(r);
			}

			rolComboBox.setModel(rolModel);

			rolComboBox.setRenderer(new DefaultListCellRenderer() {

				@Override
				public Component getListCellRendererComponent(JList<?> list, Object value, int index,
						boolean isSelected, boolean cellHasFocus) {

					super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

					if (value instanceof Rol) {

						setText(((Rol) value).getNombre());
					}

					return this;
				}
			});

			searchController = new EmpleadoSearchController(this);
			buscarButton.addActionListener(e -> searchController.buscar(1));

			anteriorButton.addActionListener(e -> {

				if (paginaActual > 1) {

					searchController.buscar(paginaActual - 1);
				}
			});

			siguienteButton.addActionListener(e -> {

				searchController.buscar(paginaActual + 1);
			});

			tabla.getColumn("Acciones").setCellRenderer(new ClienteEmpleadoButtonRenderer());

			tabla.getColumn("Acciones").setCellEditor(new ClienteEmpleadoButtonEditor(new JCheckBox()));
		} catch (Exception e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actualizarPaginacion(int pagina, int pageSize, int total) {
		this.paginaActual = pagina;
		int totalPaginas = (int) Math.ceil((double) total / pageSize);
		if (totalPaginas == 0)
			totalPaginas = 1;
		paginaLabel.setText("Página " + pagina + " de " + totalPaginas);
		anteriorButton.setEnabled(pagina > 1);
		siguienteButton.setEnabled(pagina < totalPaginas);
	}

	public String getNombre() {
		return nombreTF.getText();
	}

	public String getDni() {
		return dniTF.getText();
	}

	public String getEmail() {
		return emailTF.getText();
	}

	public Rol getRol() {
		Rol selected = (Rol) rolComboBox.getSelectedItem();
		if (selected == null || selected.getId() == null)
			return null;
		return selected;
	}

	public void setTableData(List<EmpleadoDTO> lista) {
		tableModel.setEmpleados(lista);
	}

	public void clearFields() {
		nombreTF.setText("");
		dniTF.setText("");
		emailTF.setText("");
		rolComboBox.setSelectedIndex(0);
		tableModel.setEmpleados(null);
	}
}