package com.milestoner.domain;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

/**
 * mission任務
 * @author 
 */
@Data
public class Mission implements Serializable {
    /**
     * primary key for mission
     */
    private Integer missionId;

    /**
     * foreign key mapping to user table
     */
    private Integer fkUserId;

    /**
     * mission title
     */
    private String title;

    /**
     * mission description
     */
    private String description;

    /**
     * mission complete date predictably
     */
    private LocalDate date;

    /**
     * mission status. 0:initial, 1:completed, 2:failed
     */
    private String status;

    /**
     * receive points when user complete the mission
     */
    private Integer completePoints;

    private static final long serialVersionUID = 1L;
}