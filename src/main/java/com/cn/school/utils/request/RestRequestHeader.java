package com.cn.school.utils.request;

import com.cn.school.constant.SpringContextHolder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.env.Environment;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author:HuMin Date:2019/3/5
 * Time:10:12
 */
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestRequestHeader implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4363387588926602131L;

    /**
     * key:spring.application.name
     */
    private static final String KEY_APP = "spring.application.name";

    /**
     * APP
     */
    private String app = null;

    /**
     * 分页大小
     */
    private int pageSize;

    /**
     * 页码
     */
    private int pageNum;

    /**
     * 排序字段
     */
    private List<RestRequestSort> sorts;

    /**
     * 扩展字段
     */
    private String ext;

    /**
     * 构造方法
     */
    public RestRequestHeader() {
        super();
        app = Optional.ofNullable(app).orElse(SpringContextHolder.getBean(Environment.class).getProperty(KEY_APP));
    }
}
