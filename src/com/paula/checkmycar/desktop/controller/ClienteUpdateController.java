package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Cliente;
import com.paula.checkmc.service.ClienteService;
import com.paula.checkmc.service.impl.ClienteServiceImpl;
import com.paula.checkmycar.desktop.views.ClienteCreateView;

public class ClienteUpdateController extends Controller {

    private static final Logger logger =
            LogManager.getLogger(ClienteUpdateController.class);

    private ClienteCreateView view = null;

    private ClienteService clienteService = null;

    public ClienteUpdateController(ClienteCreateView view) {

        super(view, "Guardar", null);

        this.view = view;

        this.clienteService = new ClienteServiceImpl();
    }

    public void doAction() {

        Cliente cliente = view.getModel();

        if (cliente == null) {

            return;
        }

        if (cliente.getId() == null) {

            JOptionPane.showMessageDialog(
                    view,
                    "Error: cliente sin ID",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        try {
        	
        	logger.debug("Actualizando cliente: {} - {}",
        	        cliente.getId(),
        	        cliente.getNombre());

            boolean updated =
                    clienteService.update(cliente);

            if (updated) {

                JOptionPane.showMessageDialog(
                        view,
                        "Cliente " + cliente.getNombre()
                                + " actualizado correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                view.setEditable(false);

                view.setAgreeController(
                        new ClienteSetEditableController(view));

            } else {

                JOptionPane.showMessageDialog(
                        view,
                        "Error al actualizar el cliente",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {

            logger.error("Error actualizando cliente {}: {}",
                    cliente.getId(),
                    e.getMessage(),
                    e);

            JOptionPane.showMessageDialog(
                    view,
                    "Error en base de datos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        doAction();
    }
}