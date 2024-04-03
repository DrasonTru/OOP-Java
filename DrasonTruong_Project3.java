package projects;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/*
 * Drason Truong
 * ITSS 3312.001
 * Project 3
 * Create a program to play 4 Dice Poker using exception handling for inputs
 * User will be prompted with instructions on how to play, to enter name, how many games to play, and how to start game
 * System will roll 4 dice and evaluate hand
 * User can swap out dice and re-roll in order to get a better hand
 * System will tell user if they have a winning hand (4 of a kind, 3 of a kind, two pairs, a pair, or a straight)
 * System will play designated amount of games and display results of all previous games
 * Player is thanked for playing and invited to play again
 */

public class DrasonTruong_Project3 { // driver class, should have no variables

	public static void main(String[] args) {
	
		// create objects for classes
		Hands3 hands = new Hands3();
		Session3 session = new Session3();
		Poker_EH poker = new Poker_EH();
		Scanner input = new Scanner(System.in); 
		
		// Instructions
		System.out.println("4-Dice_Draw_Poker is a poker game that uses dice instead of cards.");
		System.out.println("The computer will roll 4 dice and then identify the best hand.");
		System.out.println("The player can draw (re-roll) up to 3 dice to improve their hand.");
		System.out.println("Possible hands include 4-of-a-kind, 3-of-a-kind, 2-Pair, Pair, or a Straight");
		System.out.println();
		System.out.print("Enter your name: ");
		session.setName(); // uses session getName method to save name for later use
		System.out.println("Hi " + session.getName() + ". Welcome to 4-Dice Draw Poker!");
		System.out.println();
		session.setNumberOfGames(); // uses session numberOfGames to keep track of games
		System.out.println();
		System.out.print("Press any letter-key and then enter to start the game and roll the dice.");
		System.out.println();
		input.next();    
		// End of Instructions 
		session.setResultsSize(); // after number of games is set, sets size of display arrays
		session.setHandArraySize();
		
		do { // Start of Game
			// Initial Hand
			hands.setDice(); // System sets array of 4 random values for each dice hands.controlDice();
			poker.pauseGame(); // pauses program to simulate rolling of the dice
			session.printArray(hands.getDice(), hands.assessHand(1)); // value 1 = true (Initial)
			System.out.println();
			hands.setReroll(); // asks for number of dice to re-roll
			if (hands.getReroll() > 0) { // don't use re-roll method if not necessary  
				// Final Hand
				hands.rerollArray(hands.getDice(), hands.getReroll()); // method to re-roll values 
				System.out.println();
				poker.pauseGame();
				session.printArray(hands.getDice(), hands.assessHand(0)); // value 0 = false (Final)
			}
			session.setHandArray(); // evaluate hand here
			session.setResults(hands.getDice()); // save results here
			session.incrementIndex(); // index++;
			session.incrementGameCounter(); // gameCounter++;
		} while (session.gameCounter() < session.getNumberOfGames()); // End of Game
		
		// Ending Message
		session.prtResults(session.getResults(), session.getHandArray()); // 
		System.out.println();
		System.out.println("Thanks for playing, " + session.getName() + ". Play again soon!");
		input.close();
	} // End of Main Method 
	
} // end of Driver Class

class Hands3 { // Class Hands - contains methods to evaluate hands 
	
	// Instantiate Object
	Poker_EH poker = new Poker_EH();
	// declare variables
	private int [] diceArray = new int[4];
	private boolean initialHand; // tells system if initial hand or final hand
	private int reroll; // tests if method re-roll should be used
	
	// TEST METHOD: Control values of array
	public int[] controlDice() {
		int[] array = {1, 2, 3, 4};
		return array;
	}
	
	// method setDice()
	public void setDice() {
		Random random = new Random();
		// create array, use for loop to initialize random values between 1 and 6
		for (int i = 0; i < diceArray.length; i++) {
			diceArray[i] = random.nextInt(6) + 1;
		}
		Arrays.sort(diceArray);
	}
	
	// method to get random dice values in an array
	public int[] getDice() {
		return diceArray;
	}
	
	// method 4 of a kind
	public boolean is4Kind(int[] array) {
		int testNumber; // static test number to compare, value will always be same
		testNumber = array[0]; 
		int count = 0; // counting instances of testNumber
		
		for (int index = 0; index < array.length; index++) { // loop to test each value inside array
			int indexNumber;
			indexNumber = array[index];
			if (testNumber == indexNumber) count++; // if all values match the first number in array then count=4
		}
		if (count == 4) { // if there is a 4 of a Kind (4 instances of testNumber)
			return true;
		}
		// if there is not a 4 of a Kind will return false	
		return false;
	}
	
	// method 3 of a kind
	public boolean is3Kind(int[] array) {
		if (array[0] == array[3]) { // to prevent triggering on 4 of a Kind, works logically because array should be sorted
			return false;
		}
		for (int testNumber = 0; testNumber < array.length; testNumber++) { // test each value of array with other values
			int count = 0;
			
			for (int indexNumber = 0; indexNumber < array.length; indexNumber++) {
				if (array[testNumber] == array[indexNumber]) count++;
				if (count == 3) { // if there are three instances of testNumber there is a 3 of a Kind
					return true;
				}
			}	
		}
		return false; // if there is not a 3 of a kind, will return false
	}
	
