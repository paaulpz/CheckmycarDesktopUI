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

import com.paula.checkmc.model.CitaDTO;
import com.paula.checkmc.service.CitaService;
import com.paula.checkmc.service.impl.CitaServiceImpl;
import com.paula.checkmycar.desktop.controller.CitaSetEditableController;
import com.paula.checkmycar.desktop.controller.Controller;
import com.paula.checkmycar.desktop.views.CitaCreateView;
import com.paula.checkmycar.desktop.views.tableModel.CitasTableModel;

public class CitaButtonEditor
		extends AbstractCellEditor
		implements TableCellEditor {

	private JPanel panel;

	private JButton editarButton;

	private JButton eliminarButton;

	private CitaDTO cita;

	private JTable tabla;

	private CitaService citaService =
			new CitaServiceImpl();

	public CitaButtonEditor(JCheckBox checkBox) {

		panel = new JPanel(
				new FlowLayout(
						FlowLayout.CENTER,
						2,
						0));

		editarButton = new JButton();

		editarButton.setIcon(
				new ImageIcon(
						CitaButtonEditor.class.getResource(
								"/nuvola/16x16/1819_pencil_pencil.png")));

		eliminarButton = new JButton();

		eliminarButton.setIcon(
				new ImageIcon(
						CitaButtonEditor.class.getResource(
								"/nuvola/16x16/1815_no_no.png")));

		panel.add(editarButton);

		panel.add(eliminarButton);

		editarButton.addActionListener(
				new ActionListener() {

			@Override
			public void actionPerformed(
					ActionEvent e) {

				fireEditingStopped();

				try {

					CitaCreateView view =
							new CitaCreateView();

					view.setCitaDTO(cita);

					view.setEditable(false);

					view.setAgreeController(
							new CitaSetEditableController(
									view));

					JFrame frame =
							new JFrame(
									"Editar cita");

					frame.setContentPane(view);

					frame.setSize(900, 600);

					frame.setLocationRelativeTo(null);

					view.setCancelController(
							new Controller(
									view,
									"Cancelar") {

						@Override
						public void doAction() {

							frame.dispose();
						}

						@Override
						public void actionPerformed(
								ActionEvent e) {

							doAction();
						}
					});

					frame.setVisible(true);

				} catch (Exception ex) {

					ex.printStackTrace();

					JOptionPane.showMessageDialog(
							null,
							"Error abriendo la cita");
				}
			}
		});

		eliminarButton.addActionListener(
				new ActionListener() {

			@Override
			public void actionPerformed(
					ActionEvent e) {

				fireEditingStopped();

				int opcion =
						JOptionPane.showConfirmDialog(
								null,
								"¿Deseas eliminar la cita seleccionada?",
								"Confirmar",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.WARNING_MESSAGE);

				if (opcion == JOptionPane.YES_OPTION) {

					try {

						boolean eliminado =
								citaService.delete(
										cita.getId());

						if (eliminado) {

							CitasTableModel model =
									(CitasTableModel)
									tabla.getModel();

							model.getCitas()
									.remove(cita);

							model.fireTableDataChanged();

							JOptionPane.showMessageDialog(
									null,
									"Cita eliminada correctamente.");

						} else {

							JOptionPane.showMessageDialog(
									null,
									"No se pudo eliminar la cita.",
									"Error",
									JOptionPane.ERROR_MESSAGE);
						}

					} catch (Exception ex) {

						ex.printStackTrace();

						JOptionPane.showMessageDialog(
								null,
								"Error eliminando cita",
								"Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	@Override
	public Component getTableCellEditorComponent(
			JTable table,
			Object value,
			boolean isSelected,
			int row,
			int column) {

		this.tabla = table;

		CitasTableModel model =
				(CitasTableModel) table.getModel();

		cita = model.getRow(row);

		return panel;
	}

	@Override
	public Object getCellEditorValue() {

		return null;
	}
}