package com.zixinxi.web.wicket.content.transcription;

import static org.wicketstuff.lazymodel.LazyModel.from;
import static org.wicketstuff.lazymodel.LazyModel.model;

import org.wicketstuff.lazymodel.LazyModel;

import com.zixinxi.domain.external.TranscriptionPlane;

public final class TranscriptionPlaneModels {

	public static final LazyModel<Integer> LM_TONE = model(from(TranscriptionPlane.class).getTone());
}
