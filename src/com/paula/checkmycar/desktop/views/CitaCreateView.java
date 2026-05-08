package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.paula.checkmc.model.Cita;
import com.paula.checkmc.model.CitaDTO;
import com.paula.checkmc.model.ClienteCriteria;
import com.paula.checkmc.model.ClienteDTO;
import com.paula.checkmc.model.CocheCriteria;
import com.paula.checkmc.model.CocheDTO;
import com.paula.checkmc.model.EstadoCita;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.ClienteService;
import com.paula.checkmc.service.CocheService;
import com.paula.checkmc.service.EstadoCitaService;
import com.paula.checkmc.service.impl.ClienteServiceImpl;
import com.paula.checkmc.service.impl.CocheServiceImpl;
import com.paula.checkmc.service.impl.EstadoCitaServiceImpl;
import com.paula.checkmycar.desktop.controller.CitaCreateController;
import com.paula.checkmycar.desktop.controller.CitaSetEditableController;
import com.paula.checkmycar.desktop.controller.Controller;
import com.toedter.calendar.JDateChooser;

public class CitaCreateView extends View {

    private JDateChooser dateChooser;
    private JTextField descripcionTF;
    private JComboBox<ClienteDTO> clienteCB;
    private JComboBox<CocheDTO> cocheCB;
    private JComboBox<EstadoCita> estadoCB;

    private JButton guardarButton;
    private JButton cancelarButton;

    private Long citaId;

    private ClienteService clienteService = new ClienteServiceImpl();
    private CocheService cocheService = new CocheServiceImpl();
    private EstadoCitaService estadoService = new EstadoCitaServiceImpl();

    public CitaCreateView() {
        initComponents();
        postInitialize();
    }

