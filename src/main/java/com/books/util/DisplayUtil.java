package com.books.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.math.NumberUtils.isParsable;

public class DisplayUtil<T> {

    public static final int KEY_SHIFT = 1;

    public Map<Integer, T> convertToMap(List<T> list) {
        Map<Integer, T> map = new HashMap<>();
        for (int i = KEY_SHIFT; i < list.size() + KEY_SHIFT; i++) {
            map.put(i, list.get(i - KEY_SHIFT));
        }
        return map;
    }

    public boolean canBeSelected(Map<Integer, T> mappedCities, String selection) {
        return isParsable(selection) && mappedCities.containsKey(Integer.parseInt(selection));
    }

    public void display(Map<Integer, T> map) {
        map.forEach((integer, value) -> System.out.println(integer + ". " + value));
    }

}
