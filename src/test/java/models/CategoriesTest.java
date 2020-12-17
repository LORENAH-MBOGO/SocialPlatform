package models;

import com.sun.source.tree.CompoundAssignmentTree;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class CategoriesTest extends TestCase {
    @Test
    public void categoriesInstantiatesCorrectly_true(){
        CompoundAssignmentTree newCat = (CompoundAssignmentTree) setupCategories();
//        Categories altCat = setupAltCategories();
        assertTrue(newCat instanceof org.junit.experimental.categories.Categories);
//        assertTrue(altCat instanceof Categories);
    }

    @Test
    public void getNameReturnsCorrectly(){
        Categories newCat = setupCategories();
        assertEquals("Travel",newCat.getName());
    }

    @Test
    public void getDescriptionReturnsCorrectly(){
        Categories newCat = setupCategories();
        assertEquals("Road Trips",newCat.getDescription());
    }

    @Test
    public void setNameReturnsCorrectly(){
        Categories newCat = setupCategories();
        String initial=newCat.getName();
        newCat.setName("Kam");
        assertNotEquals(initial,newCat.getName());
    }

//    @Test
//    public void setDescriptionReturnsCorrectly(){
//        Categories newCat = setupCategories();
//        String initial=newCat.getDescription();
//        newCat.setDescription("Kam");
//        assertNotEquals(initial,newCat.getDescription());
//    }

    //Helpers
    public Categories setupCategories() {
        return new Categories("Sports", "Enjoy a variety of sporty activities eg.biking");
    }

    public Categories setUpAltCategories() {
        return new Categories("Travel", "Road Trips");
    }
}