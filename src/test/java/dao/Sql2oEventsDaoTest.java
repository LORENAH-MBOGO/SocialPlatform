package dao;

import models.Categories;
import models.Events;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oNewsDaoTest {
    private Sql20UserDao userDao;
    private Sql2oEventsDao eventsDao;
    private Sql2oCategoriesDao categoriesDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/social_test";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "skylar");
        userDao = new Sql20UserDao(sql2o);
        eventsDao = new Sql2oEventsDao(sql2o);
        categoriesDao = new Sql2oCategoriesDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        userDao.clearAll();
        categoriesDao.clearAll();
        eventsDao.clearAll();
        conn.close();
    }

    @Test
    public void newsIsSavedCorrectly(){
        Events newPost = setupEvents();
        eventsDao.add(newPost);
        assertEquals(eventsDao.getAll().get(0),newPost);
    }

    @Test
    public void addingEventsSetsId() throws Exception {
        Events testEvents = setupEvents();
        int originalPostId = testEvents.getId();
        eventsDao.add(testEvents);
        assertNotEquals(originalPostId,testEvents.getId());
    }

    @Test
    public void addedNewsAreReturnedFromGetAll() throws Exception {
        Events testEvents = setupEvents();
        eventsDao.add(test);
        assertEquals(1,eventsDao.getAll().size());
    }

    @Test
    public void noNewsReturnsEmptyList() throws Exception {
        assertEquals(0, eventsDao.getAll().size());
    }

    @Test
    public void deleteByIdDeletesCorrectUser() throws Exception {
        Events testEvents = setupEvents();
        eventsDao.add(testEvents);
        eventsDao.deleteById(testEvents.getId());
        assertEquals(0, eventsDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        Events testEvents = setupEvents();
        Events altEvents = setupAltEvents();
        eventsDao.clearAll();
        assertEquals(0, eventsDao.getAll().size());
    }

    //Helpers
    public Categories setupCategories() {
        return new Categories("Sports", "Full day biking at The Karura Forest.");
    }

    public Categories setUpAltCategories() {
        return new Categories("Travel", "Road Trip to The Mara.");
    }
}