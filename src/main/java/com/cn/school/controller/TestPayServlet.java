package com.cn.school.controller;

import com.cn.school.service.GateWayService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <一句话功能简述>
 * <功能详细描述>测试支付（统一入口）
 *
 * @author Administrator
 * @version [版本号, 2014-8-28]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
@WebServlet(name = "TestPayServlet",urlPatterns = "/first")
public class TestPayServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        GateWayService service = new GateWayService();
        String method = req.getParameter("method");
        try {
            if ("submitOrderInfo".equals(method)) {
                service.pay(req, resp);
            } else if ("queryOrder".equals(method)) {
                service.query(req, resp);
            } else if ("queryRefund".equals(method)) {
                service.refundQuery(req, resp);
            } else if ("submitRefund".equals(method)) {
                service.refund(req, resp);
            }
        } catch (Exception e) {
            log.error("操作失败，原因：", e);
        }
    }
}
