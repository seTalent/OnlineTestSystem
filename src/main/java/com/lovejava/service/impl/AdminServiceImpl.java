package com.lovejava.service.impl;

import com.lovejava.mapper.AdminMapper;
import com.lovejava.pojo.Admin;
import com.lovejava.pojo.User;
import com.lovejava.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuzhicheng
 * @create 2022-07-05 8:53
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin selectByUserName(String username) {
        return adminMapper.selectByUserName(username);
    }

    @Override
    public int insert(Admin record) {
        return adminMapper.insert(record);
    }

    @Override
    public int deleteByUserName(String username) {
        return adminMapper.deleteByUserName(username);
    }

    @Override
    public int updateByPrimaryKey(Admin record) {
        return adminMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Admin> selectByUserNameFuzzy(String username) {
        return adminMapper.selectByUserNameFuzzy(username);
    }
}
