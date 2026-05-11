package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.paula.checkmc.model.ClienteCriteria;
import com.paula.checkmc.model.ClienteDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.model.VentaCriteria;
import com.paula.checkmc.model.VentaDTO;
import com.paula.checkmc.service.ClienteService;
import com.paula.checkmc.service.VentaService;
import com.paula.checkmc.service.impl.ClienteServiceImpl;
import com.paula.checkmc.service.impl.VentaServiceImpl;
import com.paula.checkmycar.desktop.views.renderer.ClienteEmpleadoButtonRenderer;
import com.paula.checkmycar.desktop.views.tableModel.VentaTableModel;
import com.paula.checkmycar.desktop.views.tableModel.editor.VentaButtonEditor;
import com.toedter.calendar.JDateChooser;

public class VentaSearchView extends View {

    private JComboBox<ClienteDTO> compradorCB;
    private JComboBox<ClienteDTO> vendedorCB;
    private JDateChooser fechaDesdeDC;
    private JDateChooser fechaHastaDC;

    private JTable tabla;
    private VentaTableModel tableModel;
    private JLabel totalLabel;

    private VentaService   ventaService   = new VentaServiceImpl();
    private ClienteService clienteService = new ClienteServiceImpl();

    public VentaSearchView() {
        initialize();
        postInitialize();
    }

    private void initialize() {
        setName("Búsqueda de ventas");
        setLayout(new BorderLayout());

        JPanel filtrosPanel = new JPanel(new GridBagLayout());
        add(filtrosPanel, BorderLayout.NORTH);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        c.gridx = 0; c.gridy = y; filtrosPanel.add(new JLabel("Comprador:"), c);
        compradorCB = new JComboBox<ClienteDTO>();
        c.gridx = 1; filtrosPanel.add(compradorCB, c);

        c.gridx = 2; filtrosPanel.add(new JLabel("Vendedor:"), c);
        vendedorCB = new JComboBox<ClienteDTO>();
        c.gridx = 3; filtrosPanel.add(vendedorCB, c);

        y++;
        c.gridx = 0; c.gridy = y; filtrosPanel.add(new JLabel("Fecha desde:"), c);
        fechaDesdeDC = new JDateChooser();
        fechaDesdeDC.setDateFormatString("dd/MM/yyyy");
        c.gridx = 1; filtrosPanel.add(fechaDesdeDC, c);

        c.gridx = 2; filtrosPanel.add(new JLabel("Fecha hasta:"), c);
        fechaHastaDC = new JDateChooser();
        fechaHastaDC.setDateFormatString("dd/MM/yyyy");
        c.gridx = 3; filtrosPanel.add(fechaHastaDC, c);

        y++;
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        c.gridx = 0; c.gridy = y; c.gridwidth = 4;
        filtrosPanel.add(botonesPanel, c);

        JButton limpiarButton = new JButton("Limpiar");
        JButton buscarButton  = new JButton("Buscar");

        botonesPanel.add(limpiarButton);
        botonesPanel.add(buscarButton);

        limpiarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiar();
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscar();
            }
        });

        tabla = new JTable();
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel footer = new JPanel();
        totalLabel = new JLabel("0 ventas");
        footer.add(totalLabel);
        add(footer, BorderLayout.SOUTH);
    }

    private void postInitialize() {
        tableModel = new VentaTableModel();
        tabla.setModel(tableModel);
        tabla.setRowHeight(25);

        tabla.getColumnModel().getColumn(7)
        .setCellRenderer(new ClienteEmpleadoButtonRenderer());

   tabla.getColumnModel().getColumn(7)
        .setCellEditor(new VentaButtonEditor(new javax.swing.JCheckBox())); 
   
        DefaultListCellRenderer clienteRenderer = new DefaultListCellRenderer() {
            public java.awt.Component getListCellRendererComponent(JList<?> list,
                    Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof ClienteDTO) {
                    ClienteDTO cl = (ClienteDTO) value;
                    setText(cl.getId() == null ? "Todos" : cl.getDniNie() + " - " + cl.getNombre());
                }
                return this;
            }
        };

        compradorCB.setRenderer(clienteRenderer);
        vendedorCB.setRenderer(clienteRenderer);

        Results<ClienteDTO> clientes = clienteService.findByCriteria(new ClienteCriteria(), 1, 1000);

        DefaultComboBoxModel<ClienteDTO> compradorModel = new DefaultComboBoxModel<ClienteDTO>();
        DefaultComboBoxModel<ClienteDTO> vendedorModel  = new DefaultComboBoxModel<ClienteDTO>();

        ClienteDTO ph1 = new ClienteDTO(); ph1.setId(null); ph1.setNombre("Todos");
        ClienteDTO ph2 = new ClienteDTO(); ph2.setId(null); ph2.setNombre("Todos");

        compradorModel.addElement(ph1);
        vendedorModel.addElement(ph2);

        for (ClienteDTO cl : clientes.getPage()) {
            compradorModel.addElement(cl);
            vendedorModel.addElement(cl);
        }

        compradorCB.setModel(compradorModel);
        vendedorCB.setModel(vendedorModel);
    }

    private void buscar() {
        try {
            VentaCriteria criteria = new VentaCriteria();

            ClienteDTO comprador = (ClienteDTO) compradorCB.getSelectedItem();
            if (comprador != null && comprador.getId() != null)
                criteria.setClienteCompradorId(comprador.getId());

            ClienteDTO vendedor = (ClienteDTO) vendedorCB.getSelectedItem();
            if (vendedor != null && vendedor.getId() != null)
                criteria.setClienteVendedorId(vendedor.getId());

            if (fechaDesdeDC.getDate() != null)
                criteria.setFechaDesde(fechaDesdeDC.getDate()
                        .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

            if (fechaHastaDC.getDate() != null)
                criteria.setFechaHasta(fechaHastaDC.getDate()
                        .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

            Results<VentaDTO> results = ventaService.findByCriteria(criteria, 1, 1000);
            tableModel.setData(results.getPage());
            totalLabel.setText(results.getTotal() + " ventas");

        } catch (Exception ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Error al buscar ventas: " + ex.getMessage());
        }
    }

    private void limpiar() {
        compradorCB.setSelectedIndex(0);
        vendedorCB.setSelectedIndex(0);
        fechaDesdeDC.setDate(null);
        fechaHastaDC.setDate(null);
        tableModel.setData(new ArrayList<>());
        totalLabel.setText("0 ventas");
    }
}