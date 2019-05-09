package com.cn.school.FormView;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("添加DES")
public class AddDESViewForm {
    /**
     * word
     */
    private String word;
    /**
     * key
     */
    private String key;
}
