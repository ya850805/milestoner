package com.milestoner.vo.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Mission create information
 *
 * @author Jason
 */
@Data
public class MissionCreateModel {
  private String title;
  private String description;
  private LocalDate date;
  private String completePoints;
}
