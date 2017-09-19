package com.k2.dev.model.serialisation.k2Entity;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.k2.common.serialisation.GenericSerialiser;
import com.k2.common.serialisation.Serialiser;
import com.k2.dev.model.K2Entity;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class K2EntitySerialiserImpl extends GenericSerialiser<K2Entity> implements Serialiser<K2Entity>, K2EntitySerialiser {

}
