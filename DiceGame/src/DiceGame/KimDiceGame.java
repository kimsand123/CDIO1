package DiceGame;

import desktop_resources.GUI;

import java.awt.Color;
import java.util.Scanner;

import desktop_fields.Field;
import desktop_fields.Street;

public class KimDiceGame {

	private int terning1værdi;
	private int terning2værdi;
	private boolean spilvundet;

	private Dice terning1 = new Dice();
	private Dice terning2 = new Dice();

	private Player player1 = new Player ("Player 1");
	private Player player2 = new Player ("Player 2");

	public KimDiceGame () {
		terning1værdi = 0;
		terning2værdi = 0;
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
				.setTitle("Bæger")
				.setBgColor(Color.green)
				.build();
		GUI.create(fields);

		//Spiller1
		//spilsequence Check for 2 ensregler og slå terning
		do {
			//Slå Terning
			System.out.println("");
			System.out.print("Player 1........Press Enter to roll dice");
			String enter = sc.nextLine();

			terning1.Roll();
			terning2.Roll();

			terning1værdi = terning1.getValue();
			terning2værdi = terning2.getValue();

			GUI.setDice(terning1værdi, terning2værdi);

			//Check for 2 ensregler
			if (terning1værdi == 1 && terning2værdi == 1) {
				player1.setScore(0);
				System.out.println("Player 1 your score is reset to 0");
			}
			if (player1.getScore() >= 40 && terning1værdi == terning2værdi){
				spilvundet = true;
				int samletscore = player1.getScore()+terning1værdi + terning2værdi;
				System.out.println("Player 1, you've won the game by " + samletscore + " points");
				return;
			}
			if (terning1værdi == 6 && terning2værdi == 6) {
				if (player1.getSuddenDeath()==true) {
					spilvundet = true;
					System.out.println("Player 1, you've won the game by SuddenDeath");
					return;
				}else{
					player1.setSuddenDeath(true);
				}
			}

			//Score tælles op
			int samletscore = player1.getScore()+terning1værdi+terning2værdi;
			player1.setScore(samletscore);
			System.out.println("Player 1, your score is " + samletscore);

		} while (terning1værdi == terning2værdi);



		//Spiller2
		//spilsequence Check for 2 ensregler og slå terning
		do {
			//Slå Terning
			System.out.println("");
			System.out.print("Player 2.......Press Enter to roll dice");
			String enter = sc.nextLine();
			
			terning1.Roll();
			terning2.Roll();

			terning1værdi = terning1.getValue();
			terning2værdi = terning2.getValue();
			GUI.setDice(terning1værdi, terning2værdi);

			//Check for 2 ensregler
			if (terning1værdi == 1 && terning2værdi == 1) {
				player2.setScore(0);
				System.out.println("Player 2 your score is reset to 0");
			}
			if (player2.getScore() >= 40 && terning1værdi == terning2værdi){
				this.spilvundet = true;
				int samletscore = player2.getScore() + terning1værdi + terning2værdi;
				System.out.print("Player 2, you've won the game with "+ samletscore + " points");
				return;

			}
			if (terning1værdi == 6 && terning2værdi == 6) {
				if (player2.getSuddenDeath()==true) {
					this.spilvundet = true;
					System.out.print("Player 2, you've won the game, by SuddenDeath ");
					return;
				}else{
					player2.setSuddenDeath(true);
				}
			}
			//Score tælles op
			int samletscore = player2.getScore()+terning1værdi+terning2værdi;
			player2.setScore(samletscore);
			System.out.println("Player 2, your score is "+ samletscore);
		} while (terning1værdi == terning2værdi);
		sc.close();
	}
}
