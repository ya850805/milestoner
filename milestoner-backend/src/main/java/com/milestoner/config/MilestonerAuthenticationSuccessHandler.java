package com.milestoner.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.milestoner.vo.model.MilestonerResponseModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Spring Security Success Handler
 *
 * @author Jason
 */
@Deprecated
public class MilestonerAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      Authentication authentication)
      throws IOException, ServletException {
    httpServletResponse
        .getOutputStream()
        .println(new ObjectMapper().writeValueAsString(new MilestonerResponseModel()));
  }
}
