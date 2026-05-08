package com.paula.checkmycar.desktop.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.paula.checkmc.dao.ClienteDAO;
import com.paula.checkmc.dao.EmpleadoDAO;
import com.paula.checkmc.dao.RolDAO;
import com.paula.checkmc.model.Rol;
import com.paula.checkmc.service.MailService;
import com.paula.checkmc.service.impl.MailServiceApacheImpl;
import com.paula.checkmycar.desktop.views.renderer.RolCBRenderer;

public class LoginWindow {

    private JFrame frame;
    private JTextField dniField;
    private JPasswordField passwordField;

    private JRadioButton clienteRadio;
    private JRadioButton empleadoRadio;
    private JComboBox<Rol> rolCombo;
    public LoginWindow() {
        initialize();
    }

    
   
    private void initialize() {

        frame = new JFrame("CheckMyCar - Login");
        frame.setSize(350, 350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JLabel title = new JLabel("CheckMyCar", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        frame.getContentPane().add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

       
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("DNI/NIE:"), gbc);

        dniField = new JTextField();
        gbc.gridx = 1;
        panel.add(dniField, gbc);

     
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Contraseña:"), gbc);

        passwordField = new JPasswordField();
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Tipo:"), gbc);

        JPanel tipoPanel = new JPanel();

        clienteRadio = new JRadioButton("Cliente", true);
        empleadoRadio = new JRadioButton("Empleado");

   
        ButtonGroup group = new ButtonGroup();
        group.add(clienteRadio);
        group.add(empleadoRadio);

        tipoPanel.add(clienteRadio);
        tipoPanel.add(empleadoRadio);

        gbc.gridx = 1;
        panel.add(tipoPanel, gbc);

     
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Rol:"), gbc);
        rolCombo = new JComboBox<Rol>();

        rolCombo.setRenderer(new RolCBRenderer());

        RolDAO rolDAO = new RolDAO();

        for (Rol r : rolDAO.findAll()) {
            rolCombo.addItem(r);
        }
        
        rolCombo.setVisible(false);

        gbc.gridx = 1;
        panel.add(rolCombo, gbc);

       
        JButton loginButton = new JButton("Entrar");
        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(loginButton, gbc);

      
        JButton forgotButton = new JButton("¿Olvidaste la contraseña?");
        forgotButton.setBorderPainted(false);
        forgotButton.setContentAreaFilled(false);
        forgotButton.setForeground(Color.BLUE);

        gbc.gridx = 1; gbc.gridy = 5;
        panel.add(forgotButton, gbc);

        frame.getContentPane().add(panel, BorderLayout.CENTER);

       
        empleadoRadio.addActionListener(e -> {
            rolCombo.setVisible(true);
            panel.revalidate();
            panel.repaint();
        });

        clienteRadio.addActionListener(e -> {
            rolCombo.setVisible(false);
            panel.revalidate();
            panel.repaint();
        });

        loginButton.addActionListener(e -> login());
        forgotButton.addActionListener(e -> recuperarPassword());

        frame.setVisible(true);
    }

    private void login() {

        String dni = dniField.getText().trim();
        String pass = new String(passwordField.getPassword()).trim();

        if (dni.isEmpty() || pass.isEmpty()) {

            JOptionPane.showMessageDialog(frame,
                    "Rellena todos los campos");

            return;
        }

        if (clienteRadio.isSelected()) {

            ClienteDAO dao = new ClienteDAO();

            if (!dao.existeDni(dni)) {

                JOptionPane.showMessageDialog(frame,
                        "DNI inexistente");

                return;
            }

            if (!dao.login(dni, pass)) {

                JOptionPane.showMessageDialog(frame,
                        "Contraseña incorrecta");

                return;
            }

            entrar();
        }

        else if (empleadoRadio.isSelected()) {

            Rol rol = (Rol) rolCombo.getSelectedItem();

            if (rol == null || rol.getId() == null) {

                JOptionPane.showMessageDialog(frame,
                        "Selecciona un rol");

                return;
            }

            EmpleadoDAO dao = new EmpleadoDAO();

            if (!dao.existeDni(dni)) {

                JOptionPane.showMessageDialog(frame,
                        "DNI inexistente");

                return;
            }

            if (!dao.login(dni, pass, rol.getId())) {

                JOptionPane.showMessageDialog(frame,
                        "Contraseña incorrecta");

                return;
            }

            entrar();
        }
    }
    
  
    private void recuperarPassword() {

        String email =
                JOptionPane.showInputDialog(frame,
                        "Introduce tu email:");

        if (email == null || email.trim().isEmpty()) {
            return;
        }

        ClienteDAO clienteDAO = new ClienteDAO();
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        boolean existe =
                clienteDAO.existeCorreo(email)
                || empleadoDAO.existeCorreo(email);

        if (!existe) {

            JOptionPane.showMessageDialog(frame,
                    "Correo incorrecto");

            return;
        }

        cambiarPassword(email);
    }

    private void cambiarPassword(String email) {

        JPanel panel = new JPanel(new GridLayout(2, 2));

        JPasswordField pass1 = new JPasswordField();
        JPasswordField pass2 = new JPasswordField();

        panel.add(new JLabel("Nueva contraseña:"));
        panel.add(pass1);
        panel.add(new JLabel("Repetir contraseña:"));
        panel.add(pass2);

        int result = JOptionPane.showConfirmDialog(frame, panel,
                "Cambiar contraseña", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {

            String p1 = new String(pass1.getPassword());
            String p2 = new String(pass2.getPassword());

            if (!p1.equals(p2)) {
                JOptionPane.showMessageDialog(frame, "Las contraseñas no coinciden");
                return;
            }

            try {
                MailService mailService = new MailServiceApacheImpl();

                mailService.sendEmail(
                        email,
                        "Cambio de contraseña",
                        "Tu contraseña ha sido cambiada correctamente.",
                        "CheckMyCar"
                );

                JOptionPane.showMessageDialog(frame,
                        "Contraseña cambiada y correo enviado");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame,
                        "Error enviando correo");
            }
        }
    }

    private void entrar() {
    	 JOptionPane.showMessageDialog(frame, "¡Bienvenido!");
         frame.dispose();

         CheckmycarWindow.getInstance().frame.setVisible(true);
    }

    private void error() {
        JOptionPane.showMessageDialog(frame, "ERROR: Credenciales incorrectas");
    }
}