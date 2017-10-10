package DiceGame;

import desktop_resources.GUI;

import java.awt.Color;
import java.util.Scanner;

import desktop_fields.Field;
import desktop_fields.Street;

public class DiceGame {

	private int terning1værdi;  //Private variable to hold the value of dice1
	private int terning2værdi; //Private variable to hold the value of dice2
	private boolean spilvundet; //Gamewon variable for gamecontrol

	private Dice terning1 = new Dice(); //Diceobject1
	private Dice terning2 = new Dice(); //Diceobject1

	private Player player1 = new Player ("Player 1"); //Player 1 object
	private Player player2 = new Player ("Player 2"); //Player 1 object
	Scanner sc = new Scanner(System.in); // Scanner so you have to press enter to roll dice
	
	//Constructor
	public DiceGame () {  
		terning1værdi = 0;
		terning2værdi = 0;
		spilvundet = false; //Set to false so the game isn't over before it's begun.
		

	}

	//Main
	public static void main(String[] args) {
		DiceGame spil = new DiceGame(); 
		
		// Game manual
		System.out.println("The name of the game is...... getting to 40, with a twist.");
		System.out.println("This game is about being the first to reach 40 points and then roll a double.");
		System.out.println("If you roll doubles, you will get an ekstra roll");
		System.out.println("If you roll double 1's, your points will reset to 0 and the eyes of the dice will be added to your score");
		System.out.println("You can win the game in 2 ways.");
		System.out.println("By reaching 40 or more AND then roll doubles OR roll double 6's AND then roll double 6's again in your free roll");


		while (spil.spilvundet==false) {	// Loop while game hasn't been won.
			spil.player1.setSuddenDeath(false); // Set SuddenDeath flag to false for both players otherwise it would be possible to win by rolling 2 sixes in the first turn and the 3rd turn.
			spil.player2.setSuddenDeath(false);
			spil.sequence(); //Game sequence..
		}
		GUI.close(); // close GUI
		spil.sc.close(); //Closing the scanner object.
	}

	private void sequence() {
		
		//instantialize, initialise and create the gameboard with only 1 field in the color green.
		Field [] fields = new Field[1];

		fields[0] = new Street.Builder()
				.setTitle("")
				.setBgColor(Color.green)
				.build();
		GUI.create(fields);

		//Spiller1
		//spilsequence Check for 2 ensregler og slå terning
		do {
			//Slå Terning
			System.out.println("");
			System.out.print("Player 1........Press Enter to roll dice");
			sc.nextLine(); //Wait for userinput

			terning1.Roll(); // Roll both dice
			terning2.Roll();			
			
			terning1værdi = terning1.getValue();  //Assign value of both dice to private variables
			terning2værdi = terning2.getValue();
			
//			System.out.print("t1 ");
//			terning1værdi = sc.nextInt();
//			System.out.print("t2 ");
//			terning2værdi = sc.nextInt();

			GUI.setDice(terning1værdi, terning2værdi); //Throw the dice graphically. As we have created the gameboard earlier, the first throw is as fast as all the others.

			//Check for 2 alike rules
			if (terning1værdi == 1 && terning2værdi == 1) { // if two 1´s
				player1.setScore(0); // reset score for player
				player1.setSuddenDeath(false); //
				System.out.println("Player 1 your score is reset to 0"); //infotext to player
			}
			if (player1.getScore() >= 40 && terning1værdi == terning2værdi){ // if reached 40 or more points and rolled two alike
				spilvundet = true; //Set gamewon
				int samletscore = player1.getScore()+terning1værdi + terning2værdi;  //variable to get around concatenation problem of making a score of 49 and a roll of 1 and 2 display as 4912
				System.out.println("Player 1, you've won the game by " + samletscore + " points");  // infotext to player
				return;  // return from method.
			}
			if (terning1værdi == terning2værdi && terning1værdi != 6) { // if 2 alike the player will loop so suddendeath flag will have to be lowered
				player1.setSuddenDeath(false);
			}
			if (terning1værdi == 6 && terning2værdi == 6) { //If two 6´s
				if (player1.getSuddenDeath()==true) { //Check if Suddendeath flag is raised
					spilvundet = true; // if so then set gamewon
					System.out.println("Player 1, you've won the game by SuddenDeath"); // infotext to player
					return; // return from method
				}else{
					player1.setSuddenDeath(true); // otherwise raise SuddenDeath flag.
				}
			}

			//Score tælles op
			int samletscore = player1.getScore()+terning1værdi+terning2værdi; //variable to sum up score.
			player1.setScore(samletscore); // set new score for player
			System.out.println("Player 1, your score is " + samletscore); //infotext to player
		} while (terning1værdi == terning2værdi); // as long as player throws 2 alike.


		//Spiller2
		//spilsequence Check for 2 ensregler og slå terning
		do {
			//Slå Terning
			System.out.println("");
			System.out.print("Player 2.......Press Enter to roll dice");
			sc.nextLine(); //Wait for userinput

			terning1.Roll();// Roll both dice
			terning2.Roll();

			terning1værdi = terning1.getValue(); //Assign value of both dice to private variables
			terning2værdi = terning2.getValue();
			
//			System.out.print("t1 ");
//			terning1værdi = sc.nextInt();
//			System.out.print("t2 ");
//			terning2værdi = sc.nextInt();		
			
			GUI.setDice(terning1værdi, terning2værdi); //Throw the dice graphically. As we have created the gameboard earlier, the first throw is as fast as all the others.

			//Check for 2 alike rules
			if (terning1værdi == 1 && terning2værdi == 1) { // if two 1´s
				player2.setScore(0); // reset score for player
				System.out.println("Player 2 your score is reset to 0");  //infotext to player
			}
			if (player2.getScore() >= 40 && terning1værdi == terning2værdi){ // if reached 40 or more points and rolled two alike
				this.spilvundet = true; //Set gamewon
				int samletscore = player2.getScore() + terning1værdi + terning2værdi;  //variable to get around concatenation problem of making a score of 49 and a roll of 1 and 2 display as 4912
				System.out.print("Player 2, you've won the game with "+ samletscore + " points");  // infotext to player
				return;// return from method
			}
			if (terning1værdi == terning2værdi && terning1værdi != 6) { // if 2 alike the player will loop so suddendeath flag will have to be lowered
				player2.setSuddenDeath(false);
			}
			if (terning1værdi == 6 && terning2værdi == 6) { //If two 6´s
				if (player2.getSuddenDeath()==true) { //Check if Suddendeath flag is raised
					this.spilvundet = true; // if so then set gamewon
					System.out.print("Player 2, you've won the game, by SuddenDeath ");  // infotext to player
					return;
				}else{
					player2.setSuddenDeath(true); // otherwise raise SuddenDeath flag.
				}
			}
			//Score tælles op
			int samletscore = player2.getScore()+terning1værdi+terning2værdi; //variable to sum up score.
			player2.setScore(samletscore);  // set new score for player
			System.out.println("Player 2, your score is "+ samletscore); //infotext to player

			
		} while (terning1værdi == terning2værdi);// as long as player throws 2 alike.
	}
}
