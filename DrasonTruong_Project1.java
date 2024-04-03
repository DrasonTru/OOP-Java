package projects;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/*
 * Drason Truong
 * ITSS 3312.001
 * Project 1
 * Create a program to play 4 Dice Poker
 * User will be prompted with instructions on how to play, to enter name, and to start game
 * System will roll 4 dice and evaluate hand
 * User can swap out dice and re-roll in order to get a better hand
 * System will tell user if they have a winning hand (4 of a kind, 3 of a kind, two pairs, a pair, or a straight)
 * Player is thanked for playing and invited to play again
 */

public class DrasonTruong_Project1 {

	public static void main(String[] args) {
	
		// declare variables
		int [] diceArray = new int[4];
		boolean hand; // tells system if initial hand or final hand
		Scanner input = new Scanner(System.in); // reads in user name using imported scanner
		int reroll; // tests if method re-roll should be used
		
		// Instructions
		System.out.println("4-Dice_Draw_Poker is a poker game that uses dice instead of cards.");
		System.out.println("The computer will roll 4 dice and then identify the best hand.");
		System.out.println("The player can draw (re-roll) up to 3 dice to improve their hand.");
		System.out.println("Possible hands include 4-of-a-kind, 3-of-a-kind, 2-Pair, Pair, or a Straight");
		System.out.println();
		System.out.println("Enter your name: ");
		String name; // saves name for ending message
		name = input.next();
		System.out.println("Hi " + name + ". Welcome to 4-Dice Draw Poker!");
		System.out.println();
		System.out.println("Press any letter-key and then enter to start the game and roll the dice.");
		input.next();
		// End of Instructions 
		
		// System gets array of 4 random values for each dice
		diceArray = getDice(); 
		// diceArray = controlDice(); // CONTROL ******************
		
		// Initial Hand 
		hand = true; // true == initial hand
		printArray(diceArray, hand);
		
		// Instructions for re-roll
		System.out.println("Enter the number of dice to draw (re-roll) - up to 3:");
		reroll = input.nextInt();
		if (reroll > 0) { // don't use re-roll method if not necessary
			System.out.println("Enter the index numbers (0,1,2,3) separated by a space of the dice you wish to draw:");
			rerollArray(diceArray, reroll);
			// Final Hand
			hand = false; // false == final hand
			printArray(diceArray, hand);
		}
		
		// Ending Message
		System.out.println("Thanks for playing, " + name + ". Play again soon!");
		input.close();
	} // End of Main Method *******************************************
	
	// TEST METHOD: Control values of array
	public static int[] controlDice() {
		int[] array = {1, 2, 3, 4};
		return array;
	}
	
	// method to get random dice values in an array
	public static int[] getDice() {
		int[] array = new int[4];
		Random random = new Random();
		
		// create array, use for loop to initialize random values between 1 and 6
		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(6) + 1;
		}
		Arrays.sort(array);
		return array;
	}

	// method print array
	public static void printArray(int[] array, boolean hand) {
		boolean is4Kind, is3Kind, is2Pair, isPair, isStraight;
		// hand distinguishes if this is initial or final hand
		if (hand == true) {
			System.out.println("     ******* INITIAL HAND *******     ");
		}
		if (hand == false) {
			System.out.println("     ******** FINAL HAND ********     ");
		}
		System.out.println(" Roll   4Kind 3Kind 2Pair Pair Straight");
		System.out.println("------  ----- ----- ----- ---- --------");
		for (int z : array) System.out.print(z + " ");
		
		// Printing results
		is4Kind = is4Kind(array);
		is3Kind = is3Kind(array);
		is2Pair = is2Pair(array);
		isPair = isPair(array);
		isStraight = isStraight(array);
		System.out.printf("%.5s %.5s %.5s %.5s %.5s", is4Kind, is3Kind, is2Pair, isPair, isStraight);
		System.out.println();
		System.out.println();
	}
	
	// method 4 of a kind
	public static boolean is4Kind (int[] array) {
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
	public static boolean is3Kind (int[] array) {
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
		// if there is not a 3 of a Kind, will return false
		return false;
	}
	
	// method 2 pairs
	public static boolean is2Pair (int[] array) {
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
		// if not two pairs, will return false
		return false;
	}
	
	// method 1 pair
	public static boolean isPair (int[] array) {
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
	
	// method straight
	public static boolean isStraight (int[] array) {
		// values are +1 from each other, value at index[3] - value at index[0] = 3. ALL must be true
		if (array[0] + 1 == array[1] && array[1] + 1 == array[2] && array[2] + 1 == array[3] && array[3] - array[0] == 3) {
			return true;
		} // if numbers are not a straight, will return false
		return false;
	}
	
	// method to re-roll
	public static int[] rerollArray (int[] array, int reroll) {
		Scanner input = new Scanner(System.in);
		Random random = new Random();
		int[] newArray = new int[4];
		newArray = array;
		// for how many number of re-rolls, that many numbers will be replaced
		// user gets to choose which index to replace. note: could use switch statement instead
		// note: could implement do while loop if number is out of bounds until player enters valid number
		if (reroll == 1) {
			int indexX;
			indexX = input.nextInt();
			newArray[indexX] = random.nextInt(6) + 1;
		}
		if (reroll == 2) {
			int indexX, indexY;
			indexX = input.nextInt();
			indexY = input.nextInt();
			newArray[indexX] = random.nextInt(6) + 1;
			newArray[indexY] = random.nextInt(6) + 1;
		}
		if (reroll == 3) {
			int indexX, indexY, indexZ;
			indexX = input.nextInt();
			indexY = input.nextInt();
			indexZ = input.nextInt();
			newArray[indexX] = random.nextInt(6) + 1;
			newArray[indexY] = random.nextInt(6) + 1;
			newArray[indexZ] = random.nextInt(6) + 1;
		}
		input.close();
		Arrays.sort(newArray);
		return newArray;
	}
	
}