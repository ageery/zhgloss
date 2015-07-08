package com.zixinxi.web.wicket.content.transcription;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import com.zixinxi.domain.external.TranscriptionPlane;
import com.zixinxi.web.wicket.content.lookup.LookupPage;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;

public class TranscriptionPointColumn<S> extends AbstractColumn<TranscriptionPlane, S> {

	private String transcriptionSystemCode;
	
	public TranscriptionPointColumn(IModel<String> displayModel, String transcriptionSystemCode) {
		super(displayModel);
		this.transcriptionSystemCode = transcriptionSystemCode;
	}

	@Override
	public void populateItem(Item<ICellPopulator<TranscriptionPlane>> cellItem, String componentId, IModel<TranscriptionPlane> rowModel) {
		cellItem.add(new LookupPageLink(componentId, rowModel));
	}

	private class TranscriptionPointModel extends LoadableDetachableModel<String> {
		
		private IModel<TranscriptionPlane> planeModel;
		
		public TranscriptionPointModel(IModel<TranscriptionPlane> planeModel) {
			super();
			this.planeModel = planeModel;
		}

		@Override
		protected String load() {
			return planeModel.getObject().getTonedRepresentationMapper().apply(transcriptionSystemCode);
		}
		
	}
	
	private class LookupPageLink extends Panel {

		public LookupPageLink(String id, IModel<TranscriptionPlane> rowModel) {
			super(id);
			TranscriptionPlane tp = rowModel.getObject();
			add(new BootstrapBookmarkablePageLink<TranscriptionPlane>("link", LookupPage.class, LookupPage.getPageParameters(transcriptionSystemCode, tp.getSyllableName() + tp.getTone()), Buttons.Type.Link)
					.setLabel( new TranscriptionPointModel(rowModel)));
		}
		
	}
	
}
