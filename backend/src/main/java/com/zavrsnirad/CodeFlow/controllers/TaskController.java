package com.zavrsnirad.CodeFlow.controllers;

import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.dto.json.CommentDtoJson;
import com.zavrsnirad.CodeFlow.dto.json.SolutionDtoJson;
import com.zavrsnirad.CodeFlow.dto.json.TaskDtoJson;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperComment;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperList;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperSolution;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperTask;
import com.zavrsnirad.CodeFlow.dto.req.TaskDtoReq;
import com.zavrsnirad.CodeFlow.dto.req.TaskUpdateDtoReq;
import com.zavrsnirad.CodeFlow.service.SolutionService;
import com.zavrsnirad.CodeFlow.service.TaskCommentService;
import com.zavrsnirad.CodeFlow.service.TaskService;
import com.zavrsnirad.CodeFlow.service.ProgrammerService;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private SolutionService solutionService;

    @Autowired
    private TaskCommentService taskCommentService;

    @Autowired
    private ProgrammerService programmerService;

    @GetMapping("/fresh")
    public ResponseEntity<?> getTasks(Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());
        List<TaskDtoJson> response = MapperList.getList(taskService.listAllTasks(), task -> MapperTask.TaskToJson(task, programmer));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/following")
    public ResponseEntity<?> getFollowedTasks(Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());
        List<TaskDtoJson> response = MapperList.getList(taskService.tasksFromFollowed(programmer), task -> MapperTask.TaskToJson(task, programmer));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/best")
    public ResponseEntity<?> getBestTasks(Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());
        List<TaskDtoJson> response = MapperList.getList(taskService.bestEver(), task -> MapperTask.TaskToJson(task, programmer));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getTasksByUsername(@PathVariable("username") String username, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());

        List<TaskDtoJson> response = MapperList.getList(taskService.tasksByUser(username), task -> MapperTask.TaskToJson(task, programmer));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable("id") Long id, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());

        TaskDtoJson response = MapperTask.TaskToJson(taskService.taskByTaskId(id), programmer);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/solutions/{id}")
    public ResponseEntity<?> getTaskSolutions(@PathVariable("id") Long id, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());

        List<SolutionDtoJson> solutions = MapperList.getList(solutionService.findSolutionsFromTask(id), solution -> MapperSolution.SolutionToJson(solution, programmer));
        return ResponseEntity.status(HttpStatus.OK).body(solutions);
    }

    @GetMapping("/solved/{username}")
    public ResponseEntity<?> getSolvedTasksByUsername(@PathVariable("username") String username, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());
        if(!programmer.getUsername().equals(username) && !programmer.getRole().equals("ADMIN"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        List<TaskDtoJson> response = MapperList.getList(taskService.taskSolvedByUser(username), task -> MapperTask.TaskToJson(task, programmer));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/create-task")
    public ResponseEntity<?> createTask(@RequestBody TaskDtoReq task, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());
        if(!task.getAuthor().equals(programmer.getProgrammerId()))
            throw new IllegalArgumentException("Unable to create task for mismatching users!");

        TaskDtoJson createdTask = MapperTask.TaskToJson(taskService.addTask(task, programmer), programmer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/update-task/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable("taskId") Long taskId, @RequestBody TaskUpdateDtoReq task, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());

        TaskDtoJson updatedTask = MapperTask.TaskToJson(taskService.updateTask(taskId, task, programmer), programmer);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedTask);
    }

    @DeleteMapping("/delete-task/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable("taskId") Long taskId, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());
        taskService.removeTask(taskId, programmer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
