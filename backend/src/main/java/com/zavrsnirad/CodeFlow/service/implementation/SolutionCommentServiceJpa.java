package com.zavrsnirad.CodeFlow.service.implementation;

import com.zavrsnirad.CodeFlow.domain.*;
import com.zavrsnirad.CodeFlow.dto.req.CommentDtoReq;
import com.zavrsnirad.CodeFlow.repository.SolutionCommentRepository;
import com.zavrsnirad.CodeFlow.repository.TaskCommentRepository;
import com.zavrsnirad.CodeFlow.service.SolutionCommentService;
import com.zavrsnirad.CodeFlow.service.SolutionService;
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
public class SolutionCommentServiceJpa implements SolutionCommentService {

    @Autowired
    private SolutionCommentRepository solutionCommentRepository;

    @Autowired
    private SolutionService solutionService;

    @Override
    public List<SolutionComment> findSolutionComments(Long id) {
        return solutionCommentRepository.findCommentBySolutionId(id);
    }

    @Override
    public SolutionComment getSolutionComment (Long id) {
        try {
            return solutionCommentRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("No such task comment!");
        }
    }

    @Override
    public SolutionComment createCommentForSolution(Long id, CommentDtoReq comment, Programmer programmer) {
        if(!id.equals(comment.getCommentBaseId())) {
            throw new IllegalArgumentException("The id's are not correct!");
        }

        Solution solution = solutionService.findSolutionById(id);
        SolutionComment solutionComment = new SolutionComment(comment.getCommentText(), programmer, solution);
        solutionComment.setUserCreated(programmer.getUsername());

        solutionComment = solutionCommentRepository.save(solutionComment);
        return solutionComment;
    }

    @Override
    public SolutionComment updateSolutionComment(Long id, CommentDtoReq commentDtoReq, Programmer programmer) {
        if(!id.equals(commentDtoReq.getCommentBaseId())) {
            throw new IllegalArgumentException("The id's are not correct! Only owner can update it's comment!");
        }

        SolutionComment oldComment = getSolutionComment(id);
        oldComment.setComment(commentDtoReq.getCommentText());
        oldComment.setModified(new Timestamp(new Date().getTime()));

        oldComment = solutionCommentRepository.save(oldComment);
        return oldComment;
    }

    @Override
    public void deleteComment(Long solutionId, Programmer programmer) {
        SolutionComment solutionComment = getSolutionComment(solutionId);
        if(!programmer.getProgrammerId().equals(solutionComment.getCommenter().getProgrammerId()))
            throw new IllegalArgumentException("Only owner of this comment can delete this comment!");
        solutionCommentRepository.delete(solutionComment);
    }
}
