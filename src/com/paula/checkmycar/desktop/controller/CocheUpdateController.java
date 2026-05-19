package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.paula.checkmc.model.Coche;
import com.paula.checkmc.service.CocheService;
import com.paula.checkmc.service.impl.CocheServiceImpl;
import com.paula.checkmycar.desktop.views.CocheCreateView;

public class CocheUpdateController extends Controller {

    private CocheCreateView view;

    private CocheService cocheService;

    public CocheUpdateController(CocheCreateView view) {

        super(view, "Actualizar", null);

        this.view = view;

        this.cocheService = new CocheServiceImpl();
    }

    @Override
    public void doAction() {

        try {

            Coche coche = view.getModel();

            if (coche == null) {
                return;
            }

            if (coche.getId() == null) {

                JOptionPane.showMessageDialog(
                        view,
                        "Error: coche sin ID.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            boolean updated =
                    cocheService.update(coche);

            if (updated) {

                JOptionPane.showMessageDialog(
                        view,
                        "Coche actualizado correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                view.setEditable(false);

                view.setAgreeController(
                        new CocheSetEditableController(view));

            } else {

                JOptionPane.showMessageDialog(
                        view,
                        "No se pudo actualizar el coche.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    view,
                    "Error al actualizar el coche.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        doAction();
    }
}