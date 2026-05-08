package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.paula.checkmc.model.Empleado;
import com.paula.checkmc.model.EmpleadoDTO;
import com.paula.checkmc.model.Genero;
import com.paula.checkmc.model.Localidad;
import com.paula.checkmc.model.Pais;
import com.paula.checkmc.model.Provincia;
import com.paula.checkmc.model.Rol;
import com.paula.checkmc.service.LocalidadService;
import com.paula.checkmc.service.PaisService;
import com.paula.checkmc.service.ProvinciaService;
import com.paula.checkmc.service.RolService;
import com.paula.checkmc.service.impl.LocalidadServiceImpl;
import com.paula.checkmc.service.impl.PaisServiceImpl;
import com.paula.checkmc.service.impl.ProvinciaServiceImpl;
import com.paula.checkmc.service.impl.RolServiceImpl;
import com.paula.checkmycar.desktop.controller.Controller;
import com.paula.checkmycar.desktop.controller.EmpleadoSetEditableController;
import com.paula.checkmycar.desktop.views.renderer.LocalidadCBRender;
import com.paula.checkmycar.desktop.views.renderer.PaisCBRenderer;
import com.paula.checkmycar.desktop.views.renderer.ProvinciaCBRenderer;

public class EmpleadoCreateView extends View {

    private JTextField nombreTF; 
    private JTextField apellido1TF;
    private JTextField apellido2TF; 
    private JTextField telefonoTF;
   
    private JTextField dniNieTF; 
    private JTextField direccionTF;
    private JTextField contrasenaTF;
    private JComboBox<Pais> paisComboBox;
    private JComboBox<Provincia> provinciaComboBox;
    private JComboBox<Localidad> ciudadComboBox;
    private JComboBox<Rol> rolComboBox;
    private JRadioButton masculinoRB, femeninoRB, otroRB;

    private PaisService paisService;
    private ProvinciaService provinciaService;
    private LocalidadService localidadService;
    private RolService rolService;

    
    private JButton agreeButton;
    private JButton cancelButton;
    private JLabel rolLabel;
    private JTextField rolTF;
    private JTextField textField;
    private JTextField emailTF;
    
    public EmpleadoCreateView() {
        initServices();
        initialize();
        postInitialize();
    }

    private void initialize() {
		setName("Registro de empleados");
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
		gbc_nombreLabel.anchor = GridBagConstraints.WEST;
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
		gbc_apellido1Label.anchor = GridBagConstraints.WEST;
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
		gbc_apellido2Label.anchor = GridBagConstraints.WEST;
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
		gbc_telefonoLabel.anchor = GridBagConstraints.WEST;
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
		gbc_emailLabel.anchor = GridBagConstraints.EAST;
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.gridx = 2;
		gbc_emailLabel.gridy = 2;
		contentPanel.add(emailLabel, gbc_emailLabel);
		
		emailTF = new JTextField();
		GridBagConstraints gbc_emailTF = new GridBagConstraints();
		gbc_emailTF.insets = new Insets(0, 0, 5, 5);
		gbc_emailTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTF.gridx = 3;
		gbc_emailTF.gridy = 2;
		contentPanel.add(emailTF, gbc_emailTF);
		emailTF.setColumns(10);
		
		
		
		
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
		gbc_direccionLabel.anchor = GridBagConstraints.WEST;
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
		gbc_paisLabel.anchor = GridBagConstraints.WEST;
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
		gbc_provinciaLabel.anchor = GridBagConstraints.WEST;
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
		gbc_ciudadLabel.anchor = GridBagConstraints.WEST;
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
		gbc_generoLabel.anchor = GridBagConstraints.WEST;
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
		gbc_contrasenaLabel.anchor = GridBagConstraints.WEST;
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
		
		rolLabel = new JLabel("Rol : ");
		GridBagConstraints gbc_rolLabel = new GridBagConstraints();
		gbc_rolLabel.anchor = GridBagConstraints.EAST;
		gbc_rolLabel.insets = new Insets(0, 0, 5, 5);
		gbc_rolLabel.gridx = 2;
		gbc_rolLabel.gridy = 6;
		contentPanel.add(rolLabel, gbc_rolLabel);
		
		rolComboBox = new JComboBox<>();
		GridBagConstraints gbc_rolComboBox = new GridBagConstraints();
		gbc_rolComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_rolComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_rolComboBox.gridx = 3;
		gbc_rolComboBox.gridy = 6;
		contentPanel.add(rolComboBox, gbc_rolComboBox);
		
		 JPanel buttons = new JPanel();
	        add(buttons, BorderLayout.SOUTH);

	        agreeButton = new JButton("Guardar");
	        cancelButton = new JButton("Cancelar");

	        buttons.add(agreeButton);
	        buttons.add(cancelButton);
		
		
    }

  
    private GridBagConstraints gbc(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.HORIZONTAL;
        return c;
    }

    private GridBagConstraints gbcSpan(int x, int y, int width) {
        GridBagConstraints c = gbc(x,y);
        c.gridwidth = width;
        return c;
    }

