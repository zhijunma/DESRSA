package com.cn.school.FormView;

import lombok.Data;

@Data
public class GetRSAViewForm {
    /**
     * id
     */
    private Long id;
    /**
     * id
     */
    private Long rsaId;
    /**
     * password
     */
    private String password;
    /**
     * priveteKey
     */
    private String privateKey;
}
