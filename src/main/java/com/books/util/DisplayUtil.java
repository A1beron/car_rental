package com.books.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.math.NumberUtils.isParsable;

public class DisplayUtil {

    public static final int KEY_SHIFT = 1;

    public static Map<Integer, String> mapAndDisplay(Collection<String> collection) {
        List<String> sortedValues = collection.stream().sorted().collect(Collectors.toList());
        Map<Integer, String> map = new HashMap<>();
        for (int i = KEY_SHIFT; i < collection.size() + KEY_SHIFT; i++) {
            map.put(i, sortedValues.get(i - KEY_SHIFT));
        }
        map.forEach((integer, value) -> System.out.println(integer + ". " + value));
        return map;
    }

    public static boolean canBeSelected(Map<Integer, String> mappedCities, String selection) {
        return isParsable(selection) && mappedCities.containsKey(Integer.parseInt(selection));
    }

}
