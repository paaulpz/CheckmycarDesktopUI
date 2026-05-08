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
import com.paula.checkmycar.desktop.views.ClienteCreateView;
import com.paula.checkmycar.desktop.views.tableModel.ClienteTableModel;

public class ClienteButtonEditor extends AbstractCellEditor implements TableCellEditor {

    private JButton button;
    private ClienteDTO cliente;

    public ClienteButtonEditor(JCheckBox checkBox) {

        button = new JButton();
        button.setIcon(new ImageIcon(ClienteButtonEditor.class.getResource("/icons/16x16/usuarioedit.png")));

        button.addActionListener((ActionEvent e) -> {
            fireEditingStopped();
            try {
                ClienteCreateView view = new ClienteCreateView();
                view.setClienteDTO(cliente);
                view.setEditable(false);
                view.setAgreeController(new com.paula.checkmycar.desktop.controller.ClienteSetEditableController(view));

                JFrame frame = new JFrame("Editar cliente");
                frame.setContentPane(view);
                frame.setSize(800, 600);
                frame.setLocationRelativeTo(null);
                frame.setAlwaysOnTop(true);

                view.setCancelController(new com.paula.checkmycar.desktop.controller.Controller(view, "Cancelar") {
                    @Override
                    public void doAction() {
                        frame.dispose();
                    }
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        doAction();
                    }
                });

                frame.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
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