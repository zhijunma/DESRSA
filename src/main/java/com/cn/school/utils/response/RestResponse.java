package com.cn.school.utils.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.cn.school.constant.CodeConstant;
import com.cn.school.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;


/**
 * 响应对象
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestResponse<T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4118386780685379201L;

    /**
     * 非异常调用栈索引位置
     */
    private static final int STACK_TRACE_ELEMENT_INX = 2;

    /**
     * 响应消息头
     */
    private RestResponseHeader header;

    /**
     * 响应消息体(泛型)
     */
    private T body = null;

    /**
     * 构造方法
     */
    public RestResponse() {
        super();
        this.header = new RestResponseHeader();
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    /**
     * 构造方法
     *
     * @param header 响应消息头
     * @param body   响应消息体(泛型)
     */
    public RestResponse(RestResponseHeader header, T body) {
        super();
        this.header = header;
        this.body = body;
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    /**
     * 构造方法
     *
     * @param header 响应消息头
     */
    public RestResponse(RestResponseHeader header) {
        super();
        this.header = header;
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    /**
     * 构造方法
     *
     * @param body 响应消息体(泛型)
     */
    public RestResponse(T body) {
        super();
        this.header = new RestResponseHeader();
        this.body = body;
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    /**
     * 构造方法
     *
     * @param code    状态码
     * @param message 状态描述
     */
    public RestResponse(String code, String message) {
        super();
        this.header = new RestResponseHeader(code, message);
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    /**
     * 构造方法
     *
     * @param code     状态码
     * @param message  状态描述
     * @param errTrace 异常堆栈信息
     */
    public RestResponse(String code, String message, String errTrace) {
        super();
        this.header = new RestResponseHeader(code, message, errTrace);
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    /**
     * 构造方法
     *
     * @param code     状态码
     * @param message  状态描述
     * @param errType  异常类型
     * @param errTrace 异常堆栈信息
     */
    public RestResponse(String code, String message, String errType, String errTrace) {
        super();
        this.header = new RestResponseHeader(code, message, errType, errTrace);
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    /**
     * 构造方法
     *
     * @param code       状态码
     * @param message    状态描述
     * @param subCode    二级状态码
     * @param subMessage 二级状态描述
     * @param errType    异常类型
     * @param errTrace   异常堆栈信息
     */
    public RestResponse(String code, String message, String subCode, String subMessage, String errType,
                        String errTrace) {
        super();
        this.header = new RestResponseHeader(code, message, subCode, subMessage, errType, errTrace);
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    /**
     * 构造方法
     *
     * @param code    状态码
     * @param message 状态描述
     * @param body    响应消息体(泛型)
     */
    public RestResponse(String code, String message, T body) {
        super();
        this.header = new RestResponseHeader(code, message);
        this.body = body;
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    /**
     * 获取状态码
     *
     * @return 状态码
     */
    @JsonIgnore
    @JSONField(serialize = false)
    public String fetchCode() {
        return this.getHeader() != null ? this.getHeader().getCode() : null;
    }

    /**
     * 获取状态描述
     *
     * @return 状态描述
     */
    @JsonIgnore
    @JSONField(serialize = false)
    public String fetchMessage() {
        return this.getHeader() != null ? this.getHeader().getMessage() : null;
    }

    /**
     * 获取二级状态码
     *
     * @return 二级状态码
     */
    @JsonIgnore
    @JSONField(serialize = false)
    public String fetchSubCode() {
        return this.getHeader() != null ? this.getHeader().getSubCode() : null;
    }

    /**
     * 获取二级状态描述
     *
     * @return 二级状态描述
     */
    @JsonIgnore
    @JSONField(serialize = false)
    public String fetchSubMessage() {
        return this.getHeader() != null ? this.getHeader().getSubMessage() : null;
    }

    /**
     * 静态构造方法，处理成功
     */
    public static <T> RestResponse<T> success() {
        return new RestResponse<T>();
    }

    /**
     * 静态构造方法，处理成功
     *
     * @param body 响应消息体(泛型)
     */
    public static <T> RestResponse<T> success(T body) {
        return new RestResponse<T>(body);
    }

    /**
     * 静态构造方法，处理失败
     */
    public static <T> RestResponse<T> error() {
        RestResponse<T> rest = new RestResponse<T>();
        RestResponseHeader header = new RestResponseHeader();
        header.setCode(CodeConstant.ERRER);
        rest.setHeader(header);
        return rest;
    }

    /**
     * 静态构造方法，处理失败
     *
     * @param body 响应消息体(泛型)
     */
    public static <T> RestResponse<T> error(T body) {
        RestResponse<T> rest = new RestResponse<T>(body);
        RestResponseHeader header = new RestResponseHeader();
        header.setCode(CodeConstant.ERRER);
        rest.setHeader(header);
        return rest;
    }

    /**
     * Code静态构造方法
     *
     * @param code 响应消息体(泛型)
     */
    public static <T> RestResponse<T> buildWithCode(String code) {
        RestResponse<T> rr = new RestResponse<T>(code, null);
        rr.makeStackTrace(Thread.currentThread().getStackTrace());
        return rr;
    }

    /**
     * 设置状态码
     *
     * @param code 状态码
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @SuppressWarnings("rawtypes")
    public RestResponse withCode(String code) {
        this.header.setCode(code);
        return this;
    }

    /**
     * 设置状态描述
     *
     * @param message 状态描述
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @SuppressWarnings("rawtypes")
    public RestResponse withMessage(String message) {
        this.header.setMessage(message);
        return this;
    }

    /**
     * 设置状态描述
     *
     * @param messagePattern 状态描述
     * @param argArray       替换参数
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @SuppressWarnings("rawtypes")
    public RestResponse withMessage(String messagePattern, Object... argArray) {
        this.header.setMessage(StringUtils.formatMessage(messagePattern, argArray));
        return this;
    }

    /**
     * 设置二级状态码
     *
     * @param subCode 二级状态码
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @SuppressWarnings("rawtypes")
    public RestResponse withSubCode(String subCode) {
        this.header.setSubCode(subCode);
        return this;
    }

    /**
     * 设置二级状态描述
     *
     * @param subMessage 二级状态描述
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @SuppressWarnings("rawtypes")
    public RestResponse withSubMessage(String subMessage) {
        this.header.setSubMessage(subMessage);
        return this;
    }

    /**
     * 设置二级状态描述
     *
     * @param subMessagePattern 二级状态描述
     * @param argArray          替换参数
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @SuppressWarnings("rawtypes")
    public RestResponse withSubMessage(String subMessagePattern, Object... argArray) {
        this.header.setSubMessage(StringUtils.formatMessage(subMessagePattern, argArray));
        return this;
    }

    /**
     * 设置异常类型
     *
     * @param errType 异常类型
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @SuppressWarnings("rawtypes")
    public RestResponse withErrType(String errType) {
        this.header.setType(errType);
        return this;
    }

    /**
     * 设置异常堆栈信息
     *
     * @param errTrace 异常堆栈信息
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @SuppressWarnings("rawtypes")
    public RestResponse withErrTrace(String errTrace) {
        this.header.setErrTrace(errTrace);
        return this;
    }

    /**
     * 设置响应消息体(泛型)
     *
     * @param body 响应消息体(泛型)
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @SuppressWarnings("rawtypes")
    public RestResponse withBody(T body) {
        this.body = body;
        return this;
    }

    /**
     * 设置响应消息体(泛型)
     *
     * @param stackTraceElements 调用栈
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @SuppressWarnings("rawtypes")
    public RestResponse withStackTraceElements(StackTraceElement[] stackTraceElements) {
        this.header.setStackTraceElements(stackTraceElements);
        return this;
    }

    /**
     * 判断response是正确的
     *
     * @return
     */
    @JsonIgnore
    @JSONField(serialize = false)
    public boolean isSuccess() {
        if (this.header == null || org.apache.commons.lang.StringUtils.isBlank(header.getCode())) {
            return false;
        }

        return org.apache.commons.lang.StringUtils.equals(header.getCode(), CodeConstant.SUCCESS);
    }

    /**
     * 判断response是错误的
     *
     * @return
     */
    @JsonIgnore
    @JSONField(serialize = false)
    public boolean isFailure() {
        return !isSuccess();
    }

    /**
     * 转换RestResponse， 因为返回对象的泛型不一样， 有时个需要转换
     *
     * @param response
     * @return
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @SuppressWarnings({"rawtypes", "unchecked"})
    public RestResponse transfer() {
        RestResponse restResponse = new RestResponse();
        restResponse.setHeader(getHeader());
        restResponse.setBody(getBody());
        return restResponse;
    }

    /**
     * 获取非异常调用栈元素
     *
     * @param stackTraceElements 调用栈列表
     */
    private void makeStackTrace(StackTraceElement[] stackTraceElements) {
        if (stackTraceElements != null && stackTraceElements.length > 1) {
            this.header.setStackTraceElements(new StackTraceElement[]{stackTraceElements[STACK_TRACE_ELEMENT_INX]});
        }
    }
}
