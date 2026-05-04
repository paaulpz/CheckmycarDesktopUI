package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.paula.checkmc.model.Cliente;
import com.paula.checkmc.service.ClienteService;
import com.paula.checkmc.service.impl.ClienteServiceImpl;
import com.paula.checkmycar.desktop.views.ClienteCreateView;

public class ClienteUpdateController extends Controller {

    private ClienteCreateView view = null;
    private ClienteService clienteService = null;

    public ClienteUpdateController(ClienteCreateView view) {
        super(view, "Actualizar", null);
        this.view = view;
        this.clienteService = new ClienteServiceImpl();
    }

    public void doAction() {

        Cliente cliente = view.getModel();

        if (cliente == null) return;

        System.out.println("ID: " + cliente.getId());
        System.out.println("DNI: " + cliente.getDniNie());

        boolean updated = clienteService.update(cliente);
       
        if (cliente.getId() == null) {
            JOptionPane.showMessageDialog(view,
                    "Error: cliente sin ID (no se puede actualizar)",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {

            if (updated) {

                JOptionPane.showMessageDialog(view,
                        "Cliente " + cliente.getNombre() + " actualizado correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                view.setEditable(false);
                view.setAgreeController(new ClienteSetEditableController(view));

            } else {

                JOptionPane.showMessageDialog(view,
                        "Error al actualizar el cliente",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(view,
                    "Error en base de datos (DNI duplicado o datos incorrectos)",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}