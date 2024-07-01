package org.bexio.dto.users.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class CreateUserResponseDto {
  private String name;
  private String job;
  private String id;
  private String createdAt;
}
