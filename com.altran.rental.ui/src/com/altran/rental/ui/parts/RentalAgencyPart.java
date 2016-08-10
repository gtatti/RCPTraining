 
package com.altran.rental.ui.parts;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.opcoach.training.rental.RentalAgency;

public class RentalAgencyPart {
	
	@Inject
	private ESelectionService selectionService;
	
	@PostConstruct
	public void createContent(Composite parent, RentalAgency agency, IEclipseContext context) {	
		TreeViewer treeViewer = new TreeViewer(parent);
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
	}
	
}