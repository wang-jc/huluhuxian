package com.hlhx.huluhuxian.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: wangjc
 * @CreateDate: 2020/3/23
 * @Version: 1.0
 */
@Data
public class AlarmLog implements Serializable {
    static final long serialVersionUID = 1L;
    private String alarmLogId;
    private int controlUnitId;
    private int alarmEventId;
    private String alarmInputSource;
    private int alarmInputType;
    private String alarmEventName;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private String alarmStartTime;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private String alarmStopTime;
    private int triggerRecord;
    private String logTxt;
    private String logRemark;
    private int intRev1;
    private int intRev2;
    private String strRev1;
    private String strRev2;
    private String cityNo;
    private String indexCode;

}
