package com.jkhhuse.plan.common;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "响应对象")
public class CommonResponse<T> {
  private MetaCode meta = new MetaCode();
  private T data = null;

  public CommonResponse() {
  }

  public CommonResponse(T data) {
    this.data = data;
  }

  public CommonResponse(String code, T data, String message) {
    this.meta = new MetaCode(String.valueOf(code), message);
    this.data = data;
  }
}