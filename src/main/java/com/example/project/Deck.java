package com.example.project;
import java.util.ArrayList;
import java.util.Collections;

public class Deck{
    private ArrayList<Card> cards; //instance variable ArrayList cards

    public Deck(){
        cards = new ArrayList<>(); //Initializes the cards ArrayList
        initializeDeck(); //Initializes deck using the cards ArrayList
        shuffleDeck(); //Shuffles deck to simulate how decks are shuffled in real life before the start of games
    }

    public ArrayList<Card> getCards(){ //returns the cards ArrayList
        return cards;
    }

    public void initializeDeck(){ //hint.. use the utility class. Initializes the deck
        for(String suit: Utility.getSuits()) { //iterates through each suit in the suits ArrayList from the Utility class
            for(String rank: Utility.getRanks()) { //iterates through each rank in the ranks ArrayList from the Utility class
                cards.add(new Card(rank, suit)); //simulates a playing card deck from real life by adding 13 card ranks for each suit, resulting in a cards ArrayList with 52 elements (playing cards)
            }
        }
    }

    public  void shuffleDeck(){ //You can use the Collections library or another method. You do not have to create your own shuffle algorithm. Shuffles the deck
        Collections.shuffle(cards); //Tyler tought be how to use Collections as a simple way of shuffling the cards ArrayList
    }

    public Card drawCard(){ //returns a drawn card from the deck, discarding the drawn card from the deck
        Card draw = cards.get(0); //initializes the Card variable draw to the first element of the cards ArrayList
        cards.remove(0); //discards the drawn card from the cards ArrayList
        return draw; //returns the drawn card
    }

    public void printCard(Card cardDrawn) { //takes in a Card parameter and prints it in terminal as a String
        String printCard = "";
        printCard += "┌──────────┐\n"; //I took this template from here: https://stackoverflow.com/questions/53578015/need-ascii-playing-cards-to-print-on-one-line 
        printCard += "|  " + cardDrawn.getRank() + "   " + cardDrawn.getSuit() + "\n"; //I kept the cards on separate lines rather than all horizontal so that I can print each card with cardDrawn.getRank() and cardDrawn.getSuit()
        printCard += "|          |\n";
        printCard += "|    J     |\n";
        printCard += "|          |\n";
        printCard += "|  " + cardDrawn.getSuit() + "   " + cardDrawn.getRank() + "\n";
        printCard += "└──────────┘\n";
        System.out.println(printCard); //prints the card drawn
    }

    public  boolean isEmpty(){ //returns empty when the deck is empty
        return cards.isEmpty();
    }
}