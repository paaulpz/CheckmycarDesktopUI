package com.paula.checkmycar.desktop.views;
import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class swinXtextview extends View  {
	
	 private JComboBox paisCB = null;
	
	public swinXtextview() {
		initialize(); 
		postInitialize();
	}
	
	private void initialize(){
	setLayout(new BorderLayout(0, 0));
		
		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		
		
		
		// esto lo cambiamos por el render 
		paisCB = new JComboBox(new String[] { "Ruben" , "Paula" , "Juan", "Toñin", "Sabic", "Alan"});
		centerPanel.add(paisCB);
	}
	
	private void postInitialize() {
		
		AutoCompleteDecorator.decorate(paisCB);
		
	}

}
