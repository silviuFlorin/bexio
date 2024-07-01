package org.bexio.features.rest;

import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DefaultBaseUrlAndAuthSettings {
  private Map<String, String> allEnvVariables;
}
