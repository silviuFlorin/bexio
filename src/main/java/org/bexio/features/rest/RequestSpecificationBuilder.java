package org.bexio.features.rest;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.Cookies;
import java.util.Map;

public class RequestSpecificationBuilder<TBody> {

  protected Request restRequest;

  public RequestSpecificationBuilder() {
    restRequest = new Request();
  }

  public RequestSpecificationBuilder<TBody> withHeader(String key, String value) {
    this.restRequest.getHeaders().put(key, value);
    return this;
  }

  public RequestSpecificationBuilder<TBody> withHeader(Map<String, String> map) {
    this.restRequest.getHeaders().putAll(map);
    return this;
  }

  public RequestSpecificationBuilder<TBody> addQueryParam(String key, Object value) {
    this.restRequest.getQueryParameters().put(key, value);
    return this;
  }

  public RequestSpecificationBuilder<TBody> addQueryParam(Map<String, Object> map) {
    this.restRequest.getQueryParameters().putAll(map);
    return this;
  }

  public RequestSpecificationBuilder<TBody> addParam(String key, Object value) {
    this.restRequest.getParameters().put(key, value);
    return this;
  }

  public RequestSpecificationBuilder<TBody> addParam(Map<String, Object> map) {
    this.restRequest.getParameters().putAll(map);
    return this;
  }

  public RequestSpecificationBuilder<TBody> addPathParameter(String key, Object value) {
    this.restRequest.getPathParameters().put(key, value);
    return this;
  }

  public RequestSpecificationBuilder<TBody> addPathParameter(Map<String, Object> map) {
    this.restRequest.getPathParameters().putAll(map);
    return this;
  }

  public RequestSpecificationBuilder<TBody> addFormParameter(String key, Object value) {
    this.restRequest.getFormParameters().put(key, value);
    return this;
  }

  public RequestSpecificationBuilder<TBody> addFormParameter(Map<String, Object> map) {
    this.restRequest.getFormParameters().putAll(map);
    return this;
  }

  public RequestSpecificationBuilder<TBody> withBasePath(String url) {
    this.restRequest.setBasePath(url);
    return this;
  }

  public RequestSpecificationBuilder<TBody> useEncoding(boolean encoding) {
    this.restRequest.setEncoding(encoding);
    return this;
  }

  public RequestSpecificationBuilder<TBody> defaultReqSettings(
      DefaultBaseUrlAndAuthSettings defaultBaseUrlAndAuthSettings) {
    return this.setLogRequest(LogDetail.ALL)
        .setLogResponse(LogDetail.ALL)
        .useEncoding(true)
        .setDefaultAuthAndBaseUrl(defaultBaseUrlAndAuthSettings);
  }

  public RequestSpecificationBuilder<TBody> setLogResponse(LogDetail logDetail) {
    this.restRequest.setLogResponse(logDetail);
    return this;
  }

  public RequestSpecificationBuilder<TBody> setLogRequest(LogDetail logDetail) {
    this.restRequest.setLogRequest(logDetail);
    return this;
  }

  public RequestSpecificationBuilder<TBody> setDefaultAuthAndBaseUrl(
      DefaultBaseUrlAndAuthSettings defaultBaseUrlAndAuthSettings) {
    this.restRequest.setDefaultAuthAndBaseUrl(defaultBaseUrlAndAuthSettings);
    return this;
  }

  public RequestSpecificationBuilder<TBody> withBody(TBody body) {
    this.restRequest.setBody(body);
    return this;
  }

  public RequestSpecificationBuilder<TBody> withHttpMethod(HttpMethod verb) {
    this.restRequest.setHttpVerb(verb);
    return this;
  }

  public RequestSpecificationBuilder<TBody> addJsonContentTypeHeader() {
    this.restRequest.getHeaders().put("Content-Type", "application/json");
    return this;
  }

  public RequestSpecificationBuilder<TBody> withCookie(Cookies cookies) {
    this.restRequest.setCookies(cookies);
    return this;
  }

  public Request build() {
    return restRequest;
  }
}
