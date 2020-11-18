/**
 * Group Assignment for Module 3: Decks of Cards
 * A program to simulate a playing cards game
 * 
 * by Cohort 12, Pyrocumulus team:
 * Joshan Dillon, Trenton Fengel, Tabitha Micheels, Marianna Petrovich
 * 
 * 11/17/2020
 */

import java.util.*;

public class Assig0
{
   public static void main(String[] args)
   {
      /*
      // Phase 1: test of class Card
      Card testCard1 = new Card();
      System.out.println(testCard1.toString());
      Card testCard2 = new Card();
      testCard2.set('1', testCard2.getSuit());
      System.out.println(testCard2.toString());
      Card testCard3 = new Card();
      testCard2.set('T', Card.Suit.hearts);
      System.out.println(testCard2.toString());
      
      
      // Phase 2: test of class Hand
      testCard1 = new Card();
      testCard2 = new Card();
      testCard2.set('7', Card.Suit.diamonds);
      testCard3 = new Card();
      testCard3.set('T', Card.Suit.clubs);
      Card testCard4 = new Card();
      testCard4.set('K', Card.Suit.hearts);
      Card testCard5 = new Card();
      testCard5.set('Q', Card.Suit.spades);
      
      Hand deal = new Hand();
      for (int l = 0; l < 10; l++)
      {
         deal.takeCard(testCard1);
         deal.takeCard(testCard2);
         deal.takeCard(testCard3);
         deal.takeCard(testCard4);
         deal.takeCard(testCard5);
      }
      
      System.out.println("Hand Full");
      System.out.println("After deal");
      System.out.println(deal.toString());
      
      System.out.println("Testing inspectCard()");
      System.out.println(deal.inspectCard(45).toString());
      System.out.println(deal.inspectCard(55).toString());
      
      for (int x = 0; x < 50; x++)
      {
         Card playedCard = deal.playCard();
         System.out.println("Playing " + playedCard.toString());
      }

      System.out.println("After playing all cards");
      System.out.println(deal.toString());      
      */
      
      // Phase 3: Test of class Deck
      // unshuffled print
      System.out.printf("Phase 3%n%n");
      
      Deck cardDeck = new Deck(2);
      while (cardDeck.getTopCard() > 0)
      {
         Card card = cardDeck.dealCard();
         System.out.print(card.toString() + " / ");
      }
      System.out.printf("%n%nDeck Empty%n%n");
      // reset, shuffle, print
      cardDeck = new Deck(2);
      cardDeck.shuffle();
      
      while (cardDeck.getTopCard()>0)
      {
         Card card = cardDeck.dealCard();
         System.out.print(card.toString() + " / ");
      }
      System.out.printf("%n%nDeck Empty%n%n");
      cardDeck = new Deck();
      while (cardDeck.getTopCard() > 0)
      {
         Card card = cardDeck.dealCard();
         System.out.print(card.toString() + " / ");
      }
      System.out.printf("%n%nDeck Empty%n%n");
      // reset, shuffle, print
      cardDeck = new Deck();
      cardDeck.shuffle();
      
      while (cardDeck.getTopCard() > 0)
      {
         Card card = cardDeck.dealCard();
         System.out.print(card.toString() + " / ");
      }
      System.out.printf("%n%nDeck Empty%n%n");
      
      
      // Phase 4: The Deck and Hand Classes
      System.out.printf("%n%nPhase 4%n%n");
      
      int players = 0;
      cardDeck = new Deck();
      Scanner keyboard = new Scanner(System.in);
      
      // Prompt user for number of players between 1 and 10
      while (players < 1 || players > 10)
      {
         System.out.print("How many hands? (1 - 10, please): ");
         players = keyboard.nextInt();
      }

      // Create an array of hands 
      Hand[] handsArray = new Hand[players];
      
      // Initialize hands
      for (int h = 0; h < players; h++)
      {
         handsArray[h] = new Hand();
      }

      // Deal cards to each player until deck is empty
      while (cardDeck.getTopCard() > 0)
      {
         for (int i = 0; i < players; i++)
         {
            if (cardDeck.getTopCard() > 0)
            {
               handsArray[i].takeCard(cardDeck.dealCard());
            }
            else
            {
               break;
            }
         }
      }
      
      System.out.println("\nHere are our hands, from unshuffled deck:\n");
      // Print each hand then reset it
      for (int j = 0; j < players; j++)
      {
         System.out.println(handsArray[j].toString() + "\n");
         handsArray[j].resetHand();
      }
    
      handsArray = new Hand[players];
      // Initialize hands
      for (int h = 0; h < players; h++)
      {
         handsArray[h] = new Hand();
      }
      
      // Reset all objects
      cardDeck.init(1);
      cardDeck.shuffle();
      
      //  Deal cards to each player until deck is empty
      while (cardDeck.getTopCard() > 0)
      {
         for(int k = 0; k < players; k++)
         {
            if(cardDeck.getTopCard() > 0)
            {
               handsArray[k].takeCard(cardDeck.dealCard());
            }
            else
            {
               break;
            }
         }
      }
      
      System.out.println("\nHere are our hands, from SHUFFLED deck:\n");
      
      // Print each hand
      for (int j = 0; j < players; j++)
      {
         System.out.println(handsArray[j].toString() + "\n");
      }
      
      // clean-up resources
      keyboard.close();
      // terminate JVM
      System.exit(0);
   }
}

