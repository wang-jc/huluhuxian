package com.hlhx.huluhuxian.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: wangjc
 * @CreateDate: 2020/3/23
 * @Version: 1.0
 */
@Data
public class ControlUnit implements Serializable{
    static final long serialVersionUID = 1L;
    private Integer controlUnitId;
    private String name;
    private String indexCode;
    private Integer num;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date time;


}
