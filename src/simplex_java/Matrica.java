/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplex_java;
import java.io.*;
import java.util.*;

/**
 * Klasa <b>Matrica</b> reprezentira simplex tablicu kod rjesavanja zadaće linearnog programiranja i pronalaska vektora normale razdvajajuce hiperravnine. Simplex tablica sadrzavat ce bazne i pomocne varijable, informacije o dopustivom skupu tocaka te funkciju cilja i njenu vrijednost u danoj bazi.
 * 
 * @author Kristina M., Mirna I., Nena V.
 * 
 */

public class Matrica {
    protected int m;        //broj redaka matrice A
    protected int n;        //broj stupaca matrice A
    protected ArrayList<ArrayList<Double>> matrica;     //prvi redak i prvi stupac su varijable (od 1 do n+m); zadnji redak i zadnji stupac su vektori z i b - dakle matrica je dimenzija (m+2)*(n+2)
    protected ArrayList<Double> z;                      //za računanje vrijednsoti fje cilja trebat će nam originalna funkcija cilja
    public ArrayList<ArrayList<ArrayList<Double>>> povijestMatrice = new ArrayList<>();
    protected ArrayList<Double> tocka;
    
    //za linearno programiranje:
    public boolean zadacaUKontradikciji = false;
    public boolean neogranicenaFunkcijaCilja = false;
    //za razdvajajucu hiperravninu:
    public boolean nedobreDimenzije = false;
    public boolean linearnaZavisnost = false;
    public boolean pripadaKonusu = false;
    
