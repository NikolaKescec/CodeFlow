package com.zavrsnirad.CodeFlow.dto.mappers;

import com.zavrsnirad.CodeFlow.domain.*;
import com.zavrsnirad.CodeFlow.dto.json.TaskDtoJson;

import java.util.function.Predicate;

public class MapperTask {

    public static TaskDtoJson TaskToJson(Task task, User loggedInUser) {
        Solution loggedInUserSolution =
                MapperFilter.filterOne(task.getSolutions(), solution -> solution.getAuthor().getUserId().equals(loggedInUser.getUserId()));

        TaskGrade loggedInUserGrade =
                MapperFilter.filterOne(task.getGrades(), grade -> grade.getGrader().getUserId().equals(loggedInUser.getUserId()));

        return new TaskDtoJson(MapperUser.UserToJson(task.getOwner()),
                task.getTaskId(),
                task.getTaskText(),
                task.getLanguage(),
                task.getCorrectOutput(),
                task.getAuthorSolution(),
                task.getAverageGrade(),
                loggedInUserSolution != null ? MapperSolution.SolutionToJson(loggedInUserSolution) : null,
                loggedInUserGrade != null ? MapperGrade.TaskGradeToJson(loggedInUserGrade) : null
                );
    }

}
