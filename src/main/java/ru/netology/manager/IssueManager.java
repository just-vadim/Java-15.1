package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
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

    public ArrayList<Issue> filter(Predicate<Issue> predicate) {
        /*ArrayList<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (predicate.test(issue)) {
                result.add(issue);
            }
        }
        return result;*/
        ArrayList<Issue> result = new ArrayList(Arrays.asList(
                getAll().stream()
                .filter(predicate)
                .collect(Collectors.toList())));

        return result;
    }

    public Predicate<Issue> getFilterByAuthor(String author) {
        return p -> p.getAuthor().equalsIgnoreCase(author);
    }

    public Predicate<Issue> getFilterByLabel(Set labels) {
        return p -> p.getLabels().contains(labels);
    }

    public Predicate<Issue> getFilterByAssignee(Set assignees) {
        return p -> p.getAssignees().contains(assignees);
    }

    public ArrayList<Issue> filterByAuthor(String author) {
        return filter(getFilterByAuthor(author));
    }

    public ArrayList<Issue> filterByLabel(Set labels) {
        return filter(getFilterByLabel(labels));
    }

    public ArrayList<Issue> filterByAssignee(Set assignees) {
        return filter(getFilterByAssignee(assignees));
    }
}