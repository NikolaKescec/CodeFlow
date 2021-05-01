package com.zavrsnirad.CodeFlow.controllers;

import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.domain.TaskComment;
import com.zavrsnirad.CodeFlow.dto.json.CommentDtoJson;
import com.zavrsnirad.CodeFlow.dto.json.TaskDtoJson;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperComment;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperList;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperTask;
import com.zavrsnirad.CodeFlow.dto.req.CommentDtoReq;
import com.zavrsnirad.CodeFlow.service.ProgrammerService;
import com.zavrsnirad.CodeFlow.service.SolutionCommentService;
import com.zavrsnirad.CodeFlow.service.TaskCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private ProgrammerService programmerService;

    @Autowired
    private TaskCommentService taskCommentService;

     @Autowired
     private SolutionCommentService solutionCommentService;

    @GetMapping("/task-comments/{id}")
    public ResponseEntity<?> getTaskComments(@PathVariable("id") Long id, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());

        List<CommentDtoJson> comments = MapperList.getList(taskCommentService.findTaskComments(id), MapperComment::TaskCommentToJson);
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    @PostMapping("/task-comments/create/{taskId}")
    public ResponseEntity<?> createCommentByTaskId(@PathVariable("taskId") Long taskId, @RequestBody CommentDtoReq commentDtoReq, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());

        CommentDtoJson newComment = MapperComment.TaskCommentToJson(taskCommentService.createCommentForTask(taskId, commentDtoReq, programmer));
        return ResponseEntity.status(HttpStatus.CREATED).body(newComment);
    }

    @DeleteMapping("/task-comments/delete/{commentId}")
    public ResponseEntity<?> deleteCommentById(@PathVariable("commentId") Long commentId, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());

        taskCommentService.deleteComment(commentId, programmer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/task-comments/update/{commentId}")
    public ResponseEntity<?> updateTaskCommentById(@PathVariable("commentId") Long taskCommentId, @RequestBody CommentDtoReq commentDtoReq, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());

        CommentDtoJson updatedComment = MapperComment.TaskCommentToJson(taskCommentService.updateTaskComment(taskCommentId, commentDtoReq, programmer));
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedComment);
    }

    @GetMapping("/solution-comments/{id}")
    public ResponseEntity<?> getSolutionComments(@PathVariable("id") Long id, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());

        List<CommentDtoJson> comments = MapperList.getList(solutionCommentService.findSolutionComments(id), MapperComment::SolutionCommentToJson);
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    @PostMapping("/solution-comments/create/{taskId}")
    public ResponseEntity<?> createCommentBySolutionId(@PathVariable("taskId") Long taskId, @RequestBody CommentDtoReq commentDtoReq, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());

        CommentDtoJson newComment = MapperComment.SolutionCommentToJson(solutionCommentService.createCommentForSolution(taskId, commentDtoReq, programmer));
        return ResponseEntity.status(HttpStatus.CREATED).body(newComment);
    }

    @DeleteMapping("/solution-comments/delete/{commentId}")
    public ResponseEntity<?> deleteSolutionCommentById(@PathVariable("commentId") Long commentId, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());

        solutionCommentService.deleteComment(commentId, programmer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PutMapping("/solution-comments/update/{commentId}")
    public ResponseEntity<?> updateSolutionCommentById(@PathVariable("commentId") Long solutionCommentId, @RequestBody CommentDtoReq commentDtoReq, Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());

        CommentDtoJson updatedComment = MapperComment.SolutionCommentToJson(solutionCommentService.updateSolutionComment(solutionCommentId, commentDtoReq, programmer));
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedComment);
    }

}
