package com.zavrsnirad.CodeFlow.controllers;

import com.zavrsnirad.CodeFlow.dto.mappers.MapperLanguage;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperList;
import com.zavrsnirad.CodeFlow.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("language")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @GetMapping
    public ResponseEntity<?> getLanguages() {
        return ResponseEntity.status(HttpStatus.OK).body(MapperList.getList(languageService.listLanguages(), MapperLanguage::LanguageToJson));
    }

}
