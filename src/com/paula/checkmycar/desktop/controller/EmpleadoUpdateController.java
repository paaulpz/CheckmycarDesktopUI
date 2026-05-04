package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.paula.checkmc.model.Empleado;
import com.paula.checkmc.service.EmpleadoService;
import com.paula.checkmc.service.impl.EmpleadoServiceImpl;
import com.paula.checkmycar.desktop.views.EmpleadoCreateView;

public class EmpleadoUpdateController extends Controller {

    private EmpleadoService empleadoService = new EmpleadoServiceImpl();

    public EmpleadoUpdateController(EmpleadoCreateView view) {
        super(view, "Actualizar", null);
    }

    @Override
    public void doAction() {
        EmpleadoCreateView view = (EmpleadoCreateView) getView();
        Empleado empleado = view.getModel();
        if (empleado == null) return;

        if (empleado.getId() == null) {
            JOptionPane.showMessageDialog(view, "Error: empleado sin ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean updated = empleadoService.update(empleado);
        if (updated) {
            JOptionPane.showMessageDialog(view, "Empleado actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            view.setEditable(false);
            view.setAgreeController(new EmpleadoSetEditableController(view));
        } else {
            JOptionPane.showMessageDialog(view, "Error al actualizar el empleado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}