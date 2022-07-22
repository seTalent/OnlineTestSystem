package com.lovejava.controller;

import com.lovejava.common.R;
import com.lovejava.util.CheckCodeUtil;
import com.lovejava.util.PhoneCheckUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用于获取手机验证码和图形验证码
 * @author wuzhicheng
 * @create 2022-07-13 23:10
 */
@Controller
public class CheckCodeController {

    /**
     * 获取图形验证码图片
     * @param resp
     * @param req
     * @return
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("/checkcode")
    public R<String> getCheckCode(HttpServletResponse resp,
                                  HttpServletRequest req) throws IOException {
        HttpSession session = req.getSession();
        ServletOutputStream outputStream = resp.getOutputStream();
        String s = CheckCodeUtil.outputVerifyImage(100, 50, outputStream, 4);
        System.out.println("验证码是："+s);
        session.setAttribute("checkcode",s);
        System.out.println("session"+session.getAttribute("checkcode"));
        session.setMaxInactiveInterval(300);
        return R.success(s);
    }

    /**
     * 向用户手机发送验证码
     * @param resp
     * @param req
     * @param phone 手机号
     * @return
     */
    @ResponseBody
    @PostMapping("/phonecode")
    public R<String> sendPhoneCode(HttpServletResponse resp,
                                   HttpServletRequest req,
                                   @RequestParam("phone") String phone){
        String code = PhoneCheckUtil.sendCode(phone);
        req.getSession().setAttribute("phonecode",code);
        return R.success(code);
    }
}
