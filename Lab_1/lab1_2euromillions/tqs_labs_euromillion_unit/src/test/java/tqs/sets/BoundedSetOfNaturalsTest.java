/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA; 
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;

    private BoundedSetOfNaturals setA_dum;


    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setA_dum = new BoundedSetOfNaturals(1);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{1, 50, 60});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = null;
    }
 
    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());
 
        assertThrows(IllegalArgumentException.class, () -> setB.add(11));
        assertTrue(!setB.contains(11), "add: added element not found in set.");
        assertEquals(6, setB.size(), "add: elements count not as expected.");
    }
 
    @Test
    public void testAddFromBadArray() { 
        // must fail with exception 
        assertThrows(IllegalArgumentException.class, () -> BoundedSetOfNaturals.fromArray(new int[]{-1}));
        assertThrows(IllegalArgumentException.class, () -> BoundedSetOfNaturals.fromArray(new int[]{10,10}));
    }

    @Test
    public void testFromArray() { 
 
        assertTrue(BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60}).contains(10));
    }

    @Test
    public void testHashCode() { 
 
        assertTrue(setA.hashCode() != setB.hashCode());
        assertTrue(setA.hashCode() == setA_dum.hashCode());
    }

    @Test
    public void testEquals() { 
 
        assertTrue(!setA.equals(setB));
        assertTrue(setA.equals(setA_dum)); 
        assertTrue(setA.equals(setA));
        assertTrue(!setA.equals(null));
        assertTrue(!setA.equals(1));
    }

    @Test 
    public void testIntersects() {
         
        assertTrue(!setA.intersects(setB));
        assertTrue(setC.intersects(setB));
    }
}
