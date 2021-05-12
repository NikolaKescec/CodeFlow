package com.zavrsnirad.CodeFlow.dto.mappers;

import com.zavrsnirad.CodeFlow.domain.Language;
import com.zavrsnirad.CodeFlow.dto.json.LanguageDtoJson;

public class MapperLanguage {

    public static LanguageDtoJson LanguageToJson(Language language) {
        return new LanguageDtoJson(
                language.getLanguageId(),
                language.getLanguage(),
                language.getImports(),
                language.getMain(),
                language.getJudgeId()
        );
    }

}
