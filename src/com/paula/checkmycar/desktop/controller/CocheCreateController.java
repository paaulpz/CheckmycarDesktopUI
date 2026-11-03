package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;


import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Coche;
import com.paula.checkmc.model.ContentData;
import com.paula.checkmc.model.ContentMetadata;
import com.paula.checkmc.service.impl.ContentServiceFileImpl;
import com.paula.checkmc.service.CocheService;
import com.paula.checkmc.service.impl.CocheServiceImpl;
import com.paula.checkmycar.desktop.views.CocheCreateView;

import java.io.File;
import java.nio.file.Files;
import java.util.Locale;

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


File foto = view.getFotoSeleccionada();
String formato = null;
byte[] bytesFoto = null;

if (foto != null) {

    String nombre = foto.getName().toLowerCase(Locale.ROOT);



    if (foto.length() > 5L * 1024 * 1024) {
        throw new IllegalArgumentException("La imagen supera los 5 MB");
    }

    bytesFoto = Files.readAllBytes(foto.toPath());
}

// Crear coche en la base de datos
Long cocheId = cocheService.create(coche);

// Guardar fotografía en disco
if (foto != null) {

    try {

        if (cocheId == null) {
            throw new IllegalStateException(
                    "No se obtuvo el ID del coche");
        }

        ContentMetadata metadata = new ContentMetadata();

        metadata.setEntityType("coche");
        metadata.setEntityId(String.valueOf(cocheId));
        metadata.setResourceId("principal");
        metadata.setFormat(formato);

        ContentData data = new ContentData();

        data.setMetadata(metadata);
        data.setRaw(bytesFoto);

        ContentServiceFileImpl contentService =
                new ContentServiceFileImpl();

        contentService.save(data);

    } catch (Exception errorFoto) {

        logger.error("Coche creado, pero error guardando fotografía",
                errorFoto);

        JOptionPane.showMessageDialog(
                view,
                "El coche se creó, pero no se pudo guardar la fotografía.",
                "Error de fotografía",
                JOptionPane.ERROR_MESSAGE);

        return;
    }
}

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