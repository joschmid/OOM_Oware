import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {

		// Neues Board erstellen
		Board board = new Board();

		// Kommandozeile einlesen
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Wollen sie gegen einen menschlichen Spieler spielen? (j/n)");
		String eingabe1 = br.readLine();

		// Benutzer, Spieler, Computer anlegen
		User sp1 = new User();
		Spieler sp2 = new User();
		Computer spc = new Computer();

		// Ob gegen einen Computer gespielt wird
		boolean spctrue = false;

		// Spielstart
		switch (eingabe1) {
		// Zwei menschliche Spieler
		case "j":
			System.out.println("Spieler 1 Name eingeben:");
			String name1 = br.readLine();
			sp1.name = name1;

			System.out.println("Spieler 2 Name eingeben:");
			String name2 = br.readLine();
			sp2.name = name2;
			break;

		// Gegen Computer spielen
		case "n":
			System.out.println("Spieler 1 Name eingeben:");
			String name3 = br.readLine();
			sp1.name = name3;

			System.out.println("Bitte Schwierigkeit eingeben(leicht/mittel/schwer)");
			String schwierigkeit = br.readLine();

			spctrue = true;
			// Schwierigkeit festlegen
			switch (schwierigkeit) {
			case "leicht":
				spc.schwierigkeitsgrad = schwierigkeit;
				spc.name = "Computer (leicht)";
				break;

			case "mittel":
				spc.schwierigkeitsgrad = schwierigkeit;
				spc.name = "Computer (mittel)";
				break;

			case "schwer":
				spc.schwierigkeitsgrad = schwierigkeit;
				spc.name = "Computer (schwer)";
				break;
			default:
				System.out.println("Falsche Eingabe! Bitte Spiel neustarten");
			}
			break;
		default:
			System.out.println("Falsche Eingabe! Bitte Spiel neustarten");
		}

		// Festlegen welcher Spieler beginnt
		boolean anDerReihe = sp1Beginnt();

		if (anDerReihe) {
			System.out.println(sp1.name + " beginnt");
		} else {
			if (spctrue) {
				System.out.println(spc.name + " beginnt");
			} else {
				System.out.println(sp2.name + " beginnt");
			}
		}

		// Board die spielenden Spieler bekannt machen
		board.sp1 = sp1;
		if (spctrue) {
			board.sp2 = spc;
		} else {
			board.sp2 = sp2;
		}

		//prüfen ob Sp vs Comp oder Sp vs SP
		if (!spctrue) {
			// Feldauswahl Eingabe Spieler vs Spieler
			char y = '.';
			do {
				board.boardAnzeigen();
				//Sp2 ist an der Reihe
				//anDerReihe wird oben per Zufall gesetzt
				//Methode kann nicht ausgelagert werden da es Großbuchstaben hat --> vllt noch ändern
				if (!anDerReihe) {
					System.out.printf("Zug angeben: ");
					String a = br.readLine();
					y = a.charAt(0);

					switch (y) {
					case 'A':
						System.out.println("Feld A");
						// Spielzug für Feld a machen
						break;

					case 'B':
						System.out.println("Feld B");
						// Spielzug für Feld b machen
						break;

					case 'C':
						System.out.println("Feld C");
						// Spielzug für Feld c machen
						break;

					case 'D':
						System.out.println("Feld D");
						// Spielzug für Feld d machen
						break;

					case 'E':
						System.out.println("Feld E");
						// Spielzug für Feld e machen
						break;

					case 'F':
						System.out.println("Feld F");
						// Spielzug für Feld f machen
						break;

					case 'x':
					case 'X':
						System.out.println("Spiel beendet");
						break;

					default:
						System.out.print("Invalid Move\n");
						break;
					}

				}
				//Spieler 1 ist dran
				//Methode siehe unten, BufferedReader, char und der boolean an der Reihe wird mitgegeben
				else {
					System.out.println("Spielzug Spieler 1");
					feldWaehlen(br, y, anDerReihe);
					// Boolean auf false setzen, dass Spieler 2 im nächsten Spielzug dran ist
					anDerReihe = false;
				}

			} while (y != 'x' && y != 'X'); // && result != 1);

		} else {

			// Feldauswahl Eingabe Spieler vs Computer
			char x = '.';
			do {
				board.boardAnzeigen();
				if (spctrue) {
					// Computer Spielzug implementieren
					System.out.println("Computer macht Spielzug");
					spctrue = false;
				} else {
					feldWaehlen(br, x, anDerReihe);
				}

			} while (x != 'x' && x != 'X'); // && result != 1);

		}

	}

	public static void feldWaehlen(BufferedReader br, char i, boolean anDerReihe) throws IOException {
		System.out.printf("Zug angeben: ");
		String a = br.readLine();
		i = a.charAt(0);

		switch (i) {
		case 'a':
			System.out.println("Feld A");
			// Spielzug für Feld a machen
			break;

		case 'b':
			System.out.println("Feld B");
			// Spielzug für Feld b machen
			break;

		case 'c':
			System.out.println("Feld C");
			// Spielzug für Feld c machen
			break;

		case 'd':
			System.out.println("Feld D");
			// Spielzug für Feld d machen
			break;

		case 'e':
			System.out.println("Feld E");
			// Spielzug für Feld e machen
			break;

		case 'f':
			System.out.println("Feld F");
			// Spielzug für Feld f machen
			break;

		case 'x':
		case 'X':
			System.out.println("Spiel beendet");
			break;

		default:
			System.out.print("Invalid Move\n");
			break;
		}
	}

	public static boolean sp1Beginnt() {
		double i = Math.random();
		if (i <= 0.5) {
			return true;
		} else {
			return false;
		}

	}

}