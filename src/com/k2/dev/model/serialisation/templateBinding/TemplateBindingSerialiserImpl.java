package com.k2.dev.model.serialisation.templateBinding;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.k2.common.serialisation.GenericSerialiser;
import com.k2.common.serialisation.Serialiser;
import com.k2.dev.model.TemplateBinding;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TemplateBindingSerialiserImpl extends GenericSerialiser<TemplateBinding> implements Serialiser<TemplateBinding>, TemplateBindingSerialiser {

}
