package com.milestoner.service.impl;

import com.milestoner.domain.Users;
import com.milestoner.mapper.MissionMapper;
import com.milestoner.mapper.UsersMapper;
import com.milestoner.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/** @author Jason */
@Service
public class UsersServiceImpl implements UsersService {

  @Autowired private UsersMapper usersMapper;
  @Autowired private MissionMapper missionMapper;
  @Autowired private PasswordEncoder passwordEncoder;

  @Override
  public Integer getUsersPoints(Integer userId) {
    return Objects.isNull(missionMapper.getUsersPoints(userId))
        ? 0
        : missionMapper.getUsersPoints(userId);
  }

  @Override
  public List<Users> getUsersByProfession(Integer professionId) {
    return usersMapper.selectUsersByProfession(professionId);
  }

  @Override
  public void createUser(Users users) {
    users.setPassword(passwordEncoder.encode(users.getPassword()));
    usersMapper.createUser(users);
  }
}
