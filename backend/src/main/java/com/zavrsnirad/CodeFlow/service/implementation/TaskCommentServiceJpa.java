package com.zavrsnirad.CodeFlow.service.implementation;

import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.domain.Task;
import com.zavrsnirad.CodeFlow.domain.TaskComment;
import com.zavrsnirad.CodeFlow.domain.TimeAndUser;
import com.zavrsnirad.CodeFlow.dto.req.CommentDtoReq;
import com.zavrsnirad.CodeFlow.repository.TaskCommentRepository;
import com.zavrsnirad.CodeFlow.service.TaskCommentService;
import com.zavrsnirad.CodeFlow.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

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
            return taskCommentRepository.findById(id).get();
        } catch (NoSuchElementException e) {
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
        taskComment.setUserCreated(programmer.getUsername());

        taskComment = taskCommentRepository.save(taskComment);
        return taskComment;
    }

    @Override
    public TaskComment updateTaskComment(Long id, CommentDtoReq commentDtoReq, Programmer programmer) {
        if(!id.equals(commentDtoReq.getCommentBaseId())) {
            throw new IllegalArgumentException("The id's are not correct! Only owner can update it's comment!");
        }

        TaskComment oldComment = getTaskComment(id);
        oldComment.setComment(commentDtoReq.getCommentText());

        TimeAndUser.updateModified(oldComment, programmer);
        oldComment = taskCommentRepository.save(oldComment);
        return oldComment;
    }

    @Override
    public void deleteComment(Long commentId, Programmer programmer) {
        TaskComment taskComment = getTaskComment(commentId);
        if(!programmer.getProgrammerId().equals(taskComment.getCommenter().getProgrammerId()))
            throw new IllegalArgumentException("Only owner of this comment can delete this comment!");
        taskCommentRepository.delete(taskComment);
    }
}
