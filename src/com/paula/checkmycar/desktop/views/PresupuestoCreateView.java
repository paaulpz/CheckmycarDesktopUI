package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.paula.checkmc.model.Cliente;
import com.paula.checkmc.model.ClienteCriteria;
import com.paula.checkmc.model.EstadoPresupuesto;
import com.paula.checkmc.model.PresupuestoDTO;
import com.paula.checkmc.service.impl.ClienteServiceImpl;
import com.paula.checkmc.service.impl.EstadoPresupuestoServiceImpl;
import com.paula.checkmc.service.impl.PresupuestoServiceImpl;
import com.toedter.calendar.JDateChooser;

public class PresupuestoCreateView extends AbstracView {
	private JTextField diagnosticoTF;
	private JComboBox estadoCB;
	private JComboBox clienteCB;
	private JDateChooser dateChooser;
	private JLabel fechaLabel;
	private JLabel estadoLabel;
	private JLabel clienteLabel;
	private JLabel nombreLabel;
	private JLabel precioLabel;
	private JPanel contentPanel;
	private JPanel buttonPanel;
	private JPanel botonesPannel;
	private JButton guardarButton;
	private JButton eliminarButton;
	private JTextField precioTF;
	
	private PresupuestoServiceImpl presupuestoService;

	
	public PresupuestoCreateView() {
		initialize();
		postInitialize();
		initServices();
		}
	
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		
		JPanel buttonPanel = new JPanel();
		add(buttonPanel);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_buttonPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_buttonPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_buttonPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		buttonPanel.setLayout(gbl_buttonPanel);
		
		JLabel fechaLabel = new JLabel("Fecha: ");
		GridBagConstraints gbc_fechaLabel = new GridBagConstraints();
		gbc_fechaLabel.anchor = GridBagConstraints.WEST;
		gbc_fechaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaLabel.gridx = 3;
		gbc_fechaLabel.gridy = 1;
		buttonPanel.add(fechaLabel, gbc_fechaLabel);
		
