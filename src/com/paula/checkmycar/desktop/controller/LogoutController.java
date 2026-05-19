package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.paula.checkmycar.desktop.main.CheckmycarWindow;
import com.paula.checkmycar.desktop.main.LoginWindow;

public class LogoutController implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        logout();
    }

    private void logout() {

        int opcion = JOptionPane.showConfirmDialog(
                CheckmycarWindow.getInstance().frame,
                "¿Deseas cerrar sesión?",
                "Cerrar sesión",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {

            CheckmycarWindow.getInstance().frame.dispose();

            LoginWindow loginWindow =
                    LoginWindow.getInstance();

            loginWindow.setLocationRelativeTo(null);

            loginWindow.setVisible(true);
        }
    }
}