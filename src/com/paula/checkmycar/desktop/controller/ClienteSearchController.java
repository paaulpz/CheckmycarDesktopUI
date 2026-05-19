package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.ClienteCriteria;
import com.paula.checkmc.model.ClienteDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.impl.ClienteServiceImpl;
import com.paula.checkmycar.desktop.views.ClienteSearchView;

public class ClienteSearchController extends AbstractAction {

    private static final Logger logger =
            LogManager.getLogger(ClienteSearchController.class);

    private ClienteSearchView view;

    private ClienteServiceImpl clienteService;

    public ClienteSearchController(ClienteSearchView view) {

        this.view = view;

        this.clienteService = new ClienteServiceImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        buscar(1);
    }

    public void buscar(int pagina) {

        try {

            ClienteCriteria criteria = new ClienteCriteria();

            if (view.getDni() != null &&
                    !view.getDni().trim().isEmpty()) {

                criteria.setDniNie(view.getDni().trim());
            }

            if (view.getEmail() != null &&
                    !view.getEmail().trim().isEmpty()) {

                criteria.setEmail(view.getEmail().trim());
            }

            int pageSize = 10;

            Results<ClienteDTO> results =
                    clienteService.findByCriteria(
                            criteria,
                            pagina,
                            pageSize);

            List<ClienteDTO> lista = results.getPage();

            view.setTableData(lista);

            view.actualizarPaginacion(
                    pagina,
                    pageSize,
                    results.getTotal());

        } catch (Exception e) {

            logger.error("Error buscando clientes: {}",
                    e.getMessage(),
                    e);

            JOptionPane.showMessageDialog(
                    null,
                    "Error buscando clientes");
        }
    }
}