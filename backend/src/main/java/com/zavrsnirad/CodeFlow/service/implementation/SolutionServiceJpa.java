package com.zavrsnirad.CodeFlow.service.implementation;

import com.zavrsnirad.CodeFlow.domain.*;
import com.zavrsnirad.CodeFlow.dto.req.SolutionDtoReq;
import com.zavrsnirad.CodeFlow.dto.req.SolutionUpdateDtoReq;
import com.zavrsnirad.CodeFlow.repository.SolutionRepository;
import com.zavrsnirad.CodeFlow.repository.TaskRepository;
import com.zavrsnirad.CodeFlow.service.LanguageService;
import com.zavrsnirad.CodeFlow.service.SolutionService;
import com.zavrsnirad.CodeFlow.service.TaskGradeService;
import com.zavrsnirad.CodeFlow.service.TaskService;
import com.zavrsnirad.CodeFlow.util.WebClientJugde0Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class SolutionServiceJpa implements SolutionService {
    @Autowired
    private SolutionRepository solutionRepository;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskGradeService taskGradeService;

    @Autowired
    private WebClientJugde0Util webClientJugde0Util;

    @Override
    public List<Solution> findSolutionsFromTask(Long id) {
        return solutionRepository.findByTask(id);
    }

    @Override
    public Solution findSolutionById(Long id) {
        try{
            return solutionRepository.findById(id).get();
        }catch(NoSuchElementException e) {
            throw new IllegalArgumentException("No such solution with id: " + id);
        }
    }

    @Override
    public Solution addSolution(Long taskId, SolutionDtoReq solutionDtoReq, Programmer programmer) {
        Task task = taskService.taskByTaskId(taskId);
        Language language = languageService.findById(solutionDtoReq.getLanguageId());

        if(!task.getWrittenIn().contains(language))
            throw new IllegalArgumentException("This task does not allow this language!");

        if(task.getSolutions().stream().anyMatch((solution -> solution.getAuthor().getProgrammerId().equals(programmer.getProgrammerId()))))
            throw new IllegalArgumentException("You can only write one solution per task!");

        if(!webClientJugde0Util.validSolution(task.getTestCases(), solutionDtoReq, language.getJudgeId())) throw new IllegalArgumentException("Invalid solution!");

        Solution solution = new Solution(solutionDtoReq.getCode(), language, programmer, task);
        solution.setUserCreated(programmer.getUsername());
        solution = solutionRepository.save(solution);

        if(task.getOwner().getProgrammerId().equals(programmer.getProgrammerId()))
            task.setAuthorSolution(solution);
        taskRepository.save(task);
        return solution;
    }

    @Override
    public Solution updateSolution(Long solutionId, SolutionUpdateDtoReq solutionUpdateDtoReq, Programmer programmer) {
        if(!solutionId.equals(solutionUpdateDtoReq.getSolutionId()))
            throw new IllegalArgumentException("Solution ID's do not match!");
        Solution solution = findSolutionById(solutionId);
        if(!solution.getAuthor().getProgrammerId().equals(programmer.getProgrammerId())){
            throw new IllegalArgumentException("Only author of this solution can update this solution!");
        }
        solution.setCode(solutionUpdateDtoReq.getCode());
        Language language = languageService.findById(solutionUpdateDtoReq.getLanguageId());
        solution.setLanguage(language);

        if(!webClientJugde0Util.validSolution(solution.getTask().getTestCases(), solutionUpdateDtoReq, language.getJudgeId())) throw new IllegalArgumentException("Invalid solution!");

        TimeAndUser.updateModified(solution, programmer);
        return solutionRepository.save(solution);
    }

    @Override
    public void deleteSolution(Long solutionId, Programmer programmer) {
        Solution solution = findSolutionById(solutionId);
        if(!solution.getAuthor().getProgrammerId().equals(programmer.getProgrammerId()) && !programmer.getRole().equals("ADMIN")){
            throw new IllegalArgumentException("Unauthorized action.");
        }
        if(solution.getTask().getAuthorSolution() != null && solution.getTask().getAuthorSolution().getAuthor().getProgrammerId().equals(programmer.getProgrammerId())){
            solution.getTask().setAuthorSolution(null);
            taskRepository.save(solution.getTask());
        }

        // DELETE GRADE FOR TASK IF IT EXISTS
        try{
            taskGradeService.deleteGradeForTask(solution.getTask().getTaskId(), programmer);
        } catch (IllegalArgumentException ignored){}

        solutionRepository.delete(solution);
    }
}
