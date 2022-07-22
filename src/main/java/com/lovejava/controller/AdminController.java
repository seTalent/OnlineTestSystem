package com.lovejava.controller;

import com.lovejava.common.R;
import com.lovejava.pojo.Admin;
import com.lovejava.pojo.Paper;
import com.lovejava.pojo.User;
import com.lovejava.service.AdminService;
import com.lovejava.service.PaperService;
import com.lovejava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * 管理员相关功能
 * @author wuzhicheng
 * @create 2022-07-05 8:43
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    PaperService paperService;

    /**
     * 增加管理员
     * @param req
     * @param username 管理员用户名
     * @param password 密码
     * @return
     */
    @ResponseBody
    @PostMapping("/add")
    public R<Admin> add(HttpServletRequest req,
                        HttpServletResponse resp,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password){
        resp.setHeader("Access-Control-Allow-Origin","*");
        Admin admin1 = adminService.selectByUserName(username);
        if(admin1!=null){
            return R.error("用户名已存在");
        }
        password= DigestUtils.md5DigestAsHex(password.getBytes());
        Admin admin=new Admin(username,password);
        adminService.insert(admin);
        return R.success(admin);
    }

    /**
     * 管理员登录
     * @param req
     * @param username 管理员用户名
     * @param password 管理员密码
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public R<Admin> login(HttpServletRequest req,
                          HttpServletResponse resp,
                          @RequestParam("username") String username,
                          @RequestParam("password") String password){
        resp.setHeader("Access-Control-Allow-Origin","*");
        Admin admin=adminService.selectByUserName(username);
        if(admin==null){
            return R.error("用户名不存在");
        }
        password=DigestUtils.md5DigestAsHex(password.getBytes());
        if(!password.equals(admin.getPassword())){
            return R.error("密码错误");
        }
        HttpSession session = req.getSession();
        session.setAttribute("user",admin);
        session.setAttribute("logintime",new Date());
        session.setMaxInactiveInterval(900);
        return R.success(admin);
    }

    /**
     * 删除管理员
     * @param req
     * @param username 管理员用户名
     * @return
     */
    @ResponseBody
    @PostMapping("/delete")
    public R<String> delete(HttpServletRequest req,
                            HttpServletResponse resp,
                            @RequestParam("username") String username){
        resp.setHeader("Access-Control-Allow-Origin","*");
        Admin admin = adminService.selectByUserName(username);
        if(admin==null){
            return R.error("用户不存在");
        }
        int i=adminService.deleteByUserName(username);
        if(i<=0){
            return R.error("删除失败");
        }
        return R.success("删除成功");
    }

    /**
     * 根据用户名显示用户信息，用于回显
     * @return
     */
    @ResponseBody
    @PostMapping("/display")
    public R<Map<String,String>> dispAdmin(HttpServletResponse resp,
                                           HttpServletRequest req){
        resp.setHeader("Access-Control-Allow-Origin","*");
        Admin admin= (Admin) req.getSession().getAttribute("user");
        if(admin==null){
            return R.error("用户名不存在或未登录");
        }
        HashMap<String, String> map = new HashMap<>();

        map.put("aid", String.valueOf(admin.getAid()));
        map.put("userName",admin.getUserName());
        map.put("password",admin.getPassword());
        map.put("phone","18343985671");

        Date loginTime = (Date) req.getSession().getAttribute("logintime");
        Timestamp timestamp = new Timestamp(loginTime.getTime());
        map.put("loginTime",timestamp.toString());

        return R.success(map);
    }

    /**
     * 修改管理员密码
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     */
    @ResponseBody
    @PostMapping("/modify")
    public R<Admin> modAdmin(HttpServletResponse resp,
                             HttpServletRequest req,
                             @RequestParam("oldpassword") String oldPassword,
                             @RequestParam("newpassword") String newPassword){
        resp.setHeader("Access-Control-Allow-Origin","*");
        Admin admin = (Admin) req.getSession().getAttribute("user");
        if(admin==null) {
            return R.error("用户不存在");
        }
        oldPassword=DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        newPassword=DigestUtils.md5DigestAsHex(newPassword.getBytes());
        if(!oldPassword.equals(admin.getPassword())){
            return R.error("旧密码输入错误");
        }
        admin.setPassword(newPassword);
        int i = adminService.updateByPrimaryKey(admin);
        if(i<=0){
            return R.error("修改失败");
        }
        return R.success(admin);
    }

    /**
     * 根据用户名查询管理员
     * @param username 用户名
     * @return
     */
    @ResponseBody
    @PostMapping("/search")
    public R<List<Admin>> searchAdmins(HttpServletResponse resp,
                                       @RequestParam("username") String username){
        resp.setHeader("Access-Control-Allow-Origin","*");
        username="%"+username+"%";
        List<Admin> admins = adminService.selectByUserNameFuzzy(username);
        System.out.println(admins);
        if(admins.size()<=0){
            return R.error("找不到对应的用户");
        }
        return R.success(admins);
    }

    /**
     * 简单的模糊查找用户
     * @param req
     * @param username 用户名
     * @return
     */
    @ResponseBody
    @RequestMapping("/searchuser")
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
     * @param username 用户名
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteuser")
    public R<String> deleteUsers(HttpServletResponse resp,
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
     * 管理员修改用户
     * @param username 用户名
     * @param password 密码
     * @param major 专业
     * @return
     */
    @ResponseBody
    @RequestMapping("/moduser")
    public R<String> modUsers(HttpServletResponse resp,
                              @RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam("major") String major){
        resp.setHeader("Access-Control-Allow-Origin","*");
        User user = userService.selectByUserName(username);
        if(user==null){
            return R.error("没有对应的用户");
        }
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        user.setMajor(major);
        int i = userService.updateByPrimaryKey(user);
        if(i<=0){
            return R.error("修改失败");
        }
        return R.success("修改成功");
    }

    /**
     * 管理员设置试卷的题目数量
     * @param singleNumber 单选题数量
     * @param multiNumber 多选题数量
     * @param judgeNumber  判断题数量
     * @param saqNumber  简答题数量
     * @return 是否修改成功
     */
    @ResponseBody
    @PostMapping("/setpaper")
    public R<String> setPaper(HttpServletResponse resp,
                              HttpServletRequest req,
                              @RequestParam("singleNumber") Integer singleNumber,
                              @RequestParam("multiNumber") Integer multiNumber,
                              @RequestParam("judgeNumber") Integer judgeNumber,
                              @RequestParam("saqNumber")Integer saqNumber,
                              @RequestParam("major") String major){
        HttpSession session = req.getSession();
        Object admin = session.getAttribute("user");
        if (admin==null)return R.error("您没有权限");
        Paper paper = new Paper(major, singleNumber, multiNumber, judgeNumber, saqNumber);
        Paper originPaper = paperService.selectPaperByMajor(major);
        //判断是否存在该专业的试卷信息， 存在就修改，不存在就插入
        try{
            if(originPaper==null){
                paperService.insertOnePaper(paper);
            }else{
                paperService.updatePaperByMajor(paper);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return R.error("修改失败，专业不存在");
        }
        return R.success("修改成功");

    }

    /**
     * 根据专业查找用户
     * @param major 专业
     * @return
     */
    @ResponseBody
    @PostMapping("/searchuserbymajor")
    public R<List<User>> searchUserByMajor(@RequestParam("major") String major){
        List<User> users = userService.selectByMajor(major);
        return R.success(users);
    }

    /**
     * 获取用户名
     * @param request
     * @return 用户名
     */
    @ResponseBody
    @PostMapping("/adminname")
    public R<String> getAdminName(HttpServletRequest request){
        HttpSession session = request.getSession();
        Admin user = (Admin) (Admin) session.getAttribute("user");
        return R.success(user.getUserName());
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
     * 根据用户名和专业查找专业
     * @param username
     * @param major
     * @return
     */
    @ResponseBody
    @RequestMapping("/searchuserum")
    public R<List<User>> selectByNameAndMajor(@RequestParam("username") String username,
                                          @RequestParam("major") String major){
        username="%"+username+"%";
        List<User> users = userService.selectByNameAndMajor(username, major);
        if(users.size()<=0){
            return R.error("找不到用户");
        }
        return R.success(users);
    }
}
