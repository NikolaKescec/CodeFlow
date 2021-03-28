package com.zavrsnirad.CodeFlow.dto.json;

import javax.persistence.Column;

public class LanguageDtoJson {

    private Long languageId;

    private String language;

    private String imports;

    private String main;

    public LanguageDtoJson(Long languageId, String language, String imports, String main) {
        this.languageId = languageId;
        this.language = language;
        this.imports = imports;
        this.main = main;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getImports() {
        return imports;
    }

    public void setImports(String imports) {
        this.imports = imports;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }
}
