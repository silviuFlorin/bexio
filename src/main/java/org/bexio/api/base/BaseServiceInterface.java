package org.bexio.api.base;

import org.bexio.features.rest.Response;

public interface BaseServiceInterface {
  Response<String> request();
}
