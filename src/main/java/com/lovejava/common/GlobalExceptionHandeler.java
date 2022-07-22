package com.lovejava.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局异常处理
 * @author wuzhicheng
 * @create 2022-07-14 11:13
 */
@ControllerAdvice(annotations = {Controller.class}) //选择需要处理异常的类
@ResponseBody //需要以json返回
public class GlobalExceptionHandeler {

    /**
     * 全局的异常处理
     * @return
     */
    @ExceptionHandler({Exception.class})
    public R<String> exceptionHandeler(Exception e){
        return R.error("出错啦！请检查你的网络设置！");
    }

}
