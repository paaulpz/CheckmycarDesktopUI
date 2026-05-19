package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.paula.checkmycar.desktop.main.CheckmycarWindow;
import com.paula.checkmycar.desktop.views.PresupuestoCreateView;

public class PresupuestoOpenCreateController implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		try {

			PresupuestoCreateView view = new PresupuestoCreateView();

			view.setAgreeController(new PresupuestoCreateController(view));

			CheckmycarWindow.getInstance().addView(view.getName(), view);

		} catch (Exception ex) {

			ex.printStackTrace();

			JOptionPane.showMessageDialog(null, "Error abriendo presupuesto", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}