	// method 2 pairs
	public boolean is2Pair(int[] array) {
		// true if (value at index 0 == value at index 1) && (value at index 2 == value at index 3), works logically because array should be sorted
		// if is2Pair is true, 1 pair must be false
		int count = 0; // count number of pairs
		int testPair1; // tests if first value matches second value, index [0,1]
		testPair1 = array[0];
		if (testPair1 == array[1]) {
			count++;
		}
		int testPair2; // tests if third value matches fourth value, index [2,3]
		testPair2 = array[2];
		if (testPair2 == array[3]) {
			count++;
		}
		if (count == 2 && array[0] != array[3]) { // 2 distinct pairs to prevent triggering on 4 of a kind
			return true; // if there are two pairs, will return true
		}
		return false; // if not two pairs will return false
	}
	
	// method 1 pair
	public boolean isPair(int[] array) {
		// instance of a value is == to a value at index +or- 1
		// if 4/3ofaKind, or 2Pairs are true, 1pair must be false
		int matchingNumber = 0;
		for (int testNumber = 0; testNumber < array.length; testNumber++) { // test values in array with other values in array
			int count = 0;
			for (int indexNumber = 0; indexNumber < array.length; indexNumber++) {
				if (array[testNumber] == array[indexNumber]) count++;
			}
			if (count == 2) matchingNumber++; // 2 matching numbers makes 1 pair
			}
		if (matchingNumber == 2) { // only 2 numbers match in the array making 1 pair
			return true;
		}
		return false;
	}
	
	// method isStraight
	public boolean isStraight(int[] array) {
		// values are +1 from each other, value at index[3] - value at index[0] = 3. ALL must be true
		if (array[0] + 1 == array[1] && array[1] + 1 == array[2] && array[2] + 1 == array[3] && array[3] - array[0] == 3) {
			return true;
		} // if numbers are not a straight, will return false
		return false;
	}
	
	// method setReroll, number of dice to reroll
	public void setReroll() {
		poker.setEntry("Enter the number of dice to draw (reroll): ", 0, 3, "Error: value must be 0-3. Try Again ");
		reroll = poker.getEntry();
	}
	
	// method getReroll
	public int getReroll() {
		return reroll;
	}
	
	// method to re-roll
	public int[] rerollArray(int[] array, int reroll) {
		Random random = new Random();
		int[] newArray = new int[4];
		newArray = array;
		// for how many number of re-rolls, that many numbers will be replaced
		// user gets to choose which index to replace, one value at a time
		if (reroll == 1) {
			int indexX;
			poker.setEntry("Enter the index of the dice to reroll: ", 0, 3, "Error: value must be 0-3. Try Again ");
			indexX = poker.getEntry();
			newArray[indexX] = random.nextInt(6) + 1;
		}
		if (reroll == 2) {
			int indexX, indexY;
			poker.setEntry("Enter the index of the dice to reroll: ", 0, 3, "Error: value must be 0-3. Try Again ");
			indexX = poker.getEntry();
			poker.setEntry("Enter the index of the dice to reroll: ", 0, 3, "Error: value must be 0-3. Try Again ");
			indexY = poker.getEntry();
			newArray[indexX] = random.nextInt(6) + 1;
			newArray[indexY] = random.nextInt(6) + 1;
		}
		if (reroll == 3) {
			int indexX, indexY, indexZ;
			poker.setEntry("Enter the index of the dice to reroll: ", 0, 3, "Error: value must be 0-3. Try Again ");
			indexX = poker.getEntry();
			poker.setEntry("Enter the index of the dice to reroll: ", 0, 3, "Error: value must be 0-3. Try Again ");
			indexY = poker.getEntry();
			poker.setEntry("Enter the index of the dice to reroll: ", 0, 3, "Error: value must be 0-3. Try Again ");
			indexZ = poker.getEntry();
			newArray[indexX] = random.nextInt(6) + 1;
			newArray[indexY] = random.nextInt(6) + 1;
			newArray[indexZ] = random.nextInt(6) + 1;
		}
		Arrays.sort(newArray);
		return newArray;
	}
	
	// method assessHand
	public boolean assessHand(int x) {
		if (x == 1) {
			initialHand = true;
		}
		if (x == 0) {
			initialHand = false;
		}
		return initialHand;
	}
	
} // end of Class Hands

class Session3 { // Class Session - contains user specific items (name, number of games, results of games) 
	
	// create object for classes
	Hands3 hands = new Hands3();
	Poker_EH poker = new Poker_EH();
	// declare variables
	private boolean is4Kind, is3Kind, is2Pair, isPair, isStraight;
	private String name;
	private int numberOfGames;
	private int gameCounter = 0;
	private int[][] resultArray; // array of number results
	private int index = 0;
	private String [] evaluateHand; // array of hand evaluation
	
