package com.milestoner.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * profession使用者職業
 * @author 
 */
@Data
public class Profession implements Serializable {
    /**
     * primary key for profession
     */
    private Integer professionId;

    /**
     * profession name
     */
    private String name;

    private static final long serialVersionUID = 1L;
}