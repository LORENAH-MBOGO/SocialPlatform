package dao;

import models.Events;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oEventsDao implements EventsDao {
    private final Sql2o sql2o;
    public Sql2oEventsDao(Sql2o sql2o) {
        this.sql2o=sql2o;
    }
    @Override
    public void add(Events events) {
        String sql = "INSERT INTO events (title, description, eventsId) VALUES (:title, :description, :eventsId)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(events)
                    .executeUpdate()
                    .getKey();
            events.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Events> getAll() {
        String sql = "SELECT * FROM events";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Events.class);
        }    }

    @Override
    public Events findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM events WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Events.class);
        }    }

    @Override
    public void update(int id, String title, String description, int eventsId) {
        String sql = "UPDATE news SET (title, description, eventsId) = (:title, :description, :eventsId) WHERE id=:id"; //CHECK!!!
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", title)
                    .addParameter("bio", description)
                    .addParameter("eventsid", eventsId)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from events WHERE id = :id";
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
        String sql = "DELETE from events";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
