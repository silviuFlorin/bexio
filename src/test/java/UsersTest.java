import org.bexio.api.users.UsersApiService;
import org.bexio.dto.users.response.UserResponseDto;
import org.bexio.dto.users.response.UsersResponseDto;
import lombok.extern.slf4j.Slf4j;
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
    Response<UsersResponseDto> response = usersApiService.getUsersFromPage(expectedPage);
    Assert.assertEquals(response.getStatusCode(), 200, "Status code incorrect");
    int actualPage = response.getBody().getPage();
    Assert.assertEquals(actualPage, expectedPage, "Pages don't match");
  }

  @Test(description = "Get a user by id")
  public void getUserByIdTest() {
    int expectedId = 1;
    Response<UserResponseDto> response = usersApiService.getUserById(expectedId);
    Assert.assertEquals(response.getStatusCode(), 200, "Status code incorrect");
    int actualId = response.getBody().getData().getId();
    Assert.assertEquals(actualId, expectedId, "User ids don't match");
  }
}
