package ru.netology.manager;

import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Sets;
import ru.netology.comparator.SortByDateComparator;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ZeroItemsIssueManagerTest {

    IssueRepository repository = new IssueRepository();
    IssueManager manager = new IssueManager(repository);
    SortByDateComparator comparator = new SortByDateComparator();

    private Issue firstIssue = new Issue(1, LocalDate.of(2020, 10, 05) , "firstHeading", "firstDescription", "firstAuthor", Sets.newSet("Label1","Label2", "Label3"), Sets.newSet("Project1", "Project2"), Sets.newSet("Milestone1", "Milestone2"), Sets.newSet("Assignee1", "Assignee2"));

    @Test
    void shouldAdd() {
        manager.add(firstIssue);
        ArrayList<Issue> expected = new ArrayList<>(Arrays.asList(firstIssue));
        ArrayList<Issue> actual = manager.getAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotGetOpened() {
        ArrayList<Issue> expected = new ArrayList<>();
        ArrayList<Issue> actual = manager.getOpened();
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotGetClosed() {
        ArrayList<Issue> expected = new ArrayList<>();
        ArrayList<Issue> actual = manager.getClosed();
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotGetAll() {
        ArrayList<Issue> expected = new ArrayList<>();
        ArrayList<Issue> actual = manager.getAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotCloseById() {
        manager.closeById(1);
        ArrayList<Issue> expected = new ArrayList<>();
        ArrayList<Issue> actual = manager.getAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotOpenById() {
        manager.openById(1);
        ArrayList<Issue> expected = new ArrayList<>();
        ArrayList<Issue> actual = manager.getAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotSortByDate() {
        ArrayList<Issue> expected = new ArrayList<>();
        ArrayList<Issue> actual = manager.sortByDate(comparator);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByAuthor() {
        List<Issue> expected = List.of();
        List<Issue> actual = manager.filterByAuthor("firstAuthor");
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByLabel() {
        List<Issue> expected = List.of();
        List<Issue> actual = manager.filterByLabel("Label1");
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByAssignee() {
        List<Issue> expected = List.of();
        List<Issue> actual = manager.filterByAssignee("Assignee1");
        assertEquals(expected, actual);
    }
}