 
package com.altran.rental.ui.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;

public class RentalPropertyPart {
	
	private Label productLabel;
	private Label rentedToLabel;
	private Label customerLabel;
	private Label startLabel;
	private Label startDateLabel;
	private Label endLabel;
	private Label endDateLabel;
	private Group dateGroup;
	
	
	
	
	@PostConstruct
	public void createContent(Composite parent, RentalAgency agency) {	
		parent.setLayout(new GridLayout(1, false));
		
		Group infoGroup = new Group(parent, SWT.NONE);
		infoGroup.setText("Informations");
		infoGroup.setLayout(new GridLayout(2, false));
		
		productLabel = new Label(infoGroup, SWT.BORDER);
		productLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		
		rentedToLabel = new Label(infoGroup, SWT.BORDER);
		rentedToLabel.setText("Loué à : ");
		
		customerLabel = new Label(infoGroup, SWT.BORDER);
		
		dateGroup = new Group(parent, SWT.NONE);
		dateGroup.setText("Dates de location");
		dateGroup.setLayout(new GridLayout(2, false));
		
		startLabel = new Label(dateGroup, SWT.NONE);
		startLabel.setText("du : ");
		
		startDateLabel = new Label(dateGroup, SWT.NONE);
		startDateLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		endLabel = new Label(dateGroup, SWT.NONE);
		endLabel.setSize(22, 15);
		endLabel.setText("au : ");
		
		endDateLabel = new Label(dateGroup, SWT.NONE);
		endDateLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		this.setRental(agency.getRentals().get(1));
	}
	
	public void setRental(Rental rental) {
		if(productLabel == null)
			return;
		productLabel.setText(rental.getRentedObject().getName());
		customerLabel.setText(rental.getCustomer().getDisplayName());
		startDateLabel.setText(rental.getStartDate().toString());
		endDateLabel.setText(rental.getEndDate().toString());
	}
	
	@Inject @Optional
	public void onRentalChanged(@Named(IServiceConstants.ACTIVE_SELECTION) Rental rental) {
		this.setRental(rental);
	}
	
}