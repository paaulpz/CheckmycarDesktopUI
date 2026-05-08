package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.paula.checkmc.model.PiezaCriteria;
import com.paula.checkmc.model.PiezaDTO;
import com.paula.checkmc.service.PiezaService;
import com.paula.checkmc.service.impl.PiezaServiceImpl;
import com.paula.checkmycar.desktop.views.tableModel.PiezaTableModel;

public class PiezaSearchView extends View {

    private JTextField nombreTF;

    private JTable tabla;
    private PiezaTableModel tableModel;
    private JLabel totalLabel;

    private PiezaService piezaService = new PiezaServiceImpl();

    public PiezaSearchView() {
        initialize();
        postInitialize();
    }

    private void initialize() {
        setName("Búsqueda de piezas");
        setLayout(new BorderLayout());

        JPanel filtrosPanel = new JPanel(new GridBagLayout());
        add(filtrosPanel, BorderLayout.NORTH);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0; c.gridy = 0; filtrosPanel.add(new JLabel("Nombre:"), c);
        nombreTF = new JTextField();
        c.gridx = 1; filtrosPanel.add(nombreTF, c);

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        c.gridx = 0; c.gridy = 1; c.gridwidth = 2;
        filtrosPanel.add(botonesPanel, c);

        JButton limpiarButton = new JButton("Limpiar");
        JButton buscarButton  = new JButton("Buscar");

        botonesPanel.add(limpiarButton);
        botonesPanel.add(buscarButton);

        limpiarButton.addActionListener(e -> limpiar());
        buscarButton.addActionListener(e -> buscar());

        tabla = new JTable();
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel footer = new JPanel();
        totalLabel = new JLabel("0 piezas");
        footer.add(totalLabel);
        add(footer, BorderLayout.SOUTH);
    }

    private void postInitialize() {
        tableModel = new PiezaTableModel();
        tabla.setModel(tableModel);
    }

    private void buscar() {
        PiezaCriteria criteria = new PiezaCriteria();

        String nombre = nombreTF.getText().trim();
        if (!nombre.isEmpty()) criteria.setNombre(nombre);

        List<PiezaDTO> resultados = piezaService.findByCriteria(criteria, 1, 1000);
        tableModel.setData(resultados);
        totalLabel.setText(resultados.size() + " piezas");
    }

    private void limpiar() {
        nombreTF.setText("");
        tableModel.setData(new ArrayList<>());
        totalLabel.setText("0 piezas");
    }
}