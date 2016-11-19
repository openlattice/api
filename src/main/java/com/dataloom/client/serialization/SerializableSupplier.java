package com.dataloom.client.serialization;

import java.io.Serializable;
import java.util.function.Supplier;

public interface SerializableSupplier<T> extends Supplier<T>, Serializable {

}
