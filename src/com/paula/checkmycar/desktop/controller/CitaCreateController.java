package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.paula.checkmc.model.Cita;
import com.paula.checkmc.service.CitaService;
import com.paula.checkmc.service.impl.CitaServiceImpl;
import com.paula.checkmycar.desktop.views.CitaCreateView;

public class CitaCreateController extends Controller {

    private CitaService citaService = new CitaServiceImpl();

    public CitaCreateController(CitaCreateView view) {
        super(view, "Guardar");
    }

    @Override
    public void doAction() {
        CitaCreateView view = (CitaCreateView) getView();
        Cita cita = view.getModel();
        if (cita == null) return;

        try {
            citaService.create(cita);
            JOptionPane.showMessageDialog(view, "Cita guardada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error al guardar la cita: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}