package org.bexio.api.users;

import lombok.Getter;
import org.bexio.api.base.BaseService;
import org.bexio.dto.users.request.UsersRequestDto;
import org.bexio.dto.users.response.CreateUserResponseDto;
import org.bexio.dto.users.response.UserResponseDto;
import org.bexio.dto.users.response.UsersResponseDto;
import org.bexio.features.rest.HttpMethod;
import org.bexio.features.rest.Request;
import org.bexio.features.rest.RequestSpecificationBuilder;
import org.bexio.features.rest.Response;
import org.bexio.features.spring.SpringConfig;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(SpringConfig.THREAD_SCOPE)
@Getter
public class UsersApiService extends BaseService {

  private final String USERS_PATH = "/api/users/";
  private final String USER_ID_PATH = "/api/users/{userId}";

  public Response<CreateUserResponseDto> createUser(UsersRequestDto body) {
    RequestSpecificationBuilder<UsersRequestDto> builder = new RequestSpecificationBuilder<>();
    Request request =
        builder
            .defaultReqSettings(getDefaultSettings())
            .withBasePath(USERS_PATH)
            .addJsonContentTypeHeader()
            .withHttpMethod(HttpMethod.POST)
            .withBody(body)
            .build();
    request.execute();

    return request.responseAs(CreateUserResponseDto.class);
  }

  public Response<UsersResponseDto> getUsers(int pageNumber) {
    RequestSpecificationBuilder<UsersResponseDto> builder = new RequestSpecificationBuilder<>();
    Request request =
        builder
            .defaultReqSettings(getDefaultSettings())
            .withBasePath(USERS_PATH)
            .addJsonContentTypeHeader()
            .withHttpMethod(HttpMethod.GET)
            .addQueryParam("page", pageNumber)
            .build();
    request.execute();

    return request.responseAs(UsersResponseDto.class);
  }

  public Response<UserResponseDto> getUserById(int id) {
    RequestSpecificationBuilder<UserResponseDto> builder = new RequestSpecificationBuilder<>();
    Request request =
        builder
            .defaultReqSettings(getDefaultSettings())
            .withBasePath(USER_ID_PATH)
            .addJsonContentTypeHeader()
            .withHttpMethod(HttpMethod.GET)
            .addPathParameter("userId", id)
            .build();
    request.execute();

    return request.responseAs(UserResponseDto.class);
  }
}
