package com.rose.yaj.exeptionHandler;

import com.rose.yaj.common.GenericResponse;
import com.rose.yaj.common.ServiceError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody  //为了能够返回数据
    public GenericResponse error(Exception e){
        e.printStackTrace();
        return GenericResponse.response(false, ServiceError.GLOBAL_ERROR.getCode(), e.getMessage(), null);

    }
    //这样就不会返回500的那个界面了

    //还需要有特殊异常的处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody  //为了能够返回数据
    public GenericResponse error(ArithmeticException e){
        e.printStackTrace();
        return GenericResponse.response(ServiceError.GLOBAL_ERROR,"执行了全局处理异常" );
    }

    @ExceptionHandler(RRException.class)
    @ResponseBody  //为了能够返回数据
    public GenericResponse error(RRException e){
        e.printStackTrace();
        return GenericResponse.response(false,e.getCode(),e.getMsg(),null );
    }
    //第三种异常就是自定义异常处理
    //先自己建一个自定义异常类；继承运行时异常，然后在抛出

//    @ResponseBody
//    @ExceptionHandler(JWTDecodeException.class)
//    public Object JWTDecodeException(Exception e) {
//        String msg = e.getMessage();
//        if (msg == null || msg.equals("")) {
//            msg = "服务器出错!";
//        }
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("code", "10");
//        jsonObject.put("msg", "token验证失败");
//        return jsonObject;
//    }


    private class JWTDecodeException extends Throwable {
    }
}
