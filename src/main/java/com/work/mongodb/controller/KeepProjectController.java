package com.work.mongodb.controller;


import com.work.mongodb.entity.KeepProject;
import com.work.mongodb.service.KeepProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project")
public class KeepProjectController {

    @Autowired
    KeepProjectService keepProjectService;

    @RequestMapping("/findAll")
    public List<KeepProject> findAllProjects(){
        return keepProjectService.findAllProjects();
    }

    @RequestMapping("/deleteProject")
    public Map<String,Object> deleteProjectById(@RequestParam("projectId")String projectId){
        return keepProjectService.deleteProjectById(projectId);
    }


    @RequestMapping(value = "/findProjectsByName",method = RequestMethod.POST)
    public List<KeepProject> findProjectsByNameContaining(@RequestParam("name") String name){
        return keepProjectService.findProjectsByNameContaining(name);
    }


    @RequestMapping(value = "/saveProject",method = RequestMethod.POST)
    public Map<String,Object> saveProject(@RequestParam("userId")String userId,@RequestBody KeepProject keepProject){
        return keepProjectService.saveProject(userId,keepProject);
    }



}
