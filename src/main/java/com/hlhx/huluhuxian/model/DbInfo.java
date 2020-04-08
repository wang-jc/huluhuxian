package com.hlhx.huluhuxian.model;

import lombok.Data;

/**
 * @Description:
 * @Author: wangjc
 * @CreateDate: 2020/3/25
 * @Version: 1.0
 */
@Data
public class DbInfo {
    private Integer id;
    private String ip;
    private String userName;
    private String password;
    private String name;
    private String baseName;
    private String port;
    private String type;
    private String areaName;

}
