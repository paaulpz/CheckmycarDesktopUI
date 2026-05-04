package com.paula.checkmycar.desktop.views;

import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

import com.paula.checkmc.model.ClienteDTO;

public class ClienteToStringConverter  extends ObjectToStringConverter{
	
	
	@Override
	public String getPreferredStringForItem(Object item) {
		ClienteDTO c = (ClienteDTO) item;
		return c.getDniNie();
	}

}
