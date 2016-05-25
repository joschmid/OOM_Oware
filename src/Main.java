import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {

		Board board = new Board();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Wollen sie gegen einen menschlichen Spieler spielen? (j/n)");
		String eingabe1 = br.readLine();


		switch (eingabe1) {
		case "j":
			System.out.println("Spieler 1 Name eingeben:");
			String name1 = br.readLine();
			User sp1 = new User(name1);

			System.out.println("Spieler 2 Name eingeben:");
			String name2 = br.readLine();
			User sp2 = new User(name2);
			break;
		case "n":
			System.out.println("Spieler 1 Name eingeben:");
			String name3 = br.readLine();
			User sp1 = new User(name1);

			System.out.println("Bitte Schwierigkeit eingeben(leicht/mittel/schwer)");
			String schwierigkeit = br.readLine();

			Computer sp2 = new Computer(null);

			switch (schwierigkeit) {
			case "leicht":
				sp2.schwierigkeitsgrad = schwierigkeit;
				break;

			case "mittel":
				sp2.schwierigkeitsgrad = schwierigkeit;

				break;

			case "schwer":
				sp2.schwierigkeitsgrad = schwierigkeit;
				break;
			default:
				System.out.println("Fehler");
			}
			break;
			default:
				System.out.println("Fehler2");
		}


//		if (br.readLine().equals("j")) {
//			System.out.println("Spieler 1 Name eingeben:");
//			String name1 = br.readLine();
//			User sp1 = new User(name1);
//
//			System.out.println("Spieler 2 Name eingeben:");
//			String name2 = br.readLine();
//			User sp2 = new User(name2);
//		} else //if (br.readLine().equals("n"))
//			{
//			System.out.println("Spieler 1 Name eingeben:");
//			String name1 = br.readLine();
//			User sp1 = new User(name1);
//
//			System.out.println("Bitte Schwierigkeit eingeben(leicht/mittel/schwer)");
//			String schwierigkeit = br.readLine();
//
//			Computer sp2 = new Computer(null);
//
//			switch (schwierigkeit) {
//			case "leicht":
//				sp2.schwierigkeitsgrad = schwierigkeit;
//				break;
//
//			case "mittel":
//				sp2.schwierigkeitsgrad = schwierigkeit;
//
//				break;
//
//			case "schwer":
//				sp2.schwierigkeitsgrad = schwierigkeit;
//				break;
//			default:
//				System.out.println("Fehler");
//			}

		}

		// //Abfragebedingung überlegen dür
		// if(){
		//
		// }
		// //name muss vorher noch als String eingegeben werden
		// User sp1 = new User(name);

	}

}