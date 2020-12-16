package dao;

import models.Categories;
import models.Events;
import models.User;

import java.util.List;

public interface CategoriesDao {
    //create
    void add(Categories categories);

    //read
    List<Categories> getAll();
    List<User> getCategoriesUsers(Categories categories);
    List<Events> getCategoriesEvents(Categories categories);

    // find by ID
    Categories findById(int id);

    //update
    void update(int id, String name, String bio);

    //delete
    void deleteById(int id);
    void clearAll();
}
