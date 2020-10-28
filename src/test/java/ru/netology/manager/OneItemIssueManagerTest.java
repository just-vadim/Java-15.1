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

class OneItemIssueManagerTest {

    IssueRepository repository = new IssueRepository();
    IssueManager manager = new IssueManager(repository);
    SortByDateComparator comparator = new SortByDateComparator();

    private Issue firstIssue = new Issue(1, LocalDate.of(2020, 10, 05) , "firstHeading", "firstDescription", "firstAuthor", Sets.newSet("Label1","Label2", "Label3"), Sets.newSet("Project1", "Project2"), Sets.newSet("Milestone1", "Milestone2"), Sets.newSet("Assignee1", "Assignee2"));

    @BeforeEach
    public void setUp() {
        manager.add(firstIssue);
    }

    @Test
    void shouldGetOpened() {
        List<Issue> expected = List.of(firstIssue);
        List<Issue> actual = manager.getOpened();
        assertEquals(expected, actual);
    }

    @Test
    void shouldGetClosed() {
        manager.closeById(1);
        List<Issue> expected = List.of(firstIssue);
        List<Issue> actual = manager.getClosed();
        assertEquals(expected, actual);
    }

    @Test
    void shouldGetAll() {
        List<Issue> expected = List.of(firstIssue);
        List<Issue> actual = manager.getAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReopen() {
        manager.closeById(1);
        manager.openById(1);
        List<Issue> expected = List.of(firstIssue);
        List<Issue> actual = manager.getOpened();
        assertEquals(expected, actual);
    }

    @Test
    void shouldSortByDate() {
        List<Issue> expected = List.of(firstIssue);
        List<Issue> actual = manager.sortBy(comparator);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAuthor() {
        List<Issue> expected = List.of(firstIssue);
        List<Issue> actual = manager.filterByAuthor("firstAuthor");
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByAuthorIfNotExist() {
        List<Issue> expected = List.of();
        List<Issue> actual = manager.filterByAuthor("secondAuthor");
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByLabel() {
        List<Issue> expected = List.of(firstIssue);
        List<Issue> actual = manager.filterByLabel("Label1");
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByLabelIfNotExist() {
        List<Issue> expected = List.of();
        List<Issue> actual = manager.filterByLabel("Label5");
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAssignee() {
        List<Issue> expected = List.of(firstIssue);
        List<Issue> actual = manager.filterByAssignee("Assignee1");
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByAssigneeIfNotExist() {
        List<Issue> expected = List.of();
        List<Issue> actual = manager.filterByAssignee("Assignee10");
        assertEquals(expected, actual);
    }
}