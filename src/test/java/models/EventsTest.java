package models;
import models.Events;
import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;

public class EventsTest extends TestCase {
    @Test
    public void EventsInstantiatesCorrectly_true(){
        Events newEvents = setupEvents();
        Events altEvents = setupAltEvents();
        assertTrue(newEvents instanceof Events);
        assertTrue(altEvents instanceof Events);
    }

    @Test
    public void getTitleReturnsCorrectly(){
        Events newEvents = setupEvents();
        assertEquals("Tembea Kenya",newEvents.getTitle());
    }

    @Test
    public void getDescriptionReturnsCorrectly(){
        Events newEvents = setupEvents();
        assertEquals("Road Trip to The Mara. 23/12/2020",newEvents.getDescription());
    }

//    @Test
//    public void getEventIdReturnsCorrectly(){
//        Events newEvents  = setupEvents();
//        Integer expected = 36;
//        assertEquals(expected,newEvents.getEventId());
//    }
    @Test
    public void setTitleReturnsCorrectly(){
        Events newEvents  = setupEvents();
        String initial=newEvents.getTitle();
        newEvents.setTitle("Kam");
        assertNotEquals(initial,newEvents.getTitle());
    }

    @Test
    public void setDescriptionReturnsCorrectly(){
        Events newEvents = setupEvents();
        String initial=newEvents.getDescription();
        newEvents.setDescription("Kam");
        assertNotEquals(initial,newEvents.getDescription());
    }

    @Test
    public void setEventsIdReturnsCorrectly(){
        Events newEvents = setupEvents();
        Integer initial=newEvents.getEventsId();
        newEvents.setEventsId(23);
        assertNotEquals(initial,newEvents.getEventsId());
    }

    //Helpers
    public Events setupEvents() {

        return new Events("Tembea Kenya", "Road Trip to The Mara. 23/12/2020", 23);
    }

    public Events setupAltEvents() {
        return new Events("Lets Bike", ".Full day biking at The Karura Forest. 19/12/2020", 25);
    }
}