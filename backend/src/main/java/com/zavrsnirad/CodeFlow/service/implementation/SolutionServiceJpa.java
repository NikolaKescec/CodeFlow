package com.zavrsnirad.CodeFlow.service.implementation;

import com.zavrsnirad.CodeFlow.domain.Solution;
import com.zavrsnirad.CodeFlow.repository.SolutionRepository;
import com.zavrsnirad.CodeFlow.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolutionServiceJpa implements SolutionService {

    @Autowired
    private SolutionRepository solutionRepository;

    @Override
    public List<Solution> findSolutionsFromTask(Long id) {
        return solutionRepository.findByTask(id);
    }
}
