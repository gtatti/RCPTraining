 
package com.altran.rental.ui.parts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.altran.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Rental;

public class RentalPropertyPart {
	
	private Label productLabel;
	private Label rentedToLabel;
	private Label customerLabel;
	private Group dateGroup;
	
	@Inject
	public RentalPropertyPart() {
		
	}
	
	@PreDestroy
	public void preDestroy() {
		
	}
	
	
	@Focus
	public void onFocus() {
		
	}
	
	@PostConstruct
	public void createContent(Composite parent) {	
		parent.setLayout(new GridLayout(1, false));
		
		Group infoGroup = new Group(parent, SWT.NONE);
		infoGroup.setText("Informations");
		infoGroup.setLayout(new GridLayout(2, false));
		
		productLabel = new Label(infoGroup, SWT.BORDER);
		GridData productGd = new GridData();
		productGd.horizontalSpan = 2;
		productLabel.setLayoutData(productGd);
		
		rentedToLabel = new Label(infoGroup, SWT.BORDER);
		rentedToLabel.setText("Loué à : ");
		
		customerLabel = new Label(infoGroup, SWT.BORDER);
		
		dateGroup = new Group(parent, SWT.NONE);
		dateGroup.setText("Dates de location");
		dateGroup.setLayout(new GridLayout(2, false));
		
		Label startLabel = new Label(dateGroup, SWT.NONE);
		startLabel.setText("du : ");
		
		Label startDatelabel = new Label(dateGroup, SWT.NONE);
		startDatelabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		startDatelabel.setText("New Label");
		
		Label endLabel = new Label(dateGroup, SWT.NONE);
		endLabel.setSize(22, 15);
		endLabel.setText("au : ");
		
		Label endDateLabel = new Label(dateGroup, SWT.NONE);
		endDateLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		endDateLabel.setText("New Label");
		
		this.setRental(RentalCoreActivator.getRentalAgency().getRentals().get(1));
	}
	
	public void setRental(Rental rental) {
		productLabel.setText(rental.getRentedObject().getName());
		customerLabel.setText(rental.getCustomer().getDisplayName());
	}
}