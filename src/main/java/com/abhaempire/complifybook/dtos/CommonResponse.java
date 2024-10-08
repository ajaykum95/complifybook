package com.abhaempire.complifybook.dtos;

import com.abhaempire.complifybook.enums.ResultTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommonResponse {
  private ResultTypeEnum result;
  private String message;
}
