package com.example.facilityService.service;


import com.example.facilityService.entity.FacilityEntity;

import java.util.List;

public interface FacilityService {

    List<FacilityEntity> queryAllByLimit(String type, String snNo, int page, int limit);

    Integer countAll(String type, String snNo);

    FacilityEntity selectById(int id);

    void addFacility(FacilityEntity facilityEntity);

    void updateFacility(FacilityEntity facilityEntity);
}
