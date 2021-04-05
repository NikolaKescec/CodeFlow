package com.zavrsnirad.CodeFlow.service;

import com.zavrsnirad.CodeFlow.domain.TaskComment;

import java.util.List;

public interface TaskCommentService {

    List<TaskComment> findTaskComments(Long id);

}
