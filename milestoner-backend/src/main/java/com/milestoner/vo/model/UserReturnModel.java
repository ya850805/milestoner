package com.milestoner.vo.model;

import lombok.Data;

/**
 * 登入成功返回vo
 *
 * @author Jason
 */
@Data
public class UserReturnModel {
    private Integer userId;
    private String email;
    private String name;
    private Integer points;
    private String profession;
}
