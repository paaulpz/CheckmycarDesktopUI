package com.paula.checkmycar.desktop.views;

import javax.swing.JPanel;

public abstract class View extends JPanel{
	

	private String name = null;
	
	public View() {		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
