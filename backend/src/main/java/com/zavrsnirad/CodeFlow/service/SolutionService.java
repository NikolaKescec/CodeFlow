package com.zavrsnirad.CodeFlow.service;

import com.zavrsnirad.CodeFlow.domain.Solution;

import java.util.List;

public interface SolutionService {

    List<Solution> findSolutionsFromTask(Long id);

    Solution findSolutionById(Long id);
}
