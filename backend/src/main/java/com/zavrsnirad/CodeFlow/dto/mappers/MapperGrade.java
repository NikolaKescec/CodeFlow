package com.zavrsnirad.CodeFlow.dto.mappers;

import com.zavrsnirad.CodeFlow.domain.SolutionGrade;
import com.zavrsnirad.CodeFlow.domain.TaskGrade;
import com.zavrsnirad.CodeFlow.dto.json.GradeDtoJson;

public class MapperGrade {

    public static GradeDtoJson TaskGradeToJson(TaskGrade grade) {
        return new GradeDtoJson(
                grade.getTaskGradeId(),
                grade.getGrade(),
                MapperUser.UserToJson(grade.getGrader())
        );
    }

    public static GradeDtoJson SolutionGradeToJson(SolutionGrade grade) {
        return new GradeDtoJson(
                grade.getSolutionGradeId(),
                grade.getGrade(),
                MapperUser.UserToJson(grade.getGrader())
        );
    }
}
