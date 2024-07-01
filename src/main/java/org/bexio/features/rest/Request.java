package org.bexio.features.rest;

import static io.restassured.RestAssured.given;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Request {
  protected Map<String, String> headers = new HashMap<>();
  protected Map<String, Object> queryParameters = new HashMap<>();
  protected Map<String, Object> pathParameters = new HashMap<>();
  protected Map<String, Object> parameters = new HashMap<>();
  protected Map<String, Object> formParameters = new HashMap<>();
  protected String basePath;
  protected Object body;
  protected HttpMethod httpVerb;
  protected LogDetail logResponse;
  protected LogDetail logRequest;
  protected boolean encoding;
  private io.restassured.response.Response response;
  private RequestSpecification request;
  private Cookies cookies;
  private int timeoutInMillis = 180000;
  private DefaultBaseUrlAndAuthSettings defaultBaseUrlAndAuthSettings;

  public RequestSpecification prepareRequest() {
    RequestSpecBuilder requestSpecBuilder =
        new RequestSpecBuilder()
            .addFilter(new AllureRestAssured())
            .setUrlEncodingEnabled(encoding)
            .setBasePath(basePath)
            .addFilter(new RequestLoggingFilter(logRequest))
            .addFilter(new ResponseLoggingFilter(logResponse));

    setBaseUri(requestSpecBuilder);
    setCookie(requestSpecBuilder);
    setRequestHeaders(requestSpecBuilder);
    setRequestParameters(requestSpecBuilder);
    setRequestPathParameters(requestSpecBuilder);
    setRequestQueryParameters(requestSpecBuilder);
    setRequestFormParameters(requestSpecBuilder);
    setConnectionTimeout(requestSpecBuilder);
    return requestSpecBuilder.build();
  }

  protected void setConnectionTimeout(RequestSpecBuilder builder) {
    RestAssuredConfig config =
        RestAssured.config()
            .httpClient(
                HttpClientConfig.httpClientConfig()
                    .setParam("http.connection.timeout", timeoutInMillis)
                    .setParam("http.socket.timeout", timeoutInMillis));
    builder.setConfig(config);
  }

  protected void setBaseUri(RequestSpecBuilder builder) {
    String baseUrl = defaultBaseUrlAndAuthSettings.getAllEnvVariables().get("base-api.url");
    System.out.println("Base URL: " + baseUrl);
    builder.setBaseUri(baseUrl);
  }

  protected void setCookie(RequestSpecBuilder builder) {
    if (Objects.nonNull(cookies)) {
      builder.addCookies(cookies);
    }
  }

  protected void setRequestHeaders(RequestSpecBuilder builder) {
    for (Map.Entry<String, String> header : headers.entrySet()) {
      builder.addHeader(header.getKey(), header.getValue());
    }
  }

  protected void setRequestQueryParameters(RequestSpecBuilder builder) {
    for (Map.Entry<String, Object> param : queryParameters.entrySet()) {
      builder.addQueryParam(param.getKey(), param.getValue());
    }
  }

  private void setRequestPathParameters(RequestSpecBuilder builder) {
    for (Map.Entry<String, Object> param : pathParameters.entrySet()) {
      builder.addPathParam(param.getKey(), param.getValue());
    }
  }

  private void setRequestParameters(RequestSpecBuilder builder) {
    for (Map.Entry<String, Object> param : parameters.entrySet()) {
      builder.addParam(param.getKey(), param.getValue());
    }
  }

  private void setRequestFormParameters(RequestSpecBuilder builder) {
    for (Map.Entry<String, Object> param : formParameters.entrySet()) {
      builder.addFormParam(param.getKey(), param.getValue());
    }
  }

  public void execute() {
    request = given().spec(prepareRequest());
    switch (httpVerb) {
      case GET -> response = request.get();
      case POST -> {
        if (Objects.nonNull(body)) {
          response = request.body(body).when().post();
        } else {
          response = request.when().post();
        }
      }
      case PATCH -> {
        if (Objects.nonNull(body)) {
          response = request.body(body).when().patch();
        } else {
          response = request.when().patch();
        }
      }
      case PUT -> {
        if (Objects.nonNull(body)) {
          response = request.body(body).when().put();
        } else {
          response = request.when().put();
        }
      }
      case DELETE -> {
        if (Objects.nonNull(body)) {
          response = request.body(body).when().delete();
        } else {
          response = request.when().delete();
        }
      }
      case HEAD -> response = request.when().head();
    }
  }

  public <TRes> Response<TRes> responseAs(Class<TRes> tRestClass) {
    return new Response<>(
        response.statusCode(), response.as(tRestClass), response.getHeaders(), response.getTime());
  }

  public void setDefaultAuthAndBaseUrl(
      DefaultBaseUrlAndAuthSettings defaultBaseUrlAndAuthSettings) {
    this.defaultBaseUrlAndAuthSettings = defaultBaseUrlAndAuthSettings;
  }
}
