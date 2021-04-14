package com.zavrsnirad.CodeFlow.service.implementation;

import com.zavrsnirad.CodeFlow.domain.Language;
import com.zavrsnirad.CodeFlow.repository.LanguageRepository;
import com.zavrsnirad.CodeFlow.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LanguageServiceJpa implements LanguageService{

    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public List<Language> listLanguages() {
        return languageRepository.findAll();
    }

    @Override
    public Language findById(Long id) {
        try {
            return languageRepository.findById(id).get();
        } catch (NoSuchElementException entityNotFoundException) {
            throw new IllegalArgumentException("Language for ID: " + id + " not found!");
        }
    }
}
