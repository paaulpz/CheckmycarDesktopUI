package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.paula.checkmc.model.Presupuesto;
import com.paula.checkmc.model.PresupuestoDTO;
import com.paula.checkmc.service.PresupuestoService;
import com.paula.checkmc.service.impl.PresupuestoServiceImpl;
import com.paula.checkmycar.desktop.views.PresupuestoCreateView;

public class PresupuestoUpdateController extends Controller {

    private PresupuestoCreateView view;

    private PresupuestoService presupuestoService;

    public PresupuestoUpdateController(
            PresupuestoCreateView view) {

        super(view, "Actualizar", null);

        this.view = view;

        this.presupuestoService =
                new PresupuestoServiceImpl();
    }

    @Override
    public void doAction() {

        try {

            PresupuestoDTO presupuesto =
                    view.getModel();

            if (presupuesto == null) {
                return;
            }

            if (presupuesto.getId() == null) {

                JOptionPane.showMessageDialog(
                        view,
                        "Error: presupuesto sin ID.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            boolean updated =
                    presupuestoService.update(
                            presupuesto);

            if (updated) {

                JOptionPane.showMessageDialog(
                        view,
                        "Presupuesto actualizado correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                view.setEditable(false);

                view.setAgreeController(
                        new PresupuestoSetEditableController(view));

            } else {

                JOptionPane.showMessageDialog(
                        view,
                        "No se pudo actualizar el presupuesto.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    view,
                    "Error al actualizar presupuesto.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        doAction();
    }
}