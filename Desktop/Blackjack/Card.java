import java.util.*;//imports the utility package to allow for user input
/**
 * This class defintion initializes the cards in the decks and performs operations on those cards and presents them to the user 
 * 
 * @author (A. McNeely)
 * @version (12/10/2017)
 */
public class Card
{
    private int Value; //value of the card
    private String Suit; //whether its hearts, diamonds etc..
    private String Face; //whether its K, J, Q or A
    private boolean answer;
    private int result;
    /**
     * This constructor with arguements accepts values for value of card, suit and face and sets them equal to
     * the respective fields.
     * 
     * @param val (a placeholder variable)
     * @param type (a placeholder variable)
     * @param face (a placeholder variable)
     */
    public Card(int val,String type, String face)
    {
        val=(val==11)?10:(val==12)?10:(val==13)?10:val;
        Value=val;
        Suit=type;
        Face=face;
    }

    /**
     * This method returns the value of the card being passed in.
     * 
     * @param other (a placeholder variable)
     * 
     * @return other.Value (the value of the other card)
     */
    public int cardValue(Card other)
    {
        return other.Value;
    }

    /**
     * The method returns the suit of the card
     * 
     * @return Suit (the suit of the current card)
     */
    public String getSuit()
    {
        return Suit; // what your card's suit is
    }

    /**
     * This method returns the face of the card
     * 
     * @return Face (the face of the current card)
     */
    public String getFace()
    {
        return Face; // whether your card is a face or not this is a String representation of what your card is
    }

    /**
     * This method returns the value of the card
     * 
     * @return Value (the value of the current card)
     */
    public int getValue()
    {
        return Value;
    }

    /**
     * This method checks if the card is in either the dealer or player hand
     * 
     * @param Top (the top card)
     * @param dealer (the dealer's cards)
     * @param player (the user's cards)
     * @param p (the player's count)
     * @param d (the dealer's count)
     * 
     * @return result 
     */
    public boolean checkCard(Card Top, Card [] dealer, Card [] player, int p,int d)
    {
        boolean answerPlayer=false;
        boolean answerDealer=false;
        boolean result=true;
        for(int i=0; i<p;i++)
        {
            if(player[i].Face==Top.Face&& player[i].Suit==Top.Suit)
            {
                answerPlayer = true;
            }
            else
            {
                answerPlayer= false;
            }
        }

        for(int i=0;i<d;i++)
        {
            if(dealer[i].Face==Top.Face&& dealer[i].Suit==Top.Suit)
            {
                answerDealer= true;
            }
            else
            {
                answerDealer=false;
            }

        }
        if(answerPlayer==true||answerDealer==true)
            result=true;
        else if(answerPlayer==false&&answerDealer==false)
            result=false;
        return result;
    }

    /**
     * The compareTo compares the values and faces of the incoming card and calling card to determine which
     * card has a higher value.
     * 
     * @param other (the other variable being compared)
     * 
     * @return result 
    */
    public int compareTo(Card other)
    {
        if(Value==1 &&other.Value!=1 || Value!=1 &&other.Value==1) //Ace wins regardless
        {
            result=1;// ace wins regardless
        }
        else if(Value==other.Value && Face!="J" || Face!="Q" || Face!="K" &&other.Face!="J" || other.Face!="Q" || other.Face!="K")
        {
            result=0; // If both values are the same and none are face, then they are the same.
        }
        else if(Value==other.Value && Face!="J" || Face!="Q" || Face!="K" &&other.Face=="J"||other.Face=="K"||other.Face=="Q")
        {
            result=-1; // if value is the same and face is not a face card, then other value wins
        }
        else if(Value==other.Value && Face=="J" || Face=="Q" || Face=="K" &&other.Face!="J"||other.Face!="K"||other.Face!="Q")
        {
            result=1; // if value is the same and other face is not a face card, then first value wins
        }
        else if(Value==other.Value && Face=="J" || Face=="Q" || Face=="K"&& other.Face=="J"||other.Face=="Q"||other.Face=="K")
        {
            if(Face=="K" &&other.Face!="K")
                result =1;
            else if(Face!="K"&& other.Face=="K")
                result=-1;
            if(Face=="Q" &&other.Face!="Q")
                result =1;
            else if(Face!="Q"&& other.Face=="Q")
                result=-1;
        }
        else if(Value>other.Value && Face!="J" || Face!="Q" || Face!="K" &&other.Face!="J" || other.Face!="Q" || other.Face!="K")
        {
            result=1; // If value is greater than other value and none are face cards then value wins
        }
        else if(Value<other.Value && Face!="J" || Face!="Q" || Face!="K" &&other.Face!="J" || other.Face!="Q" || other.Face!="K")
        {
            result=-1; // If other value is greater than first value, and none are face cards then other value wins
        }
        else if(Value>other.Value && Face=="J" || Face=="Q" || Face=="K" &&other.Face!="J" || other.Face!="Q" || other.Face!="K")
        {
            result=1; // If value is greater than other value and none are face cards then value wins
        }
        else if(Value<other.Value && Face!="J" || Face!="Q" || Face!="K" &&other.Face=="J" || other.Face=="Q" || other.Face=="K")
        {
            result=-1; // If value is greater than other value and none are face cards then value wins
        }
        return result;
    }

    /**
     * This method provides a textual representation of the data 
     */
    public String toString()
    {
        return Face+ " of " + Suit;
    }
}