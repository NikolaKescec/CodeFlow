package com.zavrsnirad.CodeFlow.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Language extends TimeAndUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long languageId;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private String imports;

    @Column(nullable = false)
    private String main;

    public Language() {
    }

    public Language(String language, String imports, String main) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language1 = (Language) o;
        return Objects.equals(languageId, language1.languageId) && Objects.equals(language, language1.language) && Objects.equals(imports, language1.imports) && Objects.equals(main, language1.main);
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageId, language, imports, main);
    }
}
