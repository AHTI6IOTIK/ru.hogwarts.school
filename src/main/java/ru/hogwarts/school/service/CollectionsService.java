package ru.hogwarts.school.service;

import java.util.ArrayList;
import java.util.List;

public class CollectionsService {
    public static  <E> List<List<E>> split(List<E> list, int batchSize) {
        List<List<E>> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i % batchSize == 0) {
                newList.add(new ArrayList<>());
            }

            newList.get(newList.size() - 1).add(list.get(i));
        }

        return newList;
    }
}
