package com.k2.dev.model.serialisation.template;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.k2.common.serialisation.GenericSerialiser;
import com.k2.common.serialisation.Serialiser;
import com.k2.dev.model.Template;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TemplateSerialiserImpl extends GenericSerialiser<Template> implements Serialiser<Template>, TemplateSerialiser {

}
