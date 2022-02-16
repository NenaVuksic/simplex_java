/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package simplex_java;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author nena
 */
public class Simplex_java {
    static {System.loadLibrary("gjt");}
    static HashMap<Integer, ArrayList<String>> mapa = new HashMap<>();
    static int brojac = 0;

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
        
        mapa.put(50, new ArrayList<>());
        
        Matrica simpleks = new Matrica(A, b, z, 50);
        simpleks.prviPlan();
        
        ArrayList<String> pov = mapa.get(50);
        for(var i : pov) {
            /*for(var j : i) {
                System.out.println(j);
            }*/
            System.out.println(i);
            System.out.println("");
        }
        
        ServerSocket server = null;
        try {
            server = new ServerSocket(6799);
            server.setReuseAddress(true);
            
            while(true) {
                Socket klijent = server.accept();
                System.out.println("novi klijent: " + klijent.getInetAddress().getHostAddress());
                int id = brojac;
                if(brojac != 0){
                    for(int i = 0; i < brojac; i++)
                        if(!mapa.containsKey(i)){
                            id = i;
                            break;
                        }
                }
                Dretva novaDretva = new Dretva(klijent, id);
                mapa.put(id, new ArrayList<>());
                brojac = Collections.max(mapa.keySet()) + 1;
                new Thread(novaDretva).start();
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            if(server != null) {
                try {
                    server.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
