package cn.itcast.domain;

import java.util.List;

/**
 * 分页的实体类
 */
public class PageBean<T> {
    int totalCount;  // 总记录数
    int totalPage;   // 总的页码
    int currentPage; // 当前页码
    List<T> list;    // 每一页的数据
    int rows;        // 每一页展示的记录条数

    public PageBean() {
    }

    public PageBean(int totalCount, int totalPage, int currentPage, List<T> list, int rows) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.list = list;
        this.rows = rows;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", list=" + list +
                ", rows=" + rows +
                '}';
    }
}
