import java.util.Scanner;

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
	 * @return gibt die letzte Mulde auf die eine Bohne fiel zur�ck
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
					lm.mulde = "Oben";
				}
				k--;
			}
			lm.index = k;
			return lm;
		}
	}

	public int feldwaehlenUser(boolean anDerReihe, int index) {
		Scanner scanner = new Scanner(System.in);
		if (anDerReihe) {
			if (0 <= index && index <= 5) {
				if (muldenOben[0] + muldenOben[1] + muldenOben[2] + muldenOben[3] + muldenOben[4]
						+ muldenOben[5] == 0) {
					if (index + muldenUnten[index] >= 6) {
						return index;
					} else {
						System.out.println("Fehler ung�ltige Eingabe: Gegner hat keine Steine mehr");
						System.out.println("bitte neue Eingabe:");
						index = scanner.nextInt();
						feldwaehlenUser(anDerReihe, index);
					}
				} else {
					if (muldenUnten[index] > 0) {
						return index;
					} else {
						System.out.println("Fehler ung�ltige Eingabe: Ausgew�hltes Feld ist leer");
						System.out.println("bitte neue Eingabe:");
						index = scanner.nextInt();
						feldwaehlenUser(anDerReihe, index);
					}
				}
			} else {
				System.out.println("Fehler ung�ltige Eingabe: Buchstabe liegt nicht im eigenen Spielfeld");
				System.out.println("bitte neue Eingabe:");
				index = scanner.nextInt();
				feldwaehlenUser(anDerReihe, index);

			}

		}else{
			if(0<=index&&index<=5){
				if(muldenUnten[0]+muldenUnten[1]+muldenUnten[2]+muldenUnten[3]+muldenUnten[4]+muldenUnten[5]==0){
					if(index+muldenOben[index]>=6){
						return index;
					}else{
						System.out.println("Fehler ung�ltige Eingabe: Gegner hat keine Steine mehr");
						System.out.println("bitte neue Eingabe:");
						index = scanner.nextInt();
						feldwaehlenUser(anDerReihe, index);
					}
				}else{
					if(muldenOben[index]>0){
						return index;
					}else{
						System.out.println("Fehler ung�ltige Eingabe: Ausgew�hltes Feld ist leer");
						System.out.println("bitte neue Eingabe:");
						index = scanner.nextInt();
						feldwaehlenUser(anDerReihe, index);
					}
				}
			}else{
				System.out.println("Fehler ung�ltige Eingabe: Buchstabe liegt nicht im eigenen Spielfeld");
				System.out.println("bitte neue Eingabe:");
				index = scanner.nextInt();
				feldwaehlenUser(anDerReihe, index);
			}
		}
		scanner.close();
		return index;
	}
}