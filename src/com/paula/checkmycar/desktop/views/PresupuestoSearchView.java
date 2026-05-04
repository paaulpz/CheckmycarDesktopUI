package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.paula.checkmc.model.PresupuestoCriteria;
import com.paula.checkmc.model.PresupuestoDTO;
import com.paula.checkmc.service.impl.PresupuestoServiceImpl;
import com.paula.checkmycar.desktop.views.tableModel.PresupuestoTableModel;

public class PresupuestoSearchView extends AbstracView {

    private PresupuestoServiceImpl presupuestoService = null;

    private JTextField clienteTF;
    private JTable table;
    private PresupuestoTableModel tableModel;

    public PresupuestoSearchView() {
        initialize();
        initServices();
        postInitialize();
    }

    private void initialize() {
        setName("Búsqueda de presupuestos");
        setLayout(new BorderLayout(0, 0));

        JPanel criteriosPanel = new JPanel();
        add(criteriosPanel, BorderLayout.NORTH);

        GridBagLayout gbl = new GridBagLayout();
        criteriosPanel.setLayout(gbl);

        // 🔎 Título
        JLabel titulo = new JLabel("Búsqueda de presupuestos:");
        GridBagConstraints gbc_titulo = new GridBagConstraints();
        gbc_titulo.insets = new Insets(0, 0, 5, 5);
        gbc_titulo.gridx = 0;
        gbc_titulo.gridy = 0;
        criteriosPanel.add(titulo, gbc_titulo);

        // 👤 Cliente
        JLabel clienteLabel = new JLabel("Nombre cliente:");
        GridBagConstraints gbc_clienteLabel = new GridBagConstraints();
        gbc_clienteLabel.anchor = GridBagConstraints.EAST;
        gbc_clienteLabel.insets = new Insets(0, 0, 5, 5);
        gbc_clienteLabel.gridx = 0;
        gbc_clienteLabel.gridy = 1;
        criteriosPanel.add(clienteLabel, gbc_clienteLabel);

        clienteTF = new JTextField();
        clienteTF.setColumns(20);
        GridBagConstraints gbc_clienteTF = new GridBagConstraints();
        gbc_clienteTF.insets = new Insets(0, 0, 5, 5);
        gbc_clienteTF.fill = GridBagConstraints.HORIZONTAL;
        gbc_clienteTF.gridx = 1;
        gbc_clienteTF.gridy = 1;
        criteriosPanel.add(clienteTF, gbc_clienteTF);

        // 🔘 Panel botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.gridwidth = 2;
        gbc_panel.fill = GridBagConstraints.HORIZONTAL;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 2;
        criteriosPanel.add(panelBotones, gbc_panel);

        // 🧹 Limpiar
        JButton limpiarButton = new JButton("Limpiar");
        limpiarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clienteTF.setText("");
                tableModel.setPresupuestos(new ArrayList<>());
            }
        });
        panelBotones.add(limpiarButton);

        // 🔍 Buscar
        JButton buscarButton = new JButton();
        buscarButton.setIcon(new ImageIcon(
                PresupuestoSearchView.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));

        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                PresupuestoCriteria criteria = new PresupuestoCriteria();
                criteria.setNombreCliente(clienteTF.getText());

                List<PresupuestoDTO> resultados = presupuestoService.findByCriteria(criteria);

                List<PresupuestoDTO> dtos = new ArrayList<>();

                for (PresupuestoDTO p : resultados) {
                    PresupuestoDTO dto = new PresupuestoDTO();

                    dto.setId(p.getId());
                    dto.setClienteId(null);
                    dto.setCoche(p.get().getModelo());
                    dto.setEmpleado(p.getnombreEmpleado().getNombre());
                    dto.setFecha(p.getFecha());
                    dto.setImporte(p.get());

                    dtos.add(dto);
                }

                tableModel.setPresupuestos(dtos);
            }
        });

        panelBotones.add(buscarButton);

        // 📊 Tabla
        JPanel resultadosPanel = new JPanel(new BorderLayout());
        add(resultadosPanel, BorderLayout.CENTER);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        resultadosPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void initServices() {
        presupuestoService = new PresupuestoServiceImpl();
    }

    private void postInitialize() {
        tableModel = new PresupuestoTableModel();
        table.setModel(tableModel);
    }
}