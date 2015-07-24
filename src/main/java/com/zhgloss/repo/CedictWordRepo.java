package com.zhgloss.repo;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CedictWordRepo {

	void load();
	
	Optional<LocalDateTime> getLastLoad();
	
}
