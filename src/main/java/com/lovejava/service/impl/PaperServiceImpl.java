package com.lovejava.service.impl;

import com.lovejava.mapper.PaperMapper;
import com.lovejava.pojo.Paper;
import com.lovejava.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperMapper paperMapper;

    /**
     * 插入一个新的专业的试卷信息
     * @param paper 试卷
     * @return 是否成功
     */
    @Override
    public int insertOnePaper(Paper paper) {
        return paperMapper.insert(paper);
    }

    /**
     * 根据专业更新试卷表中的内容
     * @param paper 试卷
     * @return 是否成功
     */
    @Override
    public int updatePaperByMajor(Paper paper) {
        return paperMapper.updateByPrimaryKey(paper);
    }

    /**
     * 根据专业选择选择试卷
     * @param major 专业
     * @return 对应的试卷信息
     */
    @Override
    public Paper selectPaperByMajor(String major) {
        return paperMapper.selectByPrimaryKey(major);
    }
}
