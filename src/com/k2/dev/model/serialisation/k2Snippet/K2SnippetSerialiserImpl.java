package com.k2.dev.model.serialisation.k2Snippet;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.k2.common.serialisation.GenericSerialiser;
import com.k2.common.serialisation.Serialiser;
import com.k2.dev.model.K2Snippet;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class K2SnippetSerialiserImpl extends GenericSerialiser<K2Snippet> implements Serialiser<K2Snippet>, K2SnippetSerialiser {

}
