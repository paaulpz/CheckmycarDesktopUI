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

import com.paula.checkmc.model.CocheDTO;
import com.paula.checkmc.service.CocheService;
import com.paula.checkmc.service.impl.CocheServiceImpl;
import com.paula.checkmycar.desktop.controller.CocheUpdateController;
import com.paula.checkmycar.desktop.controller.Controller;
import com.paula.checkmycar.desktop.views.CocheCreateView;
import com.paula.checkmycar.desktop.views.tableModel.CocheTableModel;

public class CocheButtonEditor extends AbstractCellEditor implements TableCellEditor {

	private JPanel panel;

	private JButton editarButton;

	private JButton eliminarButton;

	private CocheDTO coche;

	private JTable tabla;

	private CocheService cocheService = new CocheServiceImpl();

	public CocheButtonEditor(JCheckBox checkBox) {

		panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 0));

		editarButton = new JButton();

		editarButton.setIcon(new ImageIcon(CocheButtonEditor.class.getResource("/icons/16x16/usuarioedit.png")));

		eliminarButton = new JButton();

		eliminarButton.setIcon(new ImageIcon(CocheButtonEditor.class.getResource("/icons/16x16/basura.png")));

		panel.add(editarButton);

		panel.add(eliminarButton);

		editarButton.addActionListener(e -> {

			fireEditingStopped();

			try {

				CocheCreateView view = new CocheCreateView();

				view.setCocheDTO(coche);

				view.setEditable(true);

				view.setAgreeController(new CocheUpdateController(view));

				JFrame frame = new JFrame("Editar coche");

				frame.setContentPane(view);

				frame.setSize(800, 600);

				frame.setLocationRelativeTo(null);

				frame.setAlwaysOnTop(true);

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

				JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
			}
		});

		eliminarButton.addActionListener(e -> {

			fireEditingStopped();

			int opcion = JOptionPane.showConfirmDialog(null,
					"¿Seguro que deseas eliminar el coche " + coche.getMatricula() + "?", "Confirmar eliminación",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

			if (opcion == JOptionPane.YES_OPTION) {

				try {

					boolean eliminado = cocheService.delete(coche.getId());

					if (eliminado) {

						JOptionPane.showMessageDialog(null, "Coche eliminado correctamente.");

						CocheTableModel model = (CocheTableModel) tabla.getModel();

						model.getData().remove(coche);

						model.fireTableDataChanged();

					} else {

						JOptionPane.showMessageDialog(null,
								"No se puede eliminar el coche porque tiene órdenes de trabajo asociadas.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (Exception ex) {

					ex.printStackTrace();

					JOptionPane.showMessageDialog(null, "Error eliminando coche", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

		this.tabla = table;

		coche = ((CocheTableModel) table.getModel()).getData().get(row);

		return panel;
	}

	@Override
	public Object getCellEditorValue() {

		return null;
	}
}