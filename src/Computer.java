
public class Computer extends Spieler {
	public String schwierigkeitsgrad;

	public Computer(String schwierigkeit) {
		super("Computer");
		this.schwierigkeitsgrad = schwierigkeit;
	}
	public Computer(){
		super("Computer");
		this.schwierigkeitsgrad = null;
	}

	public int feldwaehlenComputer(int feld) {
		return feld;
	}

}
