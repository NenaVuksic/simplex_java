/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package simplex_java;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main klasa. 
 * @author Nena, Mirna
 */
public class Simplex_java {
    static {System.loadLibrary("gjt");}
    static int brojDretvi = 0;

    /**
     * Kreiramo bazu podataka i pokrećemo serversku aplikaciju, te za svakog klijenta stvaramo novu dretvu.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String url = "jdbc:sqlite:baza.db";
        String sql = "CREATE TABLE IF NOT EXISTS klijenti (\n"
                        + "id integer PRIMARY KEY, \n"
                        + "br_dretve ,\n"
                        + "tablica text );";
        
        ServerSocket server = null;
        Connection konekcija = null;
        
        try {
            konekcija = DriverManager.getConnection(url); 
            Statement stmt = konekcija.createStatement();
                        
            stmt.execute(sql);
            
            stmt.execute("DELETE FROM klijenti ;");
            
            server = new ServerSocket(7777);
            server.setReuseAddress(true);
            
            while(true) {
                Socket klijent = server.accept();
                System.out.println("novi klijent: " + klijent.getInetAddress().getHostAddress());
                Dretva novaDretva = new Dretva(klijent, brojDretvi++, konekcija);
                new Thread(novaDretva).start();
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch (SQLException s) {
            s.printStackTrace();
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
            if(konekcija != null) {
                try {
                    konekcija.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Simplex_java.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }

}
