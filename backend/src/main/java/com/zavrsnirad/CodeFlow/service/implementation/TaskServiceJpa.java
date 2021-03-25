package com.zavrsnirad.CodeFlow.service.implementation;

import com.zavrsnirad.CodeFlow.domain.Task;
import com.zavrsnirad.CodeFlow.dto.req.TaskDtoReq;
import com.zavrsnirad.CodeFlow.repository.TaskRepository;
import com.zavrsnirad.CodeFlow.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceJpa implements TaskService {

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public List<Task> tasksByUser(String username) {
        return taskRepository.findAllByUsername(username);
    }

    @Override
    public List<Task> bestEver() {
        return null;
    }

    @Override
    public List<Task> taskFromFollowedPeople(UUID userId) {
        return null;
    }

    @Override
    public List<Task> listAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task addTask(TaskDtoReq task) {
        return null;
    }

    @Override
    public Task removeTask(UUID taskId) {
        return null;
    }

    @Override
    public Task updateTask(UUID taskId, TaskDtoReq task) {
        return null;
    }
}
