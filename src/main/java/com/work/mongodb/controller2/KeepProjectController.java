package com.work.mongodb.controller2;


import com.work.mongodb.entity2.KeepProject;
import com.work.mongodb.service2.KeepProjectService;
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
    public Map<String,Object> saveProject(@RequestBody KeepProject keepProject){
        return keepProjectService.saveProject(keepProject);
    }



}
