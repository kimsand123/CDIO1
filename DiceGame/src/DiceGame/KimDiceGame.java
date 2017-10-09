package DiceGame;

import desktop_resources.GUI;

import java.awt.Color;
import java.util.Scanner;

import desktop_fields.Field;
import desktop_fields.Street;

public class KimDiceGame {

	private int terning1v�rdi;
	private int terning2v�rdi;
	private boolean spilvundet;

	private Dice terning1 = new Dice();
	private Dice terning2 = new Dice();

	private Player player1 = new Player ("Player 1");
	private Player player2 = new Player ("Player 2");

	public KimDiceGame () {
		terning1v�rdi = 0;
		terning2v�rdi = 0;
		spilvundet = false;

	}
	
	public static void main(String[] args) {
		KimDiceGame spil = new KimDiceGame();

		System.out.println("The name of the game is...... getting to 40 with a twist.");
		System.out.println("This game is about being the first to reach 40 points and then roll a double.");
		System.out.println("If you roll doubles, you will get an ekstra roll");
		System.out.println("If you roll double 1's, your points will reset and the eyes of the dice will be added to your score");
		System.out.println("You can win the game in 2 ways.");
		System.out.println("By reaching 40 AND then roll doubles OR roll double 6's AND then roll double 6's again in your free roll");
		System.out.println("If you ");

		while (spil.spilvundet==false) {
			spil.player1.setSuddenDeath(false);
			spil.player2.setSuddenDeath(false);
			spil.sequence();
		}

		GUI.close();
	}
	
	private void sequence() {
		Scanner sc = new Scanner(System.in); // Scanner so you have to press enter to roll dice
		
		//instantialize, initialise and create the gameboard with only 1 field in the color green.
		Field [] fields = new Field[1];

		fields[0] = new Street.Builder()
				.setTitle("B�ger")
				.setBgColor(Color.green)
				.build();
		GUI.create(fields);

		//Spiller1
		//spilsequence Check for 2 ensregler og sl� terning
		do {
			//Sl� Terning
			System.out.println("");
			System.out.print("Player 1........Press Enter to roll dice");
			String enter = sc.nextLine();

			terning1.Roll();
			terning2.Roll();

			terning1v�rdi = terning1.getValue();
			terning2v�rdi = terning2.getValue();

			GUI.setDice(terning1v�rdi, terning2v�rdi);

			//Check for 2 ensregler
			if (terning1v�rdi == 1 && terning2v�rdi == 1) {
				player1.setScore(0);
				System.out.println("Player 1 your score is reset to 0");
			}
			if (player1.getScore() >= 40 && terning1v�rdi == terning2v�rdi){
				spilvundet = true;
				int samletscore = player1.getScore()+terning1v�rdi + terning2v�rdi;
				System.out.println("Player 1, you've won the game by " + samletscore + " points");
				return;
			}
			if (terning1v�rdi == 6 && terning2v�rdi == 6) {
				if (player1.getSuddenDeath()==true) {
					spilvundet = true;
					System.out.println("Player 1, you've won the game by SuddenDeath");
					return;
				}else{
					player1.setSuddenDeath(true);
				}
			}

			//Score t�lles op
			int samletscore = player1.getScore()+terning1v�rdi+terning2v�rdi;
			player1.setScore(samletscore);
			System.out.println("Player 1, your score is " + samletscore);

		} while (terning1v�rdi == terning2v�rdi);



		//Spiller2
		//spilsequence Check for 2 ensregler og sl� terning
		do {
			//Sl� Terning
			System.out.println("");
			System.out.print("Player 2.......Press Enter to roll dice");
			String enter = sc.nextLine();
			
			terning1.Roll();
			terning2.Roll();

			terning1v�rdi = terning1.getValue();
			terning2v�rdi = terning2.getValue();
			GUI.setDice(terning1v�rdi, terning2v�rdi);

			//Check for 2 ensregler
			if (terning1v�rdi == 1 && terning2v�rdi == 1) {
				player2.setScore(0);
				System.out.println("Player 2 your score is reset to 0");
			}
			if (player2.getScore() >= 40 && terning1v�rdi == terning2v�rdi){
				this.spilvundet = true;
				int samletscore = player2.getScore() + terning1v�rdi + terning2v�rdi;
				System.out.print("Player 2, you've won the game with "+ samletscore + " points");
				return;

			}
			if (terning1v�rdi == 6 && terning2v�rdi == 6) {
				if (player2.getSuddenDeath()==true) {
					this.spilvundet = true;
					System.out.print("Player 2, you've won the game, by SuddenDeath ");
					return;
				}else{
					player2.setSuddenDeath(true);
				}
			}
			//Score t�lles op
			int samletscore = player2.getScore()+terning1v�rdi+terning2v�rdi;
			player2.setScore(samletscore);
			System.out.println("Player 2, your score is "+ samletscore);
		} while (terning1v�rdi == terning2v�rdi);
		sc.close();
	}
}
