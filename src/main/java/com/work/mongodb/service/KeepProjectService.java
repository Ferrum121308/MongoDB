package com.work.mongodb.service;


import com.work.mongodb.dao.KeepProjectDao;
import com.work.mongodb.dao.UserDao;
import com.work.mongodb.entity.KeepProject;
import com.work.mongodb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class KeepProjectService {

    @Autowired
    KeepProjectDao keepProjectDao;

    @Autowired
    UserDao userDao;

    //查询所有的项目
    public List<KeepProject> findAllProjects(){
        return keepProjectDao.findAll();
    }

    //根据projectId判断项目是否存在
    public boolean existById(String projectId){
        KeepProject uncheck = keepProjectDao.findKeepProjectByProjectId(projectId);
        return uncheck != null;
    }


    //判断项目是否存在
    public boolean isExist(KeepProject incomeProject){
        String incomeProjectId = incomeProject.getProjectId();
        KeepProject uncheckProject = keepProjectDao.findKeepProjectByProjectId(incomeProjectId);
        return uncheckProject != null;
    }



    //插入或者更新项目信息
    public Map<String,Object> saveProject(String userId,KeepProject incomeProject){
        User toSave = userDao.findUserByUserId(userId);
        Map<String,Object> condition = new HashMap<>();
        if(!isExist(incomeProject)){
            keepProjectDao.save(incomeProject);
            condition.put("condition","插入完成！");
        }
        else {
            KeepProject keepProject = keepProjectDao.findKeepProjectByProjectId(incomeProject.getProjectId());
            if(incomeProject.getProjectName() != null){
                keepProject.setProjectName(incomeProject.getProjectName());
            }
            if(incomeProject.getRecordId() != null){
                keepProject.setRecordId(incomeProject.getRecordId());
            }
            keepProjectDao.save(keepProject);
            condition.put("condition","修改完成！");
        }
        return condition;
    }


    public List<KeepProject> findProjectsByNameContaining(String incomeName){
        return keepProjectDao.findKeepProjectsByProjectNameContaining(incomeName);
    }

    public KeepProject findProjectById(String projectId){
        return keepProjectDao.findKeepProjectByProjectId(projectId);
    }

    public Map<String,Object> deleteProjectById(String projectId){
        Map<String,Object> condition = new HashMap<>();
        keepProjectDao.deleteKeepProjectByProjectId(projectId);
        condition.put("condition","删除完成！");
        return condition;
    }



}
