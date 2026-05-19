package com.paula.checkmycar.desktop.main;

import javax.swing.SwingUtilities;

import com.paula.checkmc.model.EmpleadoDTO;
import com.paula.checkmc.service.EmpleadoService;
import com.paula.checkmc.service.impl.EmpleadoServiceImpl;

public class Launch {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                try {

                    EmpleadoService empleadoService =
                            new EmpleadoServiceImpl();

                    EmpleadoDTO empleado =
                            empleadoService.login(
                                    "12345678A",
                                    "admin123",
                                    1L);

                    if (empleado != null) {

                        System.out.println(
                                "Login correcto: "
                                + empleado.getNombre());

                        CheckmycarWindow window =
                                CheckmycarWindow.getInstance();

                        window.frame.setLocationRelativeTo(null);

                        window.frame.setVisible(true);

                    } else {

                        System.err.println(
                                "Error en el login de prueba");
                    }

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });
    }
}