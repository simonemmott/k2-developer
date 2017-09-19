package com.k2.dev.model.serialisation.k2SnippetParameter;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.k2.common.serialisation.GenericSerialiser;
import com.k2.common.serialisation.Serialiser;
import com.k2.dev.model.K2SnippetParameter;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class K2SnippetParameterSerialiserImpl extends GenericSerialiser<K2SnippetParameter> implements Serialiser<K2SnippetParameter> {

}
