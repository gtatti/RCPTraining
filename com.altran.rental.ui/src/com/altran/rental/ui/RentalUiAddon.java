 
package com.altran.rental.ui;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.contexts.IEclipseContext;

import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.core.helpers.RentalAgencyGenerator;


public class RentalUiAddon {

	@PostConstruct
	public void init(IEclipseContext context) {
		RentalAgency rentalAgency = RentalAgencyGenerator.createSampleAgency();
		context.set(RentalAgency.class, rentalAgency);
	}

}
