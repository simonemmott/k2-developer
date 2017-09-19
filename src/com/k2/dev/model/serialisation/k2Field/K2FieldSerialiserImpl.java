package com.k2.dev.model.serialisation.k2Field;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.k2.common.serialisation.GenericSerialiser;
import com.k2.common.serialisation.Serialiser;
import com.k2.dev.model.K2Field;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class K2FieldSerialiserImpl extends GenericSerialiser<K2Field> implements Serialiser<K2Field>, K2FieldSerialiser {

}
