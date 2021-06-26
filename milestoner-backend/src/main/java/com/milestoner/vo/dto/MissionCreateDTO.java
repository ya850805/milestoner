package com.milestoner.vo.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * Mission create DTO
 *
 * @author Jason
 */
@Data
public class MissionCreateDTO {
  private Integer fkUserId;
  private String title;
  private String description;
  private LocalDate date;
  private String status;
  private Integer completePoints;
}
