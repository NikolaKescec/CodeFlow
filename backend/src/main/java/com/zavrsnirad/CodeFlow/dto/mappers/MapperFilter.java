package com.zavrsnirad.CodeFlow.dto.mappers;

import java.util.List;
import java.util.function.Predicate;

public class MapperFilter {

    public static <T> T filterOne(List<T> beginningList, Predicate<T> predicate) {
        return beginningList
                .stream().filter(predicate)
                .findFirst().orElse(null);
    }

}
