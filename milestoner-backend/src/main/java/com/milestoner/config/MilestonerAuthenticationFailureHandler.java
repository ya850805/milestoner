package com.milestoner.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.milestoner.vo.model.MilestonerResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Spring Security Failure Handler
 *
 * @author Jason
 */
@Deprecated
public class MilestonerAuthenticationFailureHandler implements AuthenticationFailureHandler {
  @Override
  public void onAuthenticationFailure(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      AuthenticationException e)
      throws IOException, ServletException {

    httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
    MilestonerResponseModel data =
        new MilestonerResponseModel(HttpStatus.FORBIDDEN.value(), e.getMessage());
    httpServletResponse.getOutputStream().println(new ObjectMapper().writeValueAsString(data));
  }
}
