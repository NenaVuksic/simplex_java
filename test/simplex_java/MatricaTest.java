/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package simplex_java;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mikulkri
 */
public class MatricaTest {
    static {System.loadLibrary("gjt");}
    static Connection conn = null;
    
    public MatricaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
            String sql = "CREATE TABLE IF NOT EXISTS klijenti (\n"
                        + "id integer PRIMARY KEY, \n"
                        + "br_dretve ,\n"
                        + "tablica text );";
            Statement stmt = conn.createStatement();
            if(conn != null) stmt.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MatricaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        String sql = "DELETE FROM klijenti WHERE br_dretve IS 100";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            sql = "DELETE FROM klijenti WHERE br_dretve IS 101";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            sql = "DELETE FROM klijenti WHERE br_dretve IS 102";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            sql = "DELETE FROM klijenti WHERE br_dretve IS 103";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            sql = "DELETE FROM klijenti WHERE br_dretve IS 104";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            sql = "DELETE FROM klijenti WHERE br_dretve IS 105";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            sql = "DELETE FROM klijenti WHERE br_dretve IS 106";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            sql = "DELETE FROM klijenti WHERE br_dretve IS 107";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            sql = "DELETE FROM klijenti WHERE br_dretve IS 108";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            sql = "DELETE FROM klijenti WHERE br_dretve IS 109";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MatricaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of pisiUBazu method, of class Matrica.
     */
    @Test
    public void testPisiUBazu() {
        System.out.println("pisiUBazu");
        ArrayList<Double> red = new ArrayList<>();
        red.add(3.0);
        red.add(5.0);
        ArrayList<ArrayList<Double>> pom = new ArrayList<>();
        pom.add(red);
        pom.add(red);
        Matrica instance = new Matrica(pom, red, red, 100, conn, 0);
        String expResult = "";
        Statement stmt;
        String result = "";
        int i = -1;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, tablica FROM klijenti WHERE br_dretve IS 100");
            i = rs.getInt("id");
            result = rs.getString("tablica");
        } catch (SQLException ex) {
            Logger.getLogger(MatricaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertNotEquals(-1, i);
        assertNotEquals(expResult, result);
    }

    /**
     * Test of vrijednostFunkcijeCilja method, of class Matrica.
     */
    @Test
    public void testVrijednostFunkcijeCilja() {
        System.out.println("vrijednostFunkcijeCilja");
        ArrayList<Double> red = new ArrayList<>();
        red.add(3.0);
        red.add(5.0);
        ArrayList<ArrayList<Double>> pom = new ArrayList<>();
        pom.add(red);
        pom.add(red);
        Matrica instance = new Matrica(pom, red, red, 101, conn, 0);
        double expResult = 0.0;
        double result = instance.vrijednostFunkcijeCilja();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of tocka method, of class Matrica.
     */
    @Test
    public void testTocka() {
        System.out.println("tocka");
        ArrayList<Double> red = new ArrayList<>();
        red.add(1.0);
        red.add(1.0);
        red.add(0.0);
        red.add(0.0);
        ArrayList<ArrayList<Double>> mat = new ArrayList<>();
        mat.add(red);
        red = new ArrayList<>();
        red.add(0.0);
        red.add(0.0);
        red.add(1.0);
        red.add(-.0);
        mat.add(red);
        red = new ArrayList<>();
        red.add(-1.0);
        red.add(0.0);
        red.add(-1.0);
        red.add(0.0);
        mat.add(red);
        red = new ArrayList<>();
        red.add(0.0);
        red.add(-1.0);
        red.add(0.0);
        red.add(-1.0);
        mat.add(red);
        
        ArrayList<Double> b = new ArrayList<>();
        b.add(100.0);
        b.add(120.0);
        b.add(-150.0);
        b.add(-60.0);
        ArrayList<Double> z = new ArrayList<>();
        z.add(-15.0);
        z.add(-21.0);
        z.add(-18.0);
        z.add(-12.0);
        
        Matrica instance = new Matrica(mat, b, z, 102, conn, 0);
        ArrayList<Double> expResult = new ArrayList<>();
        expResult.add(100.0);
        expResult.add(0.0);
        expResult.add(50.0);
        expResult.add(60.0);
        instance.prviPlan();
        ArrayList<Double> result = instance.tocka();
        assertEquals(expResult, result);
    }

    /**
     * Test of GJT method, of class Matrica.
     */
    @Test
    public void testGJT() {
        System.out.println("GJT");
        ArrayList<Double> red = new ArrayList<>();
        red.add(3.0);
        red.add(5.0);
        ArrayList<ArrayList<Double>> pom = new ArrayList<>();
        pom.add(red);
        pom.add(red);
        Matrica instance = new Matrica(pom, red, red, 103, conn, 0);
        ArrayList<ArrayList<Double>> mat = pom;
        int redak = 1;
        int stupac = 1;
        ArrayList<ArrayList<Double>> expResult = null;
        ArrayList<ArrayList<Double>> result = instance.GJT(mat, redak, stupac);
        assertNotEquals(expResult, result);
    }

    /**
     * Test of optimalniPlan method, of class Matrica.
     */
    @Test
    public void testOptimalniPlan() {
        System.out.println("optimalniPlan");
        ArrayList<Double> red = new ArrayList<>();
        red.add(1.0);
        red.add(-1.0);
        red.add(-1.0);
        ArrayList<ArrayList<Double>> mat = new ArrayList<>();
        mat.add(red);
        red = new ArrayList<>();
        red.add(1.0);
        red.add(1.0);
        red.add(1.0);
        mat.add(red);
        
        ArrayList<Double> b = new ArrayList<>();
        b.add(1.0);
        b.add(3.0);
        ArrayList<Double> z = new ArrayList<>();
        z.add(-1.0);
        z.add(2.0);
        z.add(4.0);
        
        Matrica instance = new Matrica(mat, b, z, 104, conn, 0);
        double expResult = 12.0;
        instance.optimalniPlan();
        double result = instance.vrijednostFunkcijeCilja();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of prviPlan method, of class Matrica.
     */
    @Test
    public void testPrviPlan() {
        System.out.println("prviPlan");
        ArrayList<Double> red = new ArrayList<>();
        red.add(1.0);
        red.add(1.0);
        red.add(0.0);
        red.add(0.0);
        ArrayList<ArrayList<Double>> mat = new ArrayList<>();
        mat.add(red);
        red = new ArrayList<>();
        red.add(0.0);
        red.add(0.0);
        red.add(1.0);
        red.add(1.0);
        mat.add(red);
        red = new ArrayList<>();
        red.add(-1.0);
        red.add(0.0);
        red.add(-1.0);
        red.add(0.0);
        mat.add(red);
        red = new ArrayList<>();
        red.add(0.0);
        red.add(-1.0);
        red.add(0.0);
        red.add(-1.0);
        mat.add(red);
        
        ArrayList<Double> b = new ArrayList<>();
        b.add(100.0);
        b.add(120.0);
        b.add(-150.0);
        b.add(-60.0);
        ArrayList<Double> z = new ArrayList<>();
        z.add(-15.0);
        z.add(-21.0);
        z.add(-18.0);
        z.add(-12.0);
        
        Matrica instance = new Matrica(mat, b, z, 105, conn, 0);
        double expResult = -3120.0;
        instance.prviPlan();
        double result = instance.vrijednostFunkcijeCilja();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of Gauss method, of class Matrica.
     */
    @Test
    public void testGauss() {
        System.out.println("Gauss");
        ArrayList<Double> red = new ArrayList<>();
        red.add(2.0);
        red.add(5.0);
        ArrayList<ArrayList<Double>> mat = new ArrayList<>();
        mat.add(red);
        red = new ArrayList<>();
        red.add(3.0);
        red.add(5.0);
        mat.add(red);
        ArrayList<ArrayList<Double>> pom = new ArrayList<>();
        pom.add(red);
        Matrica instance = new Matrica(pom, red, red, 106, conn, 0);
        int expResult = 2;
        int result = instance.Gauss(mat);
        assertEquals(expResult, result);
    }

    /**
     * Test of copyM method, of class Matrica.
     */
    @Test
    public void testCopyM() {
        System.out.println("copyM");
        ArrayList<Double> red = new ArrayList<>();
        red.add(2.0);
        red.add(5.0);
        List<ArrayList<Double>> mat = new ArrayList<>();
        mat.add(red);
        ArrayList<ArrayList<Double>> pom = new ArrayList<>();
        pom.add(red);
        pom.add(red);
        
        Matrica instance = new Matrica(pom, red, red, 107, conn, 0);
        ArrayList<ArrayList<Double>> expResult = null;
        ArrayList<ArrayList<Double>> result = instance.copyM(mat);
        assertNotEquals(expResult, result);
    }

    /**
     * Test of razdvajajucaHiperravnina method, of class Matrica.
     */
    @Test
    public void testRazdvajajucaHiperravnina() {
        System.out.println("razdvajajucaHiperravnina");
        ArrayList<Double> red = new ArrayList<>();
        red.add(-1.0);
        red.add(-1.0);
        red.add(-3.0);
        ArrayList<ArrayList<Double>> mat = new ArrayList<>();
        mat.add(red);
        red = new ArrayList<>();
        red.add(-1.0);
        red.add(-2.0);
        red.add(-4.0);
        mat.add(red);
        red = new ArrayList<>();
        red.add(0.0);
        red.add(0.0);
        red.add(-1.0);
        mat.add(red);
        red = new ArrayList<>();
        red.add(-2.0);
        red.add(-1.0);
        red.add(-2.0);
        mat.add(red);
        
        ArrayList<Double> b = new ArrayList<>();
        b.add(0.0);
        b.add(0.0);
        b.add(0.0);
        b.add(0.0);
        ArrayList<Double> z = new ArrayList<>();
        z.add(0.0);
        z.add(1.0);
        z.add(1.0);
        
        Matrica instance = new Matrica(mat, b, z, 108, conn, 0);
        ArrayList<Double> expResult = new ArrayList<>();
        expResult.add(-2.0);
        expResult.add(1.0);
        expResult.add(-0.0);
        ArrayList<Double> result = instance.razdvajajucaHiperravnina();
        assertEquals(expResult, result);
    }

    /**
     * Test of rangMatrice method, of class Matrica.
     */
    @Test
    public void testRangMatrice() {
        System.out.println("rangMatrice");
        ArrayList<Double> red = new ArrayList<>();
        red.add(2.0);
        red.add(5.0);
        ArrayList<ArrayList<Double>> mat = new ArrayList<>();
        mat.add(red);
        red = new ArrayList<>();
        red.add(3.0);
        red.add(5.0);
        mat.add(red);
        
        Matrica instance = new Matrica(mat, red, red, 109, conn, 0);
        int expResult = 2;
        int result = instance.rangMatrice();
        assertEquals(expResult, result);
    }
}
