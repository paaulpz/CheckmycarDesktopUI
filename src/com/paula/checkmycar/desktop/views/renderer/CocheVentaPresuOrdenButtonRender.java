package com.paula.checkmycar.desktop.views.renderer;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CocheVentaPresuOrdenButtonRender extends JPanel implements TableCellRenderer {

    private JButton editarButton;
    private JButton eliminarButton;

    public CocheVentaPresuOrdenButtonRender() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 2, 0));
        editarButton = new JButton();
        editarButton.setIcon(new ImageIcon(CocheVentaPresuOrdenButtonRender.class.getResource("/nuvola/16x16/1819_pencil_pencil.png")));
        eliminarButton = new JButton();
        eliminarButton.setIcon(new ImageIcon(CocheVentaPresuOrdenButtonRender.class.getResource("/nuvola/16x16/1815_no_no.png")));
        add(editarButton);
        add(eliminarButton);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}