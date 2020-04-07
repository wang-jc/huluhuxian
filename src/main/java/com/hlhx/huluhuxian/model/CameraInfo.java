package com.hlhx.huluhuxian.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: wangjc
 * @CreateDate: 2020/3/24
 * @Version: 1.0
 */
@Data
public class CameraInfo implements Serializable {
    static final long serialVersionUID = 1L;
    private Integer cameraId;
    private Integer regionId;
    private String indexCode;
    private String name;
    private Integer cameraType;
    private Integer num;
    private String cityNo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date time;



}
