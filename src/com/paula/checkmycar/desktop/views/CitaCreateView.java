package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

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
import com.paula.checkmycar.desktop.views.renderer.ClienteCBRenderer;

public class CitaCreateView extends View {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private JTextField descripcionTF;
    private JTextField fechaTF;

    private JComboBox<ClienteDTO>  clienteCB;
    private JComboBox<CocheDTO>    cocheCB;
    private JComboBox<EstadoCita>  estadoCB;

    private JButton guardarButton;
    private JButton limpiarButton;

    private Long citaId;

    private ClienteService    clienteService    = new ClienteServiceImpl();
    private CocheService      cocheService      = new CocheServiceImpl();
    private EstadoCitaService estadoCitaService = new EstadoCitaServiceImpl();

    public CitaCreateView() {
        initialize();
        postInitialize();
    }

    private void initialize() {
        setName("Crear cita");
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        add(formPanel, BorderLayout.NORTH);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        c.gridx = 0; c.gridy = y; formPanel.add(new JLabel("Fecha (dd/MM/yyyy HH:mm) *:"), c);
        fechaTF = new JTextField();
        c.gridx = 1; formPanel.add(fechaTF, c);

        c.gridx = 2; formPanel.add(new JLabel("Estado *:"), c);
        estadoCB = new JComboBox<>();
        c.gridx = 3; formPanel.add(estadoCB, c);

        y++;
        c.gridx = 0; c.gridy = y; formPanel.add(new JLabel("Cliente *:"), c);
        clienteCB = new JComboBox<>();
        c.gridx = 1; formPanel.add(clienteCB, c);

        c.gridx = 2; formPanel.add(new JLabel("Coche *:"), c);
        cocheCB = new JComboBox<>();
        c.gridx = 3; formPanel.add(cocheCB, c);

        y++;
        c.gridx = 0; c.gridy = y; formPanel.add(new JLabel("Descripción:"), c);
        descripcionTF = new JTextField();
        c.gridx = 1; c.gridwidth = 3; formPanel.add(descripcionTF, c);
        c.gridwidth = 1;

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        add(botonesPanel, BorderLayout.SOUTH);

        limpiarButton = new JButton("Limpiar");
        guardarButton = new JButton("Guardar");

        botonesPanel.add(limpiarButton);
        botonesPanel.add(guardarButton);

        limpiarButton.addActionListener(e -> limpiar());
        guardarButton.addActionListener(e -> new CitaCreateController(this).doAction());

        clienteCB.addActionListener(e -> cargarCoches());
    }

    private void postInitialize() {

        clienteCB.setRenderer(new ClienteCBRenderer());

        Results<ClienteDTO> clientes = clienteService.findByCriteria(new ClienteCriteria(), 1, 1000);
        DefaultComboBoxModel<ClienteDTO> clienteModel = new DefaultComboBoxModel<>();
        ClienteDTO clientePlaceholder = new ClienteDTO();
        clientePlaceholder.setId(null);
        clientePlaceholder.setNombre("Seleccionar");
        clienteModel.addElement(clientePlaceholder);
        for (ClienteDTO cl : clientes.getPage()) clienteModel.addElement(cl);
        clienteCB.setModel(clienteModel);
        AutoCompleteDecorator.decorate(clienteCB);

        DefaultComboBoxModel<CocheDTO> cocheModel = new DefaultComboBoxModel<>();
        CocheDTO cochePlaceholder = new CocheDTO();
        cochePlaceholder.setId(null);
        cochePlaceholder.setMatricula("Seleccionar");
        cocheModel.addElement(cochePlaceholder);
        cocheCB.setModel(cocheModel);

        DefaultComboBoxModel<EstadoCita> estadoModel = new DefaultComboBoxModel<>();
        EstadoCita estadoPlaceholder = new EstadoCita();
        estadoPlaceholder.setId(null);
        estadoPlaceholder.setNombre("Seleccionar");
        estadoModel.addElement(estadoPlaceholder);
        for (EstadoCita e : estadoCitaService.findAll()) estadoModel.addElement(e);
        estadoCB.setModel(estadoModel);
        estadoCB.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            javax.swing.JLabel label = new javax.swing.JLabel();
            if (value instanceof EstadoCita) label.setText(((EstadoCita) value).getNombre());
            if (isSelected) { label.setBackground(list.getSelectionBackground()); label.setOpaque(true); }
            return label;
        });
        
        cocheCB.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            javax.swing.JLabel label = new javax.swing.JLabel();
            if (value instanceof CocheDTO) {
                CocheDTO co = (CocheDTO) value;
                String texto = co.getId() == null ? "Seleccionar" : co.getMatricula();
                label.setText(texto);
            }
            if (isSelected) {
                label.setBackground(list.getSelectionBackground());
                label.setOpaque(true);
            }
            return label;
        });
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
        cocheCB.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            javax.swing.JLabel label = new javax.swing.JLabel();
            if (value instanceof CocheDTO) label.setText(((CocheDTO) value).getMatricula());
            if (isSelected) { label.setBackground(list.getSelectionBackground()); label.setOpaque(true); }
            return label;
        });
    }

    private void limpiar() {
        fechaTF.setText("");
        descripcionTF.setText("");
        clienteCB.setSelectedIndex(0);
        estadoCB.setSelectedIndex(0);
        citaId = null;
    }

    public Cita getModel() {

        String fechaStr = fechaTF.getText().trim();
        if (fechaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La fecha es obligatoria.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        LocalDateTime fecha;
        try {
            fecha = LocalDateTime.parse(fechaStr, FORMATTER);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Usa dd/MM/yyyy HH:mm", "Error", JOptionPane.ERROR_MESSAGE);
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
        fechaTF.setEditable(editable);
        descripcionTF.setEditable(editable);
        clienteCB.setEnabled(editable);
        cocheCB.setEnabled(editable);
        estadoCB.setEnabled(editable);
    }

    public void setAgreeController(Controller controller) {
        guardarButton.setAction(controller);
    }

    public void setCitaDTO(CitaDTO cita) {
        this.citaId = cita.getId();

        fechaTF.setText(cita.getFecha() != null ? cita.getFecha().format(FORMATTER) : "");
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
            EstadoCita e = estadoCB.getItemAt(i);
            if (e.getId() != null && e.getId().equals(cita.getEstadoCitaId())) {
                estadoCB.setSelectedIndex(i);
                break;
            }
        }

        setEditable(false);
        setAgreeController(new CitaSetEditableController(this));
    }
}