	// method print array
	public void printArray(int[] array, boolean hand) {
		// hand distinguishes if this is initial or final hand
		System.out.println();
		if (hand == true) {
			System.out.println("   ******* Game " + (gameCounter + 1) + " INITIAL HAND *******");
		}
		if (hand == false) {
			System.out.println("   ******* Game " + (gameCounter + 1) + " FINAL HAND ********");
		}
		System.out.println(" Roll   4Kind 3Kind 2Pair Pair Straight");
		System.out.println("------  ----- ----- ----- ---- --------");
		for (int z : array) System.out.print(z + " ");
		// Printing results
		is4Kind = hands.is4Kind(array);
		is3Kind = hands.is3Kind(array);
		is2Pair = hands.is2Pair(array);
		isPair = hands.isPair(array);
		isStraight = hands.isStraight(array);
		System.out.printf("%.5s %.5s %.5s %.5s %.5s", is4Kind, is3Kind, is2Pair, isPair, isStraight);
		System.out.println();
		System.out.println();
	}
	
	// method setName - setter method for keeping name
	public void setName() {
		Scanner input = new Scanner(System.in);
		name = input.next();
	}
	
	// method getName - getter method to call on name
	public String getName() {
		return name;
	}
	
	// method gameCounter - keeps running count of current game number
	public int gameCounter() {
		return gameCounter;
	}
	
	// method incrementGameCounter - increments to keep accurate total of game numbers
	public void incrementGameCounter() {
		gameCounter++;
	}
	
	// method setNumberOfGames - setter method for keeping desired number of games to play
	public void setNumberOfGames() {
		poker.setEntry("Enter the number of games you would like to play: ", 1, 9, "Error: values must be 1-9. Try Again");
		numberOfGames = poker.getEntry();
	}
	
	// method getNumberOfGames - getter method for calling on desired number of games
	public int getNumberOfGames() {
		return numberOfGames;
	}
	
	// method setResults (save results) 
	public void setResults(int[] diceArray) { // reads in getDice()
		for (int i = 0; i < resultArray.length; i++) { // rows
			for (int j = 0; j < resultArray[i].length; j++) {
				if (i == index) {
					resultArray[i][j] = diceArray[j];		
				}
			}	
		}
	}	
	
	// method setResultsSize - keeps record of numbers in hand of each game, uses numberOfGames to create correct array size
	public void setResultsSize() {
		resultArray = new int[numberOfGames][4];
	} // only used once before game start
	
	// method getResults - getter method to call upon results
	public int[][] getResults() {
		return resultArray;
	} // only used once after game end as method prtResults(getResults()) parameter
	
	// method prtResults
	public void prtResults(int[][] numberArray, String[] handArray) { // reads in getResults([i][j]resultArray) and getHandArray([]evaluateResults)
		int count = 1; // acts as game counter
		System.out.println();
		System.out.println("Game Session Results");
		for (int i = 0; i < numberArray.length; i++) {
			System.out.print("Game " + count + ": ");
			count++;
			for (int j = 0; j < numberArray[i].length; j++) {
				System.out.printf("%2d", numberArray[i][j]);
			} 
			System.out.print(handArray[i]);
			System.out.println();
		}
	}
	
	// method incrementIndex - increment index in array 
	public void incrementIndex() {
		index++;
	}
	
	// method setHandArray - evaluates results of hand and stores in array
	public void setHandArray() {
		if (is4Kind) {
			evaluateHand[index] = " - 4 of a Kind";
		} else if (is3Kind) {
			evaluateHand[index] = " - 3 of a Kind";
		} else if (is2Pair) {
			evaluateHand[index] = " - 2 Pairs";
		} else if (isPair) {
			evaluateHand[index] = " - Pair";
		} else if (isStraight) {
			evaluateHand[index] = " - Straight";
		} else {
			evaluateHand[index] = "";
		}
	}
	
	// method getHandArray - calls upon result array
	public String[] getHandArray() {
		return evaluateHand;
	}

	// method hand evaluation array size - sets size of array using numberOfGames
	public void setHandArraySize() {
		evaluateHand = new String[numberOfGames];
	}
	
}

class Poker_EH {
	// declare vars and instantiate object
	private int value; // value is constantly changed each time it is called upon.
	Scanner input = new Scanner(System.in);
	
	public int setEntry(String prompt, int min, int max, String errMsg) { // save player input, checks to see if valid, prints error message to console
		boolean tryAgain = true;
		do {
			System.out.print(prompt);
			try {
				String line = input.nextLine(); // if string is input, only takes first character
				String[] lineSplit = line.split(" ");
				value = Integer.parseInt(lineSplit[0]);
				tryAgain = false; // if value is valid (int) program will move on
				
				if (value < min || value > max) {
					tryAgain = true;
					throw new IllegalArgumentException();
				}
			} 
			catch (IllegalArgumentException iae) {
				System.out.println(iae);
				System.out.println(errMsg);
			}
		} while(tryAgain);
		return value;
	}
	
	public int getEntry() { // calls on valid value from setEntry
		return value;
	}

	public void pauseGame() { // emulates the rolling of the dice
		System.out.println("Rolling the dice... ");
		try {
			TimeUnit.SECONDS.sleep(2); // 2 second pause
		} 
		catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
	}
	
}
