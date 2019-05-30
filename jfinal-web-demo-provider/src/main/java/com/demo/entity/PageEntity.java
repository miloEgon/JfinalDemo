package com.demo.entity;

import org.hibernate.validator.constraints.NotBlank;

public class PageEntity {

    private Integer pageSize;

    private Integer pageNum;

    @NotBlank(message = "自定义登录态不能为空")
    private String authKey;

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize == null ? 10 : pageSize;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum == null ? 1 : pageNum;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }
}
