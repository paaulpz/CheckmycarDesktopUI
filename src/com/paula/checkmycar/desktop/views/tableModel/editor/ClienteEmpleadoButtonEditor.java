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

import com.paula.checkmc.model.ClienteDTO;
import com.paula.checkmc.service.ClienteService;
import com.paula.checkmc.service.impl.ClienteServiceImpl;
import com.paula.checkmycar.desktop.controller.ClienteSetEditableController;
import com.paula.checkmycar.desktop.controller.Controller;
import com.paula.checkmycar.desktop.views.ClienteCreateView;
import com.paula.checkmycar.desktop.views.tableModel.ClienteTableModel;

public class ClienteEmpleadoButtonEditor extends AbstractCellEditor implements TableCellEditor {

	private JPanel panel;
	private JButton editarButton;
	private JButton eliminarButton;
	private ClienteDTO cliente;
	private JTable tabla;
	private ClienteService clienteService = new ClienteServiceImpl();

	public ClienteEmpleadoButtonEditor(JCheckBox checkBox) {

		panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 0));

		editarButton = new JButton();
		editarButton.setIcon(new ImageIcon(ClienteEmpleadoButtonEditor.class.getResource("/icons/16x16/usuarioedit.png")));

		eliminarButton = new JButton();
		eliminarButton.setIcon(new ImageIcon(ClienteEmpleadoButtonEditor.class.getResource("/icons/16x16/basura.png")));

		panel.add(editarButton);
		panel.add(eliminarButton);

		editarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
				try {
					ClienteCreateView view = new ClienteCreateView();
					view.setClienteDTO(cliente);
					view.setEditable(false);
					view.setAgreeController(new ClienteSetEditableController(view));
					JFrame frame = new JFrame("Editar cliente");
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
			}
		});

		eliminarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				fireEditingStopped();

				int opcion = JOptionPane.showConfirmDialog(null,
						"¿Seguro que deseas eliminar al cliente " + cliente.getNombre() + " "
								+ cliente.getPrimerApellido() + "?",
						"Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (opcion == JOptionPane.YES_OPTION) {

					try {

						boolean eliminado = clienteService.delete(cliente.getId());

						if (eliminado) {

							JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente.");

							ClienteTableModel model = (ClienteTableModel) tabla.getModel();

							model.getClientes().remove(cliente);

							model.fireTableDataChanged();

						} else {

							JOptionPane.showMessageDialog(null,
									"No se puede eliminar el cliente porque tiene datos asociados.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}

					} catch (Exception ex) {

						ex.printStackTrace();

						JOptionPane.showMessageDialog(null, "Error eliminando cliente", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.tabla = table;
		cliente = ((ClienteTableModel) table.getModel()).getClientes().get(row);
		return panel;
	}

	@Override
	public Object getCellEditorValue() {
		return null;
	}
}