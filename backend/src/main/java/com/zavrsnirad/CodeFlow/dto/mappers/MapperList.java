package com.zavrsnirad.CodeFlow.dto.mappers;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapperList {

    public static <T, E> List<T> getList(List<E> initialList, Function<E, T> mapper) {
        return initialList.stream().map(mapper).collect(Collectors.toList());
    }

}
