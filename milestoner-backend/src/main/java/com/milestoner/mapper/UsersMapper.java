package com.milestoner.mapper;

import com.milestoner.domain.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsersMapper {
  Users selectByPrimaryKey(Integer userId);

  Users selectByEmail(String email);

  List<Users> selectUsersByProfession(Integer professionId);

  void updatePoints(Integer userId, Integer points);

  void createUser(Users users);
}
