package com.lovejava.mapper;

import com.lovejava.pojo.Major;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wuzhicheng
 * @create 2022-07-08 8:50
 */
@Mapper
public interface MajorMapper {
    List<Major> getMajor();
}
