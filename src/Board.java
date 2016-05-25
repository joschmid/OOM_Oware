
public class Board {
	int[] muldenUnten = new int[6];
	int[] muldenOben = new int[6];

	public Board() {

		for (int k = 0; k < muldenOben.length; k++) {
			muldenOben[k] = 4;
		}
		for (int k = 0; k < muldenUnten.length; k++) {
			muldenUnten[k] = 4;
		}

	}

	public String toString() {

		return "-------------------------------------------------------------------";

	}
}
