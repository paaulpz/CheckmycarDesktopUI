package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.Component;
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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.paula.checkmc.model.ClienteDTO;
import com.paula.checkmycar.desktop.controller.ClienteSearchController;
import com.paula.checkmycar.desktop.views.renderer.ButtonRenderer;
import com.paula.checkmycar.desktop.views.tableModel.ClienteTableModel;
import com.paula.checkmycar.desktop.views.tableModel.editor.ClienteButtonEditor;

public class ClienteSearchView extends View {

    private JTextField dniTF;
    private JTextField emailTF;
    private JTable table;
    private ClienteTableModel tableModel;
    
    private JButton anteriorButton;
    private JButton siguienteButton;
    private JLabel paginaLabel;
    private int paginaActual = 1;
    private ClienteSearchController searchController;

    public ClienteSearchView() {
        initialize();
        postInitialize();
    }

    private void initialize() {
        setName("Búsqueda de clientes");
        setLayout(new BorderLayout(0, 0));

        JPanel criteriosPanel = new JPanel();
        add(criteriosPanel, BorderLayout.NORTH);
        GridBagLayout gbl_criteriosPanel = new GridBagLayout();
        gbl_criteriosPanel.columnWidths = new int[] { 41, 86, 0, 8, 0, 46, 86, 1, 5, 49, 0 };
        gbl_criteriosPanel.rowHeights = new int[] { 25, 20, 0, 0, 0, 0 };
        gbl_criteriosPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE };
        gbl_criteriosPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
        criteriosPanel.setLayout(gbl_criteriosPanel);

        JLabel dniLabel = new JLabel("DNI/NIE:");
        GridBagConstraints gbc_dniLabel = new GridBagConstraints();
        gbc_dniLabel.anchor = GridBagConstraints.EAST;
        gbc_dniLabel.insets = new Insets(0, 0, 5, 5);
        gbc_dniLabel.gridx = 0;
        gbc_dniLabel.gridy = 1;
        criteriosPanel.add(dniLabel, gbc_dniLabel);

        dniTF = new JTextField();
        dniTF.setColumns(10);
        GridBagConstraints gbc_dniTF = new GridBagConstraints();
        gbc_dniTF.gridwidth = 2;
        gbc_dniTF.insets = new Insets(0, 0, 5, 5);
        gbc_dniTF.fill = GridBagConstraints.HORIZONTAL;
        gbc_dniTF.gridx = 1;
        gbc_dniTF.gridy = 1;
        criteriosPanel.add(dniTF, gbc_dniTF);

        JLabel emailLabel = new JLabel("Email:");
        GridBagConstraints gbc_emailLabel = new GridBagConstraints();
        gbc_emailLabel.anchor = GridBagConstraints.EAST;
        gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
        gbc_emailLabel.gridx = 5;
        gbc_emailLabel.gridy = 1;
        criteriosPanel.add(emailLabel, gbc_emailLabel);

        emailTF = new JTextField();
        emailTF.setColumns(10);
        GridBagConstraints gbc_emailTF = new GridBagConstraints();
        gbc_emailTF.gridwidth = 4;
        gbc_emailTF.insets = new Insets(0, 0, 5, 0);
        gbc_emailTF.fill = GridBagConstraints.HORIZONTAL;
        gbc_emailTF.gridx = 6;
        gbc_emailTF.gridy = 1;
        criteriosPanel.add(emailTF, gbc_emailTF);

        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.gridwidth = 10;
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 4;
        criteriosPanel.add(panel, gbc_panel);

        JButton limpiarButton = new JButton("Limpiar");
        limpiarButton.setIcon(new ImageIcon(ClienteSearchView.class.getResource("/icons/16x16/basura.png")));
        limpiarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dniTF.setText("");
                emailTF.setText("");
                tableModel.setClientes(new ArrayList<>());
            }
        });
        panel.add(limpiarButton);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(e -> searchController.buscar(1));
        buscarButton.setIcon(new ImageIcon(ClienteSearchView.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));
        panel.add(buscarButton);

        JPanel resultadosPanel = new JPanel();
        add(resultadosPanel, BorderLayout.CENTER);
        resultadosPanel.setLayout(new BorderLayout(0, 0));

        table = new JTable();

        JScrollPane scrollPane = new JScrollPane(table);
        resultadosPanel.add(scrollPane, BorderLayout.CENTER);
        
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
        tableModel = new ClienteTableModel();
        table.setModel(tableModel);

        table.setRowHeight(30);

        table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(4).setCellEditor(new ClienteButtonEditor(new JCheckBox()));

        table.setSurrendersFocusOnKeystroke(true);
        table.setCellSelectionEnabled(true);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (col == 4) {
                    table.editCellAt(row, col);
                    Component editor = table.getEditorComponent();
                    if (editor != null) editor.requestFocus();
                }
            }
        });

        searchController = new ClienteSearchController(this);

        anteriorButton.addActionListener(e -> {
            if (paginaActual > 1) searchController.buscar(--paginaActual);
        });

        siguienteButton.addActionListener(e -> {
            searchController.buscar(++paginaActual);
        });
    }

    public void actualizarPaginacion(int pagina, int pageSize, int total) {
        this.paginaActual = pagina;
        int totalPaginas = (int) Math.ceil((double) total / pageSize);
        if (totalPaginas == 0) totalPaginas = 1;
        paginaLabel.setText("Página " + pagina + " de " + totalPaginas);
        anteriorButton.setEnabled(pagina > 1);
        siguienteButton.setEnabled(pagina < totalPaginas);
    }
    

    public String getDni() {
        return dniTF.getText();
    }

    public String getEmail() {
        return emailTF.getText();
    }

    public void setTableData(List<ClienteDTO> clientes) {
        tableModel.setClientes(clientes);
    }

    public ClienteDTO getSelectedCliente() {
        int fila = table.getSelectedRow();
        if (fila == -1) return null;

        return tableModel.getClienteAt(fila);
    }
}