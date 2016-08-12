package com.altran.rental.ui.palettes;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.graphics.Color;

public class Palette implements IColorProvider {
	
	String id;
	String name;
	IColorProvider provider;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setProvider(IColorProvider provider) {
		this.provider = provider;
	}
	@Override
	public Color getForeground(Object element) {
		return provider.getForeground(element);
	}
	@Override
	public Color getBackground(Object element) {
		return provider.getBackground(element);
	}
	

}
