package com.example.facilityService.controller;

import com.example.facilityService.entity.FacilityEntity;
import com.example.facilityService.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/facility")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @PostMapping("/list")
    public Map queryPage(@RequestParam String type,@RequestParam String snNo,@RequestParam Integer page,@RequestParam Integer limit){
        Map result = new HashMap();
        List<FacilityEntity> list = facilityService.queryAllByLimit(type, snNo, page, limit);
        Integer total = facilityService.countAll(type, snNo);
        result.put("list",list);
        result.put("total",total);
        return result;
    }

    @PostMapping("/queryFile")
    public Map queryFile(@RequestParam String type,@RequestParam String snNo){

        Map result = new HashMap();

        try {
            List<FacilityEntity> list = facilityService.queryAllByLimit(type, snNo, 1, 10);
            if (list.size() == 0) {
                FacilityEntity facilityEntity = new FacilityEntity();
                facilityEntity.setType(type);
                facilityEntity.setSnNo(snNo);
                facilityService.addFacility(facilityEntity);
                result.put("status", "success");
                result.put("file", null);
            } else if (list.size() == 1){
                FacilityEntity facilityEntity = list.get(0);
                if (facilityEntity.getFile() != null) {
                    result.put("status", "success");
                    result.put("file", facilityEntity.getFile());
                } else {
                    result.put("status", "success");
                    result.put("file", null);
                }
            }else {
                result.put("status", "error");
            }
        } catch (Exception e) {
            result.put("status", "error");
        }
        return result;
    }

    @PostMapping("/uploadFile")
    public Map uploadFile(@RequestParam MultipartFile multipartFile, @RequestParam String id) throws IOException {
        Map result = new HashMap();
        FacilityEntity facilityEntity = facilityService.selectById(Integer.valueOf(id));
        if (facilityEntity != null) {
            byte[] bytes = multipartFile.getBytes();
            facilityEntity.setFile(bytes);
            facilityEntity.setFileName(multipartFile.getOriginalFilename());
//            facilityEntity.setFileType(multipartFile.getContentType());
            facilityService.updateFacility(facilityEntity);
            result.put("status", "success");
        } else {
            result.put("status", "error");
        }

        return result;
    }

    @PostMapping("/uploadFileBatch")
    public Map uploadFileBatch(@RequestParam MultipartFile multipartFile, @RequestParam String ids) throws IOException {
        Map result = new HashMap();
        String[] idAtr = ids.split(";");
        for (int i=0; i < idAtr.length; i ++) {
            FacilityEntity facilityEntity = facilityService.selectById(Integer.valueOf(idAtr[i]));
            if (facilityEntity != null) {
                byte[] bytes = multipartFile.getBytes();
                facilityEntity.setFile(bytes);
                facilityEntity.setFileName(multipartFile.getOriginalFilename());
//                facilityEntity.setFileType(multipartFile.getContentType());
                facilityService.updateFacility(facilityEntity);
            }
        }
        result.put("status", "success");
        return result;
    }

    @GetMapping("/downloadFile/{id}")
    public void downloadFile(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
        FacilityEntity facilityEntity = facilityService.selectById(Integer.valueOf(id));
        if (facilityEntity != null) {
            byte[] bytes = facilityEntity.getFile();
//            response.setContentType(facilityEntity.getFileType());
            response.setCharacterEncoding("UTF-8");
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
        }
    }
}
