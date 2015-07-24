package com.zhgloss.domain;

import java.io.Serializable;
import java.util.function.BiConsumer;

public interface SerializableBiConsumer<T, U> extends Serializable, BiConsumer<T, U> {

}
