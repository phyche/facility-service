package com.example.facilityService.controller;

import com.example.facilityService.entity.FacilityEntity;
import com.example.facilityService.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/server")
public class ServerController {

    @Autowired
    private FacilityService facilityService;

    @RequestMapping("/index")
    public String index() {
        System.out.println("index");
        return "get success";
    }

    @PostMapping("/list")
    public Map queryPage(@RequestParam String type,@RequestParam String snNo,@RequestParam Integer page,@RequestParam Integer limit){
        Map result = new HashMap();
        List<FacilityEntity> list = facilityService.queryAllByLimit(type, snNo, page, limit);
        Integer total = facilityService.countAll(type, snNo);
        result.put("list",list);
        result.put("total",total);
        return result;
    }
}
