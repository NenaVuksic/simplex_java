# Simplex algoritam i primjene

### Opis problema
Postoji veliki broj raznovrsnih problema koji se mogu svesti na jednostavnu primjenu simplex algoritma. Mana takvog pristupa jest što računanje istih može biti zahtjevno, pogotovo u situacijama kada je uključena velika količina podataka te se lako izgubiti u računu.
Upravo iz tog razloga smo odlučile putem ovog projekta realizirati java aplikaciju koja će rješavati razne optimizacijske probleme koristeći simplex metodu te ispisati sve međukorake računa radi lakše provjere i praćenja algoritma i koja će, nadamo se, pomoći studentima pri samostalnoj vježbi i pripremi za kolokvije. Projekt ćemo realizirati u okvirima kolegija "Uvod u optimizaciju" matematičkog odsjeka zagrebačkog PMF-a, no vjerujemo da će u praksi biti primjenjiv i van restrikcija kolegija.

### Matematička pozadina

Simplex metoda je standardna metoda u linearnom programiranju koja se koristi za rješavanje optimizacijskih problema, u pravilu, realiziranih preko jedne funkcije koju optimiziramo te više ograda prikazanih preko nejednadžbi. Algoritam provodimo po koracima na sljedeći način:

- Iz dane zadaće 
<img src="https://latex.codecogs.com/gif.latex?%5Cbg_white%20%5Cleft%5C%7B%5Cbegin%7Bmatrix%7D%20z%5E%7B%5Ctau%7D%5Crightarrow%5Cmax%5C%5C%20Ax%5Cle%20b%5C%5C%20x%5Cge%200%20%5Cend%7Bmatrix%7D%5Cright." />
kreiramo početnu tablicu

|                 | x<sub>1</sub> |      ...      | x<sub>n</sub> |   |
|:---------------:|:-------------:|:-------------:|:-------------:|:-:|
| x<sub>n+1</sub> |               |               |               |   |
|       ...       |               |       -A      |               | b |
| x<sub>n+m</sub> |               |               |               |   |
|                 |               | z<sup>T</sup> |               | 0 |


- Zatim, ovisno o vrsti problema kojeg rješavamo, primjenjujemo algoritme za
  + Prvi plan
  + Optimalni plan
  + Razdvajajuću hiperravninu
  Svi ovi algoritmi koriste Gauss-Jordanove transformacije na tablicama kao gore.
  
### Realizacija

Implementacija se sastoji od klijentske i serverske aplikacije. Klijent ima grafičko sučelje (prozor na desktopu) putem kojeg će vršiti komunikaciju sa serverom. Kako bi se više klijenata moglo koristiti serverom istovremeno, serverski je program biti višedretveni. Server će nakon unosa klijenta početne linearne zadaće u tablicu te odabira klijenta koju vrstu zadatka želi riješiti ispisati konačno rješenje problema te međukorake spremati u bazu podataka, a na zahtjev ih slati klijentu.

Konkretne funkcionalnosti aplikacije su:
- Rješavanje zadaće linearnog programiranja. Aplikacija vraća optimalnu točku zadaće, ako ista postoji.
- Provjera nalazi li se dani vektor b u konusu zadanom matricom A. Ako ne, povratna je vrijednost vektor normale hiperravnine koja ih razdvaja.
- Računanje ranga zadane matrice i njezinog inverza, ako isti postoji.

Potrebni paketi i tehnologije bili su:
- JNI za korištenje nativnog C++ koda za implementaciju Gauss-Jordanovih transformacija
- knjižnica Java Swing za stvaranje grafičkog sučelja
- SQLite za upravljanje bazom podataka.
