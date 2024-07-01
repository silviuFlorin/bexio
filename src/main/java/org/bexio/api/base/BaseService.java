package org.bexio.api.base;

import java.util.Map;
import org.bexio.features.rest.DefaultBaseUrlAndAuthSettings;
import org.bexio.features.rest.EnvironmentPropertiesExtractor;
import org.bexio.features.rest.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseService implements BaseServiceInterface {
  @Autowired Environment environment;
  @Autowired EnvironmentPropertiesExtractor environmentPropertiesExtractor;

  public DefaultBaseUrlAndAuthSettings getDefaultSettings() {
    Map<String, String> allEnvVariables = environmentPropertiesExtractor.extractAll(environment);
    return DefaultBaseUrlAndAuthSettings.builder().allEnvVariables(allEnvVariables).build();
  }

  @Override
  public Response<String> request() {
    return null;
  }
}
