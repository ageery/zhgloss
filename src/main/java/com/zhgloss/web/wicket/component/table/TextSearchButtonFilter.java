package com.zhgloss.web.wicket.component.table;

import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.AbstractFilter;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.wicketstuff.event.annotation.OnEvent;

import com.zhgloss.web.wicket.component.button.AbstractButton;
import com.zhgloss.web.wicket.component.button.SearchButton;
import com.zhgloss.web.wicket.event.ValidationErrorEvent;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormGroup.Size;

public class TextSearchButtonFilter<T> extends AbstractFilter {

    private TextField<T> filter;
    private FormGroup group;

    public TextSearchButtonFilter(final String id, final IModel<T> model, final FilterForm<?> form) {
        this(id, model, form, null);
    } 
    
    public TextSearchButtonFilter(final String id, final IModel<T> model, final FilterForm<?> form, IModel<String> titleModel) {
        super(id, form);
        
        group = new FormGroup("group")
        		.size(Size.Small)
        		.useFormComponentLabel(false);
        group.setOutputMarkupId(true);
        add(group);
        
        AbstractButton searchButton = new SearchButton("search");
        group.add(searchButton);
        filter = new TextField<>("filter", model);
        filter.setLabel(titleModel);

        group.add(filter);
        filter.setEscapeModelStrings(false);
        
        enableFocusTracking(filter);
        
        form.setDefaultButton(searchButton);
    }
    
    @OnEvent
    public void handleValidationErrorEvent(ValidationErrorEvent event) {
    	if (filter.hasErrorMessage()) {
    		event.getTarget().add(group);
    	}
    }

}
