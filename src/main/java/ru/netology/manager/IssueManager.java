package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IssueManager {
    private IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void add(Issue item) {
        repository.save(item);
    }

    public ArrayList<Issue> getOpened() {
        return repository.getOpened();
    }

    public ArrayList<Issue> getClosed() {
        return repository.getCosed();
    }

    public ArrayList<Issue> getAll() {
        return repository.findAll();
    }

    public void closeById(int id) {
        repository.closeById(id);
    }

    public void openById(int id) {
        repository.openById(id);
    }

    public ArrayList<Issue> sortByDate(Comparator<Issue> comparator) {
        ArrayList<Issue> result = new ArrayList<>(getAll());
        result.sort(comparator);
        return result;
    }

    public List<Issue> filter(Predicate<Issue> predicate) {
        return getAll().stream()
                .filter(predicate)
                .collect(Collectors.<Issue>toList());
    }

    public Predicate<Issue> getFilterByAuthor(String author) {
        return p -> p.getAuthor().equalsIgnoreCase(author);
    }

    public Predicate<Issue> getFilterByLabel(String label) {
        return p -> p.getLabels().contains(label);
    }

    public Predicate<Issue> getFilterByAssignee(String assignee) {
        return p -> p.getAssignees().contains(assignee);
    }

    public List<Issue> filterByAuthor(String author) {
        return filter(getFilterByAuthor(author));
    }

    public List<Issue> filterByLabel(String label) {
        return filter(getFilterByLabel(label));
    }

    public List<Issue> filterByAssignee(String assignee) {
        return filter(getFilterByAssignee(assignee));
    }
}