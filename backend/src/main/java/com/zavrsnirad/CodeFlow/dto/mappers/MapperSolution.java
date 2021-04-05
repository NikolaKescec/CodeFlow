package com.zavrsnirad.CodeFlow.dto.mappers;

import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.domain.Solution;
import com.zavrsnirad.CodeFlow.domain.SolutionGrade;
import com.zavrsnirad.CodeFlow.domain.TaskGrade;
import com.zavrsnirad.CodeFlow.dto.json.SolutionDtoJson;

public class MapperSolution {

    public static SolutionDtoJson SolutionToJson(Solution solution, Programmer loggedInProgrammer) {
        SolutionGrade loggedInUserGrade = solution.getGrades() != null ?
                MapperFilter.filterOne(solution.getGrades(), grade -> grade.getGrader().getProgrammerId().equals(loggedInProgrammer.getProgrammerId())) : null;

        return new SolutionDtoJson(
                solution.getSolutionId(),
                solution.getCode(),
                solution.getAverageGrade(),
                loggedInUserGrade != null ? MapperGrade.SolutionGradeToJson(loggedInUserGrade) : null,
                MapperLanguage.LanguageToJson(solution.getLanguage()),
                MapperUser.UserToJson(solution.getAuthor())
        );
    }

}
