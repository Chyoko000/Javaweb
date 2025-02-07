package com.situ.web.util;

import com.situ.web.pojo.Banji;

import java.util.List;

public class PageInfo<T> {
    //private List<Banji> list;
    private List<T> list;
    private Integer totalPage;
    private Integer pageNo;
    private Integer pageSize;

    public PageInfo() {
    }

    public PageInfo(List<T> list, Integer totalPage, Integer pageNo, Integer pageSize) {
        this.list = list;
        this.totalPage = totalPage;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "list=" + list +
                ", totalPage=" + totalPage +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}