//Represents card in deck of cards
class Card
{
   public enum Suit { clubs, diamonds, hearts, spades };

   private char value;
   private Suit suit;
   private boolean errorFlag = false;

   // Default constructor
   public Card()
   {
      set('A', Suit.spades);
   }

   // Constructor with params
   public Card(char value, Suit suit)
   {
      set(value, suit);
   }

   // object as a single string
   public String toString()
   {
      if (errorFlag)
      {
         return "** invalid **";
      } else
      {
         return value + " of " + suit;
      }
   }

   // Mutator for both value and suit,
   // individual mutators not needed
   public void setFlag(boolean flag)
   {
      this.errorFlag = flag;
   }

   public boolean set(char value, Suit suit)
   {
      this.value = value;
      this.suit = suit;
      boolean valid = isValid(value, suit);
      setFlag(!valid);

      return valid;
   }

   // Accessor
   public char getValue()
   {
      return value;
   }

   public Suit getSuit()
   {
      return suit;
   }

   public boolean getFlag()
   {
      return errorFlag;
   }

   // Returns whether passed card is equal to the original
   public boolean equals(Card card)
   {
      return (this.value == card.getValue()
           && this.errorFlag == card.getFlag()
           && this.suit == card.getSuit());
   }

   // is card of valid value?
   // private only called internally
   private boolean isValid(char value, Suit suit)
   {
      return ("A23456789TJQK".indexOf(value) != -1);
   }
}

class Hand
{
   //initialize our variables
   public static final int MAX_CARDS = 50;
   private Card[] myCards;
   private int numCards;
   
   //default constructor set numCards equal to 0
   public Hand()
   {
      resetHand();   
   }   
   
   //Makes our hand (array) empty 
   public void resetHand()
   {
      numCards = 0;
      myCards = new Card[MAX_CARDS];      
   }   
   
   //Adds new card to hand when hand is not full
   public boolean takeCard(Card card)
   {      
      if (numCards <= MAX_CARDS)
      {
         myCards[numCards] = new Card(card.getValue(), card.getSuit());
         numCards++;
         return true;
      }      
      else
      {
         return false;  
      }
   }   
   
   //Plays most recent card, removes it from the array as well
   public Card playCard()
   {
      numCards --;
      Card trackCard = myCards[numCards];
      myCards[numCards] = null;
      return trackCard;
   }   
   
