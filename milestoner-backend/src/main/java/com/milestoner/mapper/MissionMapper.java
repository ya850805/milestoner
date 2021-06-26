package com.milestoner.mapper;

import com.milestoner.domain.Mission;
import com.milestoner.vo.dto.MissionCreateDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MissionMapper {
  Mission selectByPrimaryKey(Integer missionId);

  void create(MissionCreateDTO missionCreateDTO);

  List<Mission> selectByUserId(Integer userId); // 找出某個使用者尚未完成且尚未過期的任務

  void updateStatus(Integer missionId, String status);

  Integer getUsersPoints(Integer userId);

}
