package com.zavrsnirad.CodeFlow.service;

import com.zavrsnirad.CodeFlow.domain.Language;

import java.util.List;

public interface LanguageService {

    List<Language> listLanguages();

    Language findById(Long id);

}
