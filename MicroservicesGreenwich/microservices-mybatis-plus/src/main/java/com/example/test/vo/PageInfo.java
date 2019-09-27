package com.example.test.vo;

public class PageInfo {

    /**
     * 当前页
     */
    private Long current;

    /**
     * 显示数量
     */
    private Long size;

    public PageInfo(Long current, Long size) {
        this.current = current;
        this.size = size;
    }

    public PageInfo() {
    }

    public Long getCurrent() {
        if(this.current == null){
            return Long.valueOf(0l);
        }
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getSize() {
        if(this.size == null){
            return Long.valueOf(0l);
        }
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
