package com.zavrsnirad.CodeFlow.service.implementation;

import com.zavrsnirad.CodeFlow.domain.*;
import com.zavrsnirad.CodeFlow.repository.TaskGradeRepository;
import com.zavrsnirad.CodeFlow.repository.TaskRepository;
import com.zavrsnirad.CodeFlow.service.TaskGradeService;
import com.zavrsnirad.CodeFlow.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TaskGradeServiceJpa implements TaskGradeService {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskGradeRepository taskGradeRepository;

    @Override
    public TaskGrade gradeTask(Long taskId, int grade, Programmer programmer) {
        Task task = taskService.taskByTaskId(taskId);
        Optional<Solution> loggedInUserSolution = task.getSolutions().stream().filter(solution -> solution.getAuthor().getProgrammerId().equals(programmer.getProgrammerId())).findFirst();

        if(task.getOwner().getProgrammerId().equals(programmer.getProgrammerId())){
            throw new IllegalArgumentException("Programmer can not grade his own task!");
        }

        if(loggedInUserSolution.isEmpty()){
            throw new IllegalArgumentException("Programmer can not grade tasks that he has not solved!");
        }

        if(grade < 1 || grade > 5)
            throw new IllegalArgumentException("Grade must be between 1 and 5!");

        TaskGrade taskGrade = taskGradeRepository.findByTaskIdAndGraderId(taskId, programmer.getProgrammerId());
        if(taskGrade == null) {
            taskGrade = new TaskGrade();
            taskGrade.setTask(task);
            taskGrade.setGrader(programmer);
            taskGrade.setUserCreated(programmer.getUsername());
            task.addTaskGrade(taskGrade);

        } else {
            TimeAndUser.updateModified(taskGrade, programmer);
        }
        taskGrade.setGrade(grade);

        taskGrade = taskGradeRepository.save(taskGrade);
        taskRepository.saveAndFlush(task);

        return taskGrade;
    }

    @Override
    public TaskGrade deleteGradeForTask(Long taskId, Programmer programmer) {
        Task task = taskService.taskByTaskId(taskId);
        TaskGrade grade = taskGradeRepository.findByTaskIdAndGraderId(taskId, programmer.getProgrammerId());
        if(grade == null) {
            throw new IllegalArgumentException("No such grade to delete!");
        }

        if(!grade.getGrader().getProgrammerId().equals(programmer.getProgrammerId())){
            throw new IllegalArgumentException("Only programmer that graded this task can delete this grade!");
        }

        taskGradeRepository.delete(grade);

        return grade;
    }
}
