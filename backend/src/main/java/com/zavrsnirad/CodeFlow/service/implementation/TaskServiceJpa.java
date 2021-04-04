package com.zavrsnirad.CodeFlow.service.implementation;

import com.zavrsnirad.CodeFlow.domain.Language;
import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.domain.Task;
import com.zavrsnirad.CodeFlow.domain.TestCase;
import com.zavrsnirad.CodeFlow.dto.req.TaskDtoReq;
import com.zavrsnirad.CodeFlow.dto.req.TestCaseDtoReq;
import com.zavrsnirad.CodeFlow.repository.TaskRepository;
import com.zavrsnirad.CodeFlow.service.LanguageService;
import com.zavrsnirad.CodeFlow.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceJpa implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private LanguageService languageService;

    @Override
    public List<Task> tasksByUser(String username) {
        return taskRepository.findAllByUsername(username);
    }

    @Override
    public List<Task> taskSolvedByUser(String username) {
        return taskRepository.findSolvedByUsername(username);
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
        return taskRepository.findAllByOrderByModifiedDesc();
    }

    @Override
    public Task addTask(TaskDtoReq task, Programmer author) {
        Task newTask = new Task(author, task.getTaskText(), task.getInputFormat(), task.getOutputFormat());

        if(task.getLanguage().isEmpty())
            throw new IllegalArgumentException("Task has to have at least one language!");
        for(Long languageId : task.getLanguage()) {
            Language language = languageService.findById(languageId);
            newTask.addWrittenInLanguage(language);
        }

        if(task.getTestCase().isEmpty())
            throw new IllegalArgumentException("Task has to have at least one test case!");
        for(TestCaseDtoReq testCaseDtoReq : task.getTestCase()) {
            TestCase testCase = new TestCase(testCaseDtoReq.getInput(), testCaseDtoReq.getOutput());
            newTask.addTestCase(testCase);
        }

        return taskRepository.save(newTask);
    }

    @Override
    public Task taskByTaskId(Long id) {
        try {
            return taskRepository.getOne(id);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("No such task with given id: " + id);
        }
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
