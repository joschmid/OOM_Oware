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

		// do {
		// board.boardAnzeigen();
		// if (spctrue) {
		// // Computer Spielzug implementieren
		// } else {
		// System.out.printf("Your Move: ");
		// String a = br.readLine();
		// x = a.charAt(0);
		//
		// switch (x) {
		// case 'a':
		// result = game.makeMove(0);
		// break;
		//
		// case 'b':
		// result = game.makeMove(1);
		// break;
		//
		// case 'c':
		// result = game.makeMove(2);
		// break;
		//
		// case 'd':
		// result = game.makeMove(3);
		// break;
		//
		// case 'e':
		// result = game.makeMove(4);
		// break;
		//
		// case 'f':
		// result = game.makeMove(5);
		// break;
		//
		// case 'x':
		// case 'X':
		// for (int i = 0; i < 6; i++) {
		// game.computerSeeds += game.computerCells[i];
		// game.playerSeeds += game.playerCells[i];
		// }
		// if (game.computerSeeds == 24 && game.playerSeeds == 24) {
		// System.out.println("It is a draw!");
		// }
		// if (game.computerSeeds > 24) {
		// System.out.println("Computer Won!");
		// }
		// if (game.playerSeeds > 24) {
		// System.out.println("You won!");
		// }
		//
		// break;
		//
		// default:
		// System.out.print("Invalid Move\n");
		// break;
		// }
		// }
		//
		// } while (x != 'x' && x != 'X' && result != 1);
		//
		//
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