   //Displays entire hand
   public String toString()
   {
      String cardInfo = "Hand = ( ";
      for (int i = 0; numCards > i; i++)
      {
         cardInfo += myCards[i].toString();
         if(i < numCards -1)
            cardInfo += ", ";     
      }
      cardInfo += " )";
      return cardInfo;
   }   
   
   //Accessor for numCards returning the number of cards
   public int getNumCards ()
   {
      return numCards;
   }
   
   //Returns individual card from specific location. 
   public Card inspectCard(int k)
   {
      if (k < 0 || k > numCards)
      {
         Card invalidCard = new Card();
         invalidCard.setFlag(true);
         return invalidCard;
      }      
      else
      {
         return myCards[k];
      }     
   }  
}

class Deck
{
   // Variables and arrays
   Random rand = new Random();
   public static final int MAX_CARDS = 52 * 6;
   private static Card[] masterPack;
   private Card[] cards;
   private int topCard;

   // Constructors
   Deck(int numPacks)
   {
      allocateMasterPack();
      init(numPacks);
   }

   Deck()
   {
      this(1);
   }

   // Re-populates card[]
   public void init(int numPacks)
   {
      this.cards = new Card[52 * numPacks];
      for (int i = 0; i < numPacks; i++)
      {
         for (int j = 0; j < masterPack.length; j++)
         {
            cards[j + (52*i)] = masterPack[j];
         }
      }
      this.topCard = 52 * numPacks;
   }

   // Mixes up the cards
   public void shuffle()
   {
      for (int i = 0; i < cards.length; i++)
      {
         int num = rand.nextInt(cards.length);
         Card temp = cards[i];
         cards[i] = cards[num];
         cards[num] = temp;
      }
   }

   // Returns and removes the top card
   public Card dealCard()
   {
      if (topCard - 1 < 0)
      {
         Card badCard = new Card();
         badCard.setFlag(true);
         return badCard;
      }
      else
      {
         Card top = cards[topCard - 1];
         cards[topCard - 1] = null;
         topCard--;
         return top;
      }
   }

   // Accessor for topCard
   public int getTopCard()
   {
      return this.topCard;
   }

   // Accessor for individual cards
   public Card inspectCard(int k)
   {
      if (k < 0 || k > topCard - 1)
      {
         Card badCard = new Card();
         badCard.setFlag(true);
         return badCard;
      } else
      {
         return cards[k];
      }
   }

   // Static Deck object used for copying to another object. Only executes once.
   private static void allocateMasterPack()
   {
   // first check if masterpack is not yet built
      if (masterPack == null)
      {
         // counters for loops
         int iValues, iSuits;
         // String = char[] array for 13 possible card values
         String validValues = "A23456789TJQK";
   
         // allocate 
         masterPack = new Card[52];
   
         // loop through the suits 
         for (iSuits = 0; iSuits < Card.Suit.values().length; iSuits++)
         {
            // loop through the values
            for (iValues=0; iValues < validValues.length(); iValues++)
            {
               masterPack[iSuits*validValues.length() + iValues] =
                     new Card(validValues.charAt(iValues),
                           Card.Suit.values()[iSuits]); 
            }  
         }
      }
   }
}

