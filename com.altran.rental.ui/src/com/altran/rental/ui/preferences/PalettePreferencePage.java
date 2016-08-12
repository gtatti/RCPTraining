package com.altran.rental.ui.preferences;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;

import com.altran.rental.ui.RentalUIConstants;
import com.altran.rental.ui.palettes.Palette;

public class PalettePreferencePage extends FieldEditorPreferencePage implements RentalUIConstants {

	public PalettePreferencePage() {
		super(GRID);
	}

	@Inject @Named(PALETTE_MANAGER)
	private Map<String, Palette> palettes;
	
	@Override
	protected void createFieldEditors() {
		String[][] entryNamesAndValues = new String[palettes.size()][2];
		int i = 0;
		for(Palette palette : palettes.values()) {
			entryNamesAndValues[i][0] = palette.getName();
			entryNamesAndValues[i][1] = palette.getId();
			i++;
		}
		addField(new ComboFieldEditor(PREF_PALETTE, "Palette : ", entryNamesAndValues, getFieldEditorParent()));
	}

}
