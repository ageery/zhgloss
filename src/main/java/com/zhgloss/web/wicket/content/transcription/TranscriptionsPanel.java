package com.zhgloss.web.wicket.content.transcription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.zhgloss.domain.OptionalFunction;
import com.zhgloss.domain.external.TranscriptionPlane;
import com.zhgloss.service.TranscriptionService;
import com.zhgloss.web.wicket.component.table.BootstrapDataTable;
import com.zhgloss.web.wicket.component.table.FunctionColumn;

import de.agilecoders.wicket.core.markup.html.bootstrap.heading.Heading;

public class TranscriptionsPanel extends Panel {
	
	@SpringBean
	private TranscriptionService service;
	
	
	public TranscriptionsPanel(String id) {
		super(id);
		
		add(new Heading("header", new ResourceModel("transcriptions.header")));
		add(new Label("description", new ResourceModel("transcriptions.description")));
		
		add(new BootstrapDataTable<>("table", getColumns(), new TranscriptionPlaneDataSource(), 3000));
		
	}
	
	private List<IColumn<TranscriptionPlane, Void>> getColumns() {
		
		List<IColumn<TranscriptionPlane, Void>> list = new ArrayList<>(Arrays.asList(new FunctionColumn<>(Model.of("Syllable"), new OptionalFunction<>(TranscriptionPlane.FUNCTION_SYLLABLE_NAME)),
				new FunctionColumn<>(Model.of("Tone"), new OptionalFunction<>(TranscriptionPlane.FUNCTION_TONE))
		));
		list.addAll(service.getTranscriptionSystems()
			.map(ts -> new TranscriptionPointColumn<Void>(Model.of(ts.getName()), ts.getCode()))
			.collect(Collectors.toList()));
		return list;
	}
	
	private static class TranscriptionPlaneDataSource extends SortableDataProvider<TranscriptionPlane, Void> {

		@SpringBean
		private TranscriptionService service;
		
		public TranscriptionPlaneDataSource() {
			super();
			Injector.get().inject(this);
		}
		
		@Override
		public Iterator<? extends TranscriptionPlane> iterator(long first,
				long count) {
			return service.getTranscriptionPlanes().iterator();
		}

		@Override
		public long size() {
			return 3000;
		}

		@Override
		public IModel<TranscriptionPlane> model(TranscriptionPlane object) {
			return Model.of(object);
		}
		
	}
	
}
