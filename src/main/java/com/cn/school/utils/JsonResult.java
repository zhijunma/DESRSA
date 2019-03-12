package com.cn.school.utils;

import java.util.HashMap;
import java.util.Map;

public  class JsonResult {
    /**
     * 状态码
     */
    static int status=200;
    static String msg= "获取成功";

    /**
     * 有参数构造函数
     * @param status
     * @param msg
     */
    public JsonResult(int status, String msg) {
        this.status=status;
        this.msg=msg;
    }


    /**
     * 返回结果响应json
     * @param code
     * @param msg
     * @return
     */
    public static Map<Object, Object> Result(int code, String msg){
        Map<Object, Object> map = Result();
        map.put("status",code);
        map.put("msg",msg);
        return map;
    }
    public static Map<Object, Object> Result(){
        Map<Object, Object> map = new HashMap<>();
        return map;
    }
}
