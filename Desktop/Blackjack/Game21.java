import java.util.*; //imports the utility package to allow for user input
/**
 * This class definition holds the game logic for classic Blackjack, using simple rules.
 * 
 * @author (A. McNeely)
 * @version (12/10/2017)
 */
public class Game21
{
    public static void main(String[] args)
    {
        int PlayerWin=0; //initially player has won 0
        int DealerWin=0; //initially dealer has won 0
        char goAgain='n';
        do{
            String winner="";
            int Draw=0;
            int choice;
            int totalDealer=0;
            int totalPlayer=0;
            Card Top;
            Card card=new Card(0,"",""); //create a new intial card with intial values

            int value; //value Of Card;
            boolean dealerBool=true;
            boolean playerBool=true;
            Scanner keyboard=new Scanner(System.in); //create a keyboard input
            char user='n'; //the initial players choice is set as zero.
            int d=0, p=0; //initial indexes of dealer (d) and players(p) hands
            Card [] dealerArray=new Card[52]; //this is the array of the dealer array
            Card [] playerArray=new Card[52]; //this is the player hand
            int [] dealerValueArray=new int[52]; // this is to store the value of the cards (to add up later)
            int [] playerValueArray=new int [52]; //store the value of the cards (to add up later)

            Deck deck=new Deck(); //create a deck
            while(totalDealer<17) // go through the loop until the dealer gets 17 or over, then stop
            {
                deck.Shuffle(); // shuffle the deck
                Top=deck.getTopCard(); //get the top card
                dealerBool=Top.checkCard(Top,dealerArray,playerArray,p,d); //check if the card is in the either hand
                if(dealerBool==false) //if it is not, go ahead and put it in the dealers hand
                {
                    dealerArray[d]=Top; //put it in the dealer's hand
                    if(totalDealer<=10 && Top.cardValue(Top)==1)
                        dealerValueArray[d]=11; // Dealer Chooses 11 if its total at that point is less than equal to 10
                    else
                        dealerValueArray[d]=Top.cardValue(Top); //otherwise the dealer picks 1.
                    totalDealer=totalDealer+dealerValueArray[d]; //running total of the dealers hand
                    d++; //increment the index
                }
                else
                    continue; //if it is, then go loop back up
            }
            System.out.println("Dealers First Card: " +dealerArray[0]);
            System.out.println("");
            deck.Shuffle();       // this is to give the player the first card
            while(playerBool)
            {
                deck.Shuffle(); //shuffle deck
                Top=deck.getTopCard(); //get top card
                playerBool=Top.checkCard(Top,dealerArray,playerArray,p,d); //check if its in the either hand
                if(playerBool==false)
                {
                    playerArray[p]=Top;   //store it at index 0
                    if(Top.cardValue(Top)==1) //if player gets an Ace, they get to choose if they want a 1 or 11
                    {
                        System.out.println("Player Card: "+playerArray[p]);
                        System.out.println("Do you want it to be a 1 or 11?"); //choose whether user wants 1 or 11
                        choice=keyboard.nextInt();
                        playerValueArray[p]=choice; //put the value into the array
                    }
                    else
                        playerValueArray[p]=Top.cardValue(Top); //if its not an Ace just put the value into the value array
                    p++; //increment the player's hand index
                }
                else
                    continue; //if the card is in either hand, go back to the stop of the loop.
            }
            playerBool=true; //reset the playerBool parameter to true so it goes into the next while loop
            while(playerBool) //the following code does exactly the same thing as the previous while loop but for the second card
            {
                deck.Shuffle(); //shuffle the deck again
                Top=deck.getTopCard(); //get the top card
                playerBool=Top.checkCard(Top,dealerArray,playerArray,p,d);
                if(playerBool==false) //if it isn't in either hand, put it into the player hand
                {
                    playerArray[p]=Top; // put it in the players hand (now index 1)
                    if(Top.cardValue(Top)==1)
                    {
                        System.out.println("Player Card: "+playerArray[p]);
                        System.out.println("Do you want it to be a 1 or 11\n");
                        choice=keyboard.nextInt();
                        playerValueArray[p]=choice;
                    }
                    else
                        playerValueArray[p]=Top.cardValue(Top); // put the value into the players value array
                }
                else
                    continue;
            }
            for(int i=0; i<p+1;i++) // display the first two card
            {
                System.out.println("Player Card is: "+playerArray[i]); //display the players hand
                //System.out.println(playerValueArray[i]); // displaye the value of the players hand
            }
            for(int i=0;i<p+1;i++) //add up the total of the first two cards
            {
                totalPlayer=totalPlayer+playerValueArray[i]; //add up the value array to get total
            }
            System.out.println("\nTotal for User is: "+totalPlayer); //show the user the total right now
            System.out.println("\nDo you want another card?");
            user=keyboard.next().charAt(0); //user enters if he or she wants to go again
            p++; //increment the player index
            totalPlayer=0; //re set the total to 0 before entering the loop
            while(user=='y' || user=='Y') // while loop to keep looping until user decides to quit
            {
                playerBool=true;
                while(playerBool)
                {
                    deck.Shuffle();      //shuffle
                    Top=deck.getTopCard(); //get top card
                    playerBool=Top.checkCard(Top,dealerArray,playerArray,p,d); //check if its in either hand
                    if(playerBool==false) //if it isn't in eithe hand but the card into the player hand
                    {
                        playerArray[p]=Top; //put top card in players hand
                        if(Top.cardValue(Top)==1)
                        {
                            System.out.println("Player Card: "+playerArray[p]); //show the card that was drawn (which is an ace)
                            System.out.println("Do you want it to be a 1 or 11\n"); //choose between 1 or 11 for value
                            choice=keyboard.nextInt();
                            playerValueArray[p]=choice;
                        }
                        else
                            playerValueArray[p]=Top.cardValue(Top); //get the value of card and put in playervalue array
                        p++;
                    }
                }
                for(int i =0 ; i<p;i++)
                {
                    System.out.println(playerArray[i]); //print the player array
                }
                System.out.println("\nDo you want to another card?"); //does the user want another card
                user=keyboard.next().charAt(0);
            }
            for(int i=0;i<p+1;i++)
            {
                totalPlayer=totalPlayer+playerValueArray[i]; //get total or player array
            }
            System.out.println("\nThe total for user is: "+totalPlayer); //display total
            System.out.println("\nThe Dealers hand is: ");
            for(int i=0; i<d;i++)
            {
                System.out.println(dealerArray[i]);
            }
            System.out.println("\nThe total for Dealer is: "+totalDealer);
            winner=deck.Winner(totalPlayer,totalDealer); //enter the total of both dealer and player to determine winner
            System.out.println(winner); //print who the WINNER is
            if(winner=="\nPlayer wins!")
            {
                PlayerWin++; //increment players number of wins if player wins
            }
            else if(winner=="\nDealer wins!")
            {
                DealerWin++; //incrment dealers number of wins if dealer wins
            }
            System.out.println("\nThe Player has won: "+PlayerWin+" times"); //print how many player won
            System.out.println("The Dealer has won: "+DealerWin+" times"); //print how many dealer won
            System.out.println("\nType 'y' or 'Y' to play again");
            goAgain=keyboard.next().charAt(0); //user chooses if he/she wants to play game all over again
        }while(goAgain=='y' || goAgain=='Y');     // user chooses y or Y to play again
    }
}