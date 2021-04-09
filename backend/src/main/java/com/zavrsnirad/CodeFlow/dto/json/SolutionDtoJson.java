package com.zavrsnirad.CodeFlow.dto.json;

public class SolutionDtoJson {

    private Long solutionId;

    private String code;

    private Double averageGrade;

    private GradeDtoJson loggedInUserGrade;

    private LanguageDtoJson language;

    private String author;


    public SolutionDtoJson(Long solutionId, String code, Double averageGrade, GradeDtoJson loggedInUserGrade, LanguageDtoJson language, String author) {
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

    public GradeDtoJson getLoggedInUserGrade() {
        return loggedInUserGrade;
    }

    public void setLoggedInUserGrade(GradeDtoJson loggedInUserGrade) {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
