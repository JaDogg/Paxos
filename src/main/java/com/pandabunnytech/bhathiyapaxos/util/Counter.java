package com.pandabunnytech.bhathiyapaxos.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class  Counter<T> {
    private final Map<T, Integer> values;
    public Counter() {
        values = new LinkedHashMap<>();
    }

    public void count(T value) {
        Integer itemCount = values.get(value);
        if (itemCount != null) {
            itemCount = itemCount + 1;
        } else {
            itemCount = 1;
        }
        values.put(value, itemCount);
    }

    public Res<T> maxOccurrence() {
        Integer max = 0;
        T maxVal = null;
        for (Map.Entry<T, Integer> entry: values.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                maxVal = entry.getKey();
            }
        }
        return new Res<T>(max, maxVal);
    }

    public static class Res<T> {
        private final int count;
        private final T value;

        private Res(int count, T value) {
            this.count = count;
            this.value = value;
        }

        public int getCount() {
            return count;
        }

        public T getValue() {
            return value;
        }
    }
}
