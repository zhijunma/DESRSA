package com.cn.school.exception;

import com.cn.school.utils.response.RestResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@RestControllerAdvice
public class ExceptionHander {
    @ExceptionHandler(UserValidationException.class)
    public RestResponse UserValidationException(Exception ex) {
        /**
         * 描述
         */
        String message = ex.getMessage();
        /**
         * 返回值
         */
        log.debug(message);
        return RestResponse.error(message);
    }

    @ExceptionHandler(RuntimeException.class)
    public RestResponse RuntimeException(Exception ex) {
        /**
         * 描述
         */
        String message = ex.getMessage();
        /**
         * 返回值
         */
        log.debug(message);
        return RestResponse.error(message);
    }

    /**
     * @功能描述: 传值验证
     * @param: 异常
     * @return:
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseBody
    public RestResponse MethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        /**
         * 描述
         */
        String message = ex.getMessage();
        /**
         * 返回值
         */
        log.debug(message);
        RestResponse result = RestResponse.error(message);
        List<ErrorValid> valids = new ArrayList<>();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        for (ObjectError obj : allErrors
        ) {
            ErrorValid errorValid = new ErrorValid();
            errorValid.setObjectName(obj.getObjectName());
            errorValid.setDefaultMessage(obj.getDefaultMessage());
            valids.add(errorValid);
        }

        return result.withBody(valids);
    }

    @Data
    public class ErrorValid {
        String objectName;
        String defaultMessage;
    }

    /**
     * @功能描述: body值为空
     * @param: 异常
     * @return:
     */
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseBody
    public RestResponse HttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ex.printStackTrace();
        /**
         * 返回值
         */
        log.debug("登录错误");
        return RestResponse.error("登录错误");
    }

    /**
     * @功能描述: body值为空
     * @param: 异常
     * @return:
     */
    @ExceptionHandler(value = {NullPointerException.class})
    @ResponseBody
    public RestResponse NullPointerException(NullPointerException ex) {
        ex.printStackTrace();
        /**
         * 返回值
         */
        log.debug("空指针异常！");
        return RestResponse.error(ex.getMessage()).withMessage("空指针异常！");
    }
}
