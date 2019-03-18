package com.cn.school.service.web;

import com.cn.school.dto.forms.usermanage.*;
import com.cn.school.utils.response.RestResponse;

import java.util.List;


public interface UsersService {

    /**
     * 用户查看个人信息
     *
     * @param userViewForm
     * @return
     */
    RestResponse getUser(GetUserViewForm userViewForm);

    /**
     * 用户修改个人信息
     *
     * @param userViewForm
     * @return
     */
    RestResponse updateUsers(UpdateUserViewForm userViewForm);

    /**
     * 教练员添加，教练员信息只能由管理员添加
     *
     * @param insertCoachViewForm
     * @return
     */
    RestResponse addCoach(InsertCoachViewForm insertCoachViewForm);

    /**
     * 教练员信息一览
     *
     * @param getCoachViewForm
     * @return
     */
    List getCoachs(GetCoachsViewForm getCoachViewForm);

    /**
     * 教练员删除,假删除（更新状态）
     *
     * @param deleteCoachViewForm
     * @return
     */
    RestResponse deleteCoach(DeleteCoachViewForm deleteCoachViewForm);

    /**
     * 教练员详情查看（根据身份证号查询教练员）
     *
     * @param getCoachViewForm
     * @return
     */
    RestResponse getCoach(GetCoachViewForm getCoachViewForm);

    /**
     * 教练员信息修改（只能由管理员修改）
     *
     * @param updateCoachViewForm
     * @return
     */
    RestResponse updateCoach(UpdateCoachViewForm updateCoachViewForm);
    /**
     * 学员信息一览
     *
     * @param getStuViewForm
     * @return
     */
    List getStu(GetStuViewForm getStuViewForm);

}
