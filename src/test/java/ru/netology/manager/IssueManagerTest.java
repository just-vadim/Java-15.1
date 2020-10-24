package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Sets;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {
    IssueRepository repository = new IssueRepository();
    IssueManager manager = new IssueManager(repository);
    private Issue firstIssue = new Issue(1, "firstHeading", "firstDescription", "firstAuthor", Sets.newSet("Label1","Label2", "Label3"), Sets.newSet("Project1", "Project2"), Sets.newSet("Milestone1", "Milestone2"), Sets.newSet("Assignee1", "Assignee2"));
    private Issue secondIssue = new Issue(2, "secondHeading", "secondDescription", "secondAuthor", Sets.newSet("Label1","Label2"), Sets.newSet("Project1", "Project2"), Sets.newSet("Milestone1", "Milestone2"), Sets.newSet("Assignee1", "Assignee2"));
    private Issue thirdIssue = new Issue(3, "secondHeading", "secondDescription", "firstAuthor", Sets.newSet("Label1","Label2", "Label3"), Sets.newSet("Project1", "Project2"), Sets.newSet("Milestone1", "Milestone2"), Sets.newSet("Assignee1", "Assignee2"));

    @BeforeEach
    public void setUp() {
        manager.add(firstIssue);
        manager.add(secondIssue);
        manager.add(thirdIssue);
    }

    @Test
    void shouldCloseById() {
        manager.closeById(2);
        ArrayList<Issue> expected = new ArrayList<>(Arrays.asList(firstIssue, thirdIssue));
        ArrayList<Issue> actual = manager.getOpened();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAuthor() {
        ArrayList<Issue> expected = new ArrayList<>(Arrays.asList(firstIssue, thirdIssue));
        ArrayList<Issue> actual = manager.filterByAuthor("firstAuthor");
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByLabels() {
        ArrayList<Issue> expected = new ArrayList<>(Arrays.asList(firstIssue, thirdIssue));
        ArrayList<Issue> actual = manager.filterByLabel(Sets.newSet("Label1", "Label2", "Label3"));
        assertEquals(expected, actual);
    }
}