package com.lovejava.service.impl;

import com.lovejava.mapper.MajorMapper;
import com.lovejava.pojo.Major;
import com.lovejava.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wuzhicheng
 * @create 2022-07-08 8:52
 */
@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
    MajorMapper majorMapper;
    @Override
    public List<String> getMajor() {
        List<Major> major = majorMapper.getMajor();
        List<String> collect = major.stream().map((item) -> {
            String m = item.getMajor();
            return m;
        }).collect(Collectors.toList());
        return collect;
    }
}
