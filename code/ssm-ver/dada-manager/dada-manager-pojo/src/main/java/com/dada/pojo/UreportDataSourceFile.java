package com.dada.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UreportDataSourceFile {
    private String fileId;

    private String fileName;

    private String categoryId;

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
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getFileDescr() {
        return fileDescr;
    }

    public void setFileDescr(String fileDescr) {
        this.fileDescr = fileDescr == null ? null : fileDescr.trim();
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
        this.publicState = publicState == null ? null : publicState.trim();
    }

    public String getVisibleState() {
        return visibleState;
    }

    public void setVisibleState(String visibleState) {
        this.visibleState = visibleState == null ? null : visibleState.trim();
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
}