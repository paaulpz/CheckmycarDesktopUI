package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.paula.checkmc.model.ClienteCriteria;
import com.paula.checkmc.model.ClienteDTO;
import com.paula.checkmc.model.CocheCriteria;
import com.paula.checkmc.model.CocheDTO;
import com.paula.checkmc.model.EmpleadoCriteria;
import com.paula.checkmc.model.EmpleadoDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.model.VentaDTO;
import com.paula.checkmc.service.ClienteService;
import com.paula.checkmc.service.CocheService;
import com.paula.checkmc.service.EmpleadoService;
import com.paula.checkmc.service.VentaService;
import com.paula.checkmc.service.impl.ClienteServiceImpl;
import com.paula.checkmc.service.impl.CocheServiceImpl;
import com.paula.checkmc.service.impl.EmpleadoServiceImpl;
import com.paula.checkmc.service.impl.VentaServiceImpl;
import com.toedter.calendar.JDateChooser;

public class VentaCreateView extends View {

    private JDateChooser dateChooser;
    private JTextField precioClienteTF;
    private JTextField precioFinalTF;

    private JComboBox<ClienteDTO>  compradorCB;
    private JComboBox<ClienteDTO>  vendedorCB;
    private JComboBox<EmpleadoDTO> empleadoCB;
    private JComboBox<CocheDTO>    cocheCB;

    private JButton guardarButton;
    private JButton cancelarButton;

    private Long ventaId;

    private ClienteService  clienteService  = new ClienteServiceImpl();
    private EmpleadoService empleadoService = new EmpleadoServiceImpl();
    private CocheService    cocheService    = new CocheServiceImpl();
    private VentaService    ventaService    = new VentaServiceImpl();

    public VentaCreateView() {
        initComponents();
        postInitialize();
    }

