package com.cn.school.entity.mzj;

import lombok.Data;

/**
 * @author mzj Date:4.22
 * Time 19:44
 */
@Data
public class DSRSA {
    /**
     * id
     */
    private Long id;
    /**
     * word
     */
    private String word;
    /**
     * password
     */
    private String password;
    /**
     * publicKey
     */
    private String publicKey;
    /**
     * priveteKey
     */
    private String privateKey;
}
