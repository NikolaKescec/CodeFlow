package com.zavrsnirad.CodeFlow.dto.mappers;

import com.zavrsnirad.CodeFlow.domain.SolutionComment;
import com.zavrsnirad.CodeFlow.domain.TaskComment;
import com.zavrsnirad.CodeFlow.dto.json.CommentDtoJson;

public class MapperComment {

    public static CommentDtoJson SolutionCommentToJson(SolutionComment solutionComment) {
        return new CommentDtoJson(
                solutionComment.getSolutionCommentId(),
                solutionComment.getComment(),
                MapperUser.UserToJson(solutionComment.getCommenter())
        );
    }

    public static CommentDtoJson TaskCommentToJson(TaskComment taskComment) {
        return new CommentDtoJson(
                taskComment.getTaskCommentId(),
                taskComment.getComment(),
                MapperUser.UserToJson(taskComment.getCommenter())
        );
    }
}
