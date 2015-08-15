package com.zhgloss.repo;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

import com.zhgloss.domain.external.CedictLoadInfo;

public interface CedictWordRepo {

	void load();
	
	Optional<LocalDateTime> getLastLoad();
	
	Stream<CedictLoadInfo> getLoadList(int offset, int limit);
	
}
