package org.bexio.features.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.SimpleThreadScope;

@ComponentScan(basePackages = {"org.bexio"})
@Configuration
public class SpringConfig {

  public static final String THREAD_SCOPE = "thread";

  @Bean
  public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
    return new ThreadBeanFactoryPostProcessor();
  }

  public static class ThreadBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory factory)
        throws BeansException {
      factory.registerScope(THREAD_SCOPE, new SimpleThreadScope());
    }
  }
}
