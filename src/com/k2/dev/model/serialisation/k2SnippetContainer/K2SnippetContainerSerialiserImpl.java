package com.k2.dev.model.serialisation.k2SnippetContainer;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.k2.common.serialisation.GenericSerialiser;
import com.k2.common.serialisation.Serialiser;
import com.k2.dev.model.K2SnippetContainer;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class K2SnippetContainerSerialiserImpl extends GenericSerialiser<K2SnippetContainer> implements Serialiser<K2SnippetContainer> {

}
