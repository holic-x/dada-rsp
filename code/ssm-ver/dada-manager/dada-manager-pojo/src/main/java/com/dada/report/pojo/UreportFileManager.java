package com.dada.report.pojo;

import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UreportFileManager {

    private String fileId;

    private String fileName;

    private String categoryId;
    
    private String deptId;

    private String fileDescr;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date modifyTime;

    private String publicState;

    private String visibleState;

    private byte[] fileContent;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getFileDescr() {
        return fileDescr;
    }

    public void setFileDescr(String fileDescr) {
        this.fileDescr = fileDescr;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getPublicState() {
        return publicState;
    }

    public void setPublicState(String publicState) {
        this.publicState = publicState;
    }

    public String getVisibleState() {
        return visibleState;
    }

    public void setVisibleState(String visibleState) {
        this.visibleState = visibleState;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    @Override
    public String toString() {
        return "UreportFileManager [fileId=" + fileId + ", fileName=" + fileName + ", categoryId=" + categoryId
                + ", deptId=" + deptId + ", fileDescr=" + fileDescr + ", createTime=" + createTime + ", modifyTime="
                + modifyTime + ", publicState=" + publicState + ", visibleState=" + visibleState + ", fileContent="
                + Arrays.toString(fileContent) + "]";
    }
}
