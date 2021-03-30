package com.zavrsnirad.CodeFlow.dto.mappers;

import com.zavrsnirad.CodeFlow.domain.*;
import com.zavrsnirad.CodeFlow.dto.json.TaskDtoJson;

public class MapperTask {

    public static TaskDtoJson TaskToJson(Task task, Programmer loggedInProgrammer) {
        Solution loggedInUserSolution =
                MapperFilter.filterOne(task.getSolutions(), solution -> solution.getAuthor().getProgrammerId().equals(loggedInProgrammer.getProgrammerId()));

        TaskGrade loggedInUserGrade =
                MapperFilter.filterOne(task.getGrades(), grade -> grade.getGrader().getProgrammerId().equals(loggedInProgrammer.getProgrammerId()));

        return new TaskDtoJson(
                task.getOwner().getUsername(),
                task.getTaskId(),
                task.getTaskText(),
                MapperList.getList(task.getWrittenIn(), MapperLanguage::LanguageToJson),
                MapperList.getList(task.getTestCases(), MapperTestCase::TestCaseToJson),
                task.getInputFormat(),
                task.getOutputFormat(),
                task.getAuthorSolution() != null ? task.getAuthorSolution().getSolutionId() : null,
                task.getAverageGrade(),
                loggedInUserSolution != null ? loggedInUserSolution.getSolutionId() : null,
                loggedInUserGrade != null ? MapperGrade.TaskGradeToJson(loggedInUserGrade) : null
        );
    }

}
