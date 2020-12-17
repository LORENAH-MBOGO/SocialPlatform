package models;

import org.graalvm.compiler.lir.LIRInstruction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void userInstantiatesCorrectly_true(){
        User newUser = setupUser();
        assertTrue(newUser instanceof User);
//        assertTrue(altUser instanceof User);
    }

    @Test
    public void getNameReturnsCorrectly(){
        User newUser = setupUser();
        assertEquals("Skylar",newUser.getName());
    }

    @Test
    public void getBioReturnsCorrectly(){
        User newUser = setupUser();
        assertEquals("Singer, Programmer, Artist",newUser.getBio());
    }

    @Test
    public void getProfessionReturnsCorrectly(){
        User newUser = setupUser();
        assertEquals("programmer",newUser.getProfession());
    }

    @Test
    public void getInterestsReturnsCorrectly(){
        User newUser = setupUser();
        assertEquals("entertainment",newUser.getInterests());
    }


    @Test
    public void setNameReturnsCorrectly(){
        User newUser = setupUser();
        String initial=newUser.getName();
        newUser.setName("Kam");
        assertNotEquals(initial,newUser.getName());
    }

    @Test
    public void setBioReturnsCorrectly(){
        User newUser = setupUser();
        String initial=newUser.getBio();
        newUser.setBio("Kam");
        assertNotEquals(initial,newUser.getBio());
    }

    @Test
    public void setProfessionReturnsCorrectly(){
        User newUser = setupUser();
        String initial=newUser.getProfession();
        newUser.setProfession("Kam");
        assertNotEquals(initial,newUser.getProfession());
    }

//    @Test
//    public void setInterestsReturnsCorrectly(){
//        User newUser = setupUser();
//        String initial=newUser.getId();
//        newUser.setInterests("Kam");
//        assertNotEquals(initial,newUser.getInterests());
//    }



    //Helpers
    public User setupUser() {
        return new User("Skylar", "Singer, Programmer, Artist", "programmer", "entertainment");
    }

}