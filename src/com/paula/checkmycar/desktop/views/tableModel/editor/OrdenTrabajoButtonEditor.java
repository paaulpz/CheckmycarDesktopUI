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

import com.paula.checkmc.model.OrdenTrabajoDTO;
import com.paula.checkmc.service.OrdenTrabajoService;
import com.paula.checkmc.service.impl.OrdenTrabajoServiceImpl;
import com.paula.checkmycar.desktop.controller.Controller;
import com.paula.checkmycar.desktop.controller.OrdenTrabajoSetEditableController;
import com.paula.checkmycar.desktop.views.OrdenTrabajoCreateView;
import com.paula.checkmycar.desktop.views.tableModel.OrdenTrabajoTableModel;

public class OrdenTrabajoButtonEditor extends AbstractCellEditor implements TableCellEditor {

	private JPanel panel;

	private JButton editarButton;

	private JButton eliminarButton;

	private OrdenTrabajoDTO ordenTrabajo;

	private JTable tabla;

	private OrdenTrabajoService ordenTrabajoService = new OrdenTrabajoServiceImpl();

	public OrdenTrabajoButtonEditor(JCheckBox checkBox) {

		panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 0));

		editarButton = new JButton();

		editarButton.setIcon(
				new ImageIcon(OrdenTrabajoButtonEditor.class.getResource("/nuvola/16x16/1819_pencil_pencil.png")));

		eliminarButton = new JButton();

		eliminarButton
				.setIcon(new ImageIcon(OrdenTrabajoButtonEditor.class.getResource("/nuvola/16x16/1815_no_no.png")));

		panel.add(editarButton);

		panel.add(eliminarButton);

		editarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				fireEditingStopped();

				try {

					OrdenTrabajoCreateView view = new OrdenTrabajoCreateView();

					view.setOrdenTrabajoDTO(ordenTrabajo);

					view.setEditable(false);

					view.setAgreeController(new OrdenTrabajoSetEditableController(view));

					JFrame frame = new JFrame("Editar orden de trabajo");

					frame.setContentPane(view);

					frame.setSize(900, 600);

					frame.setLocationRelativeTo(null);

					view.setCancelController(new Controller(view, "Cancelar") {

						@Override
						public void doAction() {

							frame.dispose();
						}

						@Override
						public void actionPerformed(ActionEvent e) {

							doAction();
						}
					});

					frame.setVisible(true);

				} catch (Exception ex) {

					ex.printStackTrace();

					JOptionPane.showMessageDialog(null, "Error abriendo la orden de trabajo");
				}
			}
		});

		eliminarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				fireEditingStopped();

				int opcion = JOptionPane.showConfirmDialog(null, "¿Deseas eliminar la orden de trabajo seleccionada?",
						"Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (opcion == JOptionPane.YES_OPTION) {

					try {

						boolean eliminado = ordenTrabajoService.delete(ordenTrabajo.getId());

						if (eliminado) {

							OrdenTrabajoTableModel model = (OrdenTrabajoTableModel) tabla.getModel();

							model.getOrdenes().remove(ordenTrabajo);

							model.fireTableDataChanged();

							JOptionPane.showMessageDialog(null, "Orden de trabajo eliminada correctamente.");

						} else {

							JOptionPane.showMessageDialog(null, "No se pudo eliminar la orden de trabajo.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}

					} catch (Exception ex) {

						ex.printStackTrace();

						JOptionPane.showMessageDialog(null, "Error eliminando la orden de trabajo", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

		this.tabla = table;

		OrdenTrabajoTableModel model = (OrdenTrabajoTableModel) table.getModel();

		ordenTrabajo = model.getRow(row);

		return panel;
	}

	@Override
	public Object getCellEditorValue() {

		return null;
	}
}