package com.zavrsnirad.CodeFlow.service.implementation;

import com.zavrsnirad.CodeFlow.domain.*;
import com.zavrsnirad.CodeFlow.repository.SolutionGradeRepository;
import com.zavrsnirad.CodeFlow.repository.TaskGradeRepository;
import com.zavrsnirad.CodeFlow.service.ProgrammerService;
import com.zavrsnirad.CodeFlow.service.SolutionGradeService;
import com.zavrsnirad.CodeFlow.service.SolutionService;
import com.zavrsnirad.CodeFlow.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SolutionGradeServiceJpa implements SolutionGradeService {

    @Autowired
    private SolutionService solutionService;

    @Autowired
    private SolutionGradeRepository solutionGradeRepository;

    @Autowired
    private ProgrammerService programmerService;

    @Override
    public SolutionGrade gradeSolution(Long solutionId, int grade, Programmer programmer) {
        Solution solution = solutionService.findSolutionById(solutionId);

        if(solution.getAuthor().getProgrammerId().equals(programmer.getProgrammerId())){
            throw new IllegalArgumentException("Programmer can not grade is own solution!");
        }

        if(grade < 1 || grade > 5)
            throw new IllegalArgumentException("Grade must be between 1 and 5!");

        SolutionGrade solutionGrade = solutionGradeRepository.findBySolutionIdAndGraderId(solutionId, programmer.getProgrammerId());
        if(solutionGrade == null) {
            solutionGrade = new SolutionGrade();
            solutionGrade.setSolution(solution);
            solutionGrade.setGrader(programmer);
            solutionGrade.setUserCreated(programmer.getUsername());
            programmerService.addSolutionPoints(grade, solution.getAuthor());
        } else {
            if(grade < solutionGrade.getGrade()){
                programmerService.removeSolutionPoints(solutionGrade.getGrade() - grade, solutionGrade.getSolution().getAuthor());
            } else if(grade > solutionGrade.getGrade()) {
                programmerService.addSolutionPoints(grade - solutionGrade.getGrade(), solutionGrade.getSolution().getAuthor());
            }
            TimeAndUser.updateModified(solutionGrade, programmer);
        }
        solutionGrade.setGrade(grade);

        return solutionGradeRepository.save(solutionGrade);
    }

    @Override
    public SolutionGrade deleteGradeForSolution(Long solutionId, Programmer programmer) {
        SolutionGrade solutionGrade = solutionGradeRepository.findBySolutionIdAndGraderId(solutionId, programmer.getProgrammerId());
        if(solutionGrade == null) {
            throw new IllegalArgumentException("No such grade to delete!");
        }

        if(!solutionGrade.getGrader().getProgrammerId().equals(programmer.getProgrammerId())){
            throw new IllegalArgumentException("Only programmer that graded this task can delete this grade!");
        }
        programmerService.removeSolutionPoints(solutionGrade.getGrade(), solutionGrade.getSolution().getAuthor());
        solutionGradeRepository.delete(solutionGrade);
        return solutionGrade;
    }

}
