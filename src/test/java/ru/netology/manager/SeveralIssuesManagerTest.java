package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Sets;
import ru.netology.comparator.SortByDateComparator;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SeveralIssuesManagerTest {

    IssueRepository repository = new IssueRepository();
    IssueManager manager = new IssueManager(repository);
    SortByDateComparator comparator = new SortByDateComparator();

    private Issue firstIssue = new Issue(1, LocalDate.of(2020, 10, 05) , "firstHeading", "firstDescription", "firstAuthor", Sets.newSet("Label1"), Sets.newSet("Project1", "Project2"), Sets.newSet("Milestone1", "Milestone2"), Sets.newSet("Assignee1"));
    private Issue secondIssue = new Issue(2, LocalDate.of(2020, 10, 07), "secondHeading", "secondDescription", "secondAuthor", Sets.newSet("Label1","Label2"), Sets.newSet("Project1", "Project2"), Sets.newSet("Milestone1", "Milestone2"), Sets.newSet("Assignee2"));
    private Issue thirdIssue = new Issue(3, LocalDate.of(2020, 10, 03), "secondHeading", "thirdDescription", "firstAuthor", Sets.newSet("Label3"), Sets.newSet("Project1", "Project2"), Sets.newSet("Milestone1", "Milestone2"), Sets.newSet("Assignee2"));
    private Issue fourthIssue = new Issue(4, LocalDate.of(2020, 10, 20), "fourthHeading", "fourthDescription", "firstAuthor", Sets.newSet("Label4"), Sets.newSet("Project1", "Project2"), Sets.newSet("Milestone1", "Milestone2"), Sets.newSet("Assignee4"));

    @BeforeEach
    public void setUp() {
        manager.add(firstIssue);
        manager.add(secondIssue);
        manager.add(thirdIssue);
        manager.add(fourthIssue);
    }

    @Test
    void shouldGetOpened() {
        manager.closeById(2);
        manager.closeById(4);
        List<Issue> expected = List.of(firstIssue, thirdIssue);
        List<Issue> actual = manager.getOpened();
        assertEquals(expected, actual);
    }

    @Test
    void shouldGetClosed() {
        manager.closeById(2);
        manager.closeById(4);
        List<Issue> expected = List.of(secondIssue, fourthIssue);
        List<Issue> actual = manager.getClosed();
        assertEquals(expected, actual);
    }

    @Test
    void shouldGetAll() {
        List<Issue> expected = List.of(firstIssue, secondIssue, thirdIssue, fourthIssue);
        List<Issue> actual = manager.getAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldSortByDate() {
        List<Issue> expected = List.of(thirdIssue, firstIssue, secondIssue, fourthIssue);
        List<Issue> actual = manager.sortBy(comparator);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAuthor() {
        List<Issue> expected = List.of(firstIssue, thirdIssue, fourthIssue);
        List<Issue> actual = manager.filterByAuthor("firstAuthor");
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByAuthorIfNotExist() {
        List<Issue> expected = List.of();
        List<Issue> actual = manager.filterByAuthor("sixthAuthor");
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByLabel() {
        List<Issue> expected = List.of(firstIssue, secondIssue);
        List<Issue> actual = manager.filterByLabel("Label1");
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByLabelIfNotExist() {
        List<Issue> expected = List.of();
        List<Issue> actual = manager.filterByLabel("Label10");
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAssignee() {
        List<Issue> expected = List.of(secondIssue, thirdIssue);
        List<Issue> actual = manager.filterByAssignee("Assignee2");
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByAssigneeIfNotExist() {
        List<Issue> expected = List.of();
        List<Issue> actual = manager.filterByAssignee("Assignee100");
        assertEquals(expected, actual);
    }
}