    private void initComponents() {
        setName("Crear cita");
        setLayout(new BorderLayout(0, 0));

        JPanel contentPanel = new JPanel();
        add(contentPanel, BorderLayout.CENTER);

        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 200, 0, 200, 0};
        gbl.rowHeights = new int[]{0, 0, 0, 0, 0};
        gbl.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl);

        // Fila 0: Fecha / Estado
        JLabel fechaLabel = new JLabel("Fecha *:");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 5, 5);
        gbc.gridx = 0; gbc.gridy = 0;
        contentPanel.add(fechaLabel, gbc);

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 5, 10);
        gbc.gridx = 1; gbc.gridy = 0;
        contentPanel.add(dateChooser, gbc);

        JLabel estadoLabel = new JLabel("Estado *:");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 5, 5);
        gbc.gridx = 2; gbc.gridy = 0;
        contentPanel.add(estadoLabel, gbc);

        estadoCB = new JComboBox<>();
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 5, 10);
        gbc.gridx = 3; gbc.gridy = 0;
        contentPanel.add(estadoCB, gbc);

        // Fila 1: Cliente / Coche
        JLabel clienteLabel = new JLabel("Cliente (DNI) *:");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 5);
        gbc.gridx = 0; gbc.gridy = 1;
        contentPanel.add(clienteLabel, gbc);

        clienteCB = new JComboBox<>();
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 10);
        gbc.gridx = 1; gbc.gridy = 1;
        contentPanel.add(clienteCB, gbc);

        JLabel cocheLabel = new JLabel("Coche (Matrícula) *:");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 5);
        gbc.gridx = 2; gbc.gridy = 1;
        contentPanel.add(cocheLabel, gbc);

        cocheCB = new JComboBox<>();
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 10);
        gbc.gridx = 3; gbc.gridy = 1;
        contentPanel.add(cocheCB, gbc);

        // Fila 2: Descripción
        JLabel descLabel = new JLabel("Descripción:");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 10, 5);
        gbc.gridx = 0; gbc.gridy = 2;
        contentPanel.add(descLabel, gbc);

        descripcionTF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 10, 10);
        gbc.gridwidth = 3;
        gbc.gridx = 1; gbc.gridy = 2;
        contentPanel.add(descripcionTF, gbc);

        // Botones
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        add(buttonsPanel, BorderLayout.SOUTH);

        guardarButton = new JButton("Guardar");
        cancelarButton = new JButton("Cancelar");

        buttonsPanel.add(cancelarButton);
        buttonsPanel.add(guardarButton);

        guardarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                new CitaCreateController(CitaCreateView.this).doAction();
            }
        });

        // Al cambiar cliente carga sus coches
        clienteCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                cargarCoches();
            }
        });
    }

    private void postInitialize() {

        // Renderer cliente — muestra DNI
        clienteCB.setRenderer(new javax.swing.DefaultListCellRenderer() {
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list,
                    Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof ClienteDTO) {
                    ClienteDTO c = (ClienteDTO) value;
                    setText(c.getId() == null ? "Seleccionar" : c.getDniNie() + " - " + c.getNombre());
                }
                return this;
            }
        });

        // Renderer coche — muestra matrícula
        cocheCB.setRenderer(new javax.swing.DefaultListCellRenderer() {
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list,
                    Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof CocheDTO) {
                    CocheDTO co = (CocheDTO) value;
                    setText(co.getId() == null ? "Seleccionar" : co.getMatricula());
                }
                return this;
            }
        });

        // Renderer estado
        estadoCB.setRenderer(new javax.swing.DefaultListCellRenderer() {
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list,
                    Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof EstadoCita) {
                    EstadoCita est = (EstadoCita) value;
                    setText(est.getId() == null ? "Seleccionar" : est.getNombre());
                }
                return this;
            }
        });

        // Cargar clientes
        Results<ClienteDTO> clientes = clienteService.findByCriteria(new ClienteCriteria(), 1, 1000);
        DefaultComboBoxModel<ClienteDTO> clienteModel = new DefaultComboBoxModel<>();
        ClienteDTO clientePlaceholder = new ClienteDTO();
        clientePlaceholder.setId(null);
        clientePlaceholder.setNombre("Seleccionar");
        clienteModel.addElement(clientePlaceholder);
        for (ClienteDTO cl : clientes.getPage()) clienteModel.addElement(cl);
        clienteCB.setModel(clienteModel);

        // Placeholder coche
        DefaultComboBoxModel<CocheDTO> cocheModel = new DefaultComboBoxModel<>();
        CocheDTO cochePlaceholder = new CocheDTO();
        cochePlaceholder.setId(null);
        cochePlaceholder.setMatricula("Seleccionar");
        cocheModel.addElement(cochePlaceholder);
        cocheCB.setModel(cocheModel);

        // Cargar estados
        DefaultComboBoxModel<EstadoCita> estadoModel = new DefaultComboBoxModel<>();
        EstadoCita estadoPlaceholder = new EstadoCita();
        estadoPlaceholder.setId(null);
        estadoPlaceholder.setNombre("Seleccionar");
        estadoModel.addElement(estadoPlaceholder);
        for (EstadoCita est : estadoService.findAll()) estadoModel.addElement(est);
        estadoCB.setModel(estadoModel);
    }

    private void cargarCoches() {
        ClienteDTO cliente = (ClienteDTO) clienteCB.getSelectedItem();
        DefaultComboBoxModel<CocheDTO> model = new DefaultComboBoxModel<>();
        CocheDTO placeholder = new CocheDTO();
        placeholder.setId(null);
        placeholder.setMatricula("Seleccionar");
        model.addElement(placeholder);

        if (cliente != null && cliente.getId() != null) {
            CocheCriteria criteria = new CocheCriteria();
            criteria.setClienteId(cliente.getId());
            Results<CocheDTO> coches = cocheService.findByCriteria(criteria, 1, 1000);
            for (CocheDTO coche : coches.getPage()) model.addElement(coche);
        }
        cocheCB.setModel(model);
    }

    public Cita getModel() {
        if (dateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(this, "La fecha es obligatoria.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        ClienteDTO cliente = (ClienteDTO) clienteCB.getSelectedItem();
        if (cliente == null || cliente.getId() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        CocheDTO coche = (CocheDTO) cocheCB.getSelectedItem();
        if (coche == null || coche.getId() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un coche.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        EstadoCita estado = (EstadoCita) estadoCB.getSelectedItem();
        if (estado == null || estado.getId() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un estado.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        Date date = dateChooser.getDate();
        LocalDateTime fecha = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        Cita cita = new Cita();
        cita.setId(citaId);
        cita.setFecha(fecha);
        cita.setDescripcion(descripcionTF.getText().trim());
        cita.setClienteId(cliente.getId());
        cita.setCocheId(coche.getId());
        cita.setEstadoCitaId(estado.getId());

        return cita;
    }

    public void setEditable(boolean editable) {
        dateChooser.setEnabled(editable);
        descripcionTF.setEditable(editable);
        clienteCB.setEnabled(editable);
        cocheCB.setEnabled(editable);
        estadoCB.setEnabled(editable);
    }

    public void setAgreeController(Controller controller) {
        guardarButton.setAction(controller);
    }

    public void setCancelController(Controller controller) {
        cancelarButton.setAction(controller);
    }

    public void setCitaDTO(CitaDTO cita) {
        this.citaId = cita.getId();

        if (cita.getFecha() != null) {
            Date date = Date.from(cita.getFecha().atZone(ZoneId.systemDefault()).toInstant());
            dateChooser.setDate(date);
        }

        descripcionTF.setText(cita.getDescripcion() != null ? cita.getDescripcion() : "");

        for (int i = 0; i < clienteCB.getItemCount(); i++) {
            ClienteDTO cl = clienteCB.getItemAt(i);
            if (cl.getId() != null && cl.getId().equals(cita.getClienteId())) {
                clienteCB.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < cocheCB.getItemCount(); i++) {
            CocheDTO co = cocheCB.getItemAt(i);
            if (co.getId() != null && co.getId().equals(cita.getCocheId())) {
                cocheCB.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < estadoCB.getItemCount(); i++) {
            EstadoCita est = estadoCB.getItemAt(i);
            if (est.getId() != null && est.getId().equals(cita.getEstadoCitaId())) {
                estadoCB.setSelectedIndex(i);
                break;
            }
        }

        setEditable(false);
        setAgreeController(new CitaSetEditableController(this));
    }
}