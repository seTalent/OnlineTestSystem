package com.lovejava.service.impl;

import com.lovejava.mapper.UserMapper;
import com.lovejava.pojo.User;
import com.lovejava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 注入相应mapper，通过mapper使用sql语句
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * Transactional发生异常的时候回滚事务
     * @param uid
     * @return
     */
    @Override
    public User selectUserByUid(Integer uid) {
        User user = userMapper.selectByPrimaryKey(uid);
        return user;
    }



    @Override
    public User selectByUserName(String username) {
        return userMapper.selectByUserName(username);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<User> selectByUserNameFuzzy(String username) {
        return userMapper.selectByUserNameFuzzy(username);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int deleteByUserName(String username) {
        return userMapper.deleteByUserName(username);
    }

    @Override
    public User selectByPhone(String phone) {
        return userMapper.selectByPhone(phone);
    }

    @Override
    public List<User> selectByMajor(String major) {
         List<User> users = userMapper.selectByMajor(major);
        return users;
    }

    @Override
    public String getPhone(Integer uid) {
        return userMapper.getPhone(uid);
    }

    @Override
    public List<User> selectByNameAndMajor(String name, String major) {
        return userMapper.selectByNameAndMajor(name,major);
    }
}
