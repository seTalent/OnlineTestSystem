package com.lovejava.controller;

import com.lovejava.common.R;
import com.lovejava.pojo.Major;
import com.lovejava.service.MajorService;
import com.lovejava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 和专业有关的功能
 * @author wuzhicheng
 * @create 2022-07-08 8:53
 */
@Controller
@RequestMapping("/major")
public class MajorController {
    @Autowired
    MajorService majorService;

    /**
     * 获取所有的专业
     * @return
     */
    @PostMapping("/disp")
    @ResponseBody
    public R<List<String>> getMajor(HttpServletResponse resp){
        resp.setHeader("Access-Control-Allow-Origin","*");
        List<String> major = majorService.getMajor();
        return R.success(major);
    }
}
