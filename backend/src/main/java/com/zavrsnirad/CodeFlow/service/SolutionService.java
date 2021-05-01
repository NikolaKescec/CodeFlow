package com.zavrsnirad.CodeFlow.service;

import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.domain.Solution;
import com.zavrsnirad.CodeFlow.dto.req.SolutionDtoReq;
import com.zavrsnirad.CodeFlow.dto.req.SolutionUpdateDtoReq;

import java.util.List;

public interface SolutionService {

    List<Solution> findSolutionsFromTask(Long id);

    Solution findSolutionById(Long id);

    Solution addSolution(Long taskId, SolutionDtoReq solutionDtoReq, Programmer programmer);

    Solution updateSolution(Long solutionId, SolutionUpdateDtoReq solutionUpdateDtoReq, Programmer programmer);

    void deleteSolution(Long solutionId, Programmer programmer);
}
