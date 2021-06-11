package com.work.mongodb.service;


import com.work.mongodb.dao.KeepRecordDao;
import com.work.mongodb.entity.KeepRecord;
import com.work.mongodb.entity.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KeepRecordService {


    @Autowired
    KeepRecordDao keepRecordDao;

    @Autowired
    KeepProjectService keepProjectService;


    public List<KeepRecord> findAllRecords(){
        return keepRecordDao.findAll();
    }

    public KeepRecord findRecordById(String recordId){
        return keepRecordDao.findKeepRecordByRecordId(recordId);
    }


    public boolean isExist(KeepRecord incomeKeepRecord){
        String incomeKeepRecordId = incomeKeepRecord.getRecordId();
        KeepRecord uncheckKeepRecord = findRecordById(incomeKeepRecordId);
        if(uncheckKeepRecord != null){
            return true;
        }
        else {
            return false;
        }
    }

    public Map<String,Object> saveRecord(KeepRecord incomeKeepRecord){
        Map<String,Object> condition = new HashMap<>();
        if(!isExist(incomeKeepRecord)){
            incomeKeepRecord.setProjectId("0");
            keepRecordDao.save(incomeKeepRecord);
            condition.put("condition","插入完成！");
        }
        else{
            KeepRecord keepRecord = keepRecordDao.findKeepRecordByRecordId(incomeKeepRecord.getRecordId());
            if(incomeKeepRecord.getProjectId() != null){
                if(!keepProjectService.existById(incomeKeepRecord.getProjectId())){
                    condition.put("condition","项目不存在，请重试！");
                    return condition;
                }
                else {
                    keepRecord.setProjectId(incomeKeepRecord.getProjectId());
                }
            }
            if(incomeKeepRecord.getRecordName() != null){
                keepRecord.setRecordName(incomeKeepRecord.getRecordName());
            }
            if(incomeKeepRecord.getMoney() != null){
                keepRecord.setMoney(incomeKeepRecord.getMoney());
            }
            if(incomeKeepRecord.getRemarks() != null){
                keepRecord.setRemarks(incomeKeepRecord.getRemarks());
            }
            if(incomeKeepRecord.getRecordDate() != null){
                keepRecord.setRecordDate(incomeKeepRecord.getRecordDate());
            }
            keepRecordDao.save(keepRecord);
            condition.put("condition","修改完成！");
        }
        return condition;
    }


    public List<KeepRecord> findRecordBySecureTime(String recordDate){
        return keepRecordDao.findKeepRecordsByRecordDate(recordDate);
    }

    public List<KeepRecord> findRecordsByDateBetween(String startDate,String endDate){
        return keepRecordDao.findKeepRecordsByRecordDateBetween(startDate,endDate);
    }

    public String getTotalSum(){
        List<KeepRecord> allRecord = findAllRecords();
        double sum = 0;
        for(int i = 0;i < allRecord.size();i ++){
            sum += Double.parseDouble(allRecord.get(i).getMoney());
        }
        return String.valueOf(sum);
    }

    public String getSumByTime(Time incomeTime){
        String param;
        if(incomeTime.getDay() != null){
            param = incomeTime.getYear() + "-" + incomeTime.getMonth()+ "-" + incomeTime.getDay();
        } else if(incomeTime.getMonth() != null){
            param = incomeTime.getYear() + "-" + incomeTime.getMonth();
        } else {
            param = incomeTime.getYear();
        }
        List<KeepRecord> records = keepRecordDao.findKeepRecordsByRecordDateContaining(param);
        double sum = 0;
        for (int i = 0;i < records.size();i ++){
            sum += Double.parseDouble(records.get(i).getMoney());
        }
        return String.valueOf(sum);
    }




}
