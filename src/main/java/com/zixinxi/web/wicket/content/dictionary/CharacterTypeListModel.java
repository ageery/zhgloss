package com.zixinxi.web.wicket.content.dictionary;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;

import com.zixinxi.domain.CharacterType;

public class CharacterTypeListModel extends LoadableDetachableModel<List<CharacterType>> {
	
	public CharacterTypeListModel() {
		super();
		Injector.get().inject(this);
	}
	
	@Override
	protected List<CharacterType> load() {
		return Arrays.asList(CharacterType.values());
	}

}