    /**
     * 
     * @param A matrica dimenzija m*n koja definira skup dopustivih točaka x, sa Ax &lt;= b
     * @param b matrica dimenzija n*1 koja definira skup dopustivih točaka x, sa Ax &lt;= b
     * @param _z matrica dimenzija 1*m koja reprezentira funkciju cilja
     */
    public Matrica(ArrayList<ArrayList<Double>> A, ArrayList<Double> b, ArrayList<Double> _z) {   //pravim simplex tablicu za zadani problem z^T * x -> max, Ax <= b
        matrica = new ArrayList<>();
        m = A.size();
        n = A.get(0).size();
        
        z = _z;
        tocka = new ArrayList<>(n);
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                A.get(i).set(j, -1 * A.get(i).get(j));
            }
        }
        
        ArrayList<Double> noviRedak = new ArrayList<>();
        
        noviRedak.add(Double.NaN);      //u gornjem lijevom cosku nema broja
        int temp;
        for(temp = 1; temp < n + 1; temp++) noviRedak.add((double) temp);     //prvi redak je sacinjen od indekasa varijabli
        noviRedak.add(Double.NaN);      //u gornjem desnom cosku nema broja
        matrica.add(noviRedak);
        
        int temp2 = 0;
        for(var i : A) {
            noviRedak = new ArrayList<>();      //srednji dio simplex tablce je sačinjen od: 
            noviRedak.add((double) temp++);     //indeksa varijable,
            noviRedak.addAll(i);                //(negativnih) elemenata od A,
            noviRedak.add(b.get(temp2++));      //i elementa od b
            matrica.add(noviRedak);
        }
        
        noviRedak = new ArrayList<>();
        noviRedak.add(Double.NaN);
        noviRedak.addAll(_z);
        noviRedak.add(Double.NaN);
        matrica.add(noviRedak);
        
        povijestMatrice.add(matrica);
    }
    
    /**
     * Računa vrijednost funkcije cilja u trenutnoj tablici.
     * 
     * @return vrijednost funkcije cilja 
     */
    public double vrijednostFunkcijeCilja() {
        double rez = 0.0;
        for(var i : matrica) {
            double indeks = i.get(0);       //gledam indekse koji se nalaze u nultom stupcu
            if(indeks <= n) {               //ako su indeksi <=n, znači da su to varijable koje su ranije bile u prvom retku pa 
                rez += i.get(n + 1) * z.get((int) indeks);   //evaluiramo fju cilja u toj točki
                tocka.set((int) indeks, i.get(n + 1));       //i usput punim polje tocka
            }
        }
        return rez;
    }
    
    /**
     * Ova metoda obavlja Gauss-Jordanovu transformaciju simplex tablice na danim koordinatama.
     * 
     * @param redak redni broj retka u kojem je ključni element transformacije
     * @param stupac redni broj stupca u kojem je ključni element transformacije
     */
    public void GJT1(int redak, int stupac){         //transformacija na mjestu(redak, stupac) u matrici
        double elem = matrica.get(redak).get(stupac);       //kljucni element
        ArrayList<ArrayList<Double>> novaMatrica = new ArrayList<>();        
        ArrayList<Double> noviRedak = new ArrayList<>();
        
        noviRedak.addAll(matrica.get(0));                   //kreiram prvi redak mtrice, koji se sastoji od indekasa varijabli
        noviRedak.set(stupac, matrica.get(redak).get(0));
        novaMatrica.add(noviRedak);
        
        int r = 0;
        for(var i : matrica) {                              //ovdje preskacem prvi redak jer u njemu nemam sta racunat
            if(r == 0) {
                r++;
                continue;
            }
            noviRedak = new ArrayList<>();
            if(r == redak) noviRedak.add(matrica.get(0).get(stupac));//u kljucnom retku na nultom mjestu je indeks varijable koja je prije bila u bazi
            else noviRedak.add(i.get(0));                            //inače samo prepišemo broj varijable od prije
            int s = 0;
            for(var j : i) {
                if(s == 0) {
                    s++;
                    continue;
                }
                if(r == redak) {
                    if(s == stupac){
                        noviRedak.add(1 / elem);
                    }
                    else {
                        //System.out.println("dodajem "+ -j/elem);
                        noviRedak.add(- j / elem);
                    }
                }
                else {
                    if(r == m + 1 && s == n + 1) {
                        noviRedak.add(vrijednostFunkcijeCilja());      //ovdje treba dodati vrijednost funkcije cilja!
                        break;
                    }
                    if(s == stupac) {
                        noviRedak.add(j / elem);
                    }
                    else {
                        noviRedak.add((j * elem - i.get(stupac) * matrica.get(redak).get(s)) / elem);
                        //System.out.println("dodajem " + (j * elem - i.get(stupac) * matrica.get(redak).get(s)) / elem);
                    }
                }
                s++;
            }
            novaMatrica.add(noviRedak);
            r++;
        }
        
        matrica = novaMatrica;
    }
	
    public native ArrayList<ArrayList<Double>> GJT(ArrayList<ArrayList<Double>> mat, int redak, int stupac);
    
    /**
     * Implementacija algoritma za optimalni plan. Obavlja Gauss-Jordanove transformacije na simplex tablici dok ne nade tocku u kojoj se maksimizira funkcija cilja.
     */
    public void optimalniPlan()
	{ 
            int stupac=0, redak=0;
            double pom, pom_min;
            boolean br;
            while(true)
            {
		pom = -1.0;
		br = true;
		for(int i = 1; i < n+2; i++) //Provjerava je li matrica vec u optimalnom stanju i trazi najmanji index koji zadovoljava uvjet
		{
			if(matrica.get(m + 1).get(i) > 0)
                        {
                            br = false;
                     	    if(matrica.get(0).get(i) > pom){
                                pom = matrica.get(0).get(i);       
                                stupac = i;
                            }
                        }
		 }
			
		 if(br)
		 {
                        System.out.println("Optimalna tablica");
                        return ;
		 }
		 stupac = matrica.get(0).indexOf(pom);
		 pom = -1.0;
		 pom_min = Double.MAX_VALUE;
		 br = true;
		 for(int i = 1; i < m+2 ; i++) //Provjerava je li funkcija cilja neogranicena i trazi najmanji index koji zadovoljava uvjet
		 {      
                        //System.out.println("pokušavam vidjet element na mjestu " + i + ", " + stupac);
                        if(matrica.get(i).get(stupac) < 0)
                        {
                            br = false;
			    if((-matrica.get(i).get(n+1)/matrica.get(i).get(stupac) < pom_min) || (-matrica.get(i).get(n+1)/matrica.get(i).get(stupac) == pom_min && matrica.get(i).get(0) < pom))
			    {
                                pom_min = -matrica.get(i).get(n+1)/matrica.get(i).get(stupac);
                                pom = matrica.get(i).get(0);
                                redak = i;
			    }
                        }
		  }
		  if(br)
		  {
                        System.out.println("Neogranicena funkcija cilja");
                        neogranicenaFunkcijaCilja = true;
                        return ;
		  }
		  GJT1(redak, stupac);
                  povijestMatrice.add(matrica);
//		  final ArrayList<ArrayList<Double>> matrica1 = GJT(matrica, redak, stupac);
//		  povijestMatrice.add(matrica1);
//		  matrica = copyM(matrica1);
        }
    	//return ;
    }    
    
    /**
     * Implementacija algoritma za prvi plan. Obavlja Gauss-Jordanove transformacije nad simplex tablicom sve dok svi elementi stupca <b>b</b> ne postanu nenegativni.
     */
    public void prviPlan() {
        int flag = 0, r = 0, temp = 0;
        for(var i : matrica) {
            if(temp > 0 && temp < m + 1 && i.get(n + 1) < 0) {
                r = temp;
                flag = 1;
                break;
            }
            temp++;
        }
        
        if(flag == 0) optimalniPlan();
        else {
            flag = 0;
            int s = 0;
            temp = 0;
            for(var i : matrica.get(r)) {
                if(i > 0 && temp > 0 && temp < n + 1) {
                    //s = matrica.get(r).indexOf(i);
                    s = temp;
                    flag = 1;
                    break;
                }
                temp++;
            }
            if(flag == 0) {
                System.out.println("kontradikcija");
                zadacaUKontradikciji = true;
            }
            else {
                GJT1(r, s);
                povijestMatrice.add(matrica);
//		final ArrayList<ArrayList<Double>> matrica1 = GJT(matrica, r, s);
//                povijestMatrice.add(matrica1);
//		matrica = copyM(matrica1);
//                prviPlan();
            }
        }  
        
    }
    
    //za provjeru lin. nezavisnosti - Gaussove eliminacije
    /**
     * Provjera linearne nezavisnosti.
     * @param mat simplex tablica (?)
     * @return odgovor na pitanje jesu li retci dane matrice linearno nezavisni
     */
    public boolean Gauss(ArrayList<ArrayList<Double>> mat){
        int r = mat.size(), r1 = 0;
        int i, j, k;
        double p, p1;
        ArrayList<Double> d1 = new ArrayList<Double>();
        ArrayList<Double> d2 = new ArrayList<Double>();
        for(i = 0; i < r-1; i++){
            p = mat.get(i).get(i+1);
            j = i+1;
            while(p == 0.0 && j < r){
                Collections.swap(mat, i, j);
                p = mat.get(i).get(i+1);
                j++;
            }
            for(j = i+1; j < r; j++){
                p1 = mat.get(j).get(i+1);
                if(p1 != 0.0){
                    d1 = mat.get(i);
                    d2 = mat.get(j);
                    for(k = i+1; k < mat.get(0).size(); k++){
                        d2.set(k, d2.get(k)-(d1.get(k)/p)*p1);
                    }
                    mat.set(j, d2);
                }
            }
        }
        r1=r;
        for(i = 1; i < r; i++){
            j = Collections.frequency(mat.get(i).subList(1,mat.get(i).size()-1), 0.0);
            if(j == mat.get(i).size()-2) r1--;
        }
        return r == r1;
    }
    
    //copy matrice
    /**
     * 
     * @param mat matrica koju kopiramo
     * @return rezultat kopiranja dane matrice
     */
    public ArrayList<ArrayList<Double>> copyM(List<ArrayList<Double>> mat){
        ArrayList<ArrayList<Double>> mat1 = new ArrayList<ArrayList<Double>>();
        for(int i = 0; i < mat.size(); i++){
            mat1.add(new ArrayList<Double>(mat.get(i)));
        }
        return mat1;
    }
    
    /**
     * Implementacija algoritma za razdvajajuću hiperravninu. Ako vektor <b>b</b> pripada konusnoj ljusci zadanih m vektora, vraca se prazan vektor. U suprotnom se vraca vektor normale hiperravnine koja razdvaja spomenuti konus od <b>b</b>.
     * @return vektor normale razdvajajuce hiperravnine, ako postoji
     */
    public ArrayList<Double> razdvajajucaHiperravnina() {
        ArrayList<Double> v = new ArrayList<Double>(m);
        int i, j, k;
        boolean ok = true;
        if(m < n){
            System.out.println("m < n!");
            nedobreDimenzije = true;
            return v;
        }
        
        ArrayList<Integer> ind = new ArrayList<Integer>(n);
        for(i = 1; i < n+1; i++)
            ind.add(i);
        
        ArrayList<ArrayList<Double>> mat1 = copyM(matrica.subList(1,n+1));
        if(!Gauss(mat1)){
            ok = false;
            for(j = n; j > 0; j--){
                ind.remove(j-1);
                for(i = n+1; i < m+1; i++){
                    mat1 = copyM(matrica.subList(1,j));
                    ArrayList<ArrayList<Double>> mat2 = copyM(matrica.subList(j+1,n+1));
                    mat1.addAll(mat2);
                    ArrayList<Double> v1 = new ArrayList<Double>(matrica.get(i));
                    mat1.add(v1);
                    if(Gauss(mat1)){
                        ok = true;
                        ind.add(i);
                        break;
                    }
                }
                if(ok) break;
                ind.add(j);
            }
            if(!ok){
                System.out.println("Linearna zavisnost!");
                linearnaZavisnost = true;
                return v;
            }
        }
        
        ArrayList<Integer> ind2 = new ArrayList<Integer>();
        for(i = 1; i < n+1; i++){
            for(j = 0; j < ind.size(); j++){
                if(matrica.get(ind.get(j)).get(i) != 0.0)
                    break;}
            GJT1(ind.get(j),i);
//	    final ArrayList<ArrayList<Double>> matrica1 = GJT(matrica, ind.get(j), i);
//            ind2.add(ind.get(j));
//            ind.remove(j);
            povijestMatrice.add(matrica);
//	    matrica = copyM(matrica1);
        }
        for(i = 1; i < n+1; i++){
            Collections.swap(matrica, ind2.get(i-1), i);
        }
        
        while(ok){
            int stop = 0;
            int l = -1;
            for(i = 1; i < n+1; i++)
                if(matrica.get(m+1).get(i) < 0){
                    stop = 1;
                    l = i;
                    break;
                }
            if(stop == 0){
                System.out.println("Vektor pripada hiperravnini.");
                pripadaKonusu = true;
                break;
            }
            
            // l vec imamo
            // stop = 1;
            k = -1;
            double minK = 0;
            for(i = n+1; i < m+1; i++){
                if(matrica.get(i).get(l) < 0){
                    stop = 0;
                    k = i;
                    break;
                }
            }
            if(stop == 1){
                System.out.println("Vektor ne pripada hiperravnini.");
                for(i = 1; i < n+1; i++)
                    v.add(-matrica.get(i).get(l));
                return v;
            }
            
            // k vec imamo
            GJT1(k, l);
            povijestMatrice.add(matrica);
//            final ArrayList<ArrayList<Double>> matrica1 = GJT(matrica, k, l);
//            povijestMatrice.add(matrica1);
//	    matrica = copyM(matrica1);
        }
        return v;
    }
}
