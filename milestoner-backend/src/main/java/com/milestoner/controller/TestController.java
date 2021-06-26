package com.milestoner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 測試用controller
 *
 * @author Jason
 */
@RestController
public class TestController {
  @GetMapping("/test")
  public String test() {
    return "success";
  }
}
