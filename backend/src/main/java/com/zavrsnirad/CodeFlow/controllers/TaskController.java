package com.zavrsnirad.CodeFlow.controllers;

import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.dto.json.TaskDtoJson;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperList;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperTask;
import com.zavrsnirad.CodeFlow.service.TaskService;
import com.zavrsnirad.CodeFlow.service.ProgrammerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProgrammerService programmerService;

    @GetMapping("/fresh")
    public ResponseEntity<?> getTasks(Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());
        List<TaskDtoJson> response = MapperList.getList(taskService.listAllTasks(), task -> MapperTask.TaskToJson(task, programmer));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getTasksByUsername(@PathVariable("username") String username, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());

        List<TaskDtoJson> response = MapperList.getList(taskService.tasksByUser(username), task -> MapperTask.TaskToJson(task, programmer));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/solved/{username}")
    public ResponseEntity<?> getSolvedTasksByUsername(@PathVariable("username") String username, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());
        if(!programmer.getUsername().equals(username) || !programmer.getRole().equals("ADMIN"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        List<TaskDtoJson> response = MapperList.getList(taskService.taskSolvedByUser(username), task -> MapperTask.TaskToJson(task, programmer));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
