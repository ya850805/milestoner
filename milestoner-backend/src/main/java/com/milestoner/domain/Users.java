package com.milestoner.domain;

import java.io.Serializable;
import java.util.Collection;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * users使用者
 *
 * @author
 */
@Data
public class Users implements Serializable, UserDetails {
  /** primary key for user */
  private Integer userId;

  /** foreign key mapping to profession table */
  private Integer fkProfessionId;

  /** user email */
  private String email;

  /** user password */
  private String password;

  /** user name */
  private String name;

  /** user current points */
  private Integer points;

  private static final long serialVersionUID = 1L;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getUsername() {
    return getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
