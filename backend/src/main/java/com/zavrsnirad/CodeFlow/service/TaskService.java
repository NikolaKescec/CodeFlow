package com.zavrsnirad.CodeFlow.service;

import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.domain.Task;
import com.zavrsnirad.CodeFlow.dto.req.TaskDtoReq;
import com.zavrsnirad.CodeFlow.dto.req.TaskUpdateDtoReq;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<Task> tasksByUser(String username);

    List<Task> taskSolvedByUser(String username);

    List<Task> bestEver();

    List<Task> tasksFromFollowed(Programmer programmer);

    List<Task> listAllTasks();

    Task addTask(TaskDtoReq task, Programmer author);

    Task taskByTaskId(Long id);

    void removeTask(Long taskId, Programmer programmer);

    Task updateTask(Long taskId, TaskUpdateDtoReq task, Programmer programmer);

}
