package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.paula.checkmc.model.Cliente;
import com.paula.checkmc.service.ClienteService;
import com.paula.checkmc.service.impl.ClienteServiceImpl;
import com.paula.checkmycar.desktop.views.ClienteCreateView;

public class ClienteCreateController extends Controller {

    private ClienteCreateView view = null;
    private ClienteService clienteService = null;

    public ClienteCreateController(ClienteCreateView view) {
        super(view, "Guardar", 
            new ImageIcon(ClienteCreateController.class.getResource("/icons/16x16/agregarusuario.png")));
        
        this.view = view;
        this.clienteService = new ClienteServiceImpl();
    }

    public void doAction() {

        Cliente cliente = view.getModel();
        if (cliente == null) return;

        try {
            Long id = clienteService.register(cliente);
            
            if (id == null) {
                JOptionPane.showMessageDialog(view, 
                    "Error al crear el cliente", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(view, "Cliente creado correctamente");
            view.setEditable(false);
            view.setAgreeController(new ClienteSetEditableController(view));

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
            	    view,
            	    e.getMessage(),
            	    "Error",
            	    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}