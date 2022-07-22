package com.lovejava.service;

import com.lovejava.pojo.Admin;
import com.lovejava.pojo.User;

import java.util.List;

/**
 * @author wuzhicheng
 * @create 2022-07-05 8:52
 */
public interface AdminService {
    Admin selectByUserName(String username);
    int insert(Admin record);
    int deleteByUserName(String username);
    int updateByPrimaryKey(Admin record);
    List<Admin> selectByUserNameFuzzy(String username);
}
