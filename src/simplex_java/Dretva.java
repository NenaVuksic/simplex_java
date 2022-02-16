/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplex_java;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Mirna
 */
public class Dretva implements Runnable {
    /**
     * utičnica za komunikaciju s klijentom
     */
    private final Socket clientSocket;
    int ID;
    
    /**
     * matrica A u zadaći linearnog programiranja Ax &le; b, ili cijela potrebna matrica za algoritam za razdvajajuću hiperravninu. Podaci u ovom polju zaprimaju se od klijenta.
     */
    private ArrayList<ArrayList<Double>> A = new ArrayList<>();
    /**
     * stupac b u zadaći linearnog programiranja Ax &le; b. Podaci u ovom polju zaprimaju se od klijenta.
     */
    private ArrayList<Double> b = new ArrayList<>();
    /**
     * redak z u zadaći linearnog programiranja Ax &le; b. Podaci u ovom polju zaprimaju se od klijenta.
     */
    private ArrayList<Double> z = new ArrayList<>();
    
    /** 
     * pisač za slanje poruka klijentu
     */
    private PrintWriter zaPoslati = null;
    /**
     * čitač za primanje poruka od klijenta
     */
    private BufferedReader primljenoOdKlijenta = null;
    /**
     * instanca klase Matrica nad kojom klijent želi odraditi operacije
     */
    private Matrica simpleks;
    
    /**
     * konstruktor za klasu Dretva
     * @param uticnica za mrežnu komunikaciju s klijentom
     */
    public Dretva(Socket uticnica, int id) {
        clientSocket = uticnica;
        ID = id;
    }
    
