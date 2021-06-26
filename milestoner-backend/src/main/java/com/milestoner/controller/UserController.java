package com.milestoner.controller;

import com.milestoner.domain.Users;
import com.milestoner.service.MilestonerUserDetailService;
import com.milestoner.service.UsersService;
import com.milestoner.vo.model.LoginModel;
import com.milestoner.vo.model.MilestonerResponseModel;
import com.milestoner.vo.model.UserCreateModel;
import com.milestoner.vo.model.UserReturnModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User controller for user login, user create etc.
 *
 * @author Jason
 */
@RestController
public class UserController {

  @Autowired private MilestonerUserDetailService milestonerUserDetailService;
  @Autowired private UsersService usersService;

  /**
   * user login
   *
   * @param loginModel
   * @return
   */
  @PostMapping("/login")
  public ResponseEntity<?> getToken(@RequestBody LoginModel loginModel) {
    return milestonerUserDetailService.getToken(loginModel);
  }

  @GetMapping("/usersPoints")
  public Integer getUsersPoints(@RequestParam("userId") Integer userId) {
    return usersService.getUsersPoints(userId);
  }

  @GetMapping("/professionUserList")
  public List<UserReturnModel> getUsersByProfession(
      @RequestParam("professionId") Integer professionId) {
    return usersService.getUsersByProfession(professionId).stream()
        .map(
            u -> {
              UserReturnModel model = new UserReturnModel();
              BeanUtils.copyProperties(u, model);
              return model;
            })
        .collect(Collectors.toList());
  }

  @PostMapping("/user")
  public MilestonerResponseModel createUser(@RequestBody UserCreateModel model) {
    Users users = new Users();
    BeanUtils.copyProperties(model, users);
    users.setFkProfessionId(model.getProfession());
    usersService.createUser(users);
    return new MilestonerResponseModel();
  }
}
