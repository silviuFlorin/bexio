package org.bexio.api.users;

import java.util.HashMap;
import java.util.Map;
import org.bexio.dto.users.response.UsersResponseDto;
import org.bexio.features.rest.HttpMethod;
import org.bexio.features.rest.Request;
import org.bexio.features.rest.RequestSpecificationBuilder;
import org.bexio.features.rest.Response;

public class UsersRequestBuilder {

  private final UsersApiService service;
  private final String path;
  private final Map<String, Object> queryParams;
  private final Map<String, Object> pathParams;

  public UsersRequestBuilder(UsersApiService service, String path) {
    this.service = service;
    this.path = path;
    this.queryParams = new HashMap<>();
    this.pathParams = new HashMap<>();
  }

  public UsersRequestBuilder withPageNumber(int page) {
    queryParams.put("page", page);
    return this;
  }

  public UsersRequestBuilder withUserId(int userId) {
    pathParams.put("userId", userId);
    return this;
  }

  public <T> Response<T> as(Class<T> responseType) {
    Request request = buildGetRequest(path);
    return request.responseAs(responseType);
  }

  private Request buildGetRequest(String path) {
    RequestSpecificationBuilder<UsersResponseDto> builder =
        new RequestSpecificationBuilder<>();
    RequestSpecificationBuilder<UsersResponseDto> requestSpecificationBuilder =
        builder
            .defaultReqSettings(service.getDefaultSettings())
            .withBasePath(path)
            .addJsonContentTypeHeader()
            .withActionToExecute(HttpMethod.GET);
    queryParams.forEach(requestSpecificationBuilder::addQueryParam);
    pathParams.forEach(requestSpecificationBuilder::addPathParameter);
    Request request = requestSpecificationBuilder.build();
    request.execute();
    return request;
  }

}
