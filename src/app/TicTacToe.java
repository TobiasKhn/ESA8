package app;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/** 
* Class constructor.
*/
public class TicTacToe {
    private String[] spielfeld;
    private String spielzug;
	/** 
	* Class constructor.
	*/
    public TicTacToe(){
        spielfeld = spielfeldErstellen();
    }

    /** 
	* Class constructor.
	@return test
	*/
    public String[] spielfeldErstellen(){
        spielfeld = new String [9];
        for (int i=0; i<9; i++){
            spielfeld[i] = String.valueOf(i+1);
        }
        return spielfeld;
    }
	/** 
	* Class constructor.
	*/
    public void spielfeldAnzeigen(){
        final String linien = "|---|---|---|";
        System.out.println(linien);
        System.out.println("| "+spielfeld[0]+" | "+spielfeld[1]+" | "+spielfeld[2]+" |");
        System.out.println(linien);
        System.out.println("| "+spielfeld[3]+" | "+spielfeld[4]+" | "+spielfeld[5]+" |");
        System.out.println(linien);
        System.out.println("| "+spielfeld[6]+" | "+spielfeld[7]+" | "+spielfeld[8]+" |");
        System.out.println(linien);
    }
    
    /** 
	* Class constructor.
	@param ergebnis spielfeld
	@return test
	*/
    public String spielBeendet(String ergebnis){	  
        if (ergebnis.equals("xGewinnt")){
            return "Spieler X hat gewonnen.";
        } else {
            return"Spieler O hat gewonnen.";
        }
    }

    /** 
	* Class constructor.
	@param spielfeld spielfeld
	@return test
	*/
    public int besetzteSpielfelderZaehlen (String[] spielfeld){
        int besetztesFeld=0;
        for (int i=0; i < 9; i++){
            if (spielfeld[i].equals("X") || spielfeld[i].equals("O")){
                besetztesFeld++;
            }
        }
        return besetztesFeld;
    }   

    /** 
	* Class constructor.
	@param spielfeld spielfeld
	@return test
	*/
    public String spielfeldTesten(String[] spielfeld){
        if (spalteEinsTesten(spielfeld).equals("XXX") || spalteZweiTesten(spielfeld).equals("XXX") || spalteDreiTesten(spielfeld).equals("XXX") 
        || zeileEinsTesten(spielfeld).equals("XXX") || zeileZweiTesten(spielfeld).equals("XXX") || zeileDreiTesten(spielfeld).equals("XXX")
        || diagonaleLinksTesten(spielfeld).equals("XXX") || diagonaleRechtsTesten(spielfeld).equals("XXX") ) {
            return "xGewinnt";
        }
        else if (spalteEinsTesten(spielfeld).equals("OOO") || spalteZweiTesten(spielfeld).equals("OOO") || spalteDreiTesten(spielfeld).equals("OOO") 
        || zeileEinsTesten(spielfeld).equals("OOO") || zeileZweiTesten(spielfeld).equals("OOO") || zeileDreiTesten(spielfeld).equals("OOO")
        || diagonaleLinksTesten(spielfeld).equals("OOO") || diagonaleRechtsTesten(spielfeld).equals("OOO") ) {
            return "oGewinnt";
        }
        else if (besetzteSpielfelderZaehlen(spielfeld) == 8){
            return "unentschieden";
        }
        else {
            return "spielNichtBeendet";
        }
    }
	/** 
	* Class constructor.
	@param spielfeld spielfeld
	@return test
	*/
    public String spalteEinsTesten(String[] spielfeld){
        return spielfeld[0]+spielfeld[3]+spielfeld[6];
    }
	/** 
	* Class constructor.
	@param spielfeld spielfeld
	@return test
	*/
    public String spalteZweiTesten(String[] spielfeld){
        return spielfeld[1]+spielfeld[4]+spielfeld[7];
    }
	/** 
	* Class constructor.
	@param spielfeld spielfeld
	@return test
	*/
    public String spalteDreiTesten(String[] spielfeld){
        return spielfeld[2]+spielfeld[5]+spielfeld[8];
    }
	/** 
	* Class constructor.
	@param spielfeld spielfeld
	@return test
	*/
    public String zeileEinsTesten(String[] spielfeld){
        return spielfeld[0]+spielfeld[1]+spielfeld[2];
    }
	/** 
	* Class constructor.
	@param spielfeld spielfeld
	@return test
	*/
    public String zeileZweiTesten(String[] spielfeld){
        return spielfeld[3]+spielfeld[4]+spielfeld[5];
    }
	/** 
	* Class constructor.
	@param spielfeld spielfeld
	@return test
	*/
    public String zeileDreiTesten(String[] spielfeld){
        return spielfeld[6]+spielfeld[7]+spielfeld[8];
    }
	/** 
	* Class constructor.
	@param spielfeld spielfeld
	@return test
	*/
    public String diagonaleLinksTesten(String[] spielfeld){
        return spielfeld[0]+spielfeld[4]+spielfeld[8]; 
    }
	/** 
	* Class constructor.
	@param spielfeld spielfeld
	@return test
	*/
    public String diagonaleRechtsTesten(String[] spielfeld){
        return spielfeld[6]+spielfeld[4]+spielfeld[2]; 
    }

    

    /**Block Main Methode 
	@param args args
	*/
    public static void main(String[] args){
        TicTacToe spiel = new TicTacToe();
         
        BufferedReader spielerEingabe = new BufferedReader(
            new InputStreamReader(System.in));

        System.out.println("Um ein Feld auszuwählen geben sie die korrespondierende Nummer ein.");
        String ergebnis = "spielNichtBeendet";
        spiel.spielzug = "X";
        while (ergebnis.equals("spielNichtBeendet")){
            int position;
            spiel.spielfeldAnzeigen();
            System.out.println("\nSpieler "+spiel.spielzug+" am Zug. Wählen sie ein Feld aus.");
            try {
                position = Integer.parseInt(spielerEingabe.readLine());
                if (!(position > 0 && position <= 9)) {
                    System.out.println("Bitte Zahl zwischen 1 und 9 eingeben.");
                    continue;
                }
            } catch(Exception e){
                System.out.println("Eingabefehler. Wählen sie erneut ein Feld aus.");
                continue;
            }
            if (spiel.spielfeld[position - 1].equals(String.valueOf(position))) {
                spiel.spielfeld[position - 1] = spiel.spielzug;
                if (spiel.spielzug.equals("X")) {
                    spiel.spielzug = "O";
                }
                else {
                    spiel.spielzug = "X";
                }
                ergebnis = spiel.spielfeldTesten(spiel.spielfeld);
            }
            else {
                System.out.println("Feld bereits belegt. Bitte anderes Feld auswählen.");
            }
        }
        spiel.spielfeldAnzeigen();
        if (ergebnis.equals("unentschieden")) {
            System.out.println("Das Spiel geht unentschieden aus.");
        } 
        else {
            System.out.println(spiel.spielBeendet(ergebnis));
        }
    }

}
