package com.zavrsnirad.CodeFlow.controllers;

import com.zavrsnirad.CodeFlow.domain.*;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperSolution;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperTask;
import com.zavrsnirad.CodeFlow.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private SolutionGradeService solutionGradeService;

    @Autowired
    private TaskGradeService taskGradeService;

    @Autowired
    private ProgrammerService programmerService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private SolutionService solutionService;

    @GetMapping("/solution/{solutionId}/{grade}")
    public ResponseEntity<?> gradeSolution(@PathVariable("solutionId") Long solutionId, @PathVariable("grade") int grade, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());
        solutionGradeService.gradeSolution(solutionId, grade, programmer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/solution/{solutionId}")
    public ResponseEntity<?> deleteGradeForSolution(@PathVariable("solutionId") Long solutionId, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());
        solutionGradeService.deleteGradeForSolution(solutionId, programmer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/task/{taskId}/{grade}")
    public ResponseEntity<?> gradeTask(@PathVariable("taskId") Long taskId, @PathVariable("grade") int grade, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());
        taskGradeService.gradeTask(taskId, grade, programmer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/task/{taskId}")
    public ResponseEntity<?> deleteGradeForTask(@PathVariable("taskId") Long taskId, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());
        taskGradeService.deleteGradeForTask(taskId, programmer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
