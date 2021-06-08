package com.work.mongodb.controller2;

import com.work.mongodb.entity2.KeepRecord;
import com.work.mongodb.entity2.Time;
import com.work.mongodb.service2.KeepProjectService;
import com.work.mongodb.service2.KeepRecordService;
import com.work.mongodb.service2.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/record")
public class KeepRecordController {

    @Autowired
    KeepRecordService keepRecordService;

    @Autowired
    UserService userService;

    @Autowired
    KeepProjectService keepProjectService;


    @RequestMapping("/findAllRecords")
    public List<KeepRecord> findAll(){
        return keepRecordService.findAllRecords();
    }

    @RequestMapping("/isExist")
    public boolean isExist(@RequestBody KeepRecord keepRecord){
        return keepRecordService.isExist(keepRecord);
    }

    @RequestMapping(value = "/saveRecord" ,method = RequestMethod.POST)
    public Map<String,Object> saveRecord(@RequestBody KeepRecord keepRecord){
        return keepRecordService.saveRecord(keepRecord);
    }

    @RequestMapping(value = "/findRecordsBySecureTime" ,method = RequestMethod.POST)
    public List<KeepRecord> findSecureRecord(@RequestParam("recordDate")String recordDate){
        return keepRecordService.findRecordBySecureTime(recordDate);
    }

    @RequestMapping(value = "/findRecordsByDateBetween" ,method = RequestMethod.POST)
    public List<KeepRecord> findRecordsByDateBetween (@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate){
        return keepRecordService.findRecordsByDateBetween(startDate,endDate);
    }

    @RequestMapping("/countTotal")
    public String sum(){
        return keepRecordService.getTotalSum();
    }


    @RequestMapping(value = "/countByTime",method = RequestMethod.POST)
    public String sumByTime(@RequestBody Time time){
        return keepRecordService.getSumByTime(time);
    }



}
