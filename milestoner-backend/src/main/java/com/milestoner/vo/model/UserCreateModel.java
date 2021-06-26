package com.milestoner.vo.model;

import lombok.Data;

/**
 * User create data
 *
 * @author Jason
 */
@Data
public class UserCreateModel {
  private String name;
  private String email;
  private String password;
  private Integer profession;
}
