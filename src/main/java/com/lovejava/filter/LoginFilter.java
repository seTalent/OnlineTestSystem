package com.lovejava.filter;

import com.lovejava.pojo.Admin;
import com.lovejava.pojo.User;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器
 * @author wuzhicheng
 * @create 2022-07-12 8:34
 */
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginFilter implements Filter {

    //支持通配符的路径匹配器
    public static final AntPathMatcher PATH_MATCHER=new AntPathMatcher();

    //不需要被检查的路径
    public static final String[] accessUrls={"/onlinetest/user/login",     //用户登录
                                            "/onlinetest/admin/login",     //管理员登录
                                            "/onlinetest/user/loginwp",    //用户通过手机登录
                                            "/onlinetest/user/signup",     //用户注册
                                            "/onlinetest/res/**",          //静态资源
                                            "/onlinetest/major/**",        //获得专业信息
                                            "/onlinetest/user/reset",      //重置用户密码
                                            "/onlinetest/phonecode",       //获得手机验证码
                                            "/onlinetest/checkcode"};      //获取验证码

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        response.setHeader("Access-Control-Allow-Credentials","true"); //支持cookie跨域

        String requestURI = request.getRequestURI();
        System.out.println("req"+requestURI);
        //不需要检查的url，直接放行
        if(check(requestURI)){
            System.out.println(requestURI);
            filterChain.doFilter(request,response);
            return;
        }

        Object user = request.getSession().getAttribute("user");
        //如果没有任何用户登录
        if(user==null){
            response.setHeader("REDIRECT", "REDIRECT");
            response.setHeader("CONTENTPATH", "/onlinetest/res/index.html");
            return;
        }

        //如果当前是普通用户登录
        if(user instanceof User && PATH_MATCHER.match("/onlinetest/user/**",requestURI)){
            filterChain.doFilter(request,response);
            return;
        }

        //如果当前是管理员登录
        if(user instanceof Admin && PATH_MATCHER.match("/onlinetest/admin/**",requestURI)){
            filterChain.doFilter(request,response);
            return;
        }
//        filterChain.doFilter(request,response);
        //不属于上述情况
        response.setHeader("REDIRECT", "REDIRECT");
        response.setHeader("CONTENTPATH", "/onlinetest/res/index.html");
    }

    /**
     * 判断本次请求是否需要处理
     * @param url
     * @return
     */
    public boolean check(String url){
        for(String u:accessUrls){
            boolean match = PATH_MATCHER.match(u, url);
            if(match) return true;
        }
        return false;
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