    @Override public void run() {
        try {
            zaPoslati = new PrintWriter(clientSocket.getOutputStream(), true);
            primljenoOdKlijenta = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while(true) {
                String line = primljenoOdKlijenta.readLine();
                if(line.equals("kraj")) break;
                if(line.equals("poslati korake")) {
                    posaljiKorake();
                    continue;
                }
                String prvaLinija[] = line.split(" ");
                int izbor = Integer.parseInt(prvaLinija[0]);
                int brojRedaka = Integer.parseInt(prvaLinija[1]);
                int brojStupaca = Integer.parseInt(prvaLinija[2]);

                //prvo stiže b:
                if(izbor == 1) {    
                    line = primljenoOdKlijenta.readLine();
                    if(line.equals("kraj")) break;
                    if(line.equals("poslati korake")) {
                        posaljiKorake();
                        continue;
                    }
                    if("b".equals(line)) {
                        line = primljenoOdKlijenta.readLine();
                        if(line.equals("kraj")) break;
                        if(line.equals("poslati korake")) {
                            posaljiKorake();
                            continue;
                        }
                        String bTekst[] = line.split(" ");
                        for(int i = 0; i < bTekst.length; i++) {
                            double novi = Double.NaN;
                            try{
                                novi = Double.parseDouble(bTekst[i]);
                            } catch(NumberFormatException nfe1) {
                                try {
                                    novi = (double) Integer.parseInt(bTekst[i]);
                                } catch(NumberFormatException nfe2) {
                                    nfe2.printStackTrace();
                                }
                            }
                            b.add(novi);
                        }
                    }
                }
                else for(int i = 0; i < brojRedaka; i++) b.add(Double.NaN);  //da b ne bude null pri kreiranju matrice na kojoj pozivamo algoritam za razdvajajuću hiperravninu

                //onda stiže z:
                line = primljenoOdKlijenta.readLine();
                if(line.equals("kraj")) break;
                if(line.equals("poslati korake")) {
                    posaljiKorake();
                    continue;
                }
                if("z".equals(line)) {
                    line = primljenoOdKlijenta.readLine();
                    if(line.equals("kraj")) break;
                    if(line.equals("poslati korake")) {
                        posaljiKorake();
                        continue;
                    }
                    String zTekst[] = line.split(" ");
                    for(int i = 0; i < zTekst.length; i++) {
                        double novi = Double.NaN;
                        try{
                            novi = Double.parseDouble(zTekst[i]);
                        } catch(NumberFormatException nfe1) {
                            try {
                                novi = (double) Integer.parseInt(zTekst[i]);
                            } catch(NumberFormatException nfe2) {
                                nfe2.printStackTrace();
                            }
                        }
                        z.add(novi);
                    }
                }
                
                //i onda A, redak po redak:
                line = primljenoOdKlijenta.readLine();
                if(line.equals("kraj")) break;
                if(line.equals("poslati korake")) {
                    posaljiKorake();
                    continue;
                }
                boolean procitanKraj = false;
                boolean procitanPosalji = false;
                if("A".equals(line)) {
                    for(int j = 0; j < brojRedaka; j++) {
                        ArrayList<Double> temp = new ArrayList<>();
                        line = primljenoOdKlijenta.readLine();
                        if(line.equals("kraj")) {
                            procitanKraj = true;
                            break;
                        }
                        if(line.equals("poslati korake")) {
                            posaljiKorake();
                            procitanPosalji = true;
                            break;
                        }
                        String ATekst[] = line.split(" ");
                        for(int i = 0; i < ATekst.length; i++) {
                            double novi = Double.NaN;
                            try{
                                novi = Double.parseDouble(ATekst[i]);
                            } catch(NumberFormatException nfe1) {
                                try {
                                    novi = (double) Integer.parseInt(ATekst[i]);
                                } catch(NumberFormatException nfe2) {
                                    nfe2.printStackTrace();
                                }
                            }
                            if(izbor == 1) temp.add(novi);
                            else temp.add(-1 * novi);
                        }
                        if(procitanKraj) break;
                        A.add(temp);
                    }
                    if(procitanKraj) break;
                    if(procitanPosalji) continue;
                }
                
                simpleks = new Matrica(A, b, z, ID);
                
                if(izbor == 1) {
                    simpleks.prviPlan();
                    if(simpleks.zadacaUKontradikciji) zaPoslati.println("kontradikcija");
                    else if(simpleks.neogranicenaFunkcijaCilja) zaPoslati.println("neogranicena fja");
                    else {
                        String temp = "";
                        for(var i : simpleks.tocka()) temp += i.toString() + " ";     //metodu vrijednostFjeCilja mozemo preraditi da vraca ovu tocku.
                        zaPoslati.println(temp);
                        zaPoslati.println(simpleks.vrijednostFunkcijeCilja());
                    }
                }
                else if (izbor == 2) {
                    ArrayList<Double> rez = simpleks.razdvajajucaHiperravnina();
                    if(simpleks.nedobreDimenzije) zaPoslati.println("lose dimenzije");
                    else if(simpleks.linearnaZavisnost) zaPoslati.println("lin zavisno");
                    else if(simpleks.pripadaKonusu) zaPoslati.println("pripada konusu");
                    else {
                        String temp = "";
                        for(var i : rez) temp += i.toString() + " ";
                        zaPoslati.println(temp);
                    }
                }
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(zaPoslati != null) zaPoslati.close();
                if(primljenoOdKlijenta != null) {
                    primljenoOdKlijenta.close();
                    clientSocket.close();
                }
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * metoda koja uzima tablice iz baze podataka te ih šalje klijentu. Navedene tablice predstavljaju korake u radu implementiranih algoritama.
     */
    private void posaljiKorake() {
        //ovdje cupamo linije iz baze i saljemo klijentu
        
        //pretpostavljam da ce prvo poslati broj tablica, a onda jednu po jednu tablicu
        zaPoslati.println(simpleks.povijestMatrice.size() + "");
        for(var i : simpleks.povijestMatrice) {
            for(var j : i) {
                String redak = "";
                for(var k : j) redak += k.toString() + " ";
                zaPoslati.println(redak);
            }
        }
    }
}
