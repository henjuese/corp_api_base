package com.h2ord.corp.api.util.model;

import java.util.List;

/**
 * Created by chy on 14-10-9.
 */
public class Page {

    private int count;
    private List results;
    private int next;
    private int previous;
    private int pageSize;
    private int currentPage;
    private int pageCount;//总的页数


    public Page(List results, int count, int next, int previous, int pageSize, int currentPage, int pageCount) {
        this.results = results;
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.pageCount = pageCount;

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List getResults() {
        return results;
    }

    public void setResults(List results) {
        this.results = results;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getPrevious() {
        return previous;
    }

    public void setPrevious(int previous) {
        this.previous = previous;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
