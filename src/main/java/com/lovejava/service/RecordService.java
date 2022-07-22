package com.lovejava.service;

import com.lovejava.pojo.Admin;
import com.lovejava.pojo.Record;

import java.util.List;

public interface RecordService {
    List<Record> selectRecordByUid(Integer uid);

    int insertOneRecord(Record record);

    Record selectRecordByUidAndRid(Integer uid,Integer rid);
}
