
package com.cn.school.context;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 用户上下文
 */
@Data
@NoArgsConstructor
public class UserContext implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7290452523159581787L;

    /**
     * 用户id
     */
    private Long currId;

    /**
     * 用户名
     */
    private String currName;

    /**
     * 用户电话号码
     */
    private String currTel;

    /**
     * 用户角色
     */
    private Integer currRole;

    /**
     * 用户所属供应商
     */
    private Long currSupplierId;

    /**
     * 用户所属供应商名称
     */
    private String currSupplierName;

    /**
     * 用户登录授权令牌
     */
    private String token;

    /**
     * 超期时长毫秒
     */
    private long expiredMillis = 0;
    /**
     * 最后查询时间
     */
    private long lastQueryTime = 0;
    /**
     * 最后更新时间
     */
    private long lastModifyTime = System.currentTimeMillis();

    /**
     * URI
     */
    private List<String> uriList;

    private Boolean initFlag;

}
