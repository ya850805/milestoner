package com.milestoner.constant;

import com.milestoner.domain.Mission;

/**
 * Mission status enum
 *
 * @author Jason
 */
public enum MissionStatusEnum {
  INITIAL("0"),
  COMPLETED("1"),
  FAILED("2");

  private String code;

  private MissionStatusEnum(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