    private void initServices() {
        paisService = new PaisServiceImpl();
        provinciaService = new ProvinciaServiceImpl();
        localidadService = new LocalidadServiceImpl();
        rolService = new RolServiceImpl();
    }

    private void postInitialize() {

        DefaultComboBoxModel<Pais> paisModel = new DefaultComboBoxModel<>();
        Pais p0 = new Pais(); p0.setNombre("Seleccionar");
        paisModel.addElement(p0);
        for (Pais p : paisService.findAll()) paisModel.addElement(p);
        paisComboBox.setModel(paisModel);
        paisComboBox.setRenderer(new PaisCBRenderer());

        provinciaComboBox.setRenderer(new ProvinciaCBRenderer());
        ciudadComboBox.setRenderer(new LocalidadCBRender());

        rolComboBox.setModel(new DefaultComboBoxModel<>(rolService.findAll().toArray(new Rol[0])));
        rolComboBox.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Rol) setText(((Rol) value).getNombre());
                return this;
            }
        });
    }

  
    public Empleado getModel() {

       
        if (nombreTF.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio", "Error", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (dniNieTF.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El DNI/NIE es obligatorio", "Error", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (contrasenaTF.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La contraseña es obligatoria", "Error", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        Localidad loc = (Localidad) ciudadComboBox.getSelectedItem();
        if (loc == null || loc.getId() == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una ciudad", "Error", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        if (!masculinoRB.isSelected() && !femeninoRB.isSelected() && !otroRB.isSelected()) {
            JOptionPane.showMessageDialog(this, "Selecciona un género", "Error", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        Rol rol = (Rol) rolComboBox.getSelectedItem();
        if (rol == null || rol.getId() == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un rol", "Error", JOptionPane.WARNING_MESSAGE);
            return null;
        }

     
        Empleado e = new Empleado();
        e.setId(empleadoId); 
        e.setNombre(nombreTF.getText().trim());
        e.setPrimerApellido(apellido1TF.getText().trim());
        e.setSegundoApellido(apellido2TF.getText().trim());
        e.setTelefono(telefonoTF.getText().trim());
        e.setEmail(emailTF.getText().trim());
        e.setDniNie(dniNieTF.getText().trim());
        e.setDireccion(direccionTF.getText().trim());
        e.setPassword(contrasenaTF.getText().trim());
        e.setLocalidadId(loc.getId());

        if (masculinoRB.isSelected()) e.setGeneroId(Genero.MASCULINO.longValue());
        else if (femeninoRB.isSelected()) e.setGeneroId(Genero.FEMENINO.longValue());
        else if (otroRB.isSelected()) e.setGeneroId(Genero.OTRO_LONG);

        e.setRolId(rol.getId());

        return e;
    }


	public void setEditable(boolean editable) {
		nombreTF.setEditable(editable);
		apellido1TF.setEditable(editable);
		apellido2TF.setEditable(editable);
		telefonoTF.setEditable(editable);
		emailTF.setEditable(editable);
		dniNieTF.setEditable(editable);
		direccionTF.setEditable(editable);
		contrasenaTF.setEditable(editable);

		paisComboBox.setEnabled(editable);
		provinciaComboBox.setEnabled(editable);
		ciudadComboBox.setEnabled(editable);
		rolComboBox.setEnabled(editable);

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
    
    private Long empleadoId;

    public void setEmpleadoDTO(EmpleadoDTO empleado) {
        this.empleadoId = empleado.getId();

        nombreTF.setText(empleado.getNombre() != null ? empleado.getNombre() : "");
        apellido1TF.setText(empleado.getPrimerApellido() != null ? empleado.getPrimerApellido() : "");
        apellido2TF.setText(empleado.getSegundoApellido() != null ? empleado.getSegundoApellido() : "");
        telefonoTF.setText(empleado.getTelefono() != null ? empleado.getTelefono() : "");
        emailTF.setText(empleado.getEmail() != null ? empleado.getEmail() : "");
        dniNieTF.setText(empleado.getDniNie() != null ? empleado.getDniNie() : "");

        for (int i = 0; i < rolComboBox.getItemCount(); i++) {
            Rol r = rolComboBox.getItemAt(i);
            if (r.getId() != null && r.getId().equals(empleado.getRolId())) {
                rolComboBox.setSelectedIndex(i);
                break;
            }
        }

        if (empleado.getGeneroId() != null) {
            if (empleado.getGeneroId().equals(Genero.MASCULINO.longValue())) masculinoRB.setSelected(true);
            else if (empleado.getGeneroId().equals(Genero.FEMENINO.longValue())) femeninoRB.setSelected(true);
            else otroRB.setSelected(true);
        }

        if (empleado.getLocalidadId() != null) {
            Localidad localidad = localidadService.findById(empleado.getLocalidadId());
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
                    if (loc.getId() != null && loc.getId().equals(empleado.getLocalidadId())) {
                        ciudadComboBox.setSelectedIndex(i);
                        break;
                    }
                }
            }
        }

        setEditable(false);
        setAgreeController(new EmpleadoSetEditableController(this));
    }
}