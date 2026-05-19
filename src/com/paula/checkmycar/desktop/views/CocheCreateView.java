package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import com.paula.checkmc.model.ClienteCriteria;
import com.paula.checkmc.model.ClienteDTO;
import com.paula.checkmc.model.Coche;
import com.paula.checkmc.model.CocheDTO;
import com.paula.checkmc.model.Marca;
import com.paula.checkmc.model.Modelo;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.model.TipoCombustible;
import com.paula.checkmc.model.TipoMotor;
import com.paula.checkmc.model.TipoTransmision;
import com.paula.checkmc.service.ClienteService;
import com.paula.checkmc.service.MarcaService;
import com.paula.checkmc.service.ModeloService;
import com.paula.checkmc.service.TipoCombustibleService;
import com.paula.checkmc.service.TipoMotorService;
import com.paula.checkmc.service.TipoTransmisionService;
import com.paula.checkmc.service.impl.ClienteServiceImpl;
import com.paula.checkmc.service.impl.MarcaServiceImpl;
import com.paula.checkmc.service.impl.ModeloServiceImpl;
import com.paula.checkmc.service.impl.TipoCombustibleServiceImpl;
import com.paula.checkmc.service.impl.TipoMotorServiceImpl;
import com.paula.checkmc.service.impl.TipoTransmisionServiceImpl;
import com.paula.checkmycar.desktop.controller.CocheCreateController;
import com.paula.checkmycar.desktop.controller.CocheSetEditableController;
import com.paula.checkmycar.desktop.controller.Controller;
import com.paula.checkmycar.desktop.views.renderer.ClienteCBRenderer;
import com.paula.checkmycar.desktop.views.renderer.MarcaCBRenderer;
import com.paula.checkmycar.desktop.views.renderer.ModeloCBRenderer;
import com.paula.checkmycar.desktop.views.renderer.TipoCombustibleCBRenderer;
import com.paula.checkmycar.desktop.views.renderer.TipoMotorCBRenderer;
import com.paula.checkmycar.desktop.views.renderer.TipoTransmisionCBRender;

public class CocheCreateView extends View {

	private JTextField matriculaTF;
	private JTextField bastidorTF;
	private JTextField anoTF;
	private JTextField potenciaTF;
	private JTextField kmTF;
	private JTextField precioTF;
	private JTextArea diagnosticoTA;

	private JComboBox<ClienteDTO> clienteCB;
	private JComboBox<Marca> marcaCB;
	private JComboBox<Modelo> modeloCB;
	private JComboBox<TipoCombustible> combustibleCB;
	private JComboBox<TipoMotor> motorCB;
	private JComboBox<TipoTransmision> transmisionCB;

	private JButton guardarButton;
	private JButton limpiarButton;

	private Long cocheId;

	private ClienteService clienteService = new ClienteServiceImpl();
	private MarcaService marcaService = new MarcaServiceImpl();
	private ModeloService modeloService = new ModeloServiceImpl();
	private TipoCombustibleService combustibleService = new TipoCombustibleServiceImpl();
	private TipoMotorService motorService = new TipoMotorServiceImpl();
	private TipoTransmisionService transmisionService = new TipoTransmisionServiceImpl();

	public CocheCreateView() {
		initialize();
		postInitialize();
	}

