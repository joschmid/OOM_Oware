import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int keineDepotAenderung = 0;

	public static void main(String[] args) throws IOException {

		// Neues Board erstellen
		Board board = new Board();

		// Benutzer, Spieler, Computer anlegen
		User sp1 = new User();
		Spieler sp2 = new User();
		Computer spc = new Computer();

		// Ob gegen einen Computer gespielt wird
		boolean spctrue = false;

		// Kommandozeile einlesen
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean spielstart = false;
		while (!spielstart) {
			System.out.println("Wollen sie gegen einen menschlichen Spieler spielen? (j/n)");
			String eingabe1 = br.readLine();

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
				spielstart = true;
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
					System.exit(0);
				}
				spielstart = true;
				break;
			default:
				System.err.println("Falsche Eingabe! Bitte Spiel neustarten");
				System.exit(0);
			}
		}

		// Board die spielenden Spieler bekannt machen
		board.sp1 = sp1;
		if (spctrue) {
			board.sp2 = spc;
		} else {
			board.sp2 = sp2;
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

		board.boardAnzeigen();
		// prüfen ob Sp vs Comp oder Sp vs SP
		if (!spctrue) {
			// Feldauswahl Eingabe Spieler vs Spieler
			char y = '.';

			// Spielstart
			do {

				// Sp2 ist an der Reihe
				// anDerReihe wird oben per Zufall gesetzt
				// Methode kann nicht ausgelagert werden da es Großbuchstaben
				// hat --> vllt noch ändern
				if (!anDerReihe) {
					System.out.printf("Spielzug " + sp2.name + ", bitte Zug angeben:");
					int tempDepot = sp2.depot;
					konsolenEingabe(br, y, anDerReihe, board);
					anDerReihe = true;
					if (sp2.depot == tempDepot) {
						keineDepotAenderung++;
					} else {
						keineDepotAenderung = 0;
					}

				}
				// Spieler 1 ist dran
				// Methode siehe unten, BufferedReader, char und der boolean an
				// der Reihe wird mitgegeben
				else {
					System.out.println("Spielzug " + sp1.name + ", bitte Zug angeben:");
					int tempDepot = sp1.depot;
					konsolenEingabe(br, y, anDerReihe, board);
					// Boolean auf false setzen, dass Spieler 2 im nächsten
					// Spielzug dran ist
					anDerReihe = false;
					if (sp1.depot == tempDepot) {
						keineDepotAenderung++;
					} else {
						keineDepotAenderung = 0;
					}
				}
				board.boardAnzeigen();
			} while (!statusAbfragen(board, keineDepotAenderung, br, anDerReihe));
			spielBeenden(board);

		} else {

			// Feldauswahl Eingabe Spieler vs Computer
			char x = '.';
			do {

				if (!anDerReihe) {
					// Computer Spielzug implementieren
					System.out.println("Computer macht Spielzug.");
					board.fangen(board.saeen(board.feldwaehlenComputer(board.sp2), anDerReihe), anDerReihe);
					anDerReihe = true;
				} else {
					System.out.println(board.sp1.name+" macht Spielzug.");
					System.out.printf("Spielzug " + sp1.name + ", bitte Zug angeben:");
					konsolenEingabe(br, x, anDerReihe, board);
					anDerReihe = false;
				}
				board.boardAnzeigen();
			} while (!statusAbfragen(board, keineDepotAenderung, br, anDerReihe));
			spielBeenden(board);
		}

	}

	public static Board konsolenEingabe(BufferedReader br, char i, boolean anDerReihe, Board board) throws IOException {
		// System.out.printf("Zug angeben: ");
		String a = br.readLine();
		i = a.charAt(0);

		switch (i) {
		case 'a':
		case 'A':
			board.fangen(board.saeen(board.feldwaehlenUser(anDerReihe, 0), anDerReihe), anDerReihe);

			// Spielzug für a/A machen
			System.out.println("Feld " + i);
			return board;
		case 'b':
		case 'B':
			board.fangen(board.saeen(board.feldwaehlenUser(anDerReihe, 1), anDerReihe), anDerReihe);

			// Spielzug für b/B machen
			System.out.println("Feld " + i);
			return board;
		case 'c':
		case 'C':
			board.fangen(board.saeen(board.feldwaehlenUser(anDerReihe, 2), anDerReihe), anDerReihe);

			// Spielzug für c/C machen
			System.out.println("Feld " + i);
			return board;
		case 'd':
		case 'D':
			board.fangen(board.saeen(board.feldwaehlenUser(anDerReihe, 3), anDerReihe), anDerReihe);

			// Spielzug für d/D machen
			System.out.println("Feld " + i);
			return board;
		case 'e':
		case 'E':
			board.fangen(board.saeen(board.feldwaehlenUser(anDerReihe, 4), anDerReihe), anDerReihe);

			// Spielzug für e/E machen
			System.out.println("Feld " + i);
			return board;
		case 'f':
		case 'F':
			board.fangen(board.saeen(board.feldwaehlenUser(anDerReihe, 5), anDerReihe), anDerReihe);

			// Spielzug für f/F machen
			System.out.println("Feld " + i);
			return board;

		case 'x':
		case 'X':
			System.out.println("Spiel beendet");
			return board;

		default:
			board.saeen(board.feldwaehlenUser(anDerReihe, -7), anDerReihe);
			// Feldwählen aufrufen
			return board;
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

	// Spiel Beenden
	public static void spielBeenden(Board board) {
		board.sp1.depot += board.summeUnten();
		board.sp2.depot += board.summeOben();
		board.boardAnzeigen();
		if (board.sp1.depot > board.sp2.depot) {
			System.out.println("Gewinner: " + board.sp1.name);
		} else if (board.sp1.depot < board.sp2.depot) {
			System.out.println("Gewinner: " + board.sp2.name);
		} else {
			System.out.println("Unentschieden!!!");
		}
		System.out.println("Depot " + board.sp1.name + ": " + board.sp1.depot);
		System.out.println("Depot " + board.sp2.name + ": " + board.sp2.depot);
		System.exit(0);
	}

	// Status abfragen
	public static boolean statusAbfragen(Board board, int keineDepotAenderung, BufferedReader br, boolean anDerReihe)
			throws IOException {
		if (board.sp1.depot + board.sp2.depot == 48) {
			return true;
		} else if (anDerReihe && (board.summeUnten() == 0)) {
			return true;
		} else if (!anDerReihe && (board.summeOben() == 0)) {
			return true;
		} else if (keineDepotAenderung == 10) {
			return abbruch(br);

		} else if (((board.summeOben() == 0) && (0 + board.muldenOben[0] >= 6) && (1 + board.muldenOben[1] >= 6)
				&& (2 + board.muldenOben[2] >= 6) && (3 + board.muldenOben[3] >= 6) && (4 + board.muldenOben[4] >= 6)
				&& (5 + board.muldenOben[5] >= 6))) {
			return true;
		} else if (((board.summeUnten() == 0) && (0 + board.muldenUnten[0] >= 6) && (1 + board.muldenUnten[1] >= 6)
				&& (2 + board.muldenUnten[2] >= 6) && (3 + board.muldenUnten[3] >= 6) && (4 + board.muldenUnten[4] >= 6)
				&& (5 + board.muldenUnten[5] >= 6))) {
			return true;
		}
		return false;

	}

	// nach Abbruch fragen
	public static boolean abbruch(BufferedReader br) throws IOException {
		System.out.println("Seit 10 Spielzügen keine Depotveränderung, Spiel beenden? (j/n)");
		String eingabe = br.readLine();
		switch (eingabe) {
		case "j":
		case "J":
			return true;

		case "n":
		case "N":
			keineDepotAenderung = 0;
			return false;
		default:
			System.out.println("Bitte erneut eingeben");
			return abbruch(br);
		}

	}

}