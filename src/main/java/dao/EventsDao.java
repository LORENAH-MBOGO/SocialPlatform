package dao;

import models.Events;
import models.Categories;

import java.util.List;

public interface EventsDao {
    //create
    void add(Events news);

    //read
    List<Events> getAll();

    // find by ID
    Events findById(int id);

    //update
    void update(int id, String title, String description, int categoriesId);

    //delete
    void deleteById(int id);
    void clearAll();
}
