package com.milestoner.service;

import com.milestoner.domain.Users;

import java.util.List;

/**
 * User service
 *
 * @author Jason
 */
public interface UsersService {
  Integer getUsersPoints(Integer userId);

  List<Users> getUsersByProfession(Integer professionId);

  void createUser(Users users);
}
