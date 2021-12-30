package com.example.facilityService.dao;

import com.example.facilityService.entity.FacilityEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FacilityDao {

    List<FacilityEntity> queryAllByLimit(@Param("type") String type, @Param("snNo") String snNo,
                                         @Param("offset") int offset, @Param("limit") int limit);

    Integer countAll(@Param("type") String type, @Param("snNo") String snNo);

    FacilityEntity selectById(@Param("id") int id);

    void addFacility(FacilityEntity facilityEntity);

    void updateFacility(FacilityEntity facilityEntity);
}
