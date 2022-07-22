package com.lovejava.mapper;

import com.lovejava.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserName(String username);

    List<User> selectByUserNameFuzzy(String username);

    int deleteByUserName(String username);

    User selectByPhone(String phone);

    List<User> selectByMajor(String major);

    String getPhone(Integer uid);

    List<User> selectByNameAndMajor(String name, String major);
}