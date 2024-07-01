package org.bexio.dto.users.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersResponseDto {
  private int userId;
  private int id;
  private String title;
  private int page;
  @JsonProperty("per_page")
  private int perPage;
  private int total;
  @JsonProperty("total_pages")
  private int totalPages;
  private List<UserDataDto> data;
}
