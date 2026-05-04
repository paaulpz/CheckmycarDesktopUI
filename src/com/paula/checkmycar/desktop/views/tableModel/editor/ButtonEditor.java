package com.paula.checkmycar.desktop.views.tableModel.editor;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.paula.checkmc.model.ClienteDTO;
import com.paula.checkmycar.desktop.controller.ClienteUpdateController;
import com.paula.checkmycar.desktop.views.ClienteCreateView;
import com.paula.checkmycar.desktop.views.tableModel.ClienteTableModel;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {

    private JButton button;
    private ClienteDTO cliente;

    public ButtonEditor(JCheckBox checkBox) {

        button = new JButton();
        button.setIcon(new ImageIcon(ButtonEditor.class.getResource("/icons/16x16/usuarioedit.png")));

        button.addActionListener((ActionEvent e) -> {

            fireEditingStopped();

            ClienteCreateView view = new ClienteCreateView();

            view.setClienteDTO(cliente);

            view.setEditable(true);

            view.setAgreeController(new ClienteUpdateController(view));

            JFrame frame = new JFrame("Editar cliente");
            frame.setContentPane(view);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {

        cliente = ((ClienteTableModel) table.getModel()).getClientes().get(row);

        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }
}