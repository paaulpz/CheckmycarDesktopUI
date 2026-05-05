package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.paula.checkmc.model.Cita;
import com.paula.checkmc.service.CitaService;
import com.paula.checkmc.service.impl.CitaServiceImpl;
import com.paula.checkmycar.desktop.views.CitaCreateView;

public class CitaUpdateController extends Controller {

    private CitaService citaService = new CitaServiceImpl();

    public CitaUpdateController(CitaCreateView view) {
        super(view, "Actualizar", null);
    }

    @Override
    public void doAction() {
        CitaCreateView view = (CitaCreateView) getView();
        Cita cita = view.getModel();
        if (cita == null) return;

        if (cita.getId() == null) {
            JOptionPane.showMessageDialog(view, "Error: cita sin ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            JOptionPane.showMessageDialog(view, "Cita actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            view.setEditable(false);
            view.setAgreeController(new CitaSetEditableController(view));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error al actualizar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}