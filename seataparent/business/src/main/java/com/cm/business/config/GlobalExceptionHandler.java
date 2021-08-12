package com.cm.business.config;


import com.cm.business.entity.R;
import com.cm.business.excption.MyExcption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类
 */
@Slf4j//日志输出
@ControllerAdvice

public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     * @param e
     * @return
     */
    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)//捕获全部的异常
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        System.out.println("执行了Exception异常");
        return R.error().message("执行了全局异常处理");
    }
    /**
     * 特定异常处理
     */
    @ExceptionHandler(ArithmeticException.class)//捕获全部的异常
    @ResponseBody
    public R error(ArithmeticException e){
        System.out.println("执行了ArithmeticException异常");
        e.printStackTrace();
        return R.error().message("执行ArithmeticException异常处理");
    }
    @ExceptionHandler(MyExcption.class)//捕获全部的异常
    @ResponseBody
    public R error(MyExcption e){
        e.printStackTrace();
        System.out.println("执行了自定义异常");
        return R.error().message("执行ArithmeticException异常处理");
    }
}
