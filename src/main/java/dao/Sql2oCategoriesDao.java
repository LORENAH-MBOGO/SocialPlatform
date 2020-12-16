package dao;

import models.Categories;
import models.Events;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oCategoriesDao implements CategoriesDao {
    private final Sql2o sql2o;
    public Sql2oCategoriesDao(Sql2o sql20) {
        this.sql2o = sql20;
    }

    @Override
    public void add(Categories categories) {
        String sql = "INSERT INTO categories (name, description) VALUES (:name, :description)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(categories)
                    .executeUpdate()
                    .getKey();
            categories.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Categories> getAll() {
        String sql = "SELECT * FROM categories";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Categories.class);
        }
    }

    @Override
    public List<User> getCategoriesUsers(Categories categories) {
        return null;
    }




    @Override
    public List<Events> getCategoriesEvents(Categories categories) {
        String sql = "SELECT * FROM events WHERE categoriesid=:id";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id",categories.getId())
                    .executeAndFetch(Events.class);
        }
    }

    @Override
    public Categories findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM categories WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Categories.class);
        }    }

    @Override
    public void update(int id, String name, String description) {
        String sql = "UPDATE users SET (name, description) = (:name, :description) WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("description", description)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from categories WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from categories";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
