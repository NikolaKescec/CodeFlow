package com.zavrsnirad.CodeFlow.dto.mappers;

import com.zavrsnirad.CodeFlow.domain.Solution;
import com.zavrsnirad.CodeFlow.dto.json.SolutionDtoJson;

public class MapperSolution {

    public static SolutionDtoJson SolutionToJson(Solution solution) {
        return new SolutionDtoJson(
                solution.getSolutionId(),
                solution.getCode(),
                MapperLanguage.LanguageToJson(solution.getLanguage()),
                MapperUser.UserToJson(solution.getAuthor()),
                MapperList.getList(solution.getComments(), MapperComment::SolutionCommentToJson)
        );
    }

}
