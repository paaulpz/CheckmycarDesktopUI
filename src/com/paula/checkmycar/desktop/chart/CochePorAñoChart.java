package com.paula.checkmycar.desktop.chart;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.data.general.DefaultPieDataset;

import com.paula.checkmycar.desktop.views.View;
import com.toedter.calendar.JDateChooser;

public class CochePorAñoChart extends  View {
	public CochePorAñoChart() {
		initialize();
		postInitialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel centerPanel = new JPanel();
		add(centerPanel);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel charPanel = new JPanel();
		centerPanel.add(charPanel, BorderLayout.CENTER);
		charPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel filtrerPanel = new JPanel();
		charPanel.add(filtrerPanel, BorderLayout.NORTH);
		GridBagLayout gbl_filtrerPanel = new GridBagLayout();
		gbl_filtrerPanel.columnWidths = new int[]{100, 45, 92, 85, 0, 0, 0};
		gbl_filtrerPanel.rowHeights = new int[]{30, 0};
		gbl_filtrerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_filtrerPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		filtrerPanel.setLayout(gbl_filtrerPanel);
		
		JLabel fechaDesdeLabel = new JLabel("Fecha Desde: ");
		GridBagConstraints gbc_fechaDesdeLabel = new GridBagConstraints();
		gbc_fechaDesdeLabel.anchor = GridBagConstraints.WEST;
		gbc_fechaDesdeLabel.insets = new Insets(0, 0, 0, 5);
		gbc_fechaDesdeLabel.gridx = 0;
		gbc_fechaDesdeLabel.gridy = 0;
		filtrerPanel.add(fechaDesdeLabel, gbc_fechaDesdeLabel);
		
		JDateChooser fechaDesdeDateChooser = new JDateChooser();
		GridBagConstraints gbc_fechaDesdeDateChooser = new GridBagConstraints();
		gbc_fechaDesdeDateChooser.insets = new Insets(0, 0, 0, 5);
		gbc_fechaDesdeDateChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_fechaDesdeDateChooser.gridx = 1;
		gbc_fechaDesdeDateChooser.gridy = 0;
		filtrerPanel.add(fechaDesdeDateChooser, gbc_fechaDesdeDateChooser);
		
		JLabel fechaHastaLabel = new JLabel("Fecha Hasta: ");
		GridBagConstraints gbc_fechaHastaLabel = new GridBagConstraints();
		gbc_fechaHastaLabel.insets = new Insets(0, 0, 0, 5);
		gbc_fechaHastaLabel.gridx = 2;
		gbc_fechaHastaLabel.gridy = 0;
		filtrerPanel.add(fechaHastaLabel, gbc_fechaHastaLabel);
		
		JDateChooser fechaHastaDateChooser = new JDateChooser();
		GridBagConstraints gbc_fechaHastaDateChooser = new GridBagConstraints();
		gbc_fechaHastaDateChooser.insets = new Insets(0, 0, 0, 5);
		gbc_fechaHastaDateChooser.fill = GridBagConstraints.BOTH;
		gbc_fechaHastaDateChooser.gridx = 3;
		gbc_fechaHastaDateChooser.gridy = 0;
		filtrerPanel.add(fechaHastaDateChooser, gbc_fechaHastaDateChooser);
		
		JButton buscarButton = new JButton("Buscar ");
		buscarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date fechaDesde = fechaDesdeDateChooser.getDate();
				Date fechaHasta = fechaHastaDateChooser.getDate();
				// statisticsService.getCochesPorAño(fechaDesde, fechaHasta);
				DefaultPieDataset dataset = new DefaultPieDataset();
				
				
			}
		});
		buscarButton.setIcon(new ImageIcon(CochePorAñoChart.class.getResource("/nuvola/16x16/1320_kfind_kfind.png")));
		GridBagConstraints gbc_buscarButton = new GridBagConstraints();
		gbc_buscarButton.gridx = 5;
		gbc_buscarButton.gridy = 0;
		filtrerPanel.add(buscarButton, gbc_buscarButton);
	}
	
	private void postInitialize() {
	
	}

}
