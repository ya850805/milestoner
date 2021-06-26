package com.milestoner.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * tier使用者等級
 * @author 
 */
@Data
public class Tier implements Serializable {
    /**
     * primary key for tier
     */
    private Integer tierId;

    /**
     * tier name
     */
    private String name;

    /**
     * lowest points require to this tier
     */
    private Integer lowestPoints;

    private static final long serialVersionUID = 1L;
}