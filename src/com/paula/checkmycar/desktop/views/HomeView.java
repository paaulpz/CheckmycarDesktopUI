package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HomeView extends View {

    public HomeView() {
        setName("Inicio");
        setLayout(new BorderLayout(10, 10));

      
        JPanel header = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(0, 102, 204),
                        getWidth(), 0, new Color(100, 180, 255)
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        header.setPreferredSize(new Dimension(800, 100));
        header.setLayout(new GridBagLayout());

        JLabel title = new JLabel("CheckMyCar");
        title.setFont(new Font("Segoe UI", Font.BOLD, 40));
        title.setForeground(Color.WHITE);

        header.add(title);

        add(header, BorderLayout.NORTH);

       
        JPanel center = new JPanel(new BorderLayout());
        center.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));

        JLabel novedadesLabel = new JLabel("📋 Información del sistema");
        novedadesLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        novedadesLabel.setForeground(new Color(0, 102, 204));

        center.add(novedadesLabel, BorderLayout.NORTH);

        JTextArea info = new JTextArea();
        info.setEditable(false);
        info.setFont(new Font("Consolas", Font.PLAIN, 13));
        info.setBackground(new Color(250, 250, 250));
        info.setMargin(new Insets(20, 20, 20, 20));
        info.setText(getTexto());

        JScrollPane scroll = new JScrollPane(info);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(200, 220, 240), 2));

        center.add(scroll, BorderLayout.CENTER);

        add(center, BorderLayout.CENTER);
    }

    private String getTexto() {
        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String usuario = "Admin";
        
        return "═══════════════════════════════════════════════\n" +
        "        CheckMyCar - Gestión Taller           \n" +
        "═══════════════════════════════════════════════\n\n" +
        "Fecha: " + fecha + "\n\n" +
        "Usuario: " + usuario + "\n\n" +

        "✦ FUNCIONALIDADES ✦\n" +
        "  ✔ Gestión de CLIENTES\n" +
        "  ✔ Gestión de EMPLEADOS\n" +
        "  ✔ Gestión de COCHES\n" +
        "  ✔ Órdenes de trabajo\n" +
        "  ✔ Presupuestos\n\n" +

        "✦ ESTADO DEL SISTEMA ✦\n" +
        "  ✔ Base de datos conectada\n" +
        "  ✔ Servicios activos\n" +
        "  ✔ Aplicación funcionando correctamente\n\n" +

        "✦ ÚLTIMAS ACCIONES ✦\n" +
        "  • Clientes registrados recientemente\n" +
        "  • Nuevas órdenes de trabajo creadas\n\n" +

        "✦ PRÓXIMAMENTE ✦\n" +
        "  • Estadísticas del taller\n" +
        "  • Historial de reparaciones\n" +
        "  • Panel de control\n\n" +

        "───────────────────────────────────────────────\n" +
        "Bienvenido al sistema 🚗\n" +
        "© CheckMyCar";
    }
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}
}