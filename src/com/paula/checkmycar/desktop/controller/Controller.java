package com.paula.checkmycar.desktop.controller;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import com.paula.checkmycar.desktop.views.View;

public abstract class Controller extends AbstractAction {
	
	private View view = null;
	
	public Controller(View view) {
		this(view, null);
	}
	
	public Controller(View view, String name) {
		this(view, name, null);
	}
	
	public Controller(View view, String name, Icon icon) {
		super(name, icon);
		this.view = view;
	}
	
	public View getView() {
		return this.view;
	}
	
	public abstract void doAction();

}

