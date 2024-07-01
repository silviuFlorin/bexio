package org.bexio.api.users;

import org.bexio.api.base.BaseService;
import org.bexio.dto.users.response.UserResponseDto;
import org.bexio.dto.users.response.UsersResponseDto;
import lombok.Getter;
import org.bexio.features.rest.Response;
import org.bexio.features.spring.SpringConfig;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(SpringConfig.THREAD_SCOPE)
@Getter
public class UsersApiService extends BaseService {

  private final String userApiPath = "/api/users/";
  private final String userApiIdPath = "/api/users/{userId}";

  public UsersRequestBuilder getUsers() {
    return new UsersRequestBuilder(this, userApiPath);
  }

  public UsersRequestBuilder getUser() {
    return new UsersRequestBuilder(this, userApiIdPath);
  }

  public Response<UsersResponseDto> getUsersFromPage(int page) {
    return getUsers()
        .withPageNumber(page)
        .as(UsersResponseDto.class);
  }

  public Response<UserResponseDto> getUserById(int userId) {
    return getUser()
        .withUserId(userId)
        .as(UserResponseDto.class);
  }
}