/******************************OUTPUT*******************************************
Phase 3

K of spades / Q of spades / J of spades / T of spades / 9 of spades / 8 of spade
s / 7 of spades / 6 of spades / 5 of spades / 4 of spades / 3 of spades / 2 of s
pades / A of spades / K of hearts / Q of hearts / J of hearts / T of hearts / 9 
of hearts / 8 of hearts / 7 of hearts / 6 of hearts / 5 of hearts / 4 of hearts 
/ 3 of hearts / 2 of hearts / A of hearts / K of diamonds / Q of diamonds / J of
 diamonds / T of diamonds / 9 of diamonds / 8 of diamonds / 7 of diamonds / 6 of
 diamonds / 5 of diamonds / 4 of diamonds / 3 of diamonds / 2 of diamonds / A of
 diamonds / K of clubs / Q of clubs / J of clubs / T of clubs / 9 of clubs / 8 o
f clubs / 7 of clubs / 6 of clubs / 5 of clubs / 4 of clubs / 3 of clubs / 2 of 
clubs / A of clubs / K of spades / Q of spades / J of spades / T of spades / 9 o
f spades / 8 of spades / 7 of spades / 6 of spades / 5 of spades / 4 of spades /
 3 of spades / 2 of spades / A of spades / K of hearts / Q of hearts / J of hear
ts / T of hearts / 9 of hearts / 8 of hearts / 7 of hearts / 6 of hearts / 5 of 
hearts / 4 of hearts / 3 of hearts / 2 of hearts / A of hearts / K of diamonds /
 Q of diamonds / J of diamonds / T of diamonds / 9 of diamonds / 8 of diamonds /
 7 of diamonds / 6 of diamonds / 5 of diamonds / 4 of diamonds / 3 of diamonds /
 2 of diamonds / A of diamonds / K of clubs / Q of clubs / J of clubs / T of clu
bs / 9 of clubs / 8 of clubs / 7 of clubs / 6 of clubs / 5 of clubs / 4 of clubs
 / 3 of clubs / 2 of clubs / A of clubs / 

Deck Empty

5 of spades / 4 of spades / 6 of spades / A of spades / 4 of hearts / T of heart
s / 9 of spades / 5 of diamonds / 7 of clubs / T of clubs / 9 of hearts / T of d
iamonds / 2 of hearts / 5 of clubs / J of spades / 9 of spades / T of clubs / 2 
of clubs / J of spades / T of spades / 8 of clubs / 3 of hearts / Q of hearts / 
4 of spades / A of clubs / Q of spades / 8 of hearts / 3 of spades / 2 of spades
 / 5 of diamonds / 8 of spades / Q of spades / 3 of diamonds / 5 of hearts / 6 o
f spades / 2 of hearts / 3 of diamonds / T of spades / 7 of diamonds / K of club
s / 8 of diamonds / Q of hearts / 4 of diamonds / 7 of hearts / A of hearts / K 
of spades / 6 of diamonds / 3 of clubs / A of diamonds / 8 of hearts / 5 of spad
es / 2 of diamonds / 9 of clubs / Q of diamonds / 6 of clubs / 7 of diamonds / K
 of hearts / J of clubs / K of diamonds / J of diamonds / 4 of clubs / K of club
s / K of hearts / 3 of clubs / 4 of hearts / 8 of diamonds / 9 of diamonds / J o
f diamonds / 7 of hearts / T of hearts / 6 of hearts / 6 of clubs / 9 of clubs /
 5 of clubs / 4 of diamonds / 8 of spades / 6 of diamonds / 7 of clubs / 5 of he
arts / 2 of clubs / 6 of hearts / Q of clubs / Q of diamonds / 3 of hearts / 3 o
f spades / 4 of clubs / 7 of spades / 9 of hearts / Q of clubs / 2 of diamonds /
 J of hearts / A of diamonds / J of clubs / 9 of diamonds / 8 of clubs / J of he
arts / A of hearts / A of clubs / 7 of spades / 2 of spades / K of spades / K of
 diamonds / A of spades / T of diamonds / 

Deck Empty

K of spades / Q of spades / J of spades / T of spades / 9 of spades / 8 of spade
s / 7 of spades / 6 of spades / 5 of spades / 4 of spades / 3 of spades / 2 of s
pades / A of spades / K of hearts / Q of hearts / J of hearts / T of hearts / 9 
of hearts / 8 of hearts / 7 of hearts / 6 of hearts / 5 of hearts / 4 of hearts 
/ 3 of hearts / 2 of hearts / A of hearts / K of diamonds / Q of diamonds / J of
 diamonds / T of diamonds / 9 of diamonds / 8 of diamonds / 7 of diamonds / 6 of
 diamonds / 5 of diamonds / 4 of diamonds / 3 of diamonds / 2 of diamonds / A of
 diamonds / K of clubs / Q of clubs / J of clubs / T of clubs / 9 of clubs / 8 o
f clubs / 7 of clubs / 6 of clubs / 5 of clubs / 4 of clubs / 3 of clubs / 2 of 
clubs / A of clubs / 

Deck Empty

3 of clubs / 6 of spades / A of diamonds / Q of spades / J of diamonds / 8 of sp
ades / 2 of diamonds / T of spades / 3 of hearts / 8 of diamonds / T of diamonds
 / 2 of clubs / 7 of diamonds / K of spades / Q of hearts / 8 of clubs / T of he
arts / 4 of diamonds / 7 of spades / 8 of hearts / 3 of spades / K of hearts / 4
 of clubs / Q of clubs / 9 of diamonds / A of hearts / J of spades / 9 of hearts
 / K of diamonds / 3 of diamonds / 6 of diamonds / A of spades / 7 of hearts / 5
 of diamonds / 2 of hearts / 5 of hearts / 6 of hearts / A of clubs / Q of diamo
nds / 9 of clubs / 4 of hearts / J of hearts / 5 of clubs / K of clubs / J of cl
ubs / T of clubs / 9 of spades / 5 of spades / 6 of clubs / 7 of clubs / 2 of sp
ades / 4 of spades / 

Deck Empty



Phase 4

How many hands? (1 - 10, please): 6

Here are our hands, from unshuffled deck:

Hand = ( K of spades, 7 of spades, A of spades, 8 of hearts, 2 of hearts, 9 of d
iamonds, 3 of diamonds, T of clubs, 4 of clubs )

Hand = ( Q of spades, 6 of spades, K of hearts, 7 of hearts, A of hearts, 8 of d
iamonds, 2 of diamonds, 9 of clubs, 3 of clubs )

Hand = ( J of spades, 5 of spades, Q of hearts, 6 of hearts, K of diamonds, 7 of
 diamonds, A of diamonds, 8 of clubs, 2 of clubs )

Hand = ( T of spades, 4 of spades, J of hearts, 5 of hearts, Q of diamonds, 6 of
 diamonds, K of clubs, 7 of clubs, A of clubs )

Hand = ( 9 of spades, 3 of spades, T of hearts, 4 of hearts, J of diamonds, 5 of
 diamonds, Q of clubs, 6 of clubs )

Hand = ( 8 of spades, 2 of spades, 9 of hearts, 3 of hearts, T of diamonds, 4 of
 diamonds, J of clubs, 5 of clubs )


Here are our hands, from SHUFFLED deck:

Hand = ( A of clubs, 4 of clubs, 7 of hearts, J of diamonds, J of spades, 5 of s
pades, 6 of diamonds, J of clubs, 2 of spades )

Hand = ( 7 of clubs, 6 of hearts, 5 of hearts, 2 of hearts, Q of clubs, 7 of spa
des, 4 of diamonds, Q of spades, 2 of diamonds )

Hand = ( 6 of spades, 9 of spades, 4 of spades, 5 of clubs, 3 of hearts, 3 of cl
ubs, 8 of clubs, 9 of diamonds, 7 of diamonds )

Hand = ( 5 of diamonds, K of spades, 2 of clubs, T of diamonds, A of spades, 6 o
f clubs, J of hearts, K of clubs, A of hearts )

Hand = ( 3 of spades, 9 of clubs, K of diamonds, 9 of hearts, 8 of spades, T of 
hearts, A of diamonds, T of clubs )

Hand = ( Q of hearts, 8 of hearts, 3 of diamonds, K of hearts, 8 of diamonds, T 
of spades, 4 of hearts, Q of diamonds )
*/