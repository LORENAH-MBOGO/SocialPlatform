package models;

import java.util.Objects;

public class Events {
    private int id;
    private String title;
    private String description;
    private Integer categoriesId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Events events = (Events) o;
        return id == events.id &&
                categoriesId == events.categoriesId &&
                title.equals(events.title) &&
                description.equals(events.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, categoriesId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }

    public Events(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Events(String title, String description, int categoriesId) {
        this.title = title;
        this.description = description;
        this.categoriesId = categoriesId;
    }
}
