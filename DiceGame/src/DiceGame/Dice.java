package DiceGame;
import java.util.*;

public class Dice {
	private int value;
	
	
	public Dice() {
		this.value = 0;
	}

	public void Roll() {
		Random tilfaldigvardi = new Random();
		this.value = tilfaldigvardi.nextInt(6)+1;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public String toString() {
		return "value = "+ value;
	} // Test1
}
