package com.milestoner.service.impl;

import com.milestoner.constant.MissionStatusEnum;
import com.milestoner.domain.Mission;
import com.milestoner.domain.Users;
import com.milestoner.mapper.MissionMapper;
import com.milestoner.mapper.UsersMapper;
import com.milestoner.service.MissionService;
import com.milestoner.vo.dto.MissionCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** @author Jason */
@Service
public class MissionServiceImpl implements MissionService {
  @Autowired MissionMapper missionMapper;
  @Autowired UsersMapper usersMapper;

  @Override
  @Transactional
  public void createMission(MissionCreateDTO missionCreateDTO) {
    missionCreateDTO.setFkUserId(
        Integer.valueOf(
            SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()));
    missionCreateDTO.setStatus(MissionStatusEnum.INITIAL.getCode());
    missionMapper.create(missionCreateDTO);
  }

  @Override
  public List<Mission> findMissionByUser(Integer userId) {
    return missionMapper.selectByUserId(userId);
  }

  @Override
  @Transactional
  public void updateStatus(Integer missionId, String status) {
    // 修改任務的狀態
    missionMapper.updateStatus(missionId, status);

    // 完成任務時->更新user table的points
    if(MissionStatusEnum.COMPLETED.getCode().equals(status)) {
      // current login user
      Integer userId =
              Integer.valueOf(
                      SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

      Integer points = usersMapper.selectByPrimaryKey(userId).getPoints();
      Integer completePoints = missionMapper.selectByPrimaryKey(missionId).getCompletePoints();

      points += completePoints;
      usersMapper.updatePoints(userId, points);
    }
  }
}
