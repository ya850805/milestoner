package com.milestoner.service;

import com.milestoner.domain.Users;
import com.milestoner.mapper.ProfessionMapper;
import com.milestoner.mapper.UsersMapper;
import com.milestoner.util.JWTUtil;
import com.milestoner.vo.model.LoginModel;
import com.milestoner.vo.model.UserReturnModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/** @author Jason */
@Service
public class MilestonerUserDetailService implements UserDetailsService {
  @Autowired private UsersMapper usersMapper;

  @Autowired private AuthenticationManager authenticationManager;

  @Autowired private JWTUtil jwtUtil;

  @Autowired private ProfessionMapper professionMapper;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    return usersMapper.selectByEmail(s);
  }

  public ResponseEntity<?> getToken(LoginModel users) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(users.getEmail(), users.getPassword()));
      Users user = usersMapper.selectByEmail(users.getEmail());
      UserReturnModel userReturnModel = new UserReturnModel();
      BeanUtils.copyProperties(user, userReturnModel);
      userReturnModel.setPoints(user.getPoints());
      userReturnModel.setProfession(professionMapper.selectByPrimaryKey(user.getFkProfessionId()).getName());

      String token = jwtUtil.sign(user.getUserId().toString());
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.set("Authorization", token);
      return ResponseEntity.ok().headers(httpHeaders).body(userReturnModel);
    } catch (AuthenticationException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
  }
}
