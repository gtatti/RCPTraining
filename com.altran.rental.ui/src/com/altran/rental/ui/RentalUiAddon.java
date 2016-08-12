 
package com.altran.rental.ui;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.altran.rental.ui.palettes.Palette;
import com.opcoach.e4.preferences.ScopedPreferenceStore;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.core.helpers.RentalAgencyGenerator;


public class RentalUiAddon implements RentalUIConstants {

	@Inject
	Logger logger;
	private Map<String, Palette> palettes;
	
	@PostConstruct
	public void init(IEclipseContext context, IExtensionRegistry extensionRegistry) {
		RentalAgency rentalAgency = RentalAgencyGenerator.createSampleAgency();
		context.set(RentalAgency.class, rentalAgency);
		context.set(RENTAL_UI_IMG_REGISTRY, getLocalImageRegistry());
		IPreferenceStore preferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, PLUGIN_ID);
		context.set(RENTAL_UI_PREF_STORE, preferenceStore);
		palettes = readPalettes(context, extensionRegistry);
		context.set(PALETTE_MANAGER, palettes);
		String prefPaletteID = preferenceStore.getString(PREF_PALETTE);
		onPaletteChanged(prefPaletteID, context);
	}

	private Map<String, Palette> readPalettes(IEclipseContext context, IExtensionRegistry extensionRegistry) {
		Map<String, Palette> palettes = new HashMap<String, Palette>();
		for(IConfigurationElement configurationElement : extensionRegistry.getConfigurationElementsFor("com.altran.rental.ui.palette")) {
			String paletteClass = configurationElement.getAttribute("paletteClass");
			try {
				// New palette
				Palette palette = ContextInjectionFactory.make(Palette.class, context);
				// ID
				String id = configurationElement.getAttribute("id");
				palette.setId(id);
				// Name
				String name = configurationElement.getAttribute("name");
				palette.setName(name);
				// ColorProvider
				Bundle bundle = Platform.getBundle(configurationElement.getNamespaceIdentifier());
				Class<?> clazz = bundle.loadClass(paletteClass);
				IColorProvider provider = (IColorProvider)ContextInjectionFactory.make(clazz, context);
				palette.setProvider(provider);
				// Add palette to map
				palettes.put(id, palette);
			}
			catch(Exception e) {
				logger.error("Unable to create class "+paletteClass);
			}
		}
		return palettes;
	}
	
	ImageRegistry getLocalImageRegistry()
	{
		// Get the bundle using the universal method to get it from the current class
		Bundle b = FrameworkUtil.getBundle(getClass());  
		
		// Create a local image registry
		ImageRegistry reg = new ImageRegistry();

		// Then fill the values...
		reg.put(IMG_CUSTOMER, ImageDescriptor.createFromURL(b.getEntry(IMG_CUSTOMER)));
		reg.put(IMG_RENTAL, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL)));
		reg.put(IMG_RENTAL_OBJECT, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL_OBJECT)));
		reg.put(IMG_AGENCY, ImageDescriptor.createFromURL(b.getEntry(IMG_AGENCY)));
		//reg.put(IMG_COLLAPSE_ALL, ImageDescriptor.createFromURL(b.getEntry(IMG_COLLAPSE_ALL)));
		//reg.put(IMG_EXPAND_ALL, ImageDescriptor.createFromURL(b.getEntry(IMG_EXPAND_ALL)));

		return reg;
	}

	
	@Inject
	public void onPaletteChanged(
			@Preference(value=PREF_PALETTE) String paletteID,
			IEclipseContext context) {
		if(palettes == null)
			return;
		Palette palette = palettes.get(paletteID);
		context.set(Palette.class, palette);
	}
	

}
