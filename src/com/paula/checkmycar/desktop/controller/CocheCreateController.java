package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Coche;
import com.paula.checkmc.service.CocheService;
import com.paula.checkmc.service.impl.CocheServiceImpl;
import com.paula.checkmycar.desktop.views.CocheCreateView;

public class CocheCreateController extends Controller {

    private static final Logger logger =
            LogManager.getLogger(CocheCreateController.class);

    private CocheService cocheService =
            new CocheServiceImpl();

    public CocheCreateController(CocheCreateView view) {

        super(view, "Añadir");
    }

    @Override
    public void doAction() {

        CocheCreateView view =
                (CocheCreateView) getView();

        Coche coche = view.getModel();

        if (coche == null) {

            return;
        }

        try {

            cocheService.create(coche);

            JOptionPane.showMessageDialog(
                    view,
                    "Coche guardado correctamente.");

        } catch (Exception e) {

            logger.error("Error creando coche: {}",
                    e.getMessage(),
                    e);

            JOptionPane.showMessageDialog(
                    view,
                    "Error al guardar el coche",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        doAction();
    }
}