package com.example.facilityService.service.impl;

import com.example.facilityService.dao.FacilityDao;
import com.example.facilityService.entity.FacilityEntity;
import com.example.facilityService.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    private FacilityDao facilityDao;

    @Override
    public List<FacilityEntity> queryAllByLimit(String type, String snNo, int page, int limit) {
        return facilityDao.queryAllByLimit(type, snNo, (page-1)*limit, limit);
    }

    @Override
    public Integer countAll(String type, String snNo) {
        return facilityDao.countAll(type, snNo);
    }

    @Override
    public FacilityEntity selectById(int id) {
        return facilityDao.selectById(id);
    }

    @Override
    public void addFacility(FacilityEntity facilityEntity) {
        facilityDao.addFacility(facilityEntity);
    }

    @Override
    public void updateFacility(FacilityEntity facilityEntity) {
        facilityDao.updateFacility(facilityEntity);
    }
}
