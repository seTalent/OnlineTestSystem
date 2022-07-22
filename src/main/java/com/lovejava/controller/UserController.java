package com.lovejava.controller;


import com.lovejava.common.R;
import com.lovejava.pojo.User;
import com.lovejava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户相关功能
 * @author wuzhicheng
 * @create 2022-07-05 8:43
 */
@Controller
@RequestMapping("/user")
@Transactional
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param req
     * @param username 用户名
     * @param password 密码
     * @return 成功返回对象，失败返回null
     */
    @ResponseBody
    @PostMapping("/login")
    public R<User> login(HttpServletRequest req,
                        HttpServletResponse resp,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password,
                         @RequestParam("code") String code){
        HttpSession session = req.getSession();
        if(!code.equals(req.getSession().getAttribute("checkcode"))){
            return R.error("验证码错误");
        }
        User user=userService.selectByUserName(username);
        password=DigestUtils.md5DigestAsHex(password.getBytes());
        if(user==null){
            return R.error("用户名不存在");
        }
        if(!password.equals(user.getPassword())){
            return R.error("密码错误");
        }

        session.setAttribute("user",user);
        session.setAttribute("logintime",new Date());
        session.setMaxInactiveInterval(900);
        return R.success(user);
    }

    /**
     * 用户注册
     * @param req
     * @param username 用户名
     * @param password 密码
     * @param major 专业
     * @return
     */
    @ResponseBody
    @PostMapping("/signup")
    public R<User> signup(HttpServletRequest req,
                          HttpServletResponse resp,
                          @RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("major") String major){
        resp.setHeader("Access-Control-Allow-Origin","*");
        User user=userService.selectByUserName(username);
        if(user!=null){
            return R.error("用户名已存在");
        }
        password=DigestUtils.md5DigestAsHex(password.getBytes());
        User createUser=new User(username,password,major);
        int insert = userService.insert(createUser);
        if(insert<=0){
            return R.error("注册错误");
        }
        return R.success(createUser);
    }

    /**
     * 根据用户名查找用户（修改时的账户回显）
     * @param req
     * @return
     */
    @ResponseBody
    @PostMapping("/display")
    public R<Map<String,String>> dispUser(HttpServletRequest req,
                                          HttpServletResponse resp){
        //resp.setHeader("Access-Control-Allow-Origin","*");
        User user= (User) req.getSession().getAttribute("user");
        Map<String, String> map = new HashMap<>();
        map.put("userName",user.getUserName());
        map.put("major",user.getMajor());

        Date loginTime = (Date) req.getSession().getAttribute("logintime");
        Timestamp timestamp = new Timestamp(loginTime.getTime());
        map.put("loginTime",timestamp.toString());
        String phone = userService.getPhone(user.getUid());
        String beginStr = phone.substring(0, 3);
        String endStr = phone.substring(7, 11);
        map.put("phone",beginStr+"****"+endStr);

        return R.success(map);
    }

    /**
     * 修改用户
     * @param req
     * @param oldPassword 旧密码
     * @return
     */
    @ResponseBody
    @RequestMapping("/modify")
    public R<User> modUser(HttpServletRequest req,
                           HttpServletResponse resp,
                           @RequestParam("oldpassword") String oldPassword,
                           @RequestParam("newpassword") String newPassword){
        resp.setHeader("Access-Control-Allow-Origin","*");
        User user = (User) req.getSession().getAttribute("user");
        oldPassword=DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        newPassword=DigestUtils.md5DigestAsHex(newPassword.getBytes());
        if(!oldPassword.equals(user.getPassword())){
            return R.error("旧密码输入错误");
        }
        user.setPassword(newPassword);
        int i = userService.updateByPrimaryKey(user);
        if(i<=0){
            return R.error("修改失败");
        }
        return R.success(user);
    }

    /**
     * 简单的模糊查找用户
     * @param req
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping("/search")
    public R<List<User>> searchUsers(HttpServletRequest req,
                                     HttpServletResponse resp,
                                     @RequestParam("username") String username){
        resp.setHeader("Access-Control-Allow-Origin","*");
        username="%"+username+"%";
        List<User> users = userService.selectByUserNameFuzzy(username);
        if(users.size()==0){
            return R.error("找不到对应的用户");
        }
        return R.success(users);
    }

    /**
     * 根据用户名删除用户
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public R<String> delete(HttpServletResponse resp,
                            @RequestParam("username") String username){
        resp.setHeader("Access-Control-Allow-Origin","*");
        User user = userService.selectByUserName(username);
        if(user==null){
            return R.error("没有对应的用户");
        }
        int i = userService.deleteByUserName(username);
        if(i<=0){
            return R.error("删除失败");
        }
        return R.success("删除成功");
    }

    /**
     * 用户退出
     * @param req
     * @param resp
     * @return
     */
    @ResponseBody
    @RequestMapping("/logout")
    public R<String> logout(HttpServletRequest req,
                            HttpServletResponse resp){
        resp.setHeader("Access-Control-Allow-Origin","*");
        req.getSession().removeAttribute("user");
        return R.success("退出成功");
    }

    /**
     * 使用手机号登录
     * @param req
     * @param resp
     * @param phone 手机号
     * @param code 验证码
     * @return
     */
    @ResponseBody
    @RequestMapping("/loginwp")
    public R<User> loginWithPhone(HttpServletRequest req,
                                    HttpServletResponse resp,
                                    @RequestParam("phone") String phone,
                                    @RequestParam("code") String code){
        User user = userService.selectByPhone(phone);
        if(user==null){
            return R.error("找不到此用户");
        }
        if(!code.equals(req.getSession().getAttribute("phonecode"))){
            return R.error("验证码错误");
        }
        HttpSession session = req.getSession();
        session.setAttribute("user",user);
        session.setAttribute("logintime",new Date());
        session.setMaxInactiveInterval(900);
        return R.success(user);
    }

    /**
     * 用于随时获取用户的用户名
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/username")
    public R<String> getUserName(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        return R.success(user.getUserName());

    }

    /**
     * 重置密码
     * @param req
     * @param resp
     * @param phone 手机号
     * @param password 密码
     * @param code 验证码
     * @return
     */
    @ResponseBody
    @PostMapping("/reset")
    public R<User> resetPassword(HttpServletRequest req, HttpServletResponse resp,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("newpassword") String password,
                                 @RequestParam("code") String code){
        User user = userService.selectByPhone(phone);
        if(user==null){
            return R.error("找不到此用户");
        }
        if(!code.equals(req.getSession().getAttribute("phonecode"))){
            return R.error("验证码错误");
        }
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userService.updateByPrimaryKey(user);
        return R.success(user);
    }

}
