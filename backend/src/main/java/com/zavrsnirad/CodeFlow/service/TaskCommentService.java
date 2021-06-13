package com.zavrsnirad.CodeFlow.service;

import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.domain.TaskComment;
import com.zavrsnirad.CodeFlow.dto.req.CommentDtoReq;

import java.util.List;

public interface TaskCommentService {

    List<TaskComment> findTaskComments(Long id);

    TaskComment getTaskComment(Long id);

    TaskComment createCommentForTask(Long id, CommentDtoReq commentDtoReq, Programmer programmer);

    TaskComment updateTaskComment(Long id, CommentDtoReq commentDtoReq, Programmer programmer);

    void deleteComment(Long commentId, Programmer programmer);
}
