package com.milestoner.controller;

import com.milestoner.domain.Mission;
import com.milestoner.service.MissionService;
import com.milestoner.vo.dto.MissionCreateDTO;
import com.milestoner.vo.model.MilestonerResponseModel;
import com.milestoner.vo.model.MissionCreateModel;
import com.milestoner.vo.model.MissionListModel;
import com.milestoner.vo.model.MissionUpdateModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mission controller for mission create, upate status etc.
 *
 * @author Jason
 */
@RestController
public class MissionController {
  @Autowired private MissionService missionService;

  @PostMapping("/mission")
  public MilestonerResponseModel createMission(@RequestBody MissionCreateModel model) {
    MissionCreateDTO dto = new MissionCreateDTO();
    BeanUtils.copyProperties(model, dto);
    dto.setCompletePoints(Integer.valueOf(model.getCompletePoints()));
    missionService.createMission(dto);

    return new MilestonerResponseModel();
  }

  @GetMapping("/mission")
  public List<MissionListModel> findMissionByUser() {
    // current login user
    Integer userId =
        Integer.valueOf(
            SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    List<Mission> missionList = missionService.findMissionByUser(userId);

    List<MissionListModel> modelList =
        missionList.stream()
            .map(
                m -> {
                  MissionListModel model = new MissionListModel();
                  BeanUtils.copyProperties(m, model);
                  return model;
                })
            .collect(Collectors.toList());

    return modelList;
  }

  /**
   * 完成/放棄任務
   *
   * @return
   */
  @PutMapping("/mission")
  public MilestonerResponseModel updateMissionStatus(@RequestBody MissionUpdateModel model) {
    missionService.updateStatus(model.getId(), model.getOperator());
    return new MilestonerResponseModel();
  }
}
