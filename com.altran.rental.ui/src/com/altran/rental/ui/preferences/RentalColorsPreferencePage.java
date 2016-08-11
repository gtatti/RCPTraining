package com.altran.rental.ui.preferences;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;

import com.altran.rental.ui.RentalUIConstants;

public class RentalColorsPreferencePage extends FieldEditorPreferencePage implements RentalUIConstants {

	public RentalColorsPreferencePage() {
		super(GRID);
	}
	
	@Override
	protected void createFieldEditors() {
		addField(new ColorFieldEditor(PREF_CUSTOMER_COLOR, "Color for customers : ", getFieldEditorParent()));
		addField(new ColorFieldEditor(PREF_RENTAL_COLOR, "Color for rentals : ", getFieldEditorParent()));
		addField(new ColorFieldEditor(PREF_RENTAL_OBJECT_COLOR, "Color for rental objects : ", getFieldEditorParent()));
	}

}
