package com.paula.checkmycar.desktop.controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import com.paula.checkmc.model.EmpleadoCriteria;
import com.paula.checkmc.model.EmpleadoDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.model.Rol;
import com.paula.checkmc.service.impl.EmpleadoServiceImpl;
import com.paula.checkmycar.desktop.views.EmpleadoSearchView;

public class EmpleadoSearchController extends AbstractAction {


	private EmpleadoSearchView view; 
	private EmpleadoServiceImpl empleadoService ; 
	
	public EmpleadoSearchController (EmpleadoSearchView view) {
		this.view = view ; 
		this.empleadoService = new EmpleadoServiceImpl(); 
		
	}
	
	public void actionPerformed(ActionEvent e) {
	    EmpleadoCriteria criteria = new EmpleadoCriteria();

	    if (view.getDni() != null && !view.getDni().trim().isEmpty()) {
	        criteria.setDniNie(view.getDni().trim());
	    }
	    
	    if (view.getEmail() != null && !view.getEmail().trim().isEmpty()) {
	        criteria.setEmail(view.getEmail().trim());
	    }
	    if (view.getNombre() != null && !view.getNombre().trim().isEmpty()) {
	        criteria.setNombre(view.getNombre().trim());
	    }
	    Rol rol = view.getRol();
	    if (rol != null && rol.getId() != null) {
	        criteria.setNombreRol(rol.getNombre());
	    }
	   
	    try {
	        Results<EmpleadoDTO> results = empleadoService.findByCriteria(criteria, 1, 100);
	        

	        
	        if (results == null || results.getPage() == null) {
	            view.setTableData(new ArrayList<>());
	            JOptionPane.showMessageDialog(null, "No se encontraron empleados.");
	            return;
	        }

	        List<EmpleadoDTO> lista = results.getPage();
	        view.setTableData(lista);

	        if (lista.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "No se encontraron empleados con esos criterios.");
	        }

	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(null,
	            "Error al buscar empleados: " + ex.getMessage(),
	            "Error", JOptionPane.ERROR_MESSAGE);
	        ex.printStackTrace(); 
	    }
	}
}