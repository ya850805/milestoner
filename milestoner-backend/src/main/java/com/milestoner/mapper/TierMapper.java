package com.milestoner.mapper;

import com.milestoner.domain.Tier;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TierMapper {
  Tier selectByPrimaryKey(Integer tierId);
}
