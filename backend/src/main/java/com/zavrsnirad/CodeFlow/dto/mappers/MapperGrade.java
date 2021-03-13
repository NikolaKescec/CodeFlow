package com.zavrsnirad.CodeFlow.dto.mappers;

import com.zavrsnirad.CodeFlow.domain.SolutionGrade;
import com.zavrsnirad.CodeFlow.domain.TaskGrade;
import com.zavrsnirad.CodeFlow.dto.json.SolutionGradeDtoJson;
import com.zavrsnirad.CodeFlow.dto.json.TaskGradeDtoJson;

public class MapperGrade {

    public static TaskGradeDtoJson TaskGradeToJson(TaskGrade grade) {
        return new TaskGradeDtoJson(grade.getTaskGradeId(), grade.getGrade(), MapperUser.UserToJson(grade.getGrader()));
    }

    public static SolutionGradeDtoJson SolutionGradeToJson(SolutionGrade grade) {
        return new SolutionGradeDtoJson(grade.getSolutionGradeId(), grade.getGrade(), MapperUser.UserToJson(grade.getGrader()));
    }
}
