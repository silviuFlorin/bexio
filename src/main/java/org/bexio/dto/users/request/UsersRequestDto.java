package org.bexio.dto.users.request;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldNameConstants;

@Getter
@Builder
@FieldNameConstants
public class UsersRequestDto {
  @Builder.Default private String name = "Silviu";
  @Builder.Default private String job = "QA Automation";
}
