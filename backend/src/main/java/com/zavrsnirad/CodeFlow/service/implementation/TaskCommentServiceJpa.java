package com.zavrsnirad.CodeFlow.service.implementation;

import com.zavrsnirad.CodeFlow.domain.TaskComment;
import com.zavrsnirad.CodeFlow.repository.TaskCommentRepository;
import com.zavrsnirad.CodeFlow.service.TaskCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskCommentServiceJpa implements TaskCommentService {

    @Autowired
    private TaskCommentRepository taskCommentRepository;

    @Override
    public List<TaskComment> findTaskComments(Long id) {
        return taskCommentRepository.findCommentByTaskId(id);
    }
}
