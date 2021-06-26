package com.milestoner.config;

import com.milestoner.filter.JWTAuthenticationFilter;
import com.milestoner.service.MilestonerUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Spring Security Configuration
 *
 * @author Jason
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired private MilestonerUserDetailService milestonerUserDetailService;
  @Autowired private JWTAuthenticationFilter jwtAuthenticationFilter;
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
//    http.authorizeRequests()
//        .anyRequest()
//        .authenticated()
//        .and()
//        .formLogin()
//        .loginProcessingUrl("/login")
//        .usernameParameter("email")
//        .passwordParameter("password")
//        .successHandler(new MilestonerAuthenticationSuccessHandler())
//        .failureHandler(new MilestonerAuthenticationFailureHandler())
//        .and()
//        .exceptionHandling()
//        .authenticationEntryPoint(new MilestonerAuthenticationEntryPoint())
//        .and()
//        .csrf()
//        .ignoringAntMatchers("/login");

    http.httpBasic().authenticationEntryPoint(new MilestonerAuthenticationEntryPoint());
    http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers(HttpMethod.POST, "/login", "/user").permitAll()
            .antMatchers(HttpMethod.GET, "/profession").permitAll()
//            .antMatchers(HttpMethod.POST, "/auth/").permitAll()
//            .antMatchers(HttpMethod.POST, "/v1/users/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(milestonerUserDetailService).passwordEncoder(passwordEncoder());
  }
}
