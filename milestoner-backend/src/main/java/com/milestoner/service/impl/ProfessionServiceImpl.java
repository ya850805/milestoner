package com.milestoner.service.impl;

import com.milestoner.domain.Profession;
import com.milestoner.mapper.ProfessionMapper;
import com.milestoner.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** @author Jason */
@Service
public class ProfessionServiceImpl implements ProfessionService {
  @Autowired private ProfessionMapper professionMapper;

  @Override
  public List<Profession> getAllProfession() {
    return professionMapper.selectAll();
  }
}
