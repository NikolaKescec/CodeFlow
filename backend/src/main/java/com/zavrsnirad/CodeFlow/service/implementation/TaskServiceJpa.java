package com.zavrsnirad.CodeFlow.service.implementation;

import com.zavrsnirad.CodeFlow.domain.*;
import com.zavrsnirad.CodeFlow.dto.req.TaskDtoReq;
import com.zavrsnirad.CodeFlow.dto.req.TaskUpdateDtoReq;
import com.zavrsnirad.CodeFlow.dto.req.TestCaseDtoReq;
import com.zavrsnirad.CodeFlow.repository.TaskRepository;
import com.zavrsnirad.CodeFlow.service.LanguageService;
import com.zavrsnirad.CodeFlow.service.TaskService;
import com.zavrsnirad.CodeFlow.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskServiceJpa implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private TestCaseService testCaseService;

    @Override
    public List<Task> tasksByUser(String username) {
        return taskRepository.findAllByUsername(username);
    }

    @Override
    public List<Task> taskSolvedByUser(String username) {
        return taskRepository.findSolvedByUsername(username);
    }

    @Override
    public List<Task> recommendedTasks(Programmer programmer) {
        return taskRepository.recommendedTasks(programmer.getProgrammerId());
    }

    @Override
    public List<Task> tasksFromFollowed(Programmer programmer) {
        return taskRepository.tasksFromFollowed(programmer.getProgrammerId());
    }

    @Override
    public List<Task> listAllTasks() {
        return taskRepository.findAllByOrderByModifiedDesc();
    }

    @Override
    public Task addTask(TaskDtoReq task, Programmer programmer) {
        Task newTask = new Task(programmer, task.getTaskText(), task.getInputFormat(), task.getOutputFormat());

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
            testCase.setTask(newTask);
            testCase.setUserCreated(programmer.getUsername());
            newTask.addTestCase(testCase);
        }
        newTask.setUserCreated(programmer.getUsername());

        return taskRepository.save(newTask);
    }

    @Override
    public Task taskByTaskId(Long id) {
        try {
            return taskRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("No such task with given id: " + id);
        }
    }

    @Override
    public void removeTask(Long taskId, Programmer programmer) {
        Task task = taskByTaskId(taskId);
        if(!task.getOwner().getProgrammerId().equals(programmer.getProgrammerId()) && !programmer.getRole().equals("ADMIN")){
            throw new IllegalArgumentException("Unauthorized action.");
        }
        taskRepository.delete(task);
    }

    @Override
    public Task updateTask(Long taskId, TaskUpdateDtoReq task, Programmer programmer) {
        if(!taskId.equals(task.getTaskId())) {
            throw new IllegalArgumentException("Task ID's are not matching!");
        }

        Task oldTask = taskByTaskId(taskId);

        if(!oldTask.getOwner().getProgrammerId().equals(programmer.getProgrammerId()) && !programmer.getRole().equals("ADMIN")) {
            throw new IllegalArgumentException("Unauthorized action.");
        }

        if(task.getLanguage().isEmpty())
            throw new IllegalArgumentException("Task has to have at least one language!");
        List<Language> oldLanguages = List.copyOf(oldTask.getWrittenIn());
        oldTask.getWrittenIn().clear();
        for(Long languageId : task.getLanguage()) {
            Language language = languageService.findById(languageId);
            oldTask.addWrittenInLanguage(language);
        }
        if(!oldTask.getWrittenIn().containsAll(oldLanguages)){
            throw new IllegalArgumentException("Languages can not be subtracted from original list, only added!");
        }

        if(task.getTestCase().isEmpty())
            throw new IllegalArgumentException("Task has to have at least one test case!");
        oldTask.getTestCases().clear();
        for(TestCaseDtoReq testCaseDtoReq : task.getTestCase()) {
            TestCase testCase;
            if(testCaseDtoReq.getTestCaseId() != null) {
                testCase = testCaseService.findById(testCaseDtoReq.getTestCaseId());
                testCase.setInput(testCaseDtoReq.getInput());
                testCase.setOutput(testCaseDtoReq.getOutput());
                TimeAndUser.updateModified(testCase, programmer);
            } else {
                testCase = new TestCase(testCaseDtoReq.getInput(), testCaseDtoReq.getOutput());
                testCase.setTask(oldTask);
                testCase.setUserCreated(programmer.getUsername());
            }
            oldTask.addTestCase(testCase);
        }

        oldTask.setTaskText(task.getTaskText());
        oldTask.setInputFormat(task.getInputFormat());
        oldTask.setOutputFormat(task.getOutputFormat());
        TimeAndUser.updateModified(oldTask, programmer);

        return taskRepository.save(oldTask);
    }
}
