package DiceGame;

public class Player {
		private int score;
		private boolean suddendeath;
		private String playername;

	public Player(String playername) {
		this.playername = playername;
		score = 0; //Afventer spørgsmål til projektleder
		suddendeath = false;
	}

	public void setScore(int score) {
		this.score = score;	
	}

	public int getScore() {
		return this.score;
	}

	public void setSuddenDeath(boolean suddendeath) {
		this.suddendeath = suddendeath;
	}

	public boolean getSuddenDeath() {
		return this.suddendeath;
	}
	public String getPlayername() {
		return this.playername;
	}
	
	public String toString() {
		return "Score = " + this.score + "   Suddendeath = " + this.suddendeath;
	}
}
