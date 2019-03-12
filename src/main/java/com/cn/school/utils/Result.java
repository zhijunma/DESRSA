package com.cn.school.utils;

/**
 * @author:HuMin Date:2019/3/1
 * Time:16:19
 */
public class Result<T> {
    private String message;
    private int retCode;
    private T data;

    private Result(T data) {
        this.retCode = 0;
        this.message = "成功";
        this.data = data;
    }

    private Result(CodeMsg cm) {
        if (cm == null) {
            return;
        }
        this.retCode = cm.getRetCode();
        this.message = cm.getMessage();
    }

    /**
     * 成功时候的调用
     *
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    /**
     * 成功，不需要传入参数
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Result<T> success() {
        return (Result<T>) success("");
    }

    /**
     * 失败时候的调用
     *
     * @return
     */
    public static <T> Result<T> error(CodeMsg cm) {
        return new Result<T>(cm);
    }

    /**
     * 失败时候的调用,扩展消息参数
     *
     * @param cm
     * @param msg
     * @return
     */
    public static <T> Result<T> error(CodeMsg cm, String msg) {
        CodeMsg newCodeMsg = null;
        try {
            newCodeMsg = (CodeMsg) cm;
        } catch (Exception e) {
            e.printStackTrace();
        }
        newCodeMsg.setMessage(cm.getMessage() + "--" + msg);
        return new Result<T>(newCodeMsg);
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getRetCode() {
        return retCode;
    }

}
