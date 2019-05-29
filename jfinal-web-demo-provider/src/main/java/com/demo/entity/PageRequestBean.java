package com.demo.entity;

/**
 * 分页请求Bean
 */
public class PageRequestBean {

    private Integer pageNum = 1;

    private Integer pageSize = 4;

    public Integer getPageNum() {
        return pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
