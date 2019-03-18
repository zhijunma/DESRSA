package com.cn.school.aop;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import com.cn.school.utils.RedisUtil;
import com.cn.school.utils.request.RestRequest;
import com.cn.school.utils.request.RestRequestHeader;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * aop
 *
 * @author humin
 */
@Slf4j
@Aspect
@Component
public class ConAspect {
    /**
     * 定义切入点,拦截controller包其子包下的所有类的所有方法
     */
    private final String ExpGetResultDataPonit = "execution(public * com.cn.school.controller.web..*.*(..))";

    private static Class<?> clazz;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 拦截指定的方法,这里指只拦截TestService.getResultData这个方法
     */
    @Pointcut(ExpGetResultDataPonit)
    public void excuteService() {

    }


    /**
     * 执行方法前的拦截方法
     *
     * @param joinPoint
     */
    @Before("excuteService()")
    public void doBeforeMethod(JoinPoint joinPoint) throws Exception {
        System.out.println("我是前置通知，我将要执行一个方法了");
        //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        for (Object argItem : obj) {
            if (argItem instanceof RestRequest) {
                RestRequest restRequest = (RestRequest) argItem;
                Object o = restRequest.getBody();
                Map<String, Object> map = objectToMap(o);
                RestRequestHeader header = restRequest.getHeader();
                String token = header.getToken();
                if (token.isEmpty()) {
                    throw new Exception("token 不能為空！");
                }

                UserContextViewForm userContextViewForm = (UserContextViewForm) redisUtil.get(token);
                map.put("currId", userContextViewForm.getCurrId());
                map.put("currName", userContextViewForm.getCurrName());
                map.put("currTel", userContextViewForm.getCurrTel());
                map.put("currRole", userContextViewForm.getCurrRole());

                restRequest.setBody(mapToObject(map, clazz));
            }
        }
    }

    /**
     * 获取利用反射获取类里面的值和名称
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>(16);
        Class<?> newClazz = obj.getClass();
        clazz = obj.getClass();
        List<Field> fieldList = new ArrayList<>();
        //当父类为null的时候说明到达了最上层的父类(Object类)
        while (newClazz != null) {
            fieldList.addAll(Arrays.asList(newClazz.getDeclaredFields()));
            //得到父类,然后赋给自己
            newClazz = newClazz.getSuperclass();
        }
        for (Field field : fieldList) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }

    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null) {
            return null;
        }
        Object obj = beanClass.newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            Method setter = property.getWriteMethod();
            if (setter != null) {
                setter.invoke(obj, map.get(property.getName()));
            }
        }
        return obj;
    }


}
