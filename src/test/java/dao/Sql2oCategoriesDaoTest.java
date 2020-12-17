package dao;

import junit.framework.TestCase;
import models.Categories;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Sql2oCategoriesDaoTest extends TestCase {

    private Sql20UserDao userDao;
    private Sql2oCategoriesDao CategoriesDao;
    private Sql2oCategoriesDao categoriesDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/social_test";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "skylar");
        userDao = new Sql20UserDao(sql2o);
        categoriesDao = new Sql2oCategoriesDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        userDao.clearAll();
        categoriesDao.clearAll();
        conn.close();
    }

    @Test
    public void CategoriesIsSavedCorrectly() {
        Categories testCategories = setupCategories();
        categoriesDao.add(testCategories);
        assertEquals(categoriesDao.getAll().get(0), testCategories);
    }

    @Test
    public void addingCategoriesSetsId() throws Exception {
        Categories testCategories = setupCategories();
        int originalPostId = testCategories.getId();
        categoriesDao.add(testCategories);
        assertNotEquals(originalPostId, testCategories.getId());
    }

//    @Test
//    public void addedCategoriesAreReturnedFromGetAll() throws Exception {
//        Categories testCategories = setUpCategories();
//        categoriesDao.add(testCategories);
//        assertEquals(1, categoriesDao.getAll().size());
//    }

    @Test
    public void noCategoriesReturnsEmptyList() throws Exception {
        assertEquals(0, categoriesDao.getAll().size());
    }

//    @Test
//    public void deleteByIdDeletesCorrectUser() throws Exception {
//        categories testCategories = setupCategories();
//        categoriesDao.add(testCategories);
//        categoriesDao.deleteById(testCategories.getId());
//        assertEquals(0, categoriesDao.getAll().size());
//    }

    @Test
    public void clearAll() throws Exception {
        Categories testCategories = setupCategories();
        Categories altDepartment = setUpAltCategories();
        categoriesDao.clearAll();
        assertEquals(0, categoriesDao.getAll().size());
    }

    //Helpers
    public Categories setupCategories() {
        return new Categories("Sports", "Enjoy a variety of sporty activities eg.biking");
    }

    public Categories setUpAltCategories() {
        return new Categories("Travel", "Road Trips");
    }
}