package com.paula.checkmycar.desktop.views.tableModel.editor;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.paula.checkmc.model.VentaDTO;
import com.paula.checkmc.service.VentaService;
import com.paula.checkmc.service.impl.VentaServiceImpl;
import com.paula.checkmycar.desktop.views.VentaCreateView;
import com.paula.checkmycar.desktop.views.tableModel.VentaTableModel;

public class VentaButtonEditor extends AbstractCellEditor implements TableCellEditor {

    private JPanel panel;
    private JButton editarButton;
    private JButton eliminarButton;
    private VentaDTO venta;
    private JTable tabla;
    private VentaService ventaService = new VentaServiceImpl();

    public VentaButtonEditor(JCheckBox checkBox) {
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 0));

        editarButton = new JButton();
        editarButton.setIcon(new ImageIcon(VentaButtonEditor.class.getResource("/icons/16x16/venta.png")));

        eliminarButton = new JButton();
        eliminarButton.setIcon(new ImageIcon(VentaButtonEditor.class.getResource("/nuvola/16x16/1516_broken_x_file_x_broken_file.png")));

        panel.add(editarButton);
        panel.add(eliminarButton);

        editarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                try {
                    VentaCreateView view = new VentaCreateView();
                    view.setVentaDTO(venta);
                    JFrame frame = new JFrame("Editar venta");
                    frame.setContentPane(view);
                    frame.setSize(800, 500);
                    frame.setLocationRelativeTo(null);
                    frame.setAlwaysOnTop(true);
                    view.setCancelController(new com.paula.checkmycar.desktop.controller.Controller(view, "Cancelar") {
                        public void doAction() { frame.dispose(); }
                        public void actionPerformed(ActionEvent evt) { doAction(); }
                    });
                    frame.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                int opcion = JOptionPane.showConfirmDialog(null,
                    "¿Seguro que deseas eliminar la venta del coche " + venta.getMatriculaCoche() + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
                if (opcion == JOptionPane.YES_OPTION) {
                    try {
                        boolean eliminado = ventaService.delete(venta.getId());
                        if (eliminado) {
                            JOptionPane.showMessageDialog(null, "Venta eliminada correctamente.");
                            VentaTableModel model = (VentaTableModel) tabla.getModel();
                            model.getData().remove(venta);
                            model.fireTableDataChanged();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al eliminar la venta.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        this.tabla = table;
        venta = ((VentaTableModel) table.getModel()).getVentaAt(row);
        return panel;
    }

    @Override
    public Object getCellEditorValue() { return null; }
}