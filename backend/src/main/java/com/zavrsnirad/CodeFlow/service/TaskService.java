package com.zavrsnirad.CodeFlow.service;

import com.zavrsnirad.CodeFlow.domain.Task;
import com.zavrsnirad.CodeFlow.dto.req.TaskDtoReq;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<Task> tasksByUser(String username);

    List<Task> bestEver();

    List<Task> taskFromFollowedPeople(UUID userId);

    List<Task> listAllTasks();

    Task addTask(TaskDtoReq task);

    Task removeTask(UUID taskId);

    Task updateTask(UUID taskId, TaskDtoReq task);

}
