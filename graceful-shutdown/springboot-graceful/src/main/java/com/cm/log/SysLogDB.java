package com.cm.log;


import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class SysLogDB {

    private String className;

    private String methodName;

    private String params;

    private Long exeuTime;

    private String remark;

    private String createDate;

}
