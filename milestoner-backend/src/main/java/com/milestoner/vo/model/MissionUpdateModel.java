package com.milestoner.vo.model;

import lombok.Data;

/**
 * Mission update model
 *
 * @author Jason
 */
@Data
public class MissionUpdateModel {
  private Integer id;
  private String operator; // 1: complete, 2: give up
}
