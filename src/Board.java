
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

		System.out.print("================="+sp2.name+"==================\n");
		System.out.print("A\tB\tC\tD\tE\tF\n");
		System.out.print(muldenOben[0]+"\t"+muldenOben[1]+"\t"+muldenOben[2]+"\t"+muldenOben[3]+"\t"+muldenOben[4]+"\t"+muldenOben[5]+"\n");
		System.out.print("-------------------------------------------\n");
		System.out.print(muldenUnten[0]+"\t"+muldenUnten[1]+"\t"+muldenUnten[2]+"\t"+muldenUnten[3]+"\t"+muldenUnten[4]+"\t"+muldenUnten[5]+"\n");
		System.out.print("a\tb\tc\td\te\tf\n");
		System.out.print("===================="+sp1.name+"====================\n");
		System.out.print(sp2.name+" Score: "+sp2.depot+"\n"+sp1.name+ " Score: "+sp1.depot+"\n\n");


	}
}
