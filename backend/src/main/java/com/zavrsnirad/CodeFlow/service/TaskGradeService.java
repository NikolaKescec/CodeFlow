package com.zavrsnirad.CodeFlow.service;

import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.domain.TaskGrade;

public interface TaskGradeService {

    TaskGrade gradeTask(Long taskId, int grade, Programmer programmer);

    TaskGrade deleteGradeForTask(Long taskId, Programmer programmer);

}
