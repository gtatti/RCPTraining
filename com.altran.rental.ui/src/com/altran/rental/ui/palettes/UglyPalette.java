package com.altran.rental.ui.palettes;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalObject;

public class UglyPalette implements IColorProvider {

	public UglyPalette() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getForeground(Object element) {
		//if(element instanceof Customer)
			return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
		/*if(element instanceof Rental)
			return Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
		if(element instanceof RentalObject)
			return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
		return null;*/
	}

	@Override
	public Color getBackground(Object element) {
		return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
		//return null;
	}

}
