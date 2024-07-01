import lombok.extern.slf4j.Slf4j;
import org.bexio.api.users.UsersApiService;
import org.bexio.dto.users.request.UsersRequestDto;
import org.bexio.dto.users.response.CreateUserResponseDto;
import org.bexio.dto.users.response.UserResponseDto;
import org.bexio.dto.users.response.UsersResponseDto;
import org.bexio.features.rest.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
public class UsersTest extends BaseTest {

  @Autowired UsersApiService usersApiService;

  @Test(description = "Get list of users by page number")
  public void getUsersFromPageTest() {
    int expectedPage = 1;
    Response<UsersResponseDto> response = usersApiService.getUsers(expectedPage);
    int actualPage = response.getBody().getPage();

    Assert.assertEquals(response.getStatusCode(), 200, "Status code incorrect");
    Assert.assertEquals(actualPage, expectedPage, "Pages don't match");
  }

  @Test(description = "Create user test")
  public void createUserTest() {
    UsersRequestDto expectedUser = UsersRequestDto.builder().build();
    Response<CreateUserResponseDto> response = usersApiService.createUser(expectedUser);
    String actualName = response.getBody().getName();
    String actualJob = response.getBody().getJob();

    Assert.assertEquals(response.getStatusCode(), 201, "Status code incorrect");
    Assert.assertEquals(actualName, expectedUser.getName());
    Assert.assertEquals(actualJob, expectedUser.getJob());
  }

  @Test(description = "Get a user by id")
  public void getUserByIdTest() {
    int expectedId = 1;
    Response<UserResponseDto> response = usersApiService.getUserById(expectedId);
    int actualId = response.getBody().getData().getId();

    Assert.assertEquals(response.getStatusCode(), 200, "Status code incorrect");
    Assert.assertEquals(actualId, expectedId, "User ids don't match");
  }
}
