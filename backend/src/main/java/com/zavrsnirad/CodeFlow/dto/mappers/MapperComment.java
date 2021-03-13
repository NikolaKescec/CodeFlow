package com.zavrsnirad.CodeFlow.dto.mappers;

import com.zavrsnirad.CodeFlow.domain.SolutionComment;
import com.zavrsnirad.CodeFlow.domain.TaskComment;
import com.zavrsnirad.CodeFlow.dto.json.SolutionCommentDtoJson;
import com.zavrsnirad.CodeFlow.dto.json.TaskCommentDtoJson;

public class MapperComment {

    public static SolutionCommentDtoJson SolutionCommentToJson(SolutionComment solutionComment) {
        return new SolutionCommentDtoJson(solutionComment.getSolutionCommentId(), solutionComment.getComment(), MapperUser.UserToJson(solutionComment.getCommenter()));
    }

    public static TaskCommentDtoJson TaskCommentToJson(TaskComment taskComment) {
        return new TaskCommentDtoJson(taskComment.getTaskCommentId(), taskComment.getComment(), MapperUser.UserToJson(taskComment.getCommenter()));
    }
}
