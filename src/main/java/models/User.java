package models;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String profession;
    private String bio;
    private String interests;
    private Integer categoriesId;

    public User(String name, String bio, String position, String role) {
        this.name = name;
        this.bio = bio;
        this.profession = profession;
        this.interests = interests;
        this.categoriesId = null;
    }

    public User(String name, String bio, String position, String role, int categoriesId) {
        this.name = name;
        this.bio = bio;
        this.profession = profession;
        this.interests = interests;
        this.categoriesId = categoriesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                categoriesId == user.categoriesId &&
                name.equals(user.name) &&
                bio.equals(user.bio) &&
                profession.equals(user.getProfession()) &&
                interests.equals(user.interests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bio, profession, interests, categoriesId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public Integer getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }
}
