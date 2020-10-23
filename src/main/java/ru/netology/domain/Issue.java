package ru.netology.domain;

import java.util.Set;

public class Issue {
    private int id;
    private String heading;
    private String description;
    private boolean isOpen = true;
    private String author;
    private Set<String> labels;
    private Set<String> projects;
    private Set<String> milestones;
    private Set<String> assignees;

    public Issue(int id, String heading, String description, String author, Set<String> labels, Set<String> projects, Set<String> milestones, Set<String> assignees) {
        this.id = id;
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
}