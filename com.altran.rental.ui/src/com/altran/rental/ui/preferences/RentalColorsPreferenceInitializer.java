package com.altran.rental.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.RGB;

import com.altran.rental.ui.RentalUIConstants;
import com.opcoach.e4.preferences.ScopedPreferenceStore;

public class RentalColorsPreferenceInitializer extends AbstractPreferenceInitializer implements RentalUIConstants {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore preferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, PLUGIN_ID);
		preferenceStore.setDefault(PREF_CUSTOMER_COLOR, StringConverter.asString(new RGB(0, 0, 255)));
		preferenceStore.setDefault(PREF_RENTAL_COLOR, StringConverter.asString(new RGB(0, 255, 0)));
		preferenceStore.setDefault(PREF_RENTAL_OBJECT_COLOR, StringConverter.asString(new RGB(255, 0, 0)));
	}

}
