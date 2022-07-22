package com.lovejava.service;

import com.lovejava.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 根据传入的ID来选择用户
     * @param uid
     * @return
     */
    public User selectUserByUid(Integer uid);


    User selectByUserName(String username);

    int updateByPrimaryKey(User record);

    List<User> selectByUserNameFuzzy(String username);

    int insert(User record);

    int deleteByUserName(String username);

    User selectByPhone(String phone);

    List<User> selectByMajor(String major);

    String getPhone(Integer uid);

    List<User> selectByNameAndMajor(String name, String major);
}
