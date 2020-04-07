package com.hlhx.huluhuxian.model;

import java.util.List;

/**
 * @Author wangjunchao
 * @create 2020/2/3 23:43
 */
public class RespPageBean {
    private Long total;
    private List<?> data;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
