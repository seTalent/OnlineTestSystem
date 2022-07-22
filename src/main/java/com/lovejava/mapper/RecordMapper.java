package com.lovejava.mapper;

import com.lovejava.pojo.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);

    List<Record> selectByUid(Integer uid);

    Record selectByUidAndRid(Integer uid,Integer rid);
}