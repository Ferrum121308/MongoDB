package com.work.mongodb.dao;

import com.work.mongodb.entity.KeepRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface KeepRecordDao extends MongoRepository<KeepRecord,String> {

    //通过记录ID查询记录
    KeepRecord findKeepRecordByRecordId(String recordId);

    //通过收支类型查找记录
    List<KeepRecord> findKeepRecordsByRecordName(String recordName);

    //通过精确时间寻找记录
    List<KeepRecord> findKeepRecordsByRecordDate(String keepDate);

    //通过时间段寻找记录
    List<KeepRecord> findKeepRecordsByRecordDateBetween(String startDate,String endDate);

    //通过时间模糊寻找记录
    List<KeepRecord> findKeepRecordsByRecordDateContaining(String day);



}
