package com.cn.school.controller.mzj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by guozhaohui628@gmail.com on 2018/12/17
 * Description:
 */
@RestController
public class test {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/dataSource")
    public String dataSource() {
        try {
            System.out.println("dataSource = "+dataSource);
            Connection conn = dataSource.getConnection();
            System.out.println("conn = "+conn);
            return "success";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "end.";
    }

}

