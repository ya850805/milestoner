package com.milestoner.mapper;

import com.milestoner.domain.Profession;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProfessionMapper {
  Profession selectByPrimaryKey(Integer professionId);
  List<Profession> selectAll();
}