		JDateChooser dateChooser = new JDateChooser();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 0);
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc.gridx = 5;
		gbc.gridy = 1;
		buttonPanel.add(dateChooser, gbc);
		
		JLabel estadoLabel = new JLabel("Estado: ");
		GridBagConstraints gbc_estadoLabel = new GridBagConstraints();
		gbc_estadoLabel.anchor = GridBagConstraints.WEST;
		gbc_estadoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_estadoLabel.gridx = 3;
		gbc_estadoLabel.gridy = 2;
		buttonPanel.add(estadoLabel, gbc_estadoLabel);
		
		JComboBox estadoCB = new JComboBox();
		GridBagConstraints gbc_estadoCB = new GridBagConstraints();
		gbc_estadoCB.insets = new Insets(0, 0, 5, 0);
		gbc_estadoCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_estadoCB.gridx = 5;
		gbc_estadoCB.gridy = 2;
		buttonPanel.add(estadoCB, gbc_estadoCB);
		
		JLabel clienteLabel = new JLabel("Cliente : ");
		GridBagConstraints gbc_clienteLabel = new GridBagConstraints();
		gbc_clienteLabel.anchor = GridBagConstraints.WEST;
		gbc_clienteLabel.insets = new Insets(0, 0, 5, 5);
		gbc_clienteLabel.gridx = 3;
		gbc_clienteLabel.gridy = 3;
		buttonPanel.add(clienteLabel, gbc_clienteLabel);
		
		JComboBox clienteCB = new JComboBox();
		GridBagConstraints gbc_clienteCB = new GridBagConstraints();
		gbc_clienteCB.insets = new Insets(0, 0, 5, 0);
		gbc_clienteCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_clienteCB.gridx = 5;
		gbc_clienteCB.gridy = 3;
		buttonPanel.add(clienteCB, gbc_clienteCB);
		
		JLabel nombreLabel = new JLabel("Diagnostico: ");
		GridBagConstraints gbc_nombreLabel = new GridBagConstraints();
		gbc_nombreLabel.anchor = GridBagConstraints.WEST;
		gbc_nombreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nombreLabel.gridx = 3;
		gbc_nombreLabel.gridy = 4;
		buttonPanel.add(nombreLabel, gbc_nombreLabel);
		
		diagnosticoTF = new JTextField();
		GridBagConstraints gbc_diagnosticoTF = new GridBagConstraints();
		gbc_diagnosticoTF.insets = new Insets(0, 0, 5, 0);
		gbc_diagnosticoTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_diagnosticoTF.gridx = 5;
		gbc_diagnosticoTF.gridy = 4;
		buttonPanel.add(diagnosticoTF, gbc_diagnosticoTF);
		diagnosticoTF.setColumns(10);
		
		JLabel precioLabel = new JLabel("Precio Final : ");
		GridBagConstraints gbc_precioLabel = new GridBagConstraints();
		gbc_precioLabel.insets = new Insets(0, 0, 0, 5);
		gbc_precioLabel.gridx = 3;
		gbc_precioLabel.gridy = 5;
		buttonPanel.add(precioLabel, gbc_precioLabel);
		
		precioTF = new JTextField();
		GridBagConstraints gbc_precioTF = new GridBagConstraints();
		gbc_precioTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_precioTF.gridx = 5;
		gbc_precioTF.gridy = 5;
		buttonPanel.add(precioTF, gbc_precioTF);
		precioTF.setColumns(10);
		
		JPanel botonesPannel = new JPanel();
		add(botonesPannel, BorderLayout.SOUTH);
		botonesPannel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton eliminarButton = new JButton("Eliminar");
		eliminarButton.addActionListener(e -> {
			 dateChooser.getDate();
			 diagnosticoTF.getText();
			 precioLabel.getText();
			 clienteCB.setSelectedIndex(0);
			 estadoCB.setSelectedIndex(0);
			 
		 });

        JButton guardarButton = new JButton("Registrar");
        guardarButton.addActionListener(e -> {
            try {
                PresupuestoDTO pre = new PresupuestoDTO();

                Date date = dateChooser.getDate();
                if (date != null) {
                    LocalDate localDate = date.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    pre.setFecha(localDate);
                }

                pre.setNombre(diagnosticoTF.getText());

                pre.setPrecioFinal(Double.parseDouble(precioLabel.getText()));

                Cliente cliente = (Cliente) clienteCB.getSelectedItem();
                if (cliente != null) {
                    pre.setClienteId(cliente.getId());
                }

                EstadoPresupuesto estado = (EstadoPresupuesto) estadoCB.getSelectedItem();
                if (estado != null) {
                    pre.setEstadoPresupuestoId(estado.getId());
                }

            
                presupuestoService.create(pre);

                JOptionPane.showMessageDialog(this,
                        "Presupuesto creado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                ex.printStackTrace();

                JOptionPane.showMessageDialog(this,
                        "Error al crear presupuesto",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
		
		
		botonesPannel.add(eliminarButton);
		botonesPannel.add(guardarButton);
		
		
	}
	
	private void postInitialize() {

	    ClienteServiceImpl clienteService = new ClienteServiceImpl();
	    EstadoPresupuestoServiceImpl estadoService = new EstadoPresupuestoServiceImpl();

	    try {
	       
	    	ClienteCriteria criteria = new ClienteCriteria();
	        List<Cliente> resultados = service.findByCriteria(criteria);

	        DefaultComboBoxModel<Cliente> clienteModel = new DefaultComboBoxModel<>();

	        for (Cliente c : clientes) {
	            clienteModel.addElement(c);
	        }

	        clienteCB.setModel(clienteModel);

	        List<EstadoPresupuesto> estados = estadoService.findAll();

	        DefaultComboBoxModel<EstadoPresupuesto> estadoModel = new DefaultComboBoxModel<>();

	        for (EstadoPresupuesto e : estados) {
	            estadoModel.addElement(e);
	        }

	        estadoCB.setModel(estadoModel);

	    } catch (Exception e) {
	        e.printStackTrace();

	        JOptionPane.showMessageDialog(this,
	                "Error cargando datos",
	                "Error",
	                JOptionPane.ERROR_MESSAGE);
	    }
	}

	private void initServices() {
	    presupuestoService = new PresupuestoServiceImpl();
	}

}