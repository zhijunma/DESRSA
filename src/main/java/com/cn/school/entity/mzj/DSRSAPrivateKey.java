package com.cn.school.entity.mzj;

import lombok.Data;

/**
 * @author mzj Date:4.22
 * Time 19:44
 */
@Data
public class DSRSAPrivateKey {
    /**
     * id
     */
    private Long id;
    /**
     * rsaId
     */
    private Long rsaId;
    /**
     * priveteKey
     */
    private String privateKey;
}
