package dao;

import models.User;

import java.util.List;

public interface UserDao {
    //create
    void add(User user);

    //read
    List<User> getAll();

    // find by ID
    User findById(int id);

    //update
    void update(int id, String name, String bio, String profession, String interests);

    //delete
    void deleteById(int id);
    void clearAll();
}
