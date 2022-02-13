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
    private final Socket clientSocket;
    
    public ArrayList<ArrayList<Double>> A = new ArrayList<>();
    public ArrayList<Double> b = new ArrayList<>();
    public ArrayList<Double> z = new ArrayList<>();
    
    public Dretva(Socket uticnica) {
        clientSocket = uticnica;
    }
    
    @Override public void run() {
        PrintWriter zaPoslati = null;
        BufferedReader primljenoOdKlijenta = null;
        
        try {
            zaPoslati = new PrintWriter(clientSocket.getOutputStream(), true);
            primljenoOdKlijenta = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while(true) {
                String line = primljenoOdKlijenta.readLine();
                if(line.equals("kraj")) break;
                String prvaLinija[] = line.split(" ");
                int izbor = Integer.parseInt(prvaLinija[0]);
                int brojRedaka = Integer.parseInt(prvaLinija[1]);
                int brojStupaca = Integer.parseInt(prvaLinija[2]);

                if(izbor == 1) {
                    line = primljenoOdKlijenta.readLine();
                    if(line.equals("kraj")) break;
                    if("z".equals(line)) {
                        line = primljenoOdKlijenta.readLine();
                        if(line.equals("kraj")) break;
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
                }
                else z.add(Double.NaN);

                line = primljenoOdKlijenta.readLine();
                if("b".equals(line)) {
                    line = primljenoOdKlijenta.readLine();
                    if(line.equals("kraj")) break;
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

                line = primljenoOdKlijenta.readLine();
                if(line.equals("kraj")) break;
                boolean procitanKraj = false;
                if("A".equals(line)) {
                    for(int j = 0; j < brojRedaka; j++) {
                        ArrayList<Double> temp = new ArrayList<>();
                        line = primljenoOdKlijenta.readLine();
                        if(line.equals("kraj")) {
                            procitanKraj = true;
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
                            temp.add(novi);
                        }
                        if(procitanKraj) break;
                        A.add(temp);
                    }
                    if(procitanKraj) break;
                }
                
                Matrica simpleks = new Matrica(A, b, z);
                if(izbor == 1) {
                    simpleks.prviPlan();
                    if(simpleks.zadacaUKontradikciji) zaPoslati.write("kontradikcija");
                    else if(simpleks.neogranicenaFunkcijaCilja) zaPoslati.write("neogranicena fja");
                    else {
                        String temp = "";
                        for(var i : simpleks.tocka) temp += i.toString() + " ";
                        zaPoslati.write(temp);
                        zaPoslati.write(simpleks.vrijednostFunkcijeCilja() + "");
                    }
                }
                else if (izbor == 2) {
                    ArrayList<Double> rez = simpleks.razdvajajucaHiperravnina();
                    if(simpleks.nedobreDimenzije) zaPoslati.write("lose dimenzije");
                    else if(simpleks.linearnaZavisnost) zaPoslati.write("lin zavisno");
                    else if(simpleks.pripadaKonusu) zaPoslati.write("pripada konusu");
                    else {
                        String temp = "";
                        for(var i : rez) temp += i.toString() + " ";
                        zaPoslati.write(temp);
                    }
                }
                
                line = primljenoOdKlijenta.readLine();
                if(line.equals("poslati korake")) posaljiKorake();
                
                break; //za svaki slucaj nek izade odmah nakon prve iteracije
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
    
    private void posaljiKorake() {
        //ovdje cupamo linije iz baze i saljemo klijentu
        
        //pretpostavljam da ce prvo poslati broj tablica, a onda jednu po jednu tablicu
    }
}
