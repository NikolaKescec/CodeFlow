package com.zavrsnirad.CodeFlow.service.implementation;

import com.zavrsnirad.CodeFlow.domain.Language;
import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.domain.Solution;
import com.zavrsnirad.CodeFlow.domain.Task;
import com.zavrsnirad.CodeFlow.dto.req.SolutionDtoReq;
import com.zavrsnirad.CodeFlow.repository.SolutionRepository;
import com.zavrsnirad.CodeFlow.repository.TaskRepository;
import com.zavrsnirad.CodeFlow.service.LanguageService;
import com.zavrsnirad.CodeFlow.service.SolutionService;
import com.zavrsnirad.CodeFlow.service.TaskService;
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

        Solution solution = new Solution(solutionDtoReq.getCode(), language, programmer, task);
        solution = solutionRepository.save(solution);

        if(task.getOwner().getProgrammerId().equals(programmer.getProgrammerId()))
            task.setAuthorSolution(solution);
        taskRepository.save(task);
        return solution;
    }
}
