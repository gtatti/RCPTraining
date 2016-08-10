package com.altran.rental.ui.parts;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.altran.rental.ui.RentalUIConstants;
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
		
	}

	@Override
	public Color getForeground(Object element) {
		if(element instanceof Customer)
			return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		if(element instanceof Customer)
			return Display.getCurrent().getSystemColor(SWT.COLOR_YELLOW);
		return null;
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

}
