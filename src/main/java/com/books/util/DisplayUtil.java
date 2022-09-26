package com.books.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DisplayUtil<T> {

    public Map<String, T> mapForDisplay(Collection<T> collection) {
        List<T> sortedValues = collection.stream().sorted().collect(Collectors.toList());
        Map<String, T> map = new HashMap<>();
        for (int i = 0; i < collection.size()+1; i++) {
            map.put(String.valueOf(i), sortedValues.get(i));
        }
        return map;
    }

}
