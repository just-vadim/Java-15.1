package ru.netology.domain;

import java.time.LocalDate;
import java.util.Set;

public class Issue implements Comparable<Issue> {
    private int id;
    private LocalDate date;
    private String heading;
    private String description;
    private boolean isOpen = true;
    private String author;
    private Set<String> labels;
    private Set<String> projects;
    private Set<String> milestones;
    private Set<String> assignees;

    public Issue(int id, LocalDate date, String heading, String description, String author, Set<String> labels, Set<String> projects, Set<String> milestones, Set<String> assignees) {
        this.id = id;
        this.date = date;
        this.heading = heading;
        this.description = description;
        this.author = author;
        this.labels = labels;
        this.projects = projects;
        this.milestones = milestones;
        this.assignees = assignees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<String> getLabels() {
        return labels;
    }

    public Set<String> getProjects() {
        return projects;
    }

    public Set<String> getMilestones() {
        return milestones;
    }

    public Set<String> getAssignees() {
        return assignees;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", heading='" + heading + '\'' +
                ", description='" + description + '\'' +
                ", isOpen=" + isOpen +
                ", author='" + author + '\'' +
                ", labels=" + labels +
                ", projects=" + projects +
                ", milestones=" + milestones +
                ", assignees=" + assignees +
                '}';
    }

    @Override
    public int compareTo(Issue issue) {
        return getDate().compareTo(issue.getDate());
    }
}