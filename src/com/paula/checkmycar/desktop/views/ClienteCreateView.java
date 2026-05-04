package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.paula.checkmc.model.Cliente;
import com.paula.checkmc.model.ClienteDTO;
import com.paula.checkmc.model.Genero;
import com.paula.checkmc.model.Localidad;
import com.paula.checkmc.model.Pais;
import com.paula.checkmc.model.Provincia;
import com.paula.checkmc.service.ClienteService;
import com.paula.checkmc.service.LocalidadService;
import com.paula.checkmc.service.PaisService;
import com.paula.checkmc.service.ProvinciaService;
import com.paula.checkmc.service.impl.ClienteServiceImpl;
import com.paula.checkmc.service.impl.LocalidadServiceImpl;
import com.paula.checkmc.service.impl.PaisServiceImpl;
import com.paula.checkmc.service.impl.ProvinciaServiceImpl;
import com.paula.checkmycar.desktop.controller.ClienteCreateController;
import com.paula.checkmycar.desktop.controller.ClienteSetEditableController;
import com.paula.checkmycar.desktop.controller.Controller;
import com.paula.checkmycar.desktop.views.renderer.LocalidadCBRender;
import com.paula.checkmycar.desktop.views.renderer.PaisCBRenderer;
import com.paula.checkmycar.desktop.views.renderer.ProvinciaCBRenderer;

public class ClienteCreateView extends View {

    private JTextField nombreTF;
    private JTextField apellido2TF;
    private JTextField apellido1TF;
    private JTextField telefonoTF;
    private JTextField emailTf;
    private JTextField dniNieTF;
    private JTextField direccionTF;
    private JComboBox paisComboBox;
    private JComboBox provinciaComboBox;
    private JComboBox ciudadComboBox;
    private JRadioButton masculinoRB;
    private JRadioButton femeninoRB;    
    private JRadioButton otroRB;
    private JTextField contrasenaTF;
    
   
    
    private JButton agreeButton;
    private JButton cancelButton;
    
    private Long clienteId; 
    private ClienteService clienteService = null;
    private PaisService paisService = null;
    private ProvinciaService provinciaService  = null;
    private LocalidadService localidadService = null;
    private JTextField contasenaLabel;

    public ClienteCreateView() {        
        initServices();
        initialize();
        postInitialize();
    }

    private void initialize() {
		setName("Registro de cliente");
		setLayout(new BorderLayout(0, 0));
		
		JPanel contentPanel = new JPanel();
		add(contentPanel, BorderLayout.WEST);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{64, 123, 0, 51, 0, 71, 0};
		gbl_contentPanel.rowHeights = new int[]{13, 19, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		JLabel lblNewLabel = new JLabel("Datos personales del cliente : ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel nombreLabel = new JLabel("Nombre: ");
		GridBagConstraints gbc_nombreLabel = new GridBagConstraints();
		gbc_nombreLabel.anchor = GridBagConstraints.EAST;
		gbc_nombreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nombreLabel.gridx = 0;
		gbc_nombreLabel.gridy = 1;
		contentPanel.add(nombreLabel, gbc_nombreLabel);
		
		nombreTF = new JTextField();
		GridBagConstraints gbc_nombreTF = new GridBagConstraints();
		gbc_nombreTF.insets = new Insets(0, 0, 5, 5);
		gbc_nombreTF.anchor = GridBagConstraints.NORTH;
		gbc_nombreTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreTF.gridx = 1;
		gbc_nombreTF.gridy = 1;
		contentPanel.add(nombreTF, gbc_nombreTF);
		nombreTF.setColumns(10);
		
		JLabel apellido1Label = new JLabel("primer Apellido: ");
		GridBagConstraints gbc_apellido1Label = new GridBagConstraints();
		gbc_apellido1Label.insets = new Insets(0, 0, 5, 5);
		gbc_apellido1Label.gridx = 2;
		gbc_apellido1Label.gridy = 1;
		contentPanel.add(apellido1Label, gbc_apellido1Label);
		
		apellido1TF = new JTextField();
		GridBagConstraints gbc_apellido1TF = new GridBagConstraints();
		gbc_apellido1TF.insets = new Insets(0, 0, 5, 5);
		gbc_apellido1TF.fill = GridBagConstraints.HORIZONTAL;
		gbc_apellido1TF.gridx = 3;
		gbc_apellido1TF.gridy = 1;
		contentPanel.add(apellido1TF, gbc_apellido1TF);
		apellido1TF.setColumns(10);
		
		JLabel apellido2Label = new JLabel("Segundo Apellido: ");
		GridBagConstraints gbc_apellido2Label = new GridBagConstraints();
		gbc_apellido2Label.insets = new Insets(0, 0, 5, 5);
		gbc_apellido2Label.anchor = GridBagConstraints.EAST;
		gbc_apellido2Label.gridx = 4;
		gbc_apellido2Label.gridy = 1;
		contentPanel.add(apellido2Label, gbc_apellido2Label);
		
		apellido2TF = new JTextField();
		GridBagConstraints gbc_apellido2TF = new GridBagConstraints();
		gbc_apellido2TF.insets = new Insets(0, 0, 5, 0);
		gbc_apellido2TF.fill = GridBagConstraints.HORIZONTAL;
		gbc_apellido2TF.gridx = 5;
		gbc_apellido2TF.gridy = 1;
		contentPanel.add(apellido2TF, gbc_apellido2TF);
		apellido2TF.setColumns(10);
		
		JLabel telefonoLabel = new JLabel("Telefono: ");
		GridBagConstraints gbc_telefonoLabel = new GridBagConstraints();
		gbc_telefonoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_telefonoLabel.gridx = 0;
		gbc_telefonoLabel.gridy = 2;
		contentPanel.add(telefonoLabel, gbc_telefonoLabel);
		
		telefonoTF = new JTextField();
		GridBagConstraints gbc_telefonoTF = new GridBagConstraints();
		gbc_telefonoTF.insets = new Insets(0, 0, 5, 5);
		gbc_telefonoTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_telefonoTF.gridx = 1;
		gbc_telefonoTF.gridy = 2;
		contentPanel.add(telefonoTF, gbc_telefonoTF);
		telefonoTF.setColumns(10);
		
		JLabel emailLabel = new JLabel("Email: ");
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.anchor = GridBagConstraints.WEST;
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.gridx = 2;
		gbc_emailLabel.gridy = 2;
		contentPanel.add(emailLabel, gbc_emailLabel);
		
		emailTf = new JTextField();
		GridBagConstraints gbc_emailTf = new GridBagConstraints();
		gbc_emailTf.insets = new Insets(0, 0, 5, 5);
		gbc_emailTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTf.gridx = 3;
		gbc_emailTf.gridy = 2;
		contentPanel.add(emailTf, gbc_emailTf);
		emailTf.setColumns(10);
		
		JLabel dniNieLabel = new JLabel("Dni/Nie: ");
		GridBagConstraints gbc_dniNieLabel = new GridBagConstraints();
		gbc_dniNieLabel.anchor = GridBagConstraints.WEST;
		gbc_dniNieLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dniNieLabel.gridx = 4;
		gbc_dniNieLabel.gridy = 2;
		contentPanel.add(dniNieLabel, gbc_dniNieLabel);
		
		dniNieTF = new JTextField();
		GridBagConstraints gbc_dniNieTF = new GridBagConstraints();
		gbc_dniNieTF.insets = new Insets(0, 0, 5, 0);
		gbc_dniNieTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_dniNieTF.gridx = 5;
		gbc_dniNieTF.gridy = 2;
		contentPanel.add(dniNieTF, gbc_dniNieTF);
		dniNieTF.setColumns(10);
		
		JLabel direccionLabel = new JLabel("Dirección: ");
		GridBagConstraints gbc_direccionLabel = new GridBagConstraints();
		gbc_direccionLabel.anchor = GridBagConstraints.EAST;
		gbc_direccionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_direccionLabel.gridx = 0;
		gbc_direccionLabel.gridy = 3;
		contentPanel.add(direccionLabel, gbc_direccionLabel);
		
		direccionTF = new JTextField();
		GridBagConstraints gbc_direccionTF = new GridBagConstraints();
		gbc_direccionTF.gridwidth = 5;
		gbc_direccionTF.insets = new Insets(0, 0, 5, 0);
		gbc_direccionTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_direccionTF.gridx = 1;
		gbc_direccionTF.gridy = 3;
		contentPanel.add(direccionTF, gbc_direccionTF);
		direccionTF.setColumns(10);
		
		JLabel paisLabel = new JLabel("País: ");
		GridBagConstraints gbc_paisLabel = new GridBagConstraints();
		gbc_paisLabel.insets = new Insets(0, 0, 5, 5);
		gbc_paisLabel.anchor = GridBagConstraints.EAST;
		gbc_paisLabel.gridx = 0;
		gbc_paisLabel.gridy = 4;
		contentPanel.add(paisLabel, gbc_paisLabel);
		
		paisComboBox = new JComboBox();
		paisComboBox.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {

		        if (e.getStateChange() == ItemEvent.SELECTED) {

		            Pais pais = (Pais) paisComboBox.getSelectedItem();

		            DefaultComboBoxModel<Provincia> model = new DefaultComboBoxModel<>();

		            Provincia placeholder = new Provincia();
		            placeholder.setId(null);
		            placeholder.setNombre("Seleccionar");

		            model.addElement(placeholder);

		            if (pais != null && pais.getId() != null) {

		                List<Provincia> provincias = provinciaService.findByPais(pais.getId());

		                if (provincias != null) {
		                    for (Provincia p : provincias) {
		                        model.addElement(p);
		                    }
		                }
		            }

		            provinciaComboBox.setModel(model);
		            provinciaComboBox.setRenderer(new ProvinciaCBRenderer());
		        }
		    }
		});
		GridBagConstraints gbc_paisComboBox = new GridBagConstraints();
		gbc_paisComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_paisComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_paisComboBox.gridx = 1;
		gbc_paisComboBox.gridy = 4;
		contentPanel.add(paisComboBox, gbc_paisComboBox);
		
		JLabel provinciaLabel = new JLabel("Provincia : ");
		GridBagConstraints gbc_provinciaLabel = new GridBagConstraints();
		gbc_provinciaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_provinciaLabel.anchor = GridBagConstraints.EAST;
		gbc_provinciaLabel.gridx = 2;
		gbc_provinciaLabel.gridy = 4;
		contentPanel.add(provinciaLabel, gbc_provinciaLabel);
		
		provinciaComboBox = new JComboBox();
		provinciaComboBox.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {

		        if (e.getStateChange() == ItemEvent.SELECTED) {

		            Provincia provincia = (Provincia) provinciaComboBox.getSelectedItem();

		            DefaultComboBoxModel<Localidad> ciudadModel = new DefaultComboBoxModel<>();

		            Localidad placeholder = new Localidad();
		            placeholder.setId(null);
		            placeholder.setNombre("Seleccionar");

		            ciudadModel.addElement(placeholder);

		            if (provincia != null && provincia.getId() != null) {

		                List<Localidad> localidades = localidadService.findByProvincia(provincia.getId());

		                if (localidades != null) {
		                    for (Localidad loc : localidades) {
		                        ciudadModel.addElement(loc);
		                    }
		                }
		            }

		            ciudadComboBox.setModel(ciudadModel);
		            ciudadComboBox.setRenderer(new LocalidadCBRender());
		            ciudadComboBox.setSelectedIndex(0);
		        }
		    }
		});
		GridBagConstraints gbc_provinciaComboBox = new GridBagConstraints();
		gbc_provinciaComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_provinciaComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_provinciaComboBox.gridx = 3;
		gbc_provinciaComboBox.gridy = 4;
		contentPanel.add(provinciaComboBox, gbc_provinciaComboBox);
		
		JLabel ciudadLabel = new JLabel("Ciudad : ");
		GridBagConstraints gbc_ciudadLabel = new GridBagConstraints();
		gbc_ciudadLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ciudadLabel.anchor = GridBagConstraints.EAST;
		gbc_ciudadLabel.gridx = 4;
		gbc_ciudadLabel.gridy = 4;
		contentPanel.add(ciudadLabel, gbc_ciudadLabel);
		
		ciudadComboBox = new JComboBox();
		GridBagConstraints gbc_ciudadComboBox = new GridBagConstraints();
		gbc_ciudadComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_ciudadComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_ciudadComboBox.gridx = 5;
		gbc_ciudadComboBox.gridy = 4;
		contentPanel.add(ciudadComboBox, gbc_ciudadComboBox);
		
		JLabel generoLabel = new JLabel("Genero: ");
		GridBagConstraints gbc_generoLabel = new GridBagConstraints();
		gbc_generoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_generoLabel.gridx = 0;
		gbc_generoLabel.gridy = 5;
		contentPanel.add(generoLabel, gbc_generoLabel);
		
		masculinoRB = new JRadioButton("Masculino ");
		GridBagConstraints gbc_masculinoRB = new GridBagConstraints();
		gbc_masculinoRB.insets = new Insets(0, 0, 5, 5);
		gbc_masculinoRB.gridx = 1;
		gbc_masculinoRB.gridy = 5;
		contentPanel.add(masculinoRB, gbc_masculinoRB);
		
		femeninoRB = new JRadioButton("Femenino ");
		GridBagConstraints gbc_femeninoRB = new GridBagConstraints();
		gbc_femeninoRB.insets = new Insets(0, 0, 5, 5);
		gbc_femeninoRB.gridx = 2;
		gbc_femeninoRB.gridy = 5;
		contentPanel.add(femeninoRB, gbc_femeninoRB);
		
		otroRB = new JRadioButton("Otro");
		GridBagConstraints gbc_otroRB = new GridBagConstraints();
		gbc_otroRB.insets = new Insets(0, 0, 5, 5);
		gbc_otroRB.gridx = 3;
		gbc_otroRB.gridy = 5;
		contentPanel.add(otroRB, gbc_otroRB);
		
		JLabel contrasenaLabel = new JLabel("Contraseña : ");
		GridBagConstraints gbc_contrasenaLabel = new GridBagConstraints();
		gbc_contrasenaLabel.anchor = GridBagConstraints.EAST;
		gbc_contrasenaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_contrasenaLabel.gridx = 0;
		gbc_contrasenaLabel.gridy = 6;
		contentPanel.add(contrasenaLabel, gbc_contrasenaLabel);
		
		contrasenaTF = new JTextField();
		GridBagConstraints gbc_contasenaLabel = new GridBagConstraints();
		gbc_contasenaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_contasenaLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_contasenaLabel.gridx = 1;
		gbc_contasenaLabel.gridy = 6;
		contentPanel.add(contrasenaTF, gbc_contasenaLabel);
		contrasenaTF.setColumns(10);
		
		 JPanel buttons = new JPanel();
	        add(buttons, BorderLayout.SOUTH);

	        agreeButton = new JButton("Guardar");
	        cancelButton = new JButton("Cancelar");

	        buttons.add(agreeButton);
	        buttons.add(cancelButton);
		
		
    }
    
    private void initServices() {
    	 clienteService = new ClienteServiceImpl();
         paisService = new PaisServiceImpl();
         provinciaService = new ProvinciaServiceImpl();
         localidadService = new LocalidadServiceImpl();
	}
    
    private void postInitialize() {

        List<Pais> paises = paisService.findAll();

        DefaultComboBoxModel<Pais> paisModel = new DefaultComboBoxModel<>();

        Pais placeholderPais = new Pais();
        placeholderPais.setId(null);
        placeholderPais.setNombre("Seleccionar");

        paisModel.addElement(placeholderPais);

        for (Pais pais : paises) {
            paisModel.addElement(pais);
        }

        paisComboBox.setModel(paisModel);
        paisComboBox.setRenderer(new PaisCBRenderer());


        DefaultComboBoxModel<Provincia> provinciaModel = new DefaultComboBoxModel<>();

        Provincia placeholderProvincia = new Provincia();
        placeholderProvincia.setId(null);
        placeholderProvincia.setNombre("Seleccionar");

        provinciaModel.addElement(placeholderProvincia);

        provinciaComboBox.setModel(provinciaModel);
        provinciaComboBox.setRenderer(new ProvinciaCBRenderer());


        DefaultComboBoxModel<Localidad> ciudadModel = new DefaultComboBoxModel<>();

        Localidad placeholderCiudad = new Localidad();
        placeholderCiudad.setId(null);
        placeholderCiudad.setNombre("Seleccionar");

        ciudadModel.addElement(placeholderCiudad);

        ciudadComboBox.setModel(ciudadModel);
        ciudadComboBox.setRenderer(new LocalidadCBRender());
        
        setAgreeController(new ClienteCreateController(this));
    }
    
    public Cliente getModel() {

       
        if (dniNieTF.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "El DNI es obligatorio",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setId(clienteId);  

        cliente.setNombre(nombreTF.getText());
        cliente.setPrimerApellido(apellido1TF.getText());
        cliente.setSegundoApellido(apellido2TF.getText());
        cliente.setDniNie(dniNieTF.getText());
        cliente.setEmail(emailTf.getText());
        cliente.setTelefono(telefonoTF.getText());
        cliente.setDireccion(direccionTF.getText());
        cliente.setPassword(contrasenaTF.getText());

        Localidad loc = (Localidad) ciudadComboBox.getSelectedItem();
        if (loc != null) cliente.setLocalidadId(loc.getId());

        if (masculinoRB.isSelected()) cliente.setGeneroId(Genero.MASCULINO.longValue());
        else if (femeninoRB.isSelected()) cliente.setGeneroId(Genero.FEMENINO.longValue());
        else if (otroRB.isSelected()) cliente.setGeneroId(Genero.OTRO_LONG);

        return cliente;
    }
    
    public void setClienteDTO(ClienteDTO cliente) {
        nombreTF.setText(cliente.getNombre());
        apellido1TF.setText(cliente.getPrimerApellido());
        apellido2TF.setText(cliente.getSegundoApellido());
        telefonoTF.setText(cliente.getTelefono());
        emailTf.setText(cliente.getEmail());
        dniNieTF.setText(cliente.getDniNie());
        direccionTF.setText(cliente.getDireccion());
        this.clienteId = cliente.getId();

        if (cliente.getGeneroId() != null) {
            if (cliente.getGeneroId().equals(Genero.MASCULINO.longValue())) masculinoRB.setSelected(true);
            else if (cliente.getGeneroId().equals(Genero.FEMENINO.longValue())) femeninoRB.setSelected(true);
            else if (cliente.getGeneroId().equals(Genero.OTRO_LONG)) otroRB.setSelected(true);
        }

        if (cliente.getLocalidadId() != null) {

            Localidad localidad = localidadService.findById(cliente.getLocalidadId());
            if (localidad != null) {

                Provincia provincia = provinciaService.findById(localidad.getProvinceId());
                if (provincia != null) {

                    Pais pais = paisService.findById(provincia.getPaisId());

                    if (pais != null) {
                        for (int i = 0; i < paisComboBox.getItemCount(); i++) {
                            Pais p = (Pais) paisComboBox.getItemAt(i);
                            if (p.getId() != null && p.getId().equals(pais.getId())) {
                                paisComboBox.setSelectedIndex(i);
                                break;
                            }
                        }
                    }

                    for (int i = 0; i < provinciaComboBox.getItemCount(); i++) {
                        Provincia prov = (Provincia) provinciaComboBox.getItemAt(i);
                        if (prov.getId() != null && prov.getId().equals(provincia.getId())) {
                            provinciaComboBox.setSelectedIndex(i);
                            break;
                        }
                    }
                }

                for (int i = 0; i < ciudadComboBox.getItemCount(); i++) {
                    Localidad loc = (Localidad) ciudadComboBox.getItemAt(i);
                    if (loc.getId() != null && loc.getId().equals(cliente.getLocalidadId())) {
                        ciudadComboBox.setSelectedIndex(i);
                        break;
                    }
                }
            }
        }

        setEditable(false);
        setAgreeController(new ClienteSetEditableController(this));
    }

public void setEditable(boolean editable) {
    nombreTF.setEditable(editable);
    apellido1TF.setEditable(editable);
    apellido2TF.setEditable(editable);
    telefonoTF.setEditable(editable);
    emailTf.setEditable(editable);
    dniNieTF.setEditable(editable);
    direccionTF.setEditable(editable);
    contrasenaTF.setEditable(editable);

    paisComboBox.setEnabled(editable);
    provinciaComboBox.setEnabled(editable);
    ciudadComboBox.setEnabled(editable);

    masculinoRB.setEnabled(editable);
    femeninoRB.setEnabled(editable);
    otroRB.setEnabled(editable);
}



public void setAgreeController(Controller controller) {
    agreeButton.setAction(controller);
}

public void setCancelController(Controller controller) {
    cancelButton.setAction(controller);
}
}