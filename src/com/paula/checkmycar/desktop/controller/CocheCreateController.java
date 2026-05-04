package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.paula.checkmc.model.Coche;
import com.paula.checkmc.service.CocheService;
import com.paula.checkmc.service.impl.CocheServiceImpl;
import com.paula.checkmycar.desktop.views.CocheCreateView;

public class CocheCreateController extends Controller {

    private CocheService cocheService = new CocheServiceImpl();

    public CocheCreateController(CocheCreateView view) {
        super(view, "Añadir");
    }

    @Override
    public void doAction() {
        CocheCreateView view = (CocheCreateView) getView();
        Coche coche = view.getModel();
        if (coche == null) return;

        cocheService.create(coche);
        JOptionPane.showMessageDialog(view, "Coche guardado correctamente.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}