package com.milestoner.vo.model;

import lombok.Data;

/**
 * login with email & password
 *
 * @author Jason
 */
@Data
public class LoginModel {
    private String email;
    private String password;
}
