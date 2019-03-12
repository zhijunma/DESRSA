package com.cn.school.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 功能描述: 异常处理
 *
 * @param:
 * @return:
 * @auther: YiTong
 * @date: 2019/2/10 10:28
 */
@RestControllerAdvice
@ControllerAdvice
public class ExceptionHander {

//
//    /**
//     * @功能描述: 捕获角色异常处理
//     * @param: 异常
//     * @return:
//     * @auther: YiTong
//     * @date: 2019/2/10 10:29
//     */
//    @ExceptionHandler(value = {AccessDeniedException.class, UsernameNotFoundException.class})
//    @ResponseBody
//    public Map<Object, Object> AccessDeniedException(Exception ex) {
//        /**
//         * 描述
//         */
//        String message = ex.getMessage();
//        /**
//         * 返回值
//         */
//        return JsonResult.Result(401, message);
//    }
//
//    /**
//     * @功能描述: 传值验证
//     * @param: 异常
//     * @return:
//     * @auther: YiTong
//     * @date: 2019/2/10 10:29
//     */
//    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
//    @ResponseBody
//    public Map<Object, Object> MethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//        //ex.printStackTrace();
//        /**
//         * 描述
//         */
//        String message = ex.getMessage();
//        /**
//         * 返回值
//         */
//        Map<Object, Object> result = JsonResult.Result(401, message);
//
//        List<ErrorValid> valids = new ArrayList<>();
//        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
//        for (ObjectError obj : allErrors
//        ) {
//            ErrorValid errorValid = new ErrorValid();
//            errorValid.setObjectName(obj.getObjectName());
//            errorValid.setDefaultMessage(obj.getDefaultMessage());
//            valids.add(errorValid);
//        }
//        result.put("errors", valids);
//        return result;
//    }
//
//    @Data
//    public class ErrorValid {
//        String objectName;
//        String defaultMessage;
//    }
//
//    /**
//     * @功能描述: body值为空
//     * @param: 异常
//     * @return:
//     * @auther: YiTong
//     * @date: 2019/2/10 10:29
//     */
//    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
//    @ResponseBody
//    public Map<Object, Object> HttpMessageNotReadableException(HttpMessageNotReadableException ex) {
//        ex.printStackTrace();
//        /**
//         * 描述
//         */
//        String message = ex.getMessage();
//        /**
//         * 返回值
//         */
//        return JsonResult.Result(401, "body不能为空");
//    }
}
