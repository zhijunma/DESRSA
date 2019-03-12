package com.cn.school.mapstruct;

import com.cn.school.dto.forms.WxInsertUserViewForm;
import com.cn.school.dto.info.po.InsertUserPO;
import org.mapstruct.Mapper;

/**
 * @author:HuMin Date:2019/3/1
 * Time:17:13
 */
@Mapper(componentModel = "spring")
public interface UserMapStruct {
    InsertUserPO InsertUserViewFormToInsertUserPO(WxInsertUserViewForm viewForm);
}
