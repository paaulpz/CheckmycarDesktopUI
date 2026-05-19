package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.CitaCriteria;
import com.paula.checkmc.model.CitaDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.impl.CitaServiceImpl;
import com.paula.checkmycar.desktop.views.CitaSearchView;

public class CitaSearchController extends AbstractAction {

    private static final Logger logger =
            LogManager.getLogger(CitaSearchController.class);

    private CitaSearchView view;

    private CitaServiceImpl citaService;

    public CitaSearchController(
            CitaSearchView view) {

        this.view = view;

        this.citaService =
                new CitaServiceImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        buscar(1);
    }

    public void buscar(int pagina) {

        try {

            CitaCriteria criteria =
                    new CitaCriteria();

            int pageSize = 10;

            Results<CitaDTO> results =
                    citaService.findByCriteria(
                            criteria,
                            pagina,
                            pageSize);

            List<CitaDTO> lista =
                    results.getPage();

            view.setTableData(lista);

            view.actualizarPaginacion(
                    pagina,
                    pageSize,
                    results.getTotal());

        } catch (Exception e) {

            logger.error(
                    "Error buscando citas: {}",
                    e.getMessage(),
                    e);

            JOptionPane.showMessageDialog(
                    null,
                    "Error buscando citas");
        }
    }
}