    private void initComponents() {
        setName("Crear venta");
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        add(formPanel, BorderLayout.NORTH);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        c.gridx = 0; c.gridy = y; formPanel.add(new JLabel("Fecha *:"), c);
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        c.gridx = 1; formPanel.add(dateChooser, c);

        c.gridx = 2; formPanel.add(new JLabel("Matrícula coche *:"), c);
        cocheCB = new JComboBox<CocheDTO>();
        c.gridx = 3; formPanel.add(cocheCB, c);

        y++;
        c.gridx = 0; c.gridy = y; formPanel.add(new JLabel("Comprador:"), c);
        compradorCB = new JComboBox<ClienteDTO>();
        c.gridx = 1; formPanel.add(compradorCB, c);

        c.gridx = 2; formPanel.add(new JLabel("Vendedor *:"), c);
        vendedorCB = new JComboBox<ClienteDTO>();
        c.gridx = 3; formPanel.add(vendedorCB, c);

        y++;
        c.gridx = 0; c.gridy = y; formPanel.add(new JLabel("Empleado *:"), c);
        empleadoCB = new JComboBox<EmpleadoDTO>();
        c.gridx = 1; formPanel.add(empleadoCB, c);

        c.gridx = 2; formPanel.add(new JLabel("Precio cliente (€):"), c);
        precioClienteTF = new JTextField();
        c.gridx = 3; formPanel.add(precioClienteTF, c);

        y++;
        c.gridx = 0; c.gridy = y; formPanel.add(new JLabel("Precio final (€):"), c);
        precioFinalTF = new JTextField();
        c.gridx = 1; formPanel.add(precioFinalTF, c);

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        add(botonesPanel, BorderLayout.SOUTH);

        cancelarButton = new JButton("Cancelar");
        guardarButton  = new JButton("Guardar");

        botonesPanel.add(cancelarButton);
        botonesPanel.add(guardarButton);

        guardarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardar();
            }
        });
    }

    private void postInitialize() {

        DefaultListCellRenderer clienteRenderer = new DefaultListCellRenderer() {
            public java.awt.Component getListCellRendererComponent(JList<?> list,
                    Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof ClienteDTO) {
                    ClienteDTO cl = (ClienteDTO) value;
                    setText(cl.getId() == null ? "Seleccionar" : cl.getDniNie() + " - " + cl.getNombre());
                }
                return this;
            }
        };

        compradorCB.setRenderer(clienteRenderer);
        vendedorCB.setRenderer(clienteRenderer);

        empleadoCB.setRenderer(new DefaultListCellRenderer() {
            public java.awt.Component getListCellRendererComponent(JList<?> list,
                    Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof EmpleadoDTO) {
                    EmpleadoDTO emp = (EmpleadoDTO) value;
                    setText(emp.getId() == null ? "Seleccionar" : emp.getNombre() + " " + emp.getPrimerApellido());
                }
                return this;
            }
        });

        cocheCB.setRenderer(new DefaultListCellRenderer() {
            public java.awt.Component getListCellRendererComponent(JList<?> list,
                    Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof CocheDTO) {
                    CocheDTO co = (CocheDTO) value;
                    setText(co.getId() == null ? "Seleccionar" : co.getMatricula());
                }
                return this;
            }
        });

        Results<ClienteDTO> clientes = clienteService.findByCriteria(new ClienteCriteria(), 1, 1000);
        DefaultComboBoxModel<ClienteDTO> clienteModel1 = new DefaultComboBoxModel<ClienteDTO>();
        DefaultComboBoxModel<ClienteDTO> clienteModel2 = new DefaultComboBoxModel<ClienteDTO>();
        ClienteDTO ph = new ClienteDTO(); ph.setId(null); ph.setNombre("Seleccionar");
        ClienteDTO ph2 = new ClienteDTO(); ph2.setId(null); ph2.setNombre("Seleccionar");
        clienteModel1.addElement(ph);
        clienteModel2.addElement(ph2);
        for (ClienteDTO cl : clientes.getPage()) {
            clienteModel1.addElement(cl);
            clienteModel2.addElement(cl);
        }
        compradorCB.setModel(clienteModel1);
        vendedorCB.setModel(clienteModel2);

        Results<EmpleadoDTO> empleados = empleadoService.findByCriteria(new EmpleadoCriteria(), 1, 1000);
        DefaultComboBoxModel<EmpleadoDTO> empleadoModel = new DefaultComboBoxModel<EmpleadoDTO>();
        EmpleadoDTO empPh = new EmpleadoDTO(); empPh.setId(null); empPh.setNombre("Seleccionar");
        empleadoModel.addElement(empPh);
        for (EmpleadoDTO emp : empleados.getPage()) empleadoModel.addElement(emp);
        empleadoCB.setModel(empleadoModel);

        Results<CocheDTO> coches = cocheService.findByCriteria(new CocheCriteria(), 1, 1000);
        DefaultComboBoxModel<CocheDTO> cocheModel = new DefaultComboBoxModel<CocheDTO>();
        CocheDTO cochePh = new CocheDTO(); cochePh.setId(null); cochePh.setMatricula("Seleccionar");
        cocheModel.addElement(cochePh);
        for (CocheDTO co : coches.getPage()) cocheModel.addElement(co);
        cocheCB.setModel(cocheModel);
    }

    private void guardar() {
        if (dateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(this, "La fecha es obligatoria.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        CocheDTO coche = (CocheDTO) cocheCB.getSelectedItem();
        if (coche == null || coche.getId() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un coche.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ClienteDTO vendedor = (ClienteDTO) vendedorCB.getSelectedItem();
        if (vendedor == null || vendedor.getId() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un vendedor.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        EmpleadoDTO empleado = (EmpleadoDTO) empleadoCB.getSelectedItem();
        if (empleado == null || empleado.getId() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un empleado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            VentaDTO venta = new VentaDTO();
            venta.setId(ventaId);
            venta.setFechaVenta(dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            venta.setCocheId(coche.getId());
            venta.setMatriculaCoche(coche.getMatricula());
            venta.setClienteVendedorId(vendedor.getId());
            venta.setEmpleadoId(empleado.getId());

            ClienteDTO comprador = (ClienteDTO) compradorCB.getSelectedItem();
            if (comprador != null && comprador.getId() != null) {
                venta.setClienteCompradorId(comprador.getId());
            }

            if (!precioClienteTF.getText().trim().isEmpty())
                venta.setPrecioCliente(Double.parseDouble(precioClienteTF.getText().trim()));

            if (!precioFinalTF.getText().trim().isEmpty())
                venta.setPrecioFinal(Double.parseDouble(precioFinalTF.getText().trim()));

            ventaService.create(venta);
            JOptionPane.showMessageDialog(this, "Venta guardada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar la venta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setCancelController(com.paula.checkmycar.desktop.controller.Controller controller) {
        cancelarButton.setAction(controller);
    }
    
    public void setVentaDTO(VentaDTO venta) {
        this.ventaId = venta.getId();

        if (venta.getFechaVenta() != null) {
            java.util.Date date = java.util.Date.from(
                venta.getFechaVenta().atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
            dateChooser.setDate(date);
        }

        if (venta.getPrecioCliente() != null)
            precioClienteTF.setText(String.valueOf(venta.getPrecioCliente()));

        if (venta.getPrecioFinal() != null)
            precioFinalTF.setText(String.valueOf(venta.getPrecioFinal()));

        for (int i = 0; i < cocheCB.getItemCount(); i++) {
            CocheDTO co = cocheCB.getItemAt(i);
            if (co.getId() != null && co.getId().equals(venta.getCocheId())) {
                cocheCB.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < compradorCB.getItemCount(); i++) {
            ClienteDTO cl = compradorCB.getItemAt(i);
            if (cl.getId() != null && cl.getId().equals(venta.getClienteCompradorId())) {
                compradorCB.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < vendedorCB.getItemCount(); i++) {
            ClienteDTO cl = vendedorCB.getItemAt(i);
            if (cl.getId() != null && cl.getId().equals(venta.getClienteVendedorId())) {
                vendedorCB.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < empleadoCB.getItemCount(); i++) {
            EmpleadoDTO emp = empleadoCB.getItemAt(i);
            if (emp.getId() != null && emp.getId().equals(venta.getEmpleadoId())) {
                empleadoCB.setSelectedIndex(i);
                break;
            }
        }
    }
}