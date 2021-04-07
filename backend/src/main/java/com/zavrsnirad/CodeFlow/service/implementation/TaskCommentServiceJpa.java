package com.zavrsnirad.CodeFlow.service.implementation;

import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.domain.Task;
import com.zavrsnirad.CodeFlow.domain.TaskComment;
import com.zavrsnirad.CodeFlow.dto.req.CommentDtoReq;
import com.zavrsnirad.CodeFlow.repository.TaskCommentRepository;
import com.zavrsnirad.CodeFlow.service.TaskCommentService;
import com.zavrsnirad.CodeFlow.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TaskCommentServiceJpa implements TaskCommentService {

    @Autowired
    private TaskCommentRepository taskCommentRepository;

    @Autowired
    private TaskService taskService;

    @Override
    public List<TaskComment> findTaskComments(Long id) {
        return taskCommentRepository.findCommentByTaskId(id);
    }

    @Override
    public TaskComment getTaskComment (Long id) {
        try {
            return taskCommentRepository.getOne(id);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("No such task comment!");
        }
    }

    @Override
    public TaskComment createCommentForTask(Long id, CommentDtoReq comment, Programmer programmer) {
        if(!id.equals(comment.getCommentBaseId())) {
            throw new IllegalArgumentException("The id's are not correct!");
        }

        Task task = taskService.taskByTaskId(id);
        TaskComment taskComment = new TaskComment(comment.getCommentText(), programmer, task);

        taskComment = taskCommentRepository.save(taskComment);
        return taskComment;
    }

    @Override
    public void deleteComment(Long commentId, Programmer programmer) {
        TaskComment taskComment = getTaskComment(commentId);
        if(!programmer.getProgrammerId().equals(taskComment.getCommenter().getProgrammerId()))
            throw new IllegalArgumentException("Only owner of this comment can delete this comment!");
        taskCommentRepository.delete(taskComment);
    }
}
