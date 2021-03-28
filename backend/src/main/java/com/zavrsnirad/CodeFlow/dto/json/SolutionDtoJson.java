package com.zavrsnirad.CodeFlow.dto.json;

import java.util.List;

public class SolutionDtoJson {

    private Long solutionId;

    private String code;

    private LanguageDtoJson language;

    private UserDtoJson author;

    private List<SolutionCommentDtoJson> comments;

    public SolutionDtoJson(Long solutionId, String code, LanguageDtoJson language, UserDtoJson author, List<SolutionCommentDtoJson> comments) {
        this.solutionId = solutionId;
        this.code = code;
        this.language = language;
        this.author = author;
        this.comments = comments;
    }

    public Long getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Long solutionId) {
        this.solutionId = solutionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LanguageDtoJson getLanguage() {
        return language;
    }

    public void setLanguage(LanguageDtoJson language) {
        this.language = language;
    }

    public UserDtoJson getAuthor() {
        return author;
    }

    public void setAuthor(UserDtoJson author) {
        this.author = author;
    }

    public List<SolutionCommentDtoJson> getComments() {
        return comments;
    }

    public void setComments(List<SolutionCommentDtoJson> comments) {
        this.comments = comments;
    }
}
