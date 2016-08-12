package com.altran.rental.ui.parts;

import java.util.Collection;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import com.altran.rental.ui.RentalUIConstants;
import com.altran.rental.ui.palettes.Palette;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider, RentalUIConstants {
	
	@Override
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof Collection) {
			return ((Collection<?>) inputElement).toArray();			
		}
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof RentalAgency) {
			RentalAgency agency = (RentalAgency)parentElement;
			return new Node[] {
				new Node(Node.CUSTOMERS, agency),
				new Node(Node.RENTAL_OBJECTS, agency),
				new Node(Node.RENTALS, agency)
			};
		}
		if(parentElement instanceof Node) {
			return ((Node) parentElement).getChildren();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if(element instanceof RentalAgency) {
			return true;
		}
		if(element instanceof Node) {
			return true;
		}
		return false;
	}
	
	@Override
	public String getText(Object element) {
		// TODO Auto-generated method stub
		if(element instanceof RentalAgency) {
			return ((RentalAgency) element).getName();
		}
		if(element instanceof Customer) {
			return ((Customer) element).getDisplayName();
		}
		if(element instanceof RentalObject) {
			return ((RentalObject) element).getName();
		}
		return super.getText(element);
	}
	
	class Node {
		
		public static final String CUSTOMERS = "Customers";
		public static final String RENTALS = "Rentals";
		public static final String RENTAL_OBJECTS = "Rental Objects";
		
		private String label;
		private RentalAgency agency;
		
		public Node(String label, RentalAgency agency) {
			super();
			this.label = label;
			this.agency = agency;
		}
		
		public Object[] getChildren() {
			if(label == CUSTOMERS) {
				return this.agency.getCustomers().toArray();
			}
			if(label == RENTALS) {
				return this.agency.getRentals().toArray();
			}
			if(label == RENTAL_OBJECTS) {
				return this.agency.getObjectsToRent().toArray();
			}
			return null;
		}
		
		@Override
		public String toString() {
			return this.label;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((agency == null) ? 0 : agency.hashCode());
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (agency == null) {
				if (other.agency != null)
					return false;
			} else if (!agency.equals(other.agency))
				return false;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			return true;
		}

		private RentalProvider getOuterType() {
			return RentalProvider.this;
		}
		
		
	}

	@Inject @Named(RENTAL_UI_PREF_STORE)
	IPreferenceStore preferenceStore;
	
	@Inject
	private Palette palette;
		
	@Override
	public Color getForeground(Object element) {
		return (palette == null) ? null : palette.getForeground(element);
	}
	
	@Override
	public Color getBackground(Object element) {
		return (palette == null) ? null : palette.getBackground(element);
	}
	
	@Inject @Named(RENTAL_UI_IMG_REGISTRY)
	private ImageRegistry imgRegistry;
	
	@Override
	public Image getImage(Object element) {
		if(element instanceof Customer)
			return imgRegistry.get(IMG_CUSTOMER);
		if(element instanceof Rental)
			return imgRegistry.get(IMG_RENTAL);
		if(element instanceof RentalObject)
			return imgRegistry.get(IMG_RENTAL_OBJECT);
		return super.getImage(element);
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
