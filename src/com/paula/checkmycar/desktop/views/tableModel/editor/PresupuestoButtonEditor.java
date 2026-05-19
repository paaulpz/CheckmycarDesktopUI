package com.paula.checkmycar.desktop.views.tableModel.editor;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.paula.checkmc.model.PresupuestoDTO;
import com.paula.checkmc.service.PresupuestoService;
import com.paula.checkmc.service.impl.PresupuestoServiceImpl;
import com.paula.checkmycar.desktop.controller.Controller;
import com.paula.checkmycar.desktop.controller.PresupuestoSetEditableController;
import com.paula.checkmycar.desktop.views.PresupuestoCreateView;
import com.paula.checkmycar.desktop.views.tableModel.PresupuestoTableModel;

public class PresupuestoButtonEditor extends AbstractCellEditor implements TableCellEditor {

	private JPanel panel;

	private JButton editarButton;

	private JButton eliminarButton;

	private PresupuestoDTO presupuesto;

	private JTable tabla;

	private PresupuestoService presupuestoService = new PresupuestoServiceImpl();

	public PresupuestoButtonEditor(JCheckBox checkBox) {

		panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 0));

		editarButton = new JButton();

		editarButton.setIcon(new ImageIcon(PresupuestoButtonEditor.class.getResource("/nuvola/16x16/1819_pencil_pencil.png")));

		eliminarButton = new JButton();

		eliminarButton.setIcon(new ImageIcon(PresupuestoButtonEditor.class.getResource("/nuvola/16x16/1815_no_no.png")));

		panel.add(editarButton);

		panel.add(eliminarButton);

		editarButton.addActionListener(e -> {

			fireEditingStopped();

			try {

				PresupuestoCreateView view = new PresupuestoCreateView();

				PresupuestoDTO p = presupuestoService.findById(presupuesto.getId());

				view.setPresupuestoDTO(p);

				view.setEditable(false);

				view.setAgreeController(new PresupuestoSetEditableController(view));

				JFrame frame = new JFrame("Editar presupuesto");

				frame.setContentPane(view);

				frame.setSize(900, 600);

				frame.setLocationRelativeTo(null);

				view.setCancelController(new Controller(view, "Cancelar") {

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

				JOptionPane.showMessageDialog(null, "Error abriendo presupuesto.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		eliminarButton.addActionListener(e -> {

			fireEditingStopped();

			int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que deseas eliminar el presupuesto?",
					"Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

			if (opcion == JOptionPane.YES_OPTION) {

				try {

					boolean eliminado = presupuestoService.delete(presupuesto.getId());

					if (eliminado) {

						JOptionPane.showMessageDialog(null, "Presupuesto eliminado correctamente.");

						PresupuestoTableModel model = (PresupuestoTableModel) tabla.getModel();

						model.getData().remove(presupuesto);

						model.fireTableDataChanged();

					} else {

						JOptionPane.showMessageDialog(null, "No se pudo eliminar el presupuesto.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (Exception ex) {

					ex.printStackTrace();

					JOptionPane.showMessageDialog(null, "Error eliminando presupuesto.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

		this.tabla = table;

		presupuesto = ((PresupuestoTableModel) table.getModel()).getData().get(row);

		return panel;
	}

	@Override
	public Object getCellEditorValue() {

		return null;
	}
}