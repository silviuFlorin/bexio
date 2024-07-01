package org.bexio.features.rest;

import io.restassured.http.Headers;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Response<T> {
  private int statusCode;
  private T body;
  private Headers headers;
  private long responseTime;
}
