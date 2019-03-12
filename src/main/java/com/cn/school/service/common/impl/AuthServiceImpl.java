package com.cn.school.service.common.impl;

import com.cn.school.auth.config.AuthProperties;
import com.cn.school.auth.global.CookieConstants;
import com.cn.school.auth.utils.CookieUtil;
import com.cn.school.auth.utils.UserContextHolder;
import com.cn.school.dto.forms.auth.LoginUserViewForm;
import com.cn.school.dto.forms.auth.UserViewForm;
import com.cn.school.dto.info.UserContextInfo;
import com.cn.school.dto.info.vo.UserContextVO;
import com.cn.school.entity.DSUser;
import com.cn.school.mapper.wx.UserMapper;
import com.cn.school.service.common.AuthService;
import com.cn.school.utils.response.RestResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

/**
 * @author:HuMin Date:2019/3/5
 * Time:9:55
 */
@Service
public class AuthServiceImpl implements AuthService {

    /**
     * UserMapper
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 鉴权配置
     */
    @Autowired
    private AuthProperties authProperties;

    /**
     * 微信公众平台
     */
    private final Integer wxOs = 1;
    /**
     * web管理端
     */
    private final Integer webOs = 2;
    /**
     * 学员
     */
    private final Integer stuRole = 1;
    /**
     * 管理者
     */
    private final Integer manRole = 2;

    @Override
    @Transactional
    public UserContextVO login(UserViewForm restRequest,
                               HttpServletRequest request, HttpServletResponse response) {
        UserViewForm userViewForm = restRequest;
        if (userViewForm == null) {
//            throw new BusinessException(MsgCdConstant.SUPPLIER_LOGIN_INVALID, mss.getOrigMessage(MsgCdConstant.SUPPLIER_LOGIN_INVALID));
        }
        String account = userViewForm.getAccount();
        // 获取人员信息
        DSUser supplierUserinfo = userMapper.getUserInfoByTel(account);
        if (supplierUserinfo == null) {
//            throw new BusinessException(MsgCdConstant.SUPPLIER_USER_NOT_EXIST, mss.getOrigMessage(MsgCdConstant.SUPPLIER_USER_NOT_EXIST));
        }

//        processToken(supplierUserinfo.getMobilePhone());


//        if (webOs.equals(userViewForm.getOs()) && stuRole.equals(supplierUserinfo.getRole())) {
//            return null;
//        }
        UserContextVO userContext = new UserContextVO();
        userContext.setCurrId(supplierUserinfo.getGuid());
        userContext.setCurrName(supplierUserinfo.getUserName());
        userContext.setCurrRole(supplierUserinfo.getRole());
        userContext.setCurrTel(supplierUserinfo.getMobilePhone());

        // 校验登录密码
        String pass = DigestUtils.sha256Hex(DigestUtils.sha256Hex(userViewForm.getPassword()) + supplierUserinfo.getSalt());
        if (!StringUtils.equals(pass, supplierUserinfo.getPassword())) {
//            throw new BusinessException(MsgCdConstant.SUPPLIER_PASSWORD_ERROR, mss.getOrigMessage(MsgCdConstant.SUPPLIER_PASSWORD_ERROR));
        }

        HttpSession session = request.getSession();
        session.setAttribute(account, userContext);
        session.setMaxInactiveInterval(1000 * 3600 * 2);
        // 处理授权
        DSUser model = new DSUser();
        model.setGuid(supplierUserinfo.getGuid());
        model.setModTime(LocalDateTime.now());
        int count = userMapper.updateTime(model);
        if (count <= 0) {
//            throw new BusinessException(MsgCdConstant.UPDATE_FAIL, mss.getOrigMessage(MsgCdConstant.UPDATE_FAIL));
        }

        return userContext;
    }

    /**
     * 注销
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public RestResponse<String> logout(
            HttpServletRequest request, HttpServletResponse response) {
        UserContextInfo userContext = UserContextHolder.get();
        if (userContext == null) {
            return RestResponse.success().withMessage("成功注销！");
        }
        LoginUserViewForm loginUserViewForm = new LoginUserViewForm();
        loginUserViewForm.setToken(userContext.getToken());
        loginUserViewForm.setCurrTel(userContext.getCurrTel());
        removeLoginToken(request, response, authProperties.isSessionEnable());
        //清空缓存
        return RestResponse.success().withMessage("注销成功");
    }

    /**
     * 删除登录令牌
     *
     * @param request
     * @param response
     * @param sessionEnable
     */
    private void removeLoginToken(HttpServletRequest request, HttpServletResponse response,
                                  boolean sessionEnable) {
        if (sessionEnable) {
            // 启用Session方式
            HttpSession session = request.getSession();
            session.removeAttribute(CookieConstants.SUPPLIER_TOKEN_NAME);
        } else {
            // 启用Cookie方式
            // 删除登录Cookie
            CookieUtil.delectCookieByName(request, response, CookieConstants.SUPPLIER_TOKEN_NAME);
        }
    }
}
