package com.situ.web.pojo.query;

public class CourseQuery {
    private Integer page;
    private Integer limit;
    private String name;
    private String credit;

    public CourseQuery() {
    }

    public CourseQuery(Integer page, Integer limit, String name, String credit) {
        this.page = page;
        this.limit = limit;
        this.name = name;
        this.credit = credit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }
}
