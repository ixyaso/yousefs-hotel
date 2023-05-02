/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package javafxapplication3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author frees
 */
public class DashoardControllerTest {
    
    public DashoardControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of availableRoomsListData method, of class DashoardController.
     */
    @Test
    public void testAvailableRoomsListData() {
        System.out.println("availableRoomsListData");
        DashoardController instance = new DashoardController();
        ObservableList<roomData> expResult = null;
        ObservableList<roomData> result = instance.availableRoomsListData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of availableRoomsShowData method, of class DashoardController.
     */
    @Test
    public void testAvailableRoomsShowData() {
        System.out.println("availableRoomsShowData");
        DashoardController instance = new DashoardController();
        instance.availableRoomsShowData();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of availableRoomsAdd method, of class DashoardController.
     */
    @Test
    public void testAvailableRoomsAdd() {
        System.out.println("availableRoomsAdd");
        DashoardController instance = new DashoardController();
        instance.availableRoomsAdd();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of availableRoomsClear method, of class DashoardController.
     */
    @Test
    public void testAvailableRoomsClear() {
        System.out.println("availableRoomsClear");
        DashoardController instance = new DashoardController();
        instance.availableRoomsClear();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of availableRoomsRoomType method, of class DashoardController.
     */
    @Test
    public void testAvailableRoomsRoomType() {
        System.out.println("availableRoomsRoomType");
        DashoardController instance = new DashoardController();
        instance.availableRoomsRoomType();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of availableroomStatus method, of class DashoardController.
     */
    @Test
    public void testAvailableroomStatus() {
        System.out.println("availableroomStatus");
        DashoardController instance = new DashoardController();
        instance.availableroomStatus();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialize method, of class DashoardController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL location = null;
        ResourceBundle resources = null;
        DashoardController instance = new DashoardController();
        instance.initialize(location, resources);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
