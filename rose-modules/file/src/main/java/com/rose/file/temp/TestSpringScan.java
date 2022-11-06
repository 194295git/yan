package com.rose.file.temp;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;

/**
 * @author rose
 * @create 2022/10/29
 */
public class TestSpringScan extends ClassPathBeanDefinitionScanner {
    public TestSpringScan(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected boolean isCandidateComponent(MetadataReader metadataReader) throws IOException {
        return super.isCandidateComponent(metadataReader);
    }

    @Override
    protected void registerBeanDefinition(BeanDefinitionHolder definitionHolder, BeanDefinitionRegistry registry) {
        super.registerBeanDefinition(definitionHolder, registry);
    }
}
