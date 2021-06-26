package com.milestoner.vo.model;

import lombok.Data;

import java.time.LocalDate;

/** @author Jason */
@Data
public class MissionListModel {
  private Integer missionId;
  private String title;
  private String description;
  private LocalDate date;
  private Integer completePoints;
}
