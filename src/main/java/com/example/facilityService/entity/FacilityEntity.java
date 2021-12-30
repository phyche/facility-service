package com.example.facilityService.entity;

public class FacilityEntity {

    private Integer id;
    private String type;
    private String snNo;
    private String fileName;
//    private String fileType;
    private byte[] file;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSnNo() {
        return snNo;
    }

    public void setSnNo(String snNo) {
        this.snNo = snNo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

//    public String getFileType() {
//        return fileType;
//    }
//
//    public void setFileType(String fileType) {
//        this.fileType = fileType;
//    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
