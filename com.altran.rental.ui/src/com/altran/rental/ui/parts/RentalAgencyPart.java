 
package com.altran.rental.ui.parts;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.altran.rental.ui.RentalUIConstants;
import com.opcoach.training.rental.RentalAgency;

public class RentalAgencyPart implements RentalUIConstants {
	
	@Inject
	private ESelectionService selectionService;
	private TreeViewer treeViewer;
	
	@PostConstruct
	public void createContent(Composite parent, RentalAgency agency, IEclipseContext context, EMenuService menuService) {	
		treeViewer = new TreeViewer(parent);
		RentalProvider rp = ContextInjectionFactory.make(RentalProvider.class, context);
		treeViewer.setLabelProvider(rp);
		treeViewer.setContentProvider(rp);
		Collection<RentalAgency> agencies = new ArrayList<RentalAgency>();
		agencies.add(agency);
		treeViewer.setInput(agencies);
		treeViewer.expandAll();
		
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				selectionService.setSelection(selection.getFirstElement());
			}
		});
		
		menuService.registerContextMenu(treeViewer.getControl(), "com.altran.rental.eap.popupmenu.popupmenu");
	}
	
	@Inject
	public void onColorChanged(
			@Preference(value=PREF_CUSTOMER_COLOR) String customerColor,
			@Preference(value=PREF_RENTAL_COLOR) String rentalColor,
			@Preference(value=PREF_RENTAL_OBJECT_COLOR) String rentalObjectColor) {
		if(this.treeViewer == null || treeViewer.getControl().isDisposed())
			return;
		this.treeViewer.refresh();
	}
	
}