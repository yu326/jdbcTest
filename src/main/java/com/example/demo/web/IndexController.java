package com.example.demo.web;

import com.example.demo.dao.JdbcBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by koreyoshi on 2018/1/31.
 */
@Controller
public class IndexController {
    @Autowired
    private JdbcBaseDao jdbcBaseDao;

    @ResponseBody
    @RequestMapping(value = "JdbcTest", method = RequestMethod.GET)
    public List<String> Index() {
        List<String> response = null;
//        jdbcBaseDao.queryAll();
        return response;
    }
}
