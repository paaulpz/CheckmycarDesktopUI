package com.paula.checkmycar.desktop.views.renderer;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton
        implements TableCellRenderer {

    public ButtonRenderer() {

        setIcon(new ImageIcon(
                ButtonRenderer.class.getResource(
                        "/icons/16x16/usuarioedit.png")));
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {

        return this;
    }
}