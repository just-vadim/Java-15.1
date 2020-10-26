package ru.netology.comparator;

import ru.netology.domain.Issue;

import java.util.Comparator;

public class SortByDateComparator implements Comparator<Issue> {
    @Override
    public int compare(Issue o1, Issue o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}