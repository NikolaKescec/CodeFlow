package com.zavrsnirad.CodeFlow.service;

import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.domain.SolutionComment;
import com.zavrsnirad.CodeFlow.dto.req.CommentDtoReq;

import java.util.List;

public interface SolutionCommentService {

    List<SolutionComment> findSolutionComments(Long id);

    SolutionComment getSolutionComment(Long id);

    SolutionComment createCommentForSolution(Long id, CommentDtoReq commentDtoReq, Programmer programmer);

    SolutionComment updateSolutionComment(Long id, CommentDtoReq commentDtoReq, Programmer programmer);

    void deleteComment(Long commentId, Programmer programmer);

}