	private void initialize() {
		setName("Crear coche");
		setLayout(new BorderLayout());

		JPanel formPanel = new JPanel(new GridBagLayout());
		add(formPanel, BorderLayout.NORTH);

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;

		int y = 0;

		c.gridx = 0;
		c.gridy = y;
		formPanel.add(new JLabel("Matrícula *:"), c);
		matriculaTF = new JTextField();
		c.gridx = 1;
		formPanel.add(matriculaTF, c);

		c.gridx = 2;
		formPanel.add(new JLabel("Nº Bastidor:"), c);
		bastidorTF = new JTextField();
		c.gridx = 3;
		formPanel.add(bastidorTF, c);

		y++;
		c.gridx = 0;
		c.gridy = y;
		formPanel.add(new JLabel("Potencia CV:"), c);
		potenciaTF = new JTextField();
		c.gridx = 1;
		formPanel.add(potenciaTF, c);

		c.gridx = 2;
		formPanel.add(new JLabel("Kilómetros:"), c);
		kmTF = new JTextField();
		c.gridx = 3;
		formPanel.add(kmTF, c);

		y++;
		c.gridx = 0;
		c.gridy = y;
		formPanel.add(new JLabel("Año:"), c);
		anoTF = new JTextField();
		c.gridx = 1;
		formPanel.add(anoTF, c);

		c.gridx = 2;
		formPanel.add(new JLabel("Precio Final (€):"), c);
		precioTF = new JTextField();
		c.gridx = 3;
		formPanel.add(precioTF, c);

		y++;

		c.gridx = 0;
		c.gridy = y;
		formPanel.add(new JLabel("Diagnóstico:"), c);

		diagnosticoTA = new JTextArea(4, 20);
		diagnosticoTA.setLineWrap(true);
		diagnosticoTA.setWrapStyleWord(true);

		JScrollPane diagnosticoSP = new JScrollPane(diagnosticoTA);

		c.gridx = 1;
		c.gridwidth = 3;
		formPanel.add(diagnosticoSP, c);

		c.gridwidth = 1;

		y++;
		c.gridx = 0;
		c.gridy = y;
		formPanel.add(new JLabel("Cliente *:"), c);
		clienteCB = new JComboBox<ClienteDTO>();
		c.gridx = 1;
		formPanel.add(clienteCB, c);

		c.gridx = 2;
		formPanel.add(new JLabel("Marca *:"), c);
		marcaCB = new JComboBox<Marca>();
		c.gridx = 3;
		formPanel.add(marcaCB, c);

		y++;
		c.gridx = 0;
		c.gridy = y;
		formPanel.add(new JLabel("Modelo *:"), c);
		modeloCB = new JComboBox<Modelo>();
		c.gridx = 1;
		formPanel.add(modeloCB, c);

		c.gridx = 2;
		formPanel.add(new JLabel("Combustible *:"), c);
		combustibleCB = new JComboBox<TipoCombustible>();
		c.gridx = 3;
		formPanel.add(combustibleCB, c);

		y++;
		c.gridx = 0;
		c.gridy = y;
		formPanel.add(new JLabel("Motor *:"), c);
		motorCB = new JComboBox<TipoMotor>();
		c.gridx = 1;
		formPanel.add(motorCB, c);

		c.gridx = 2;
		formPanel.add(new JLabel("Transmisión *:"), c);
		transmisionCB = new JComboBox<TipoTransmision>();
		c.gridx = 3;
		formPanel.add(transmisionCB, c);

		JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		add(botonesPanel, BorderLayout.SOUTH);

		limpiarButton = new JButton("Limpiar");
		guardarButton = new JButton("Añadir");

		botonesPanel.add(limpiarButton);
		botonesPanel.add(guardarButton);

		setAgreeController(new CocheCreateController(this));

		limpiarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});

		marcaCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarModelos();
			}
		});
	}

	private void postInitialize() {

		marcaCB.setRenderer(new MarcaCBRenderer());
		modeloCB.setRenderer(new ModeloCBRenderer());
		combustibleCB.setRenderer(new TipoCombustibleCBRenderer());
		motorCB.setRenderer(new TipoMotorCBRenderer());
		transmisionCB.setRenderer(new TipoTransmisionCBRender());
		clienteCB.setRenderer(new ClienteCBRenderer());

		cargarClientes();
		cargarMarcas();
		cargarCombustibles();
		cargarMotores();
		cargarTransmisiones();
	}

	private void cargarClientes() {

		DefaultComboBoxModel<ClienteDTO> model = new DefaultComboBoxModel<ClienteDTO>();

		ClienteDTO placeholder = new ClienteDTO();
		placeholder.setId(null);
		placeholder.setNombre("Seleccionar");

		model.addElement(placeholder);

		try {

			Results<ClienteDTO> resultado = clienteService.findByCriteria(new ClienteCriteria(), 1, 1000);

			if (resultado != null && resultado.getPage() != null) {

				for (ClienteDTO cliente : resultado.getPage()) {
					model.addElement(cliente);
				}
			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(this, "Error al cargar los clientes.", "Error", JOptionPane.ERROR_MESSAGE);

			e.printStackTrace();
		}

		clienteCB.setModel(model);
	}

	private void cargarMarcas() {

		DefaultComboBoxModel<Marca> model = new DefaultComboBoxModel<Marca>();

		Marca placeholder = new Marca();
		placeholder.setId(null);
		placeholder.setNombre("Seleccionar");

		model.addElement(placeholder);

		try {

			for (Marca marca : marcaService.findAll()) {
				model.addElement(marca);
			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(this, "Error al cargar las marcas.", "Error", JOptionPane.ERROR_MESSAGE);

			e.printStackTrace();
		}

		marcaCB.setModel(model);
	}

	private void cargarCombustibles() {

		DefaultComboBoxModel<TipoCombustible> model = new DefaultComboBoxModel<TipoCombustible>();

		TipoCombustible placeholder = new TipoCombustible();
		placeholder.setId(null);
		placeholder.setNombre("Seleccionar");

		model.addElement(placeholder);

		try {

			for (TipoCombustible combustible : combustibleService.findAll()) {
				model.addElement(combustible);
			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(this, "Error al cargar los combustibles.", "Error",
					JOptionPane.ERROR_MESSAGE);

			e.printStackTrace();
		}

		combustibleCB.setModel(model);
	}

	private void cargarMotores() {

		DefaultComboBoxModel<TipoMotor> model = new DefaultComboBoxModel<TipoMotor>();

		TipoMotor placeholder = new TipoMotor();
		placeholder.setId(null);
		placeholder.setNombre("Seleccionar");

		model.addElement(placeholder);

		try {

			for (TipoMotor motor : motorService.findAll()) {
				model.addElement(motor);
			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(this, "Error al cargar los motores.", "Error", JOptionPane.ERROR_MESSAGE);

			e.printStackTrace();
		}

		motorCB.setModel(model);
	}

	private void cargarTransmisiones() {

		DefaultComboBoxModel<TipoTransmision> model = new DefaultComboBoxModel<TipoTransmision>();

		TipoTransmision placeholder = new TipoTransmision();
		placeholder.setId(null);
		placeholder.setNombre("Seleccionar");

		model.addElement(placeholder);

		try {

			for (TipoTransmision transmision : transmisionService.findAll()) {
				model.addElement(transmision);
			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(this, "Error al cargar las transmisiones.", "Error",
					JOptionPane.ERROR_MESSAGE);

			e.printStackTrace();
		}

		transmisionCB.setModel(model);
	}

	private void cargarModelos() {

		Marca marca = (Marca) marcaCB.getSelectedItem();

		DefaultComboBoxModel<Modelo> model = new DefaultComboBoxModel<Modelo>();

		Modelo placeholder = new Modelo();
		placeholder.setId(null);
		placeholder.setNombre("Seleccionar");

		model.addElement(placeholder);

		if (marca != null && marca.getId() != null) {

			try {

				for (Modelo m : modeloService.findByMarca(marca.getId())) {
					model.addElement(m);
				}

			} catch (Exception e) {

				JOptionPane.showMessageDialog(this, "Error al cargar los modelos.", "Error", JOptionPane.ERROR_MESSAGE);

				e.printStackTrace();
			}
		}

		modeloCB.setModel(model);
	}

	private void limpiar() {
		matriculaTF.setText("");
		bastidorTF.setText("");
		anoTF.setText("");
		potenciaTF.setText("");
		kmTF.setText("");
		precioTF.setText("");
		diagnosticoTA.setText("");
		clienteCB.setSelectedIndex(0);
		marcaCB.setSelectedIndex(0);
		combustibleCB.setSelectedIndex(0);
		motorCB.setSelectedIndex(0);
		transmisionCB.setSelectedIndex(0);
		cocheId = null;
	}

	public Coche getModel() {

		String matricula = matriculaTF.getText().trim();

		if (matricula.isEmpty()) {
			JOptionPane.showMessageDialog(this, "La matrícula es obligatoria.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		ClienteDTO cliente = (ClienteDTO) clienteCB.getSelectedItem();

		if (cliente == null || cliente.getId() == null) {
			JOptionPane.showMessageDialog(this, "Seleccione un cliente.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		Marca marca = (Marca) marcaCB.getSelectedItem();

		if (marca == null || marca.getId() == null) {
			JOptionPane.showMessageDialog(this, "Seleccione una marca.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		Modelo modelo = (Modelo) modeloCB.getSelectedItem();

		if (modelo == null || modelo.getId() == null) {
			JOptionPane.showMessageDialog(this, "Seleccione un modelo.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		TipoCombustible combustible = (TipoCombustible) combustibleCB.getSelectedItem();

		if (combustible == null || combustible.getId() == null) {
			JOptionPane.showMessageDialog(this, "Seleccione un combustible.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		TipoMotor motor = (TipoMotor) motorCB.getSelectedItem();

		if (motor == null || motor.getId() == null) {
			JOptionPane.showMessageDialog(this, "Seleccione un tipo de motor.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		TipoTransmision transmision = (TipoTransmision) transmisionCB.getSelectedItem();

		if (transmision == null || transmision.getId() == null) {
			JOptionPane.showMessageDialog(this, "Seleccione una transmisión.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		try {

			Coche coche = new Coche();

			coche.setId(cocheId);
			coche.setMatricula(matricula);
			coche.setNumeroBastidor(bastidorTF.getText().trim());
			coche.setDiagnostico(diagnosticoTA.getText().trim());

			coche.setClienteId(cliente.getId());
			coche.setMarcaId(marca.getId());
			coche.setModeloId(modelo.getId());

			coche.setTipoCombustibleId(combustible.getId());
			coche.setTipoMotorId(motor.getId());
			coche.setTipoTransmisionId(transmision.getId());

			String anoTexto = anoTF.getText().trim();
			String potenciaTexto = potenciaTF.getText().trim();
			String kmTexto = kmTF.getText().trim();
			String precioTexto = precioTF.getText().trim();

			if (!anoTexto.isEmpty()) {
				coche.setAno(Integer.parseInt(anoTexto));
			}

			if (!potenciaTexto.isEmpty()) {
				coche.setPotenciaCv(Long.parseLong(potenciaTexto));
			}

			if (!kmTexto.isEmpty()) {
				coche.setKilometros(Long.parseLong(kmTexto));
			}

			if (!precioTexto.isEmpty()) {
				coche.setPrecioFinal(Double.parseDouble(precioTexto));
			}

			return coche;

		} catch (NumberFormatException e) {

			JOptionPane.showMessageDialog(this, "Revise los campos numéricos. Hay valores inválidos.",
					"Error de formato", JOptionPane.ERROR_MESSAGE);

		} catch (Exception e) {

			JOptionPane.showMessageDialog(this, "Se produjo un error al crear el coche.", "Error",
					JOptionPane.ERROR_MESSAGE);

			e.printStackTrace();
		}

		return null;
	}

	public void setEditable(boolean editable) {
		matriculaTF.setEditable(editable);
		bastidorTF.setEditable(editable);
		anoTF.setEditable(editable);
		potenciaTF.setEditable(editable);
		kmTF.setEditable(editable);
		precioTF.setEditable(editable);
		diagnosticoTA.setEditable(editable);
		clienteCB.setEnabled(editable);
		marcaCB.setEnabled(editable);
		modeloCB.setEnabled(editable);
		combustibleCB.setEnabled(editable);
		motorCB.setEnabled(editable);
		transmisionCB.setEnabled(editable);
	}

	public void setAgreeController(Controller controller) {
	    guardarButton.setAction(controller);
	}

	public void setCancelController(Controller controller) {
	}

	public void setCocheDTO(CocheDTO coche) {
		this.cocheId = coche.getId();

		matriculaTF.setText(coche.getMatricula());
		bastidorTF.setText(coche.getNumeroBastidor());
		anoTF.setText(coche.getAno() != null ? coche.getAno().toString() : "");
		potenciaTF.setText(String.valueOf(coche.getPotenciaCv()));
		kmTF.setText(String.valueOf((long) coche.getKilometros()));
		precioTF.setText(String.valueOf(coche.getPrecioFinal()));
		diagnosticoTA.setText(coche.getDiagnostico());

		for (int i = 0; i < marcaCB.getItemCount(); i++) {
			Marca m = marcaCB.getItemAt(i);
			if (m.getId() != null && m.getNombre().equals(coche.getNombreMarca())) {
				marcaCB.setSelectedIndex(i);
				break;
			}
		}

		for (int i = 0; i < modeloCB.getItemCount(); i++) {
			Modelo m = modeloCB.getItemAt(i);
			if (m.getId() != null && m.getNombre().equals(coche.getNombreModelo())) {
				modeloCB.setSelectedIndex(i);
				break;
			}
		}

		for (int i = 0; i < combustibleCB.getItemCount(); i++) {
			TipoCombustible tc = combustibleCB.getItemAt(i);
			if (tc.getId() != null && tc.getId().equals(coche.getTipoCombustibleId())) {
				combustibleCB.setSelectedIndex(i);
				break;
			}
		}

		for (int i = 0; i < motorCB.getItemCount(); i++) {
			TipoMotor tm = motorCB.getItemAt(i);
			if (tm.getId() != null && tm.getId().equals(coche.getTipoMotorId())) {
				motorCB.setSelectedIndex(i);
				break;
			}
		}

		for (int i = 0; i < transmisionCB.getItemCount(); i++) {
			TipoTransmision tt = transmisionCB.getItemAt(i);
			if (tt.getId() != null && tt.getId().equals(coche.getTipoTransmisionId())) {
				transmisionCB.setSelectedIndex(i);
				break;
			}
		}

		for (int i = 0; i < clienteCB.getItemCount(); i++) {
			ClienteDTO cl = clienteCB.getItemAt(i);
			if (cl.getId() != null && cl.getId().equals(coche.getClienteId())) {
				clienteCB.setSelectedIndex(i);
				break;
			}
		}

		setEditable(false);
		setAgreeController(new CocheSetEditableController(this));
	}
}