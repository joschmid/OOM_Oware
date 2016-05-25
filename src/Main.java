import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {

		Board board = new Board();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Wollen sie gegen einen menschlichen Spieler spielen? (j/n)");
		String eingabe1 = br.readLine();

		User sp1 = new User();
		Spieler sp2 = new User();

		switch (eingabe1) {
		case "j":
			System.out.println("Spieler 1 Name eingeben:");
			String name1 = br.readLine();
			sp1.name = name1;

			System.out.println("Spieler 2 Name eingeben:");
			String name2 = br.readLine();

			sp2.name = name2;
			break;

		case "n":
			System.out.println("Spieler 1 Name eingeben:");
			String name3 = br.readLine();
			sp1.name = name3;

			System.out.println("Bitte Schwierigkeit eingeben(leicht/mittel/schwer)");
			String schwierigkeit = br.readLine();

			Computer spc = new Computer();
			boolean spctrue = true;
			switch (schwierigkeit) {
			case "leicht":
				spc.schwierigkeitsgrad = schwierigkeit;
				break;

			case "mittel":
				spc.schwierigkeitsgrad = schwierigkeit;

				break;

			case "schwer":
				spc.schwierigkeitsgrad = schwierigkeit;
				break;
			default:
				System.out.println("Fehler");
			}
			break;
		default:
			System.out.println("Fehler2");
		}

	}

}