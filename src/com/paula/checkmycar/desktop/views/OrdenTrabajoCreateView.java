package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.ZoneId;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.paula.checkmc.model.CocheCriteria;
import com.paula.checkmc.model.CocheDTO;
import com.paula.checkmc.model.OrdenTrabajoDTO;
import com.paula.checkmc.model.PresupuestoCriteria;
import com.paula.checkmc.model.PresupuestoDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.CocheService;
import com.paula.checkmc.service.PresupuestoService;
import com.paula.checkmc.service.impl.CocheServiceImpl;
import com.paula.checkmc.service.impl.PresupuestoServiceImpl;
import com.paula.checkmycar.desktop.controller.Controller;
import com.paula.checkmycar.desktop.views.renderer.PresupuestoCBRenderer;
import com.toedter.calendar.JDateChooser;

public class OrdenTrabajoCreateView extends View {

	private JTextArea diagnosticoTA;

	private JDateChooser fechaInicioChooser;

	private JDateChooser fechaFinChooser;

	private JComboBox<PresupuestoDTO> presupuestoCB;

	private JTextField matriculaTF;

	private JButton guardarButton;

	private JButton cancelarButton;

	private Long ordenTrabajoId;

	private Long cocheId;

	private PresupuestoService presupuestoService;

	private CocheService cocheService;

	public OrdenTrabajoCreateView() {

		presupuestoService = new PresupuestoServiceImpl();

		cocheService = new CocheServiceImpl();

		initialize();

		postInitialize();
	}

	private void initialize() {

		setName("Crear orden trabajo");

		setLayout(new BorderLayout());

		JPanel formPanel = new JPanel(new GridBagLayout());

		add(formPanel, BorderLayout.NORTH);

		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(5, 5, 5, 5);

		c.fill = GridBagConstraints.HORIZONTAL;

		int y = 0;

		c.gridx = 0;
		c.gridy = y;

		formPanel.add(new JLabel("Diagnóstico *:"), c);

		diagnosticoTA = new JTextArea(4, 25);

		JScrollPane diagnosticoSP = new JScrollPane(diagnosticoTA);

		c.gridx = 1;
		c.gridwidth = 3;

		formPanel.add(diagnosticoSP, c);

		y++;

		c.gridwidth = 1;

		c.gridx = 0;
		c.gridy = y;

		formPanel.add(new JLabel("Fecha inicio *:"), c);

		fechaInicioChooser = new JDateChooser();

		fechaInicioChooser.setDateFormatString("dd/MM/yyyy");

		c.gridx = 1;

		formPanel.add(fechaInicioChooser, c);

		c.gridx = 2;

		formPanel.add(new JLabel("Fecha fin:"), c);

		fechaFinChooser = new JDateChooser();

		fechaFinChooser.setDateFormatString("dd/MM/yyyy");

		c.gridx = 3;

		formPanel.add(fechaFinChooser, c);

		y++;

		c.gridx = 0;
		c.gridy = y;

		formPanel.add(new JLabel("Presupuesto *:"), c);

		presupuestoCB = new JComboBox<>();

		c.gridx = 1;

		formPanel.add(presupuestoCB, c);

		c.gridx = 2;

		formPanel.add(new JLabel("Matrícula coche *:"), c);

		matriculaTF = new JTextField();

		c.gridx = 3;

		formPanel.add(matriculaTF, c);

		JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		add(botonesPanel, BorderLayout.SOUTH);

		cancelarButton = new JButton("Cancelar");

		guardarButton = new JButton("Guardar");

		botonesPanel.add(cancelarButton);

		botonesPanel.add(guardarButton);
	}

	private void postInitialize() {

		try {

			presupuestoCB.setRenderer(new PresupuestoCBRenderer());

			cargarPresupuestos();

		} catch (Exception e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(this, "Error cargando datos", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cargarPresupuestos() throws Exception {

		Results<PresupuestoDTO> results = presupuestoService.findByCriteria(new PresupuestoCriteria(), 1, 1000);

		DefaultComboBoxModel<PresupuestoDTO> model = new DefaultComboBoxModel<>();

		PresupuestoDTO vacio = new PresupuestoDTO();

		vacio.setId(null);

		model.addElement(vacio);

		for (PresupuestoDTO presupuesto : results.getPage()) {

			model.addElement(presupuesto);
		}

		presupuestoCB.setModel(model);
	}

	public OrdenTrabajoDTO getModel() {

		OrdenTrabajoDTO dto = new OrdenTrabajoDTO();

		dto.setId(ordenTrabajoId);

		dto.setDiagnostico(diagnosticoTA.getText().trim());

		if (fechaInicioChooser.getDate() != null) {

			dto.setFechaInicio(fechaInicioChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		}

		if (fechaFinChooser.getDate() != null) {

			dto.setFechaFin(fechaFinChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		}

		PresupuestoDTO presupuesto = (PresupuestoDTO) presupuestoCB.getSelectedItem();

		if (presupuesto != null && presupuesto.getId() != null) {

			dto.setPresupuestoId(presupuesto.getId());
		}

		String matricula = matriculaTF.getText().trim();

		if (!matricula.isEmpty()) {

			try {

				CocheCriteria criteria = new CocheCriteria();

				criteria.setMatricula(matricula);

				Results<CocheDTO> results = cocheService.findByCriteria(criteria, 1, 1);

				if (!results.getPage().isEmpty()) {

					CocheDTO coche = results.getPage().get(0);

					cocheId = coche.getId();

					dto.setCocheId(cocheId);

					dto.setMatriculaCoche(coche.getMatricula());
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public void setOrdenTrabajoDTO(OrdenTrabajoDTO dto) {

		ordenTrabajoId = dto.getId();

		diagnosticoTA.setText(dto.getDiagnostico());

		if (dto.getFechaInicio() != null) {

			java.util.Date fechaInicio = java.util.Date
					.from(dto.getFechaInicio().atStartOfDay(ZoneId.systemDefault()).toInstant());

			fechaInicioChooser.setDate(fechaInicio);
		}

		if (dto.getFechaFin() != null) {

			java.util.Date fechaFin = java.util.Date
					.from(dto.getFechaFin().atStartOfDay(ZoneId.systemDefault()).toInstant());

			fechaFinChooser.setDate(fechaFin);
		}

		for (int i = 0; i < presupuestoCB.getItemCount(); i++) {

			PresupuestoDTO presupuesto = presupuestoCB.getItemAt(i);

			if (presupuesto.getId() != null && presupuesto.getId().equals(dto.getPresupuestoId())) {

				presupuestoCB.setSelectedIndex(i);

				break;
			}
		}

		if (dto.getMatriculaCoche() != null) {

			matriculaTF.setText(dto.getMatriculaCoche());
		}
	}

	public void setEditable(boolean editable) {

		diagnosticoTA.setEditable(editable);

		fechaInicioChooser.setEnabled(editable);

		fechaFinChooser.setEnabled(editable);

		presupuestoCB.setEnabled(editable);

		matriculaTF.setEditable(editable);
	}

	public void setAgreeController(Controller controller) {

		guardarButton.setAction(controller);
	}

	public void setCancelController(Controller controller) {

		cancelarButton.setAction(controller);
	}
}