import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Das Spielfeld.
 * 
 * @author Martin (Mobil)
 *
 */
public class Board {
	// Attribute
	int[] muldenUnten = new int[6];
	int[] muldenOben = new int[6];

	public Spieler sp1;
	public Spieler sp2;

	// Konstruktoren
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

		for (int k = 0; k < muldenOben.length; k++) {
			muldenOben[k] = 4;
		}
		for (int k = 0; k < muldenUnten.length; k++) {
			muldenUnten[k] = 4;
		}
	}

	// Methoden
	/**
	 * Methode die das Spielfeld auf der Konsole ausgibt
	 */
	void boardAnzeigen() {
		System.out.print("==========================================\n");
		System.out.print(sp2.name + "           Score: " + sp2.depot + "\n");
		System.out.print("==========================================\n");
		System.out.print("A\tB\tC\tD\tE\tF\n");
		System.out.print(muldenOben[0] + "\t" + muldenOben[1] + "\t" + muldenOben[2] + "\t" + muldenOben[3] + "\t"
				+ muldenOben[4] + "\t" + muldenOben[5] + "\n");
		System.out.print("------------------------------------------\n");
		System.out.print(muldenUnten[0] + "\t" + muldenUnten[1] + "\t" + muldenUnten[2] + "\t" + muldenUnten[3] + "\t"
				+ muldenUnten[4] + "\t" + muldenUnten[5] + "\n");
		System.out.print("a\tb\tc\td\te\tf\n");
		System.out.print("==========================================\n");
		System.out.print(sp1.name + "           Score: " + sp1.depot + "\n");
		System.out.print("==========================================\n\n\n");
	}

	/**
	 * Methode die den Sae Vorgang ausfuehrt.
	 *
	 * @param startMulde
	 *            Mulde die der Spieler zum saeen ausgewaehlt hat
	 * @param sp1AnDerReihe
	 *            zeigt ob Spieler 1 an der Reihe ist
	 * @return gibt die letzte Mulde auf die eine Bohne fiel zurï¿½ck
	 */
	public LetzteMulde saeen(int startMulde, boolean sp1AnDerReihe) {

		int k = startMulde;
		if (sp1AnDerReihe) {

			int bohnen = muldenUnten[k];
			muldenUnten[k] = 0;
			LetzteMulde lm = new LetzteMulde();
			lm.mulde = "Unten";
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
			lm.mulde = "Oben";
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
	 * Methode die ï¿½berprï¿½ft ob ein vom Spieler (!) gewï¿½hltes Feld
	 * zulï¿½ssig ist
	 *
	 * @param anDerReihe
	 *            gibt an ob Spieler 1 an der Reihe ist
	 * @param index
	 *            vom Spieler gewï¿½hltes Feld
	 * @return gibt zulï¿½ssigen Index zurï¿½ck
	 */
	public int feldwaehlenUser(boolean anDerReihe, int index) throws IOException {
		BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
		if (anDerReihe) {
			if (0 <= index && index <= 5) {
				if (summeOben() == 0) {
					if (index + muldenUnten[index] >= 6) {
						return index;
					} else {
						System.out.println("Fehler ungï¿½ltige Eingabe: Gegner hat keine Steine mehr");
						System.out.println("bitte neue Eingabe:");
						index = buchstabeZuZahl(br2.readLine());
						return feldwaehlenUser(anDerReihe, index);
					}
				} else {
					if (muldenUnten[index] > 0) {
						return index;
					} else {
						System.out.println("Fehler ungï¿½ltige Eingabe: Ausgewï¿½hltes Feld ist leer");
						System.out.println("bitte neue Eingabe:");
						index = buchstabeZuZahl(br2.readLine());
						return feldwaehlenUser(anDerReihe, index);
					}
				}
			} else {
				System.out.println("Fehler ungï¿½ltige Eingabe: Buchstabe liegt nicht im eigenen Spielfeld");
				System.out.println("bitte neue Eingabe:");
				index = buchstabeZuZahl(br2.readLine());
				return feldwaehlenUser(anDerReihe, index);

			}

		} else {
			if (0 <= index && index <= 5) {
				if (summeUnten() == 0) {
					if (index + muldenOben[index] >= 6) {
						return index;
					} else {
						System.out.println("Fehler ungï¿½ltige Eingabe: Gegner hat keine Steine mehr");
						System.out.println("bitte neue Eingabe:");
						index = buchstabeZuZahl(br2.readLine());
						return feldwaehlenUser(anDerReihe, index);
					}
				} else {
					if (muldenOben[index] > 0) {
						return index;
					} else {
						System.out.println("Fehler ungï¿½ltige Eingabe: Ausgewï¿½hltes Feld ist leer");
						System.out.println("bitte neue Eingabe:");
						index = buchstabeZuZahl(br2.readLine());
						return feldwaehlenUser(anDerReihe, index);
					}
				}
			} else {
				System.out.println("Fehler ungï¿½ltige Eingabe: Buchstabe liegt nicht im eigenen Spielfeld");
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

	/**
	 * Methode die bestimmt welches Feld der Computer wählt.
	 * 
	 * @param computerVorerst
	 *            Computer der mitgegeben wird um festzulegen welche Taktik
	 *            verwendet wird.
	 * @return returnt das gewählte Feld.
	 */
	public int feldwaehlenComputer(Spieler computerVorerst) {
		int ergebnis = -1;

		// Clohans verbeuge dich vor diesem meisterhaften
		// Typecast!!!!!!!!!!!!!!!
		Computer computer = (Computer) computerVorerst;
		if (computer.schwierigkeitsgrad.equals("leicht")) {

			return minimaleBohnen(muldenOben);
		} else if (computer.schwierigkeitsgrad.equals("mittel")) {
			if (this.defensivtaktik() != -1)
				return this.defensivtaktik();
			else {
				return minimaleBohnen(muldenOben);
			}
		} else if (computer.schwierigkeitsgrad.equals("schwer")) {
			if (this.angriffstaktik() != -1) {
				return this.angriffstaktik();
			} else {
				if (this.defensivtaktik() != -1)
					return this.defensivtaktik();
				else {
					return minimaleBohnen(muldenOben);
				}
			}
		}
		return ergebnis;
	}

	/**
	 * Methode die die Angriffstaktik des Computers enthält.
	 * 
	 * @return returnt entweder das Feld, dass am meisten Gewinn bringt oder -1
	 *         wenn kein Feld Gewinn bringt.
	 */
	public int angriffstaktik() {
		int gewinn = 0;
		int index = -1;
		for (int i = 0; i < 6; i++) {
			Board boardKopie = spielstandAktuell();
			int bohnenAnzahlVorher = boardKopie.sp2.depot;
			boardKopie.fangen(boardKopie.saeen(i, false), false);
			if ((boardKopie.sp2.depot - bohnenAnzahlVorher) > gewinn) {
				gewinn = boardKopie.sp2.depot - bohnenAnzahlVorher;
				index = i;
			}
		}
		return index;
	}

	/**
	 * Methode die die Defensivtatktik des Computers enthält.
	 * 
	 * @return returnt entweder das gefährdetste Feld oder -1 wenn kein Feld
	 *         gefärdet ist.
	 */
	public int defensivtaktik() {
		int gewinn = 0;
		int index = -1;
		for (int i = 0; i < 6; i++) {
			Board boardKopie = spielstandAktuell();
			int bohnenAnzahlVorher = boardKopie.sp1.depot;
			boardKopie.fangen(boardKopie.saeen(i, true), true);
			if ((boardKopie.sp1.depot - bohnenAnzahlVorher) > gewinn) {
				gewinn = boardKopie.sp1.depot - bohnenAnzahlVorher;
				index = i;
			}
		}
		if (index == -1) {
			return index;
		}
		Board boardKopie2 = this;
		LetzteMulde gefaehrdetsteMulde = boardKopie2.saeen(index, true);
		return gefaehrdetsteMulde.index;
	}

	/**
	 * Methode die nach dem Saevorgang bestimmt ob etwas gefangen werden kann
	 * und falls ja dies auch dem Depot zurechnet.
	 * 
	 * @param lm
	 *            die letzte Mulde des Saevorgangs.
	 * @param anDerReihe
	 *            Boolean der angibt welcher Spieler an der Reihe ist.
	 */
	public void fangen(LetzteMulde lm, boolean anDerReihe) {
		int index = lm.index;
		if (anDerReihe) {
			if (lm.mulde.equals("Oben") && ((muldenOben[index] == 2) || (muldenOben[index] == 3))) {
				while ((0 <= index && index <= 5) && ((muldenOben[index] == 2) || (muldenOben[index] == 3))) {
					this.sp1.depot += muldenOben[index];
					muldenOben[index] = 0;
					index++;
				}
			}

		} else {
			if (lm.mulde.equals("Unten") && ((muldenUnten[index] == 2) || (muldenUnten[index] == 3))) {
				while ((0 <= index && index <= 5) && ((muldenUnten[index] == 2) || (muldenUnten[index] == 3))) {
					this.sp2.depot += muldenUnten[index];
					muldenUnten[index] = 0;
					index--;
				}
			}

		}

	}

	/**
	 * Hilfsmethode die den gesamten Inhalt der oberen Feldhälfte bestimmt.
	 * 
	 * @return gesamter Inhalt der oberen Feldhälfte
	 */
	public int summeOben() {
		int ergebnis = 0;
		for (int i : muldenOben) {
			ergebnis += i;
		}
		return ergebnis;
	}

	/**
	 * Hilfsmethode die den gesamten Inhalt der unteren Feldhälfte bestimmt.
	 * 
	 * @return gesamter Inhalt der unteren Feldhälfte
	 */
	public int summeUnten() {
		int ergebnis = 0;
		for (int i : muldenUnten) {
			ergebnis += i;
		}
		return ergebnis;
	}

	/**
	 * Methode die das kleinste Feld ungleich 0 des Computers bestimmt.
	 * 
	 * @param mulde
	 *            array der Mulden des Computers
	 * @return gewaehlter Array- Index
	 */
	public int minimaleBohnen(int[] mulde) {
		int arrayIndex = 0;
		int i = 0;
		while (i + 1 < mulde.length) {
			if (mulde[i] < mulde[i + 1]) {
				if (mulde[i] <= mulde[arrayIndex]) {
					if (mulde[i] == 0) {
						arrayIndex = i + 1;
					} else {
						arrayIndex = i;
					}
				}
			} else {
				if (mulde[i + 1] < mulde[arrayIndex]) {
					if (mulde[i + 1] != 0) {
						arrayIndex = i;
					}
				}
			}
			i++;
		}
		return arrayIndex;
	}

	/**
	 * Methode die den aktuellen Spielstand kopiert (noetig zur Simulation)
	 * 
	 * @return returnt ein neues Board mit gleichem Inhalt des ursprünglichen.
	 */
	public Board spielstandAktuell() {
		Board boardk = new Board();
		boardk.muldenOben = this.muldenOben.clone();
		boardk.muldenUnten = this.muldenUnten.clone();
		boardk.sp1 = new Spieler();
		boardk.sp2 = new Spieler();
		boardk.sp1.depot = this.sp1.depot;
		boardk.sp2.depot = this.sp2.depot;

		return boardk;
	}
}
