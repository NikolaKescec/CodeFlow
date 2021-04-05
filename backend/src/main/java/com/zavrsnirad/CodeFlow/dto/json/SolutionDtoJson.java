package com.zavrsnirad.CodeFlow.dto.json;

public class SolutionDtoJson {

    private Long solutionId;

    private String code;

    private Double averageGrade;

    private SolutionGradeDtoJson loggedInUserGrade;

    private LanguageDtoJson language;

    private UserDtoJson author;


    public SolutionDtoJson(Long solutionId, String code, Double averageGrade, SolutionGradeDtoJson loggedInUserGrade, LanguageDtoJson language, UserDtoJson author) {
        this.solutionId = solutionId;
        this.code = code;
        this.averageGrade = averageGrade;
        this.loggedInUserGrade = loggedInUserGrade;
        this.language = language;
        this.author = author;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public SolutionGradeDtoJson getLoggedInUserGrade() {
        return loggedInUserGrade;
    }

    public void setLoggedInUserGrade(SolutionGradeDtoJson loggedInUserGrade) {
        this.loggedInUserGrade = loggedInUserGrade;
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
}
