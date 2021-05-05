package com.zavrsnirad.CodeFlow.service;

import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.domain.SolutionGrade;
import com.zavrsnirad.CodeFlow.domain.TaskGrade;

public interface SolutionGradeService {

    SolutionGrade gradeSolution(Long solutionId, int grade, Programmer programmer);

    SolutionGrade deleteGradeForSolution(Long solutionId, Programmer programmer);

}
