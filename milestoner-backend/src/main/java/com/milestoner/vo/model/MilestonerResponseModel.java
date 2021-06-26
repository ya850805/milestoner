package com.milestoner.vo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * 封裝status code、message返回之物件
 *
 * @author Jason
 */
@Data
@AllArgsConstructor
public class MilestonerResponseModel {
  private Integer status;
  private String message;

  public MilestonerResponseModel() {
    this.status = HttpStatus.OK.value();
    this.message = "success";
  }
}
