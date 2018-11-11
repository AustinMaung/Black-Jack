

import java.util.Scanner;
import java.util.Random;

public class BlackJack_Part_1 {

	public static int rand(int begin, int end)
	// how the cards will be determined
	{
		//not math.Random() but still works
		Random rand = new Random();
		
		int n = rand.nextInt(end) + begin; /* finds random number from 0 to end, 
		so u have to add begin to get random numbers from begin to end(1 - 11)*/
		return n; // returns a random number
	}
	
	public static void stay(int score,int d_score,int d_card_1) // when someone stays. score and d_score are parameters
	//essentially variables without value. like count = 0 and how u change that value
	{
		System.out.println("The dealer's first card was: " + d_card_1);
		if (d_score < 16) 
		{
			System.out.print("The rest of his cards were: " );
		}
		//adds new cards to dealer till he reaches 16 or higher
		while (d_score < 16) 
		{
			int next_card = hit(); // changes next_card to the new value of hit, 
			System.out.println(next_card);
			d_score += next_card;
		}	
		if (d_score > 21) // checks first if the dealer got more than 21 = insta win
		{
			System.out.println("The dealer had a score of: " + d_score + " congradulations you win");
		}
		
		else if (score > d_score) // else if your score is higher = win
		{
			System.out.println("The dealer's total was: " + d_score);
			System.out.println("nice you beat the dealer");
		}
		else if (score < d_score) // else if the dealer's higher = you lose
		{ 
			System.out.println("his total is: " + d_score);
			System.out.println("Your number was less than the dealer, you lose .. try again next time!");
		}
		else if (score == d_score) // else if u guys are even, dealer wins
		{
			System.out.println("The dealer's total was: " + d_score);
			
			System.out.println("you guys tied, meaning that dealer wins, gl next time!");
		}
	}
	public static int hit() // adds value
	{
		int new_card = rand(1,11); // 1 and 11 are arguments,inputs 1 to begin and 11 to end
		return new_card; // returns random value from 1 to 11
		
	}
	
	public static boolean run(boolean game_on) // lets players hit as much as they want or stay
	// I changed the method to return boolean as I wanted to change the value of game_on, explained in the main method
	{
		//variables
		Scanner read = new Scanner(System.in);
		int card_1 = rand(1,11); // 1 and 11 are arguments
		int card_2 = rand(1,11);
		int total = card_1 + card_2;
		int next_card = 0; // like count = 0 example, you'll change the value of next_card by hit()
		// DO NOT have next_card = hit(), if you do this then next card will be a random value but it wont change
		
		//dealer variables
		int d_card_1 = rand(1,11);
		int d_card_2 = rand(1,11);
		int d_total = d_card_1 + d_card_2;
		
		//cards go to the player
		System.out.println("your first card dealt is "  + card_1 + " and your second card dealt is " + card_2);
		
		//checks if you got black jack in your first hand which is 11 and 10
		if (d_total == 21) 
		{
			System.out.println("DEALER GOT BLACKJACK, DANG SORRY MATE");
			return false;
		}
		else if (total == 21 ) 
		{
			System.out.println("YOU GOT BLACKJACK, NICE ONE MATE");
			return false;
		}
		
		System.out.println("Here is your current total: " + total);
		
		//shows dealers 2nd card
		System.out.println("The dealer has one hidden card but his second one is: " + d_card_2);
		
				
		//asks player to hit or stand
		System.out.println("do you want to hit or stay");
		String run = read.next();
		
		//if person types in the wrong thing , && is the and operator checks if both conditions are true
		while (run.compareTo("hit") != 0 && run.compareTo("stay") != 0) 
		{
			System.out.println("Please type in hit or stay properly");
			run = read.next();
		}
		
		//adds in the new card to the players total, compare it to the dealer's total, and determines who wins
		while (run.compareTo("hit") == 0 ) 
		{
			next_card = hit();
			System.out.println("Your next card is " + next_card);
			total += next_card;
			
			if (total > 21 ) 
			{
				System.out.println("You got more than 21, you busted .. try again next time!");
				game_on = false; // game_on explained in the main method
				break; // used to get out of the while loop
			}
			
			System.out.println("Here is your current total: " + total);
			
			System.out.println("do you want to hit or stay");
			run = read.next();
			
			// checks if the person writes in the wrong thing but now in a while loop
			while (run.compareTo("hit") != 0 && run.compareTo("stay") != 0) 
			{
				System.out.println("Please type in hit or stay properly");
				run = read.next();
			}
		}
		
		if (run.compareTo("stay") == 0) // if they type stay instead
		{
			stay(total, d_total, d_card_1); // total and d_total are arguments for stay, inputs the value of total and d_total
		}
		
		return game_on = false; // this code dosen't actually happen, eclipse just asks to return something
	}
	public static void main(String[] args)/* main method is essentially just asks if the person want to play or not,
	if yes, then run(), in no then stop the code */
	{
		System.out.println("Welcome to Austin's Blackjack Casino! ");
		
		while(true)	// keeps the person playing the game, main loop
		{
			
			System.out.println("Would you like to play Blackjack against me? yes or no");
			Scanner read = new Scanner(System.in);
			
			String run = read.next();
			boolean game_on = false;
			
			if (run.compareTo("yes") == 0 ) // if they type in yes
			{
				game_on = true;
			}
			while (game_on == true) // when game on is true, run the game with the value of game on, which is true
			/* when the person plays the game, the value of game_on will eventually reach false when the game stops, which forces them out of this loop
			 goes to the next loop, asking them if they want to play again or not. 
			 you can have a loop in a loop */
			{
				game_on = run(game_on);	
			}
			if (run.compareTo("no") == 0) /* if they type in no, then break out of the first while loop,
			stopping the code from asking them if the want to play or not */
			{
				break;
			}
		}
	}

}