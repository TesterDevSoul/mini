package top.testeru.mini.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.testeru.mini.util.R;
import top.testeru.mini.util.ResultCode;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project top.testeru.mini
 * @Description
 * @createTime 2023年03月27日 15:37:23
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class})
    public R exceptionHandler(Exception e) {
        //控制台打印异常，万一出现异常调试 记录日志、通知运维、通知开发
        e.printStackTrace();
        return R.error(ResultCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public R arithmeticExceptionHandler(HttpServletRequest req,
                                        UserNotFoundException userNotFoundException) {
        //获取请求路径
        String requestUrl = req.getRequestURI();
        //获取请求方法 GET POST
        String method = req.getMethod();
        //命令行打印trace异常
        userNotFoundException.printStackTrace();
        HashMap<String, Object> map = new HashMap<>(){{
            put("error api", method + " "+ requestUrl);
            put("error msg", userNotFoundException.getMessage());
        }};
        //统一返回结果 5001, "系统繁忙，请稍后重试"
        return R.error(ResultCode.INTERNAL_SERVER_ERROR).data(map);
    }

}
