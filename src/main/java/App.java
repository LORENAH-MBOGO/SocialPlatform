import dao.Sql20UserDao;
import dao.Sql2oCategoriesDao;
import dao.Sql2oEventsDao;
import exceptions.ApiException;
import models.Categories;
import models.Events;
import models.User;
import org.sql2o.Connection;
import com.google.gson.Gson;
import dao.DB;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        Sql20UserDao userDao = new Sql20UserDao(DB.sql2o);
        Sql2oCategoriesDao categoriesDao = new Sql2oCategoriesDao(DB.sql2o);
        Sql2oEventsDao eventsDao = new Sql2oEventsDao(DB.sql2o);
        Connection conn = DB.sql2o.open();
        Gson gson = new Gson();

        post("/user/new","application/json",(req,res)->{
            User user = gson.fromJson(req.body(), User.class);
            userDao.add(user);
            res.status(201);
            res.type("application/json");
            return gson.toJson(user);
        });

        post("/categories/new","application/json",(req,res)->{
            Categories categories = gson.fromJson(req.body(), Categories.class);
            if (categories.getClass() != null){
                if(categories != null){
                    categoriesDao.add(categories);
                    res.status(201);
                    res.type("application/json");
                    return gson.toJson(categories);
                }
                else {
                    throw new ApiException(404, String.format("The category assigned does not exist"));
                }
            } else {
                categoriesDao.add(categories);
                res.status(201);
                res.type("application/json");
                return gson.toJson(categories);
            }
        });

        post("/events/new","application/json",(req,res)->{
            Events events = gson.fromJson(req.body(), Events.class);
            if (events.getEventsId() != null){
                if(events != null){
                    eventsDao.add(events);
                    res.status(201);
                    res.type("application/json");
                    return gson.toJson(events);
                }
                else {
                    throw new ApiException(404, String.format("The event  does not exist"));
                }
            } else {
//                events.a(events);
//                res.status(201);
                res.type("application/json");
                return gson.toJson(events);
            }
        });

        get("/user", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            if (userDao.getAll().size() == 0){
                return "{\"message\":\"I'm sorry, but no users yet added.\"}";
            }else{
                return gson.toJson(userDao.getAll());//send it back to be displayed
            }
        });
        get("/", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return "{\"Add user\":\"/user/new\",\"Add categories\":\"/categories/new\",\"Post events\":\"/events/post\",\"show user\":\"/user\",\"show categories\":\"/categories\",\"show events\":\"/events\",\"show category events\":\"/categories/:id/events\",\"show category user\":\"/categories/:id/users\"}";
        });

        get("/events", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            if (eventsDao.getAll().size() == 0){
                return "{\"message\":\"I'm sorry, but no events yet added.\"}";
            } else{
                return gson.toJson(eventsDao.getAll());//send it back to be displayed
            }
        });


        get("/categories", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            if (categoriesDao.getAll().size() == 0){
                return "{\"message\":\"I'm sorry, but no categories yet added.\"}";
            } else{
                return gson.toJson(categoriesDao.getAll());//send it back to be displayed
            }
        });

        get("/events/:id", "application/json", (req, res) -> {
            int events = Integer.parseInt(req.params("id"));

            Events eventsToFind = eventsDao.findById(events);

            if (eventsToFind == null){
                throw new ApiException(404, String.format("No events with the id: \"%s\" exists", req.params("id")));
            }

            return gson.toJson(eventsToFind);
        });

//        get("/events/:id/users", "application/json", (req, res) -> {
//            int eventsId = Integer.parseInt(req.params("id"));
//
//            Users usersToFind = userDao.findById(eventsId);
//
//            if (categoriesToFind == null){
//                throw new ApiException(404, String.format("No category with the id: \"%s\" exists", req.params("id")));
//            }
//            else if (categoriesDao.getCategoriesUsers(categoriesToFind).size()==0){
//                return "{\"message\":\"I'm sorry, but no users belong to this events.\"}";
//            }
//            else {
//                return gson.toJson(categoriesDao.getCategoriesUsers(categoriesToFind));
//            }
//        });


//        post("/events/:id/user", "application/json", (req, res) -> {
//            int postId = Integer.parseInt(req.params("id"));
//
//            Events postToFind = eventsDao.findById(postId);
//
//            if (postToFind == null){
//                throw new ApiException(404, String.format("No events posted with the id: \"%s\" exists", req.params("id")));
//            }
//
//            return gson.toJson(postToFind);
//        });

        post("/events/:id", "application/json", (req, res) -> {
            int eventId = Integer.parseInt(req.params("id"));

            Events eventsToFind = eventsDao.findById(eventId);

            if (eventsToFind == null){
                throw new ApiException(404, String.format("No events with the id: \"%s\" exists", req.params("id")));
            }

            return gson.toJson(eventsToFind);
        });


        //FILTERS
        exception(ApiException.class, (exc, req, res) -> {
            ApiException err = (ApiException) exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json"); //after does not run in case of an exception.
            res.status(err.getStatusCode()); //set the status
            res.body(gson.toJson(jsonMap));  //set the output.
        });

        after((req, res) ->{
            res.type("application/json");
        });
    }
}