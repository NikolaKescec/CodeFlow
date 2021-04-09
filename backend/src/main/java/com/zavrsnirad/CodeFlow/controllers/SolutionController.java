package com.zavrsnirad.CodeFlow.controllers;

import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.domain.Solution;
import com.zavrsnirad.CodeFlow.dto.json.SolutionDtoJson;
import com.zavrsnirad.CodeFlow.dto.json.TaskDtoJson;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperSolution;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperTask;
import com.zavrsnirad.CodeFlow.service.ProgrammerService;
import com.zavrsnirad.CodeFlow.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/solution")
public class SolutionController {

    @Autowired
    private ProgrammerService programmerService;

    @Autowired
    private SolutionService solutionService;

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable("id") Long id, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());

        SolutionDtoJson response = MapperSolution.SolutionToJson(solutionService.findSolutionById(id), programmer);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
