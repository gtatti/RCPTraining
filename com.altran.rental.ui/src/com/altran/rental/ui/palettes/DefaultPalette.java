package com.altran.rental.ui.palettes;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.graphics.Color;

import com.altran.rental.ui.RentalUIConstants;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalObject;

public class DefaultPalette implements IColorProvider, RentalUIConstants {

	public DefaultPalette() {
		// TODO Auto-generated constructor stub
	}

	@Inject @Named(RENTAL_UI_PREF_STORE)
	IPreferenceStore preferenceStore;
	
	@Override
	public Color getForeground(Object element) {
		if(element instanceof Customer)
			return getAColor(PREF_CUSTOMER_COLOR);
		if(element instanceof Rental)
			return getAColor(PREF_RENTAL_COLOR);
		if(element instanceof RentalObject)
			return getAColor(PREF_RENTAL_OBJECT_COLOR);
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	private Color getAColor(String preferenceKey) {
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
		String rgbKey = preferenceStore.getString(preferenceKey);
		Color color = colorRegistry.get(rgbKey);
		if(color == null) {
			colorRegistry.put(rgbKey, StringConverter.asRGB(rgbKey));
			color = colorRegistry.get(rgbKey); 
		}
		return color;
	}

}
