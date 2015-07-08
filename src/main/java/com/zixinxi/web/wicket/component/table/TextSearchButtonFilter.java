package com.zixinxi.web.wicket.component.table;

import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.AbstractFilter;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.wicketstuff.event.annotation.OnEvent;

import com.zixinxi.web.wicket.component.button.AbstractButton;
import com.zixinxi.web.wicket.component.button.ClearButton;
import com.zixinxi.web.wicket.component.button.SearchButton;
import com.zixinxi.web.wicket.event.ClearEvent;
import com.zixinxi.web.wicket.event.ValidationErrorEvent;

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
        
        group.add(new ClearButton("clear"));
        
        AbstractButton searchButton = new SearchButton("search");
        group.add(searchButton);
        filter = new TextField<>("filter", model);
        filter.setLabel(titleModel);

        group.add(filter);
        filter.setEscapeModelStrings(false);
        
        enableFocusTracking(filter);
    }
    
    @OnEvent
    public void handleValidationErrorEvent(ValidationErrorEvent event) {
    	if (filter.hasErrorMessage()) {
    		event.getTarget().add(group);
    	}
    }
    
    @OnEvent
    public void handleClearEvent(ClearEvent event) {
    	filter.setModelObject(null);
    	event.getTarget().add(group);
    }

}
