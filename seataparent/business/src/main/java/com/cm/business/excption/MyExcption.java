package com.cm.business.excption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyExcption extends RuntimeException{
    private Integer code;//状态码
    private String msg;//异常信息
}
