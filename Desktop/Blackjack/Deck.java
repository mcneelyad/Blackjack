import java.util.*;//imports the utility pakage to allow for user input
/**
 * This class defintion file creates the Deck and performs operations on the cards in that deck
 * 
 * @author (A. McNeely)
 * @version (12/10/2017)
 */
public class Deck
{
    Card [] cardArray=new Card[52]; //array of cards
    private final int TOP_OF_DECK=0; //Top of deck index

    String suit, face;
    int value;
    int number =0;
    Random rand=new Random();
    /**
     * This constructor generates the deck by using a for loop and switch statements.
     */
    public Deck()
    { //generate the deck
        for(int i=0;i<52;i++)
        {
            int number=i%4;// suits repeat every 4
            switch(number) //this swtich number assignes the suit
            {
                case 0: suit="Hearts";
                    break;
                case 1: suit="Diamonds";
                    break;
                case 2: suit="Spades";
                    break;
                case 3: suit="Clubs";
                    break;
            }

            int number2=i%13; //numbers and faces repeat every 13
            switch(number2) //this switch assigned value and face to each integer
            {
                case 0: value= 1; face="Ace"; //Assign the value based on face of the card
                    break;
                case 1: value= 2; face="2";
                    break;
                case 2: value= 3; face="3";
                    break;
                case 3: value= 4; face="4";
                    break;
                case 4: value= 5; face="5";
                    break;
                case 5: value= 6; face="6";
                    break;
                case 6: value= 7; face="7";
                    break;
                case 7: value= 8; face="8";
                    break;
                case 8: value= 9; face="9";
                    break;
                case 9: value= 10; face="10";
                    break;
                case 10: value= 11; face="Jack"; //all face will = 10
                    break;
                case 11: value= 12; face="Quean";
                    break;
                case 12: value= 13; face="King";
                    break;
            }
            cardArray[i]=new Card(value,suit,face); //This creates the array
        }
    }

    /**
     * This method returns the top card which is at index 0
     */
    public Card getTopCard()
    {
        return cardArray[TOP_OF_DECK]; //the top card is always at index 0
    }

    /**
     * This method shuffles the deck by swapping elements of the array
     */
    public void Shuffle() //shuffle the deck
    {
        for(int k=0;k<1000;k++)
        {
            for(int i=1; i<52;i++)
            {
                Card temp; //this is a placeholder variable
                int j=rand.nextInt(i); //generates random number and sets it as an index
                temp=cardArray[i];
                cardArray[i]=cardArray[j]; //switches card i with some random card j
                cardArray[j]=temp;
            }
        }
    }

    /**
     * This method determines the winner and returns the winner.
     * 
     * @param totalPlayer (the total amount of points based on the cards for the user)
     * @param totalDealer (the total amount of points based on the cards for the dealer)
     * 
     * @return winner (the String represenation of the winner, either "Player" or "Dealer")
     */
    public String Winner(int totalPlayer, int totalDealer)
    {
        String winner="null";
        if(totalPlayer!=21 && totalDealer==21) //if dealer = 21 dealer wins
        {
            winner="\nDealer wins!!";
        }
        else if(totalPlayer==21 && totalDealer!=21) //if player = 21 player wins
        {
            winner="\nPlayer wins!";
        }
        else if(totalPlayer>21 &&totalDealer>21) //if both are above 21 its a draw (bust)
        {
            winner="\nDraw!";
        }
        else if(totalPlayer>totalDealer &&totalPlayer<21 &&totalDealer<21)
        {
            winner="\nPlayer wins!"; // if both are under 21 and player is greater/player wins
        }
        else if(totalPlayer<totalDealer &&totalPlayer<21 &&totalDealer<21)
        {
            winner="\nDealer wins!"; // if both are under 21 and dealer is greater dealer wins
        }
        else if(totalPlayer<=21&&totalDealer>21)
        {
            winner="\nPlayer wins!"; //if player is less than 21 and dealer is greater, player wins
        }
        else if(totalPlayer>21&&totalDealer<=21)
        {
            winner="\nDealer wins!"; // if player is greater than 21 and dealer is less than 21
            //dealer wins
        }
        else if(totalDealer==totalPlayer)
        {
            winner="\nDraw!"; // if dealer and player are equal, its a draw
        }
        return winner; //return the winner
    }

    /**
     * This method provides a textual representation of the data 
     */
    public String toString()
    {
        for(int i=0 ; i< 52; i++)
        {
            System.out.println(cardArray[i]);
        }
        return "";
    }

}