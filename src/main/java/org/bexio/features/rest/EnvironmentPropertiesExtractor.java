package org.bexio.features.rest;

import java.util.HashMap;
import java.util.Map;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentPropertiesExtractor {
  public Map<String, String> extractAll(Environment environment) {
    Map<String, String> allEnvVariables = new HashMap<>();

    for (PropertySource<?> source : ((AbstractEnvironment) environment).getPropertySources()) {
      if (source instanceof EnumerablePropertySource<?> eps) {
        for (String propertyName : eps.getPropertyNames()) {
          String value = environment.getProperty(propertyName);
          allEnvVariables.put(propertyName, value);
        }
      }
    }

    return allEnvVariables;
  }
}
