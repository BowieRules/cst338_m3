/**
 * Group Assignment for Module 3: Decks of Cards
 * A program to simulate a playing cards game
 * 
 * by Cohort 12, Pyrocumulus team:
 * Joshan Dillon, Trenton Fengel, Tabitha Micheels, Marianna Petrovich
 * 
 * 11/12/2020
 */

// for Random and Scanner 
import java.util.*; 

public class Assig3
{

   public static void main(String[] args)
   {
      /** Phase 1: test of class Card
      Card testCard1 = new Card();
      System.out.println(testCard1.toString());
      Card testCard2 = new Card();
      testCard2.set('1', testCard2.getSuit());
      System.out.println(testCard2.toString());
      Card testCard3 = new Card();
      testCard2.set('T', Card.Suit.hearts);
      System.out.println(testCard2.toString());
      */
      
      /* Phase 2: test of class Hand
      Card testCard1 = new Card();
      Card testCard2 = new Card();
      testCard2.set('7', Card.Suit.diamonds);
      Card testCard3 = new Card();
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

// Represents card in deck of cards
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
      }
      else 
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
      return (this.getValue() == card.getValue()
            && this.getFlag() == card.getFlag()
            && this.getSuit() == card.getSuit());
   }

   // is card of valid value?
   // private only called internally
   private boolean isValid(char value, Suit suit)
   {
      return ("A23456789TJQK".indexOf(value) != -1);
   }
}

// Represents a hand (array) of numCards cards
class Hand {
   
   public static final int MAX_CARDS = 50;
   private Card[] myCards;
   private int numCards;

   // Default constructor
   public Hand()
   {
      resetHand();
   }

   // Remove all cards from the hand
   public void resetHand()
   {
      myCards = new Card[MAX_CARDS];
      numCards = 0;
   }

   // Adds a card to the next available position in collection
   public boolean takeCard(Card card)
   {
      if (numCards > MAX_CARDS)
      {
         return false;
      }      
      else
      {
         // Object copy, not a reference copy
         Card newCard = new Card();
         newCard.set(card.getValue(), card.getSuit());
         myCards[numCards] = newCard;
         numCards++;
         return true;
      }
   }

   // This method will remove and return the top card in the array
   public Card playCard()
   {
      numCards--;
      Card topCard = myCards[numCards];
      myCards[numCards] = null; 
      return topCard;
   }

   // Returns the entire hand built up by the Stringizer
   public String toString()
   {
      String strCards = "Hand = ( ";
      
      for (int i = 0; i < numCards; i++)
      {
         strCards += myCards[i].toString() + ", ";
      }
      return strCards.substring(0, strCards.length() - 2) +" )";      
   }

   // Accessors
   public int getNumCards()
   {
      return numCards;
   }

   // Returns a card at a given index
   // or a card with errorFlag = true if k is bad
   public Card inspectCard(int k)
   {
      if (k < 0 || k > numCards)
      {
         Card badCard = new Card();
         badCard.setFlag(true);
         return badCard;
      }
      else
      {
         return myCards[k];
      }
   }
}

// Represents the source of all cards numPacks*52
class Deck
{

   // Final constant (6 packs= 6*52 cards maximum): 
   public static final int MAX_CARDS = 6 * 52;

   // exactly 52 card reference = all standard cards
   private static Card[] masterPack;

   private Card[] cards; 
   private int topCard; 
   private int numPacks;

   // Main constructor populates the arrays and initial masterpack
   public Deck(int numPacks)
   {
      allocateMasterPack();
      init(numPacks);
   }

   // Overloaded constructor with no params and 1 pack is default 
   public Deck()
   {
      this(1);
   }

   // Re-populates Card array with 52 * numPacks, not masterpack
   void init(int numPacks)
   {
      this.topCard = 52 * numPacks;
      this.cards = new Card[topCard]; 
      this.numPacks = numPacks;
      
      for (int i=0; i < numPacks; i++)
      {
         for (int j=0; j < 52; j++)
         {
            this.cards[i*52 + j] = new Card(masterPack[j].getValue(),
                  masterPack[j].getSuit());         
         }
      }
   }

   // Mix up the cards with standard random number generator
   public void shuffle()
   {   
      Random rand = new Random(); 

      for (int i=0; i<cards.length; i++)
      {
         // generate random integer between 0 and length-1
         int randNum = rand.nextInt(cards.length);

         // Swapping the cards in array with random card
         Card tempCard = cards[randNum];
         cards[randNum] = cards[i]; 
         cards[i] = tempCard; 
      }
   } 
   
   // Remove the top card
   public Card dealCard()
   {
      if (topCard > 0)
      {
         topCard--;
      }

      return cards[topCard];
   }

   // Accessor for topCard
   public int getTopCard()
   {
      return topCard;
   }

   // Accessor for an individual card. Returns a card with errorFlag = true if k is bad. 
   public Card inspectCard(int k)
   {
      if (k < 0 || k > topCard)
      {
         Card badCard = new Card();
         badCard.setFlag(true);
         return badCard;
      }
      else
      {
         return cards[k];
      }
   }
   
   private static void allocateMasterPack()
   {
      // first check if masterpack is not yet built
      if (masterPack == null)
      {
         // counters for loops
         int iValues, iSuits;
         // array for 13 possible card values
         final char[] cardValues =
         {
            'K',
            'Q',
            'J',
            'T',
            '9',
            '8',
            '7',
            '6',
            '5',
            '4',
            '3',
            '2',
            'A'
        };
   
         // allocate 
         masterPack = new Card[52];
   
         // loop through the suits 
         for (iSuits = 0; iSuits < Card.Suit.values().length; iSuits++)
         {
            // loop through the values
            for (iValues=0; iValues<cardValues.length; iValues++)
            {
               masterPack[iSuits*cardValues.length + iValues] =
                     new Card(cardValues[iValues],Card.Suit.values()[iSuits]); 
            }  
         }
      }
   }
}

/************************************OUTPUT*********************************************************************

Phase 3

A of spades / 2 of spades / 3 of spades / 4 of spades / 5 of spades / 6 of spade
s / 7 of spades / 8 of spades / 9 of spades / T of spades / J of spades / Q of s
pades / K of spades / A of hearts / 2 of hearts / 3 of hearts / 4 of hearts / 5 
of hearts / 6 of hearts / 7 of hearts / 8 of hearts / 9 of hearts / T of hearts 
/ J of hearts / Q of hearts / K of hearts / A of diamonds / 2 of diamonds / 3 of
 diamonds / 4 of diamonds / 5 of diamonds / 6 of diamonds / 7 of diamonds / 8 of
 diamonds / 9 of diamonds / T of diamonds / J of diamonds / Q of diamonds / K of
 diamonds / A of clubs / 2 of clubs / 3 of clubs / 4 of clubs / 5 of clubs / 6 o
f clubs / 7 of clubs / 8 of clubs / 9 of clubs / T of clubs / J of clubs / Q of 
clubs / K of clubs / A of spades / 2 of spades / 3 of spades / 4 of spades / 5 o
f spades / 6 of spades / 7 of spades / 8 of spades / 9 of spades / T of spades /
 J of spades / Q of spades / K of spades / A of hearts / 2 of hearts / 3 of hear
ts / 4 of hearts / 5 of hearts / 6 of hearts / 7 of hearts / 8 of hearts / 9 of 
hearts / T of hearts / J of hearts / Q of hearts / K of hearts / A of diamonds /
 2 of diamonds / 3 of diamonds / 4 of diamonds / 5 of diamonds / 6 of diamonds /
 7 of diamonds / 8 of diamonds / 9 of diamonds / T of diamonds / J of diamonds /
 Q of diamonds / K of diamonds / A of clubs / 2 of clubs / 3 of clubs / 4 of clu
bs / 5 of clubs / 6 of clubs / 7 of clubs / 8 of clubs / 9 of clubs / T of clubs
 / J of clubs / Q of clubs / K of clubs / 

Deck Empty

5 of clubs / K of spades / Q of spades / 8 of diamonds / 3 of spades / 2 of spad
es / 4 of spades / 8 of hearts / 6 of spades / 9 of spades / 9 of clubs / A of c
lubs / T of spades / 5 of diamonds / 7 of clubs / 8 of hearts / T of hearts / Q 
of diamonds / Q of diamonds / J of spades / 9 of clubs / 5 of hearts / 2 of hear
ts / 3 of spades / 7 of spades / K of hearts / 7 of hearts / 6 of hearts / T of 
clubs / Q of hearts / 3 of clubs / 4 of clubs / J of diamonds / J of clubs / 6 o
f diamonds / 3 of diamonds / 5 of hearts / A of hearts / Q of clubs / K of clubs
 / A of diamonds / 7 of diamonds / Q of hearts / Q of spades / T of spades / 2 o
f clubs / 3 of clubs / 2 of clubs / 9 of diamonds / 5 of clubs / T of diamonds /
 J of clubs / 3 of hearts / 2 of hearts / A of spades / J of spades / 8 of diamo
nds / A of clubs / 4 of clubs / 6 of clubs / J of diamonds / 9 of diamonds / 4 o
f spades / 3 of diamonds / 6 of spades / 4 of diamonds / 9 of spades / 7 of club
s / 9 of hearts / 8 of clubs / Q of clubs / A of hearts / 4 of diamonds / 7 of s
pades / 5 of diamonds / T of diamonds / A of spades / T of clubs / J of hearts /
 8 of spades / 4 of hearts / J of hearts / 5 of spades / 6 of clubs / 2 of diamo
nds / T of hearts / 3 of hearts / K of diamonds / 9 of hearts / 8 of clubs / 6 o
f hearts / A of diamonds / 7 of diamonds / 5 of spades / K of clubs / 2 of diamo
nds / 2 of spades / K of hearts / 4 of hearts / 8 of spades / K of spades / 6 of
 diamonds / K of diamonds / 7 of hearts / 

Deck Empty

A of spades / 2 of spades / 3 of spades / 4 of spades / 5 of spades / 6 of spade
s / 7 of spades / 8 of spades / 9 of spades / T of spades / J of spades / Q of s
pades / K of spades / A of hearts / 2 of hearts / 3 of hearts / 4 of hearts / 5 
of hearts / 6 of hearts / 7 of hearts / 8 of hearts / 9 of hearts / T of hearts 
/ J of hearts / Q of hearts / K of hearts / A of diamonds / 2 of diamonds / 3 of
 diamonds / 4 of diamonds / 5 of diamonds / 6 of diamonds / 7 of diamonds / 8 of
 diamonds / 9 of diamonds / T of diamonds / J of diamonds / Q of diamonds / K of
 diamonds / A of clubs / 2 of clubs / 3 of clubs / 4 of clubs / 5 of clubs / 6 o
f clubs / 7 of clubs / 8 of clubs / 9 of clubs / T of clubs / J of clubs / Q of 
clubs / K of clubs / 

Deck Empty

7 of hearts / A of diamonds / T of clubs / A of spades / 6 of hearts / 3 of club
s / T of hearts / 4 of spades / 2 of clubs / J of clubs / 5 of diamonds / 5 of c
lubs / 5 of spades / 4 of hearts / J of spades / J of hearts / 8 of hearts / A o
f hearts / 9 of diamonds / K of spades / Q of hearts / K of hearts / 6 of diamon
ds / T of spades / 4 of clubs / 3 of hearts / 8 of clubs / 4 of diamonds / Q of 
diamonds / 9 of clubs / 2 of spades / 2 of hearts / Q of clubs / 6 of spades / 7
 of spades / J of diamonds / 3 of spades / 2 of diamonds / 7 of diamonds / 8 of 
spades / 7 of clubs / T of diamonds / K of diamonds / 6 of clubs / K of clubs / 
5 of hearts / A of clubs / 3 of diamonds / Q of spades / 8 of diamonds / 9 of sp
ades / 9 of hearts / 

Deck Empty



Phase 4

How many hands? (1 - 10, please): 6

Here are our hands, from unshuffled deck:

Hand = ( A of spades, 7 of spades, K of spades, 6 of hearts, Q of hearts, 5 of d
iamonds, J of diamonds, 4 of clubs, T of clubs )

Hand = ( 2 of spades, 8 of spades, A of hearts, 7 of hearts, K of hearts, 6 of d
iamonds, Q of diamonds, 5 of clubs, J of clubs )

Hand = ( 3 of spades, 9 of spades, 2 of hearts, 8 of hearts, A of diamonds, 7 of
 diamonds, K of diamonds, 6 of clubs, Q of clubs )

Hand = ( 4 of spades, T of spades, 3 of hearts, 9 of hearts, 2 of diamonds, 8 of
 diamonds, A of clubs, 7 of clubs, K of clubs )

Hand = ( 5 of spades, J of spades, 4 of hearts, T of hearts, 3 of diamonds, 9 of
 diamonds, 2 of clubs, 8 of clubs )

Hand = ( 6 of spades, Q of spades, 5 of hearts, J of hearts, 4 of diamonds, T of
 diamonds, 3 of clubs, 9 of clubs )


Here are our hands, from SHUFFLED deck:

Hand = ( 5 of clubs, Q of diamonds, 9 of clubs, K of spades, 3 of hearts, T of d
iamonds, 4 of spades, 9 of hearts, 7 of hearts )

Hand = ( 5 of spades, 8 of clubs, K of diamonds, J of clubs, J of diamonds, 6 of
 spades, J of spades, 6 of hearts, 2 of diamonds )

Hand = ( 3 of spades, A of spades, 7 of clubs, 6 of clubs, 6 of diamonds, A of d
iamonds, 5 of hearts, 9 of spades, J of hearts )

Hand = ( 8 of diamonds, T of hearts, K of hearts, 7 of spades, T of spades, 2 of
 clubs, 9 of diamonds, T of clubs, 5 of diamonds )

Hand = ( 4 of diamonds, A of clubs, 4 of hearts, 2 of hearts, 4 of clubs, 3 of d
iamonds, Q of clubs, 8 of spades )

Hand = ( Q of spades, 2 of spades, 3 of clubs, K of clubs, A of hearts, 7 of dia
monds, 8 of hearts, Q of hearts )

 */
