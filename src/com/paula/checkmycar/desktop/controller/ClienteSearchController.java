package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;

import com.paula.checkmc.model.ClienteCriteria;
import com.paula.checkmc.model.ClienteDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.impl.ClienteServiceImpl;
import com.paula.checkmycar.desktop.views.ClienteSearchView;

public class ClienteSearchController extends AbstractAction{

    private ClienteSearchView view;
    private ClienteServiceImpl clienteService;

    public ClienteSearchController(ClienteSearchView view) {
        this.view = view;
        this.clienteService = new ClienteServiceImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ClienteCriteria criteria = new ClienteCriteria();

        if (view.getDni() != null && !view.getDni().trim().isEmpty()) {
            criteria.setDniNie(view.getDni().trim());
        }

        if (view.getEmail() != null && !view.getEmail().trim().isEmpty()) {
            criteria.setEmail(view.getEmail().trim());
        }

        Results<ClienteDTO> results = clienteService.findByCriteria(criteria, 1, 100);

        List<ClienteDTO> lista = results.getPage();

        view.setTableData(lista);
    }
}