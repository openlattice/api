package com.dataloom.client.serialization;

import java.io.Serializable;
import java.util.function.Function;

//@JsonTypeInfo( use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class" )
public interface SerializableFunction<I, O> extends Function<I, O>, Serializable {
}
