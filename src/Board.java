import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Board {
	int[] muldenUnten = new int[6];
	int[] muldenOben = new int[6];

	public Spieler sp1;
	public Spieler sp2;

	public Board(Spieler sp1, Spieler sp2) {

		this.sp1 = sp1;
		this.sp2 = sp2;

		for (int k = 0; k < muldenOben.length; k++) {
			muldenOben[k] = 4;
		}
		for (int k = 0; k < muldenUnten.length; k++) {
			muldenUnten[k] = 4;
		}

	}

	public Board() {
		this.sp1 = null;
		this.sp2 = null;

		for (int k = 0; k < muldenOben.length; k++) {
			muldenOben[k] = 4;
		}
		for (int k = 0; k < muldenUnten.length; k++) {
			muldenUnten[k] = 4;
		}
	}

	void boardAnzeigen() {

		System.out.print("=================" + sp2.name + "==================\n");
		System.out.print("A\tB\tC\tD\tE\tF\n");
		System.out.print(muldenOben[0] + "\t" + muldenOben[1] + "\t" + muldenOben[2] + "\t" + muldenOben[3] + "\t"
				+ muldenOben[4] + "\t" + muldenOben[5] + "\n");
		System.out.print("-------------------------------------------\n");
		System.out.print(muldenUnten[0] + "\t" + muldenUnten[1] + "\t" + muldenUnten[2] + "\t" + muldenUnten[3] + "\t"
				+ muldenUnten[4] + "\t" + muldenUnten[5] + "\n");
		System.out.print("a\tb\tc\td\te\tf\n");
		System.out.print("====================" + sp1.name + "====================\n");
		System.out.print(sp2.name + " Score: " + sp2.depot + "\n" + sp1.name + " Score: " + sp1.depot + "\n\n");

	}

	/**
	 * Methode die den Sae Vorgang ausfuehrt.
	 *
	 * @param startMulde
	 *            Mulde die der Spieler zum saeen ausgewaehlt hat
	 * @param sp1AnDerReihe
	 *            zeigt ob Spieler 1 an der Reihe ist
	 * @return gibt die letzte Mulde auf die eine Bohne fiel zurück
	 */
	public LetzteMulde saeen(int startMulde, boolean sp1AnDerReihe) {

		int k = startMulde;
		if (sp1AnDerReihe) {

			int bohnen = muldenUnten[k];
			muldenUnten[k] = 0;
			LetzteMulde lm = new LetzteMulde();

			while (bohnen > 0) {
				while (k < 6 && bohnen > 0) {
					if (startMulde != k) {
						muldenUnten[k] += 1;
						bohnen--;
						lm.mulde = "Unten";
					} else {

					}
					k++;
				}
				k--;
				while (k >= 0 && bohnen > 0) {
					muldenOben[k] += 1;
					k--;
					bohnen--;
					lm.mulde = "Oben";
				}
				k++;
			}
			lm.index = k;
			return lm;
		} else {
			int bohnen = muldenOben[k];
			muldenOben[k] = 0;
			LetzteMulde lm = new LetzteMulde();

			while (bohnen > 0) {
				while (k >= 0 && bohnen > 0) {
					if (startMulde != k) {
						muldenOben[k] += 1;
						bohnen--;
						lm.mulde = "Oben";
					} else {

					}
					k--;
				}
				k++;
				while (k < 6 && bohnen > 0) {
					muldenUnten[k] += 1;
					k++;
					bohnen--;
					lm.mulde = "Unten";
				}
				k--;
			}
			lm.index = k;
			return lm;
		}
	}

	/**
	 * Methode die überprüft ob ein vom Spieler (!) gewähltes Feld zulässig ist
	 *
	 * @param anDerReihe
	 *            gibt an ob Spieler 1 an der Reihe ist
	 * @param index
	 *            vom Spieler gewähltes Feld
	 * @return gibt zulässigen Index zurück
	 */
	public int feldwaehlenUser(boolean anDerReihe, int index) throws IOException {
		BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
		if (anDerReihe) {
			if (0 <= index && index <= 5) {
				if (muldenOben[0] + muldenOben[1] + muldenOben[2] + muldenOben[3] + muldenOben[4]
						+ muldenOben[5] == 0) {
					if (index + muldenUnten[index] >= 6) {
						return index;
					} else {
						System.out.println("Fehler ungültige Eingabe: Gegner hat keine Steine mehr");
						System.out.println("bitte neue Eingabe:");
						index = buchstabeZuZahl(br2.readLine());
						return feldwaehlenUser(anDerReihe, index);
					}
				} else {
					if (muldenUnten[index] > 0) {
						return index;
					} else {
						System.out.println("Fehler ungültige Eingabe: Ausgewähltes Feld ist leer");
						System.out.println("bitte neue Eingabe:");
						index = buchstabeZuZahl(br2.readLine());
						return feldwaehlenUser(anDerReihe, index);
					}
				}
			} else {
				System.out.println("Fehler ungültige Eingabe: Buchstabe liegt nicht im eigenen Spielfeld");
				System.out.println("bitte neue Eingabe:");
				index = buchstabeZuZahl(br2.readLine());
				return feldwaehlenUser(anDerReihe, index);

			}

		} else {
			if (0 <= index && index <= 5) {
				if (muldenUnten[0] + muldenUnten[1] + muldenUnten[2] + muldenUnten[3] + muldenUnten[4]
						+ muldenUnten[5] == 0) {
					if (index + muldenOben[index] >= 6) {
						return index;
					} else {
						System.out.println("Fehler ungültige Eingabe: Gegner hat keine Steine mehr");
						System.out.println("bitte neue Eingabe:");
						index = buchstabeZuZahl(br2.readLine());
						return feldwaehlenUser(anDerReihe, index);
					}
				} else {
					if (muldenOben[index] > 0) {
						return index;
					} else {
						System.out.println("Fehler ungültige Eingabe: Ausgewähltes Feld ist leer");
						System.out.println("bitte neue Eingabe:");
						index = buchstabeZuZahl(br2.readLine());
						return feldwaehlenUser(anDerReihe, index);
					}
				}
			} else {
				System.out.println("Fehler ungültige Eingabe: Buchstabe liegt nicht im eigenen Spielfeld");
				System.out.println("bitte neue Eingabe:");
				index = buchstabeZuZahl(br2.readLine());
				return feldwaehlenUser(anDerReihe, index);
			}
		}

	}

	/**
	 * Methode die den Buchstaben a-f und A-F in entsprechende Zahlen umwandelt.
	 *
	 * @param buchstabe
	 *            Buchstabe der umgewandelt werden soll
	 * @return zahl die sich aus dem Buchstaben ergibt
	 */
	public int buchstabeZuZahl(String buchstabe) {
		int zahl;

		switch (buchstabe) {
		case "a":
		case "A":
			zahl = 0;
			return zahl;
		case "b":
		case "B":
			zahl = 1;
			return zahl;
		case "c":
		case "C":
			zahl = 2;
			return zahl;
		case "d":
		case "D":
			zahl = 3;
			return zahl;
		case "e":
		case "E":
			zahl = 4;
			return zahl;
		case "f":
		case "F":
			zahl = 5;
			return zahl;
		default:
			zahl = -8;
			return zahl;
		}
	}

	// Feldwählen Comtputer //---->Parameter bestimmen, Schwierigkeitsgrad
	// beachten,
	// KI hier!
	public int feldwaehlenComputer() {
		return 1;
	}

	// Hier Fangen-Methode
	public void fangen(LetzteMulde lm,boolean anDerReihe) {
		int index = lm.index;
		if(anDerReihe){
			if(lm.mulde.equals("Oben")&&((muldenOben[index]==2)||(muldenOben[index]==3))){
				while((0<=index&&index<=5)&&((muldenOben[index]==2)||(muldenOben[index]==3))){
					sp1.depot+=muldenOben[index];
					muldenOben[index]=0;
					index++;
				}
			}


		}else{
			if(lm.mulde.equals("Unten")&&((muldenUnten[index]==2)||(muldenUnten[index]==3))){
				while((0<=index&&index<=5)&&((muldenUnten[index]==2)||(muldenUnten[index]==3))){
					sp2.depot+=muldenUnten[index];
					muldenUnten[index]=0;
					index--;
				}
			}

		}

	}
}