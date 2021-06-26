package com.milestoner.controller;

import com.milestoner.domain.Profession;
import com.milestoner.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** @author Jason */
@RestController
public class ProfessionController {
  @Autowired private ProfessionService professionService;

  @GetMapping("/profession")
  public List<Profession> getAllProfession() {
    return professionService.getAllProfession();
  }
}
