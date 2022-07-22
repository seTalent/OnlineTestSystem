package com.lovejava.service.impl;

import com.lovejava.mapper.*;
import com.lovejava.pojo.JudgementQuestion;
import com.lovejava.pojo.MultiQuestion;
import com.lovejava.pojo.Record;
import com.lovejava.pojo.SaqQuestion;
import com.lovejava.pojo.SingleQuestion;
import com.lovejava.service.ExamService;
import com.lovejava.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    /**
     * 根据用户的uid选择考试记录
     * @param uid 用户id
     * @return 所有的考试记录
     */
    @Override
    public List<Record> selectRecordByUid(Integer uid) {
        List<Record> records = recordMapper.selectByUid(uid);
        return records;
    }

    /**
     * 插入一条考试记录
     * @param record 考试记录
     * @return 影响的行数
     */
    @Override
    public int insertOneRecord(Record record) {
        return recordMapper.insert(record);
    }

    /**
     * 根据用户id和记录id返回特定的考试记录
     * @param uid 用户id
     * @param rid 记录id
     * @return 考试记录
     */
    @Override
    public Record selectRecordByUidAndRid(Integer uid, Integer rid) {
        return recordMapper.selectByUidAndRid(uid, rid);
    }
}
