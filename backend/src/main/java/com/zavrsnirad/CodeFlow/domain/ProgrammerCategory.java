package com.zavrsnirad.CodeFlow.domain;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity(name = "programmer_category")
public class ProgrammerCategory extends TimeAndUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long programmer_category_id;


    @Column(nullable = false)
    private String title;

    public ProgrammerCategory() {
    }

    public ProgrammerCategory(String title) {
        this.title = title;
    }

    public Long getProgrammer_category_id() {
        return programmer_category_id;
    }

    public void setProgrammer_category_id(Long programmer_category_id) {
        this.programmer_category_id = programmer_category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammerCategory that = (ProgrammerCategory) o;
        return Objects.equals(programmer_category_id, that.programmer_category_id) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(programmer_category_id, title);
    }
}
