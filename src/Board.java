
public class Board {
	int[] muldenUnten = new int[6];
	int[] muldenOben = new int[6];

	public Board(){
		for (int i : muldenOben) {
			muldenOben[i]=4;
		}
		for (int i : muldenUnten) {
			muldenUnten[i]=4;
		}
	}

	public String toString(){

	return "-------------------------------------------------------------------";

	}
}
