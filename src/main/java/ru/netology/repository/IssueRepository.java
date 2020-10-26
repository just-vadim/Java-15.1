package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;

public class IssueRepository {
    private ArrayList<Issue> issues = new ArrayList<>();

    public void save(Issue item) {
        issues.add(item);
    }

    public ArrayList<Issue> findAll() {
        return issues;
    }

    public ArrayList<Issue> getOpened() {
        ArrayList<Issue> result = new ArrayList<>();
        for (Issue issue : issues) {
            if (issue.isOpen()) {
                result.add(issue);
            }
        }
        return result;
    }

    public ArrayList<Issue> getCosed() {
        ArrayList<Issue> result = new ArrayList<>();
        for (Issue issue : issues) {
            if (!issue.isOpen()) {
                result.add(issue);
            }
        }
        return result;
    }

    public void closeById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                issue.setOpen(false);
            }
        }
    }

    public void openById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                issue.setOpen(true);
            }
        }
    }
}