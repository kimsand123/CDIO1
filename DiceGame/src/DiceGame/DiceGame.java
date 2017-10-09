package DiceGame;

import desktop_resources.GUI;

import java.awt.Color;
import java.util.Random;
import java.util.Scanner;
import desktop_fields.Field;
import desktop_fields.Street;

public class DiceGame {

	private int terning1v�rdi = 0;
	private int terning2v�rdi = 0;
	private boolean gamewon = false;

	private Dice terning1 = new Dice();
	private Dice terning2 = new Dice();

	public DiceGame() {}

	public static void main(String[] args) {
		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");
		String enter = "";
		
		DiceGame game = new DiceGame();

		
		while (true){

			game.sequence(player1);
			if (game.gamewon == true) {
				break;
			}
			game.sequence(player2);	
			if (game.gamewon == true) {
				break;
			}
			System.out.println("Player 1 you have "+player1.getScore()+" points");
			System.out.println("Player 2 you have "+player2.getScore()+" points");
			System.out.println("");
		}


	}

	private void sequence(Player player) {
		Scanner sc = new Scanner(System.in);

		do{
			System.out.print(player.getPlayername() + " press enter to roll");	
			sc.nextLine();
			roll2Dice();
			System.out.println("");
			System.out.println("You rolled a " + this.terning1v�rdi + " and a " + this.terning2v�rdi);
			System.out.println("");
			player.setScore(player.getScore()+terning2v�rdi+terning1v�rdi);
			
			if (terning1v�rdi == terning2v�rdi){

				//hvis man sl�r 2 seksere skal der checkes for suddendeath, og sejr skal udskrives. Hvis ikke suddendeath er sat, s� skal markeringen s�ttes hos spilleren
				if (terning1v�rdi == 6 && player.getSuddenDeath()) {
					System.out.println("Player " + player.getPlayername() + " Won the game by SuddenDeath");
					this.gamewon = true;
					return;
				} else {player.setSuddenDeath(true);}

				//Hvis player har mere end 40 point og har sl�et 2 ens s� har man vundet.
				if (player.getScore()-(this.terning1v�rdi+this.terning2v�rdi) >= 40 && terning1v�rdi != 1) {
					int endeligscore = player.getScore();
					System.out.println(player.getPlayername()+ " Won the game by  " + endeligscore + " points");
					this.gamewon = true;
					return;
				}

				//Hvis player har sl�et 2 ettere s�ttes score til 0
				if (terning1v�rdi == 1) {
					player.setScore(0);
					System.out.println(player.getPlayername()+ ". Your points have been reset to 0 ");}
			}
		}
		while(terning1v�rdi == terning2v�rdi);
		player.setSuddenDeath(false);
	}

	private void roll2Dice() {
		Field [] fields = new Field[1];

		fields[0] = new Street.Builder()
				.setTitle("B�ger")
				.setBgColor(Color.green)
				.build();

		GUI.create(fields);

		for (int x = 1;x<9;x=x+1) {
			terning1.Roll();
			terning1v�rdi = terning1.getValue();
			terning2.Roll();
			terning2v�rdi = terning2.getValue();

			Random tal  = new Random();// Tilf�ldighedsgenerator til at lave ny animering hver gang.
			GUI.setDice(terning1v�rdi, tal.nextInt(9)*10, 1+(int)(x/(tal.nextInt(2)+1)), 1+(int)(x/(tal.nextInt(2)+1)), terning2v�rdi, tal.nextInt(9)*10, 2+(int)(x/(tal.nextInt(1)+1)), 2+(int)(x/(tal.nextInt(2)+1)));
			pause(220); // pause mellem hver frame
		}
		pause(2000);
		GUI.close();

	}

	private void pause(int pause){
		try {
			Thread.sleep(pause);
		}
		catch(InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
}
