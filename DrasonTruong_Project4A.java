package projects;

import java.util.Arrays;
import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DrasonTruong_Project4A extends Application {
	// Project 4B
	// ITSS 3312.001
	// Create GUI for 4 Dice Poker, include event handling
	
	// Instantiate Objects
	Hands hands = new Hands();
	Session session = new Session();
	
	@Override
	public void start(Stage primaryStage) {
		
		// Labels
		Label lHeader = new Label("4 Dice Poker!  "); lHeader.setFont(new Font("Trebuchet MS", 36));
		Label lName = new Label("Name:"); lName.setFont(new Font("Trebuchet MS", 20));
		Label lDice = new Label("Dice:"); lDice.setFont(new Font("Trebuchet MS", 20));
		Label lDice2 = new Label("Dice:"); lDice2.setFont(new Font("Trebuchet MS", 20));
		Label lDraw = new Label("Draw:"); lDraw.setFont(new Font("Trebuchet MS", 20));
		Label lHand = new Label("null"); lHand.setFont(new Font ("Trebuchet MS", 20));
		Label lHand2 = new Label("null"); lHand2.setFont(new Font ("Trebuchet MS", 20));
		
		// Text Field
		TextField tfName = new TextField();
		tfName.setMaxWidth(120);
		
		// Buttons
		Button firstRollButton = new Button(" Click Here to Roll Dice ");
		firstRollButton.setStyle("-fx-base: tomato");
		
		Button rerollButton = new Button(" Click Here to Re-Roll   ");
		rerollButton.setStyle("-fx-base: tomato");
	
		Button playAgain = new Button("Click Here to Play Again");
		playAgain.setStyle("-fx-base: tomato");
		
		// Images
		ImageView iv = new ImageView("https://thumbs.gfycat.com/ElatedImpartialArmadillo-max-1mb.gif");
		iv.setFitHeight(200);
		iv.setFitWidth(200);
		
		ImageView iv2 = new ImageView("https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/Two_red_dice_01.svg/1024px-Two_red_dice_01.svg.png");
		iv2.setFitHeight(100);
		iv2.setFitWidth(100);
		
		ImageView iv3 = new ImageView("https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/Two_red_dice_01.svg/1024px-Two_red_dice_01.svg.png");
		iv3.setFitHeight(100);
		iv3.setFitWidth(100);
		
		// Hboxes
		Label lValue1 = new Label("0"); lValue1.setFont(new Font("Trebuchet MS", 20));
		Label lValue2 = new Label("0"); lValue2.setFont(new Font("Trebuchet MS", 20));
		Label lValue3 = new Label("0"); lValue3.setFont(new Font("Trebuchet MS", 20));
		Label lValue4 = new Label("0"); lValue4.setFont(new Font("Trebuchet MS", 20));
		HBox hboxValues = new HBox(lValue1, lValue2, lValue3, lValue4);
		hboxValues.setSpacing(25);
		
		Label lValue5 = new Label("0"); lValue5.setFont(new Font("Trebuchet MS", 20));
		Label lValue6 = new Label("0"); lValue6.setFont(new Font("Trebuchet MS", 20));
		Label lValue7 = new Label("0"); lValue7.setFont(new Font("Trebuchet MS", 20));
		Label lValue8 = new Label("0"); lValue8.setFont(new Font("Trebuchet MS", 20));
		HBox hboxValues2 = new HBox(lValue5, lValue6, lValue7, lValue8);
		hboxValues2.setSpacing(25);
		
		// Radio Buttons
		RadioButton rb1 = new RadioButton(); rb1.setStyle("-fx-base: tomato");
		RadioButton rb2 = new RadioButton(); rb2.setStyle("-fx-base: tomato");
		RadioButton rb3 = new RadioButton(); rb3.setStyle("-fx-base: tomato");
		RadioButton rb4 = new RadioButton(); rb4.setStyle("-fx-base: tomato");
		HBox hboxButtons = new HBox(rb1, rb2, rb3, rb4);
		hboxButtons.setSpacing(7);
		
		// Grid Panes
		GridPane grid = new GridPane();
		grid.getChildren().addAll(lHeader, lName, tfName, firstRollButton, hboxButtons, lDice, lDraw, hboxValues, iv, rerollButton, lDice2, lHand, hboxValues2, playAgain, iv2, iv3, lHand2);
		grid.setPadding(new Insets(30, 20, 20, 20));
		grid.setHgap(15);
		grid.setVgap(40);
		
		// Grid Coordinates (listed top to bottom)
		grid.setConstraints(iv2, 1, 0);
		grid.setConstraints(lHeader, 2, 0); 
		grid.setConstraints(iv3, 3, 0); // 0
		grid.setConstraints(lName, 0, 1);
		grid.setConstraints(tfName, 1, 1);
		grid.setConstraints(iv, 2, 1); // 1
		grid.setConstraints(firstRollButton, 2, 2); // 2
		grid.setConstraints(lDice, 0, 3);
		grid.setConstraints(hboxValues, 1, 3);
		grid.setConstraints(lHand, 2, 3); // 3
		grid.setConstraints(lDraw, 0, 4);
		grid.setConstraints(hboxButtons, 1, 4); // 4
		grid.setConstraints(rerollButton, 2, 5); // 5
		grid.setConstraints(lDice2, 0, 6);
		grid.setConstraints(hboxValues2, 1, 6);
		grid.setConstraints(lHand2, 2, 6); // 6
		grid.setConstraints(playAgain, 2, 7); // 7
		
		Scene scene = new Scene(grid, 650, 850);
		scene.setFill(Color.AZURE);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		// END GUI
		// START EVENT HANDLING
		// ***************************************************
	
		tfName.setOnKeyPressed(e-> {
			String name;
			name = tfName.getText();
			session.setName(name);
		});
		
		firstRollButton.setOnAction(e-> {
			hands.setDice();
			lValue1.setText(hands.getDice0(hands.getDice()));
			lValue2.setText(hands.getDice1(hands.getDice()));
			lValue3.setText(hands.getDice2(hands.getDice()));
			lValue4.setText(hands.getDice3(hands.getDice()));
			session.setHandEval(hands.getDice());
			lHand.setText(session.getHandEval());
			rb1.requestFocus();
			hands.pressed();
		});
		
		rb1.setOnAction(e-> {hands.toggleRB1(); rerollButton.requestFocus(); });
		rb2.setOnAction(e-> {hands.toggleRB2(); rerollButton.requestFocus(); });
		rb3.setOnAction(e-> {hands.toggleRB3(); rerollButton.requestFocus(); });
		rb4.setOnAction(e-> {hands.toggleRB4(); rerollButton.requestFocus(); });
		
		rerollButton.setOnAction(e-> { 
			if(hands.getPressed()) {
				hands.rerollArray(hands.getDice());
				playAgain.requestFocus();
				lValue5.setText(hands.getDice0(hands.getRerollArray()));
				lValue6.setText(hands.getDice1(hands.getRerollArray()));
				lValue7.setText(hands.getDice2(hands.getRerollArray()));
				lValue8.setText(hands.getDice3(hands.getRerollArray()));
				session.setHandEval(hands.getRerollArray());
				lHand2.setText(session.getHandEval());
			}
		});
		
		playAgain.setOnAction(e-> { // when player presses "Play Again" whole game is reset to default values
			if(hands.getPressed()) {
				session.printResults(hands.getRerollArray());
			} else {
				session.printResults(hands.getDice());
			}
			lValue1.setText("0");
			lValue2.setText("0");
			lValue3.setText("0");
			lValue4.setText("0");
			lValue5.setText("0");
			lValue6.setText("0");
			lValue7.setText("0");
			lValue8.setText("0");
			rb1.setSelected(false);
			rb2.setSelected(false);
			rb3.setSelected(false);
			rb4.setSelected(false);
			lHand.setText("null");
			lHand2.setText("null");
			firstRollButton.requestFocus();
			hands.clearRB();
			session.incrementGameCounter();
		});
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}

class HandsA { // Class Hands - contains methods to evaluate hands 
	
	// declare variables
	private int [] diceArray = new int[4];
	private boolean rb1_on, rb2_on, rb3_on, rb4_on, pressed = false;
	private int[] newArray = new int[4];
	
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
	
	public String getDice0(int[] array) {
		String name;
		name = String.valueOf(array[0]);
		return name;
	}
	
	public String getDice1(int[] array) {
		String name;
		name = String.valueOf(array[1]);
		return name;
	}
	
	public String getDice2(int[] array) {
		String name;
		name = String.valueOf(array[2]);
		return name;
	}
	
	public String getDice3(int[] array) {
		String name;
		name = String.valueOf(array[3]);
		return name;
	}
	
	// method 4 of a kind
	public boolean is4Kind (int[] array) {
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
	public boolean is3Kind (int[] array) {
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
	public boolean is2Pair (int[] array) {
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
	public boolean isPair (int[] array) {
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
	public boolean isStraight (int[] array) {
		// values are +1 from each other, value at index[3] - value at index[0] = 3. ALL must be true
		if (array[0] + 1 == array[1] && array[1] + 1 == array[2] && array[2] + 1 == array[3] && array[3] - array[0] == 3) {
			return true;
		} // if numbers are not a straight, will return false
		return false;
	}
	
	// method to re-roll
	public void rerollArray (int[] array) {
		Random random = new Random();
		newArray = array;
		if(rb1_on) {
			newArray[0] = random.nextInt(6) + 1;
 		}
		if(rb2_on) {
			newArray[1] = random.nextInt(6) + 1;
		}
		if(rb3_on) {
			newArray[2] = random.nextInt(6) + 1;
		}
		if(rb4_on) {
			newArray[3] = random.nextInt(6) + 1;
		}
		Arrays.sort(newArray);
	}
	
	public int[] getRerollArray () {
		return newArray;
	}
	
	//methods to toggle radio buttons
	public boolean toggleRB1() {
		if(rb1_on == false) {
			rb1_on = true;
		} 
		else {
			rb1_on = false;
		}
		return rb1_on;
	}
	
	public boolean toggleRB2() {
		if(rb2_on == false) {
			rb2_on = true;
		} 
		else {
			rb2_on = false;
		}
		return rb2_on;
	}
	
	public boolean toggleRB3() {
		if(rb3_on == false) {
			rb3_on = true;
		} 
		else {
			rb3_on = false;
		}
		return rb3_on;
	}
	
	public boolean toggleRB4() {
		if(rb4_on == false) {
			rb4_on = true;
		} 
		else {
			rb4_on = false;
		}
		return rb4_on;
	}
	
	// method clear radio buttons
	public void clearRB() {
		rb1_on = false;
		rb2_on = false;
		rb3_on = false;
		rb4_on = false;
	}
	
	public boolean getPressed() {
		return pressed;
	}
	
	public void pressed() {
		pressed = true;
	}
	
} // end of Class Hands

class SessionA { // Class Session - contains user specific items (name, number of games, results of games) 
	
	// create object for classes
	Hands hands = new Hands();
	
	// declare variables
	private String name;
	private int gameCounter = 1;
	private String handEval;
	
	// method print results
	public void printResults(int[] array) {
		System.out.print(gameCounter + ": " + name + " ");
		
		for(int i = 0; i < array.length; i++) {
				System.out.print(array[i] + " ");
		}
		System.out.print(handEval);
		System.out.println();
	}
	
	// method setName - setter method for keeping name
	public void setName(String name) {
		this.name = name;
	}
	
	// method incrementGameCounter - increments to keep accurate total of game numbers
	public void incrementGameCounter() {
		gameCounter++;
	}
	
	// method set handEval
	public void setHandEval(int[] array) {
		if (hands.is4Kind(array)) {
			handEval = " - 4 of a Kind";
		} else if (hands.is3Kind(array)) {
			handEval = " - 3 of a Kind";
		} else if (hands.is2Pair(array)) {
			handEval = " - 2 Pairs";
		} else if (hands.isPair(array)) {
			handEval = " - Pair";
		} else if (hands.isStraight(array)) {
			handEval = " - Straight";
		} else {
			handEval = "";
		}
	}
	
	// method get handEval
	public String getHandEval() {
		return handEval;
	}
	
}