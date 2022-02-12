/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package simplex_java;

import java.util.ArrayList;

/**
 *
 * @author nena
 */
public class Simplex_java {
    static { System.loadLibrary("gjt"); }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Double> redA = new ArrayList<>();         //redak
        ArrayList<ArrayList<Double>> A = new ArrayList<>(); //matrica
        
        //punim prvi redak:
        redA.add(-1.0);
        redA.add(0.0);
        redA.add(-1.0);
        redA.add(0.0);
        //dodajem ga u matricu
        A.add(redA);
        
        //punim drugi redak:
        redA = new ArrayList<>();
        redA.add(0.0);
        redA.add(-1.0);
        redA.add(0.0);
        redA.add(-1.0);
        
        //dodajem ga u matricu
        A.add(redA);
        
        redA = new ArrayList<>();
        redA.add(1.0);
        redA.add(1.0);
        redA.add(0.0);
        redA.add(0.0);
        A.add(redA);
        
        redA = new ArrayList<>();
        redA.add(0.0);
        redA.add(0.0);
        redA.add(1.0);
        redA.add(1.0);
        A.add(redA);
        
        ArrayList<Double> b = new ArrayList<>();
        b.add(-150.0);
        b.add(-60.0);
        b.add(100.0);
        b.add(120.0);
        
        ArrayList<Double> z = new ArrayList<>();
        z.add(-15.0);
        z.add(-21.0);
        z.add(-18.0);
        z.add(-12.0);
        
        Matrica simpleks = new Matrica(A, b, z);
        simpleks.prviPlan();
        
        for(var i : simpleks.povijestMatrice) {
            for(var j : i) {
                System.out.println(j);
            }
            System.out.println("");
        }
    }

}
