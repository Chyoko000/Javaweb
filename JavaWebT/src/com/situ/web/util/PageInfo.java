package com.situ.web.util;

import java.util.List;
//封装就是将数据或函数等集合在一个个的单元中（我们称之为类）
//是否可以理解为写一个类就是封装
//封装一个工具类
public class PageInfo<T> {
    //封装一个pageinfo需要的信息
    //当前页的数据使用泛型T使其能适应与更多servlet
    private List<T> list;
    //页的总数
    private Integer totalPage;
    //第几页
    private Integer pageNo;

    private Integer pageSize;

    public PageInfo() {
    }
//泛型使整个工具类让多数情况能使用
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

