package com.work.mongodb.dao;

import com.work.mongodb.entity.KeepProject;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface KeepProjectDao extends MongoRepository<KeepProject,String> {

    //通过projectId查询项目
    KeepProject findKeepProjectByProjectId(String keepProjectId);

    //通过项目名称精确查找项目
    KeepProject findKeepProjectByProjectName(String projectName);


    //通过项目名称模糊查询项目
    List<KeepProject> findKeepProjectsByProjectNameContaining(String projectName);

    //删除项目
    void deleteKeepProjectByProjectId(String projectId);

}
