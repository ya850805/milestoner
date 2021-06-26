package com.milestoner.service;

import com.milestoner.domain.Mission;
import com.milestoner.vo.dto.MissionCreateDTO;

import java.util.List;

/**
 * Mission service
 *
 * @author Jason
 */
public interface MissionService {
    void createMission(MissionCreateDTO missionCreateDTO);
    List<Mission> findMissionByUser(Integer userId);
    void updateStatus(Integer missionId, String status);
}
