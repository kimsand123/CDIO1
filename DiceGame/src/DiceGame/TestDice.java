package DiceGame;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import desktop_resources.GUI;


public class TestDice {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDice() {
		GUI.setDice(4, 5, 6, 2);
	}

	@Test
	public void testRoll() {
		Dice terning1 = new Dice();
		int ettere = 0;
		int toere = 0;
		int treere = 0;
		int firere = 0;
		int femmere = 0;
		int seksere = 0;

		for (int x=1;x <= 60000;x=x+1) {
			terning1.Roll();
			int vardi = terning1.getValue();

			switch(vardi) {
			case 1:
				ettere = ettere + 1;
				break;
			case 2:
				toere = toere + 1;
				break;
			case 3:
				treere = treere + 1;
				break;
			case 4:
				firere = firere + 1;
				break;
			case 5:
				femmere = femmere + 1;
				break;
			case 6:
				seksere = seksere + 1;
				break;
			}
		}
		System.out.println("ettere = "+ ettere);
		System.out.println("toere = "+ toere);
		System.out.println("treere = "+ treere);
		System.out.println("firere = "+ firere);
		System.out.println("femmere = "+ femmere);
		System.out.println("seksere = "+ seksere);

	}




	@Test
	public void testSetValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testEquals() {
		fail("Not yet implemented");
	}

	@Test
	public void testClone() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString1() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotify() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotifyAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLongInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testWait() {
		fail("Not yet implemented");
	}

	@Test
	public void testFinalize() {
		fail("Not yet implemented");
	}

}
