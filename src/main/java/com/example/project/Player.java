package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Player{
    private ArrayList<Card> hand; //instance variable ArrayList hand
    private ArrayList<Card> allCards; //instance variable ArrayList allCards, which represents the current community cards + hand
    String[] suits  = Utility.getSuits(); //initializes the array suits to Utility.getSuits()
    String[] ranks = Utility.getRanks(); //initializes the array ranks to Utility.getRanks()
    
    public Player(){
        hand = new ArrayList<>(); //initializes the instance varibale hand
        allCards = new ArrayList<>(); //initializes the instance variable allCards
    }

    public ArrayList<Card> getHand(){return hand;} //returns hand
    public ArrayList<Card> getAllCards(){return allCards;} //returns allCards

    public void addCard(Card c){ //adds a card, c, to the Array hand
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){ //takes in a parameter ArrayList community Cards and returns the hand ranking of allCards
        allCards = new ArrayList<>(hand); //resets allCards to an ArrayList with values of the ArrayList hand
        for(int i = 0; i < 3; i++) { //adds each card in the parameter communityCards to allCards using index iteration
            allCards.add(communityCards.get(i));
        }
        SortAllCards(); //sorts allCards
        if(isRoyalFlush()) return "Royal Flush"; //returns "Royal Flush" if isRoyalFlush() is true
        if(isStraightFlush())return "Straight Flush"; //returns "Straight Flush" if isStraightFlush() is true
        if(isFourOfAKind()) return "Four of a Kind"; //returns "Four of a Kind" if isFourOfAKind() is true
        if(isFullHouse()) return "Full House"; //returns "Full House" if isFullHouse() is true
        if(isFlush() && !isStraight()) return "Flush"; //returns "Flush" if both isFlush() is true and isStraight() is false
        if(isStraight()) return "Straight"; //returns "Straight" if isStraight() is true
        if(isThreeOfAKind()) return "Three of a Kind"; //returns "Three of a Kind" if isThreeOfAKind() is true
        if(isTwoPair()) return "Two Pair"; //returns "Two Pair" if isTwoPair() is true
        if(isOnePair()) return "A Pair"; //returns "A Pair" if isOnePair() is true
        if(isHighCard()) {
            return "High Card"; //returns "High Card" if isHighCard() is true
        } else {
            return "Nothing"; //returns "Nothing" if isHighCard() is false
        }
    }

    private boolean isRoyalFlush() {
        SortAllCards(); //sorts allCards
        if(isStraightFlush() && allCards.get(4).getRank().equals("A")) { //returns true if isStraightFlush() is true and the last Card in the allCards ArrayList has a rank of "A," returns false otherwise
            return true;
        }
        return false;
    }

    private boolean isStraightFlush() { //returns true if both isStraight() and isFlush() is true, returns false otherwise
        if(isStraight() && isFlush()) {
            return true;
        }
        return false;
    }

    private boolean isFourOfAKind() { //iterates through the ArrayList returned by findRankingFrequency() and checks if the ArrayList contains an element equal to 4 (a rank has a frequency of 4/ 4 cards have the same rank), if it does, it returns true, if it doesn't, it returns false
        for(int i = 0; i < findRankingFrequency().size(); i++) {
            if(findRankingFrequency().get(i) == 4) {
                return true;
            }
        }
        return false;
    }

    private boolean isFullHouse() { //returns true if both isThreeOfAKind() and isOnePair() is true, returns false otherwise
        if(isThreeOfAKind() && isOnePair()) {
            return true;
        }
        return false;
    }

    private boolean isFlush() { //initializes a boolean variable sameSuit to true and iterates through the allCards ArrayList. if the suit of the card in allCards at index i is not equal to the suit of the card in allCards at index i + 1, sameSuit is set to false. sameSuit is returned
        boolean sameSuit = true;
        for(int i = 0; i < allCards.size() - 1; i++) {
            if(allCards.get(i).getSuit() != allCards.get(i + 1).getSuit()) {
                sameSuit = false;
            }
        }
        return sameSuit;
    }

    private boolean isStraight() { //initializes an int variable count to 1 and iterates through the allCards ArrayList. if the rank value of the card in allCards at index i is one less than the rank value of the card in allCards at index i + 1, count is incremented by one. If count is equal to 5, true is returned, otherwise false is returned
        SortAllCards(); //sorts allCards
        int count = 1;
        for(int i = 0; i < allCards.size() - 1; i++) {
            if(Utility.getRankValue(allCards.get(i).getRank()) == Utility.getRankValue(allCards.get(i + 1).getRank()) - 1) {
                count++;
            }
        }
        if(count == 5) {
            return true;
        }
        return false;
    }

    private boolean isThreeOfAKind() { //iterates through the ArrayList returned by findRankingFrequency() and checks if the ArrayList contains an element equal to 3 (a rank has a frequency of 3/ 3 cards have the same rank) using the doesContain() method I created. If it does, it returns true, if it doesn't, it returns false
        if(doesContain(findRankingFrequency(), 3)) {
            return true;
        }
        return false;
    }

    private boolean isTwoPair() { //initializes an int variable count to 0 and iterates through the allCards ArrayList except for the last card in allCards. If the rank of the card in allCards at index i is equal to the rank of the card in allCards at index i + 1, count is incremented by 1. If count is equal to 2, true is returned, otherwise, false is returned.
        int count = 0;
        for(int i = 0; i < allCards.size() - 1; i++) {
            if(allCards.get(i).getRank() == allCards.get(i + 1).getRank()) {
                count++;
            }
        }
        if(count == 2) {
            return true;
        }
        return false;
    }

    private boolean isOnePair() { //iterates through the ArrayList returned by findRankingFrequency() and checks if the ArrayList contains an element equal to 2 (a rank has a frequency of 2/ 2 cards have the same rank) using the doesContain() method I created. If it does, it returns true, if it doesn't, it returns false
        if(doesContain(findRankingFrequency(), 2)) {
            return true;
        }
        return false;
    }

    private boolean isHighCard() { //iterates through the ArrayList hand and checks if a card in hand is equal to the card of the greatest rank in allCards (the last card in the sorted allCards ArrayList). If a card in hand is equal to the card of the greatest rank, true is returned, otherwise false is returned.
        SortAllCards(); //sorts allCards
        for(int i = 0; i < hand.size(); i++) {
            if(hand.get(i) == allCards.get(4)) {
                return true;
            }
        }
        return false;
    }

    public void SortAllCards(){ //Utilizes insertion sort to sort the cards in the allCards ArrayList. I used the insertion sort lab to aid me in sorting the cards. Replaces the ArrayList for allCards, and the elements being compared for Utility.getRankValue(allCards.get().getRank())
        for(int i = 1; i < allCards.size(); i++) {
            int count = i;
            while(count > 0 && Utility.getRankValue(allCards.get(i).getRank()) < Utility.getRankValue(allCards.get(i - 1).getRank())) {
                Card temp = allCards.get(count);
                allCards.set(count, allCards.get(count - 1));
                allCards.set(count - 1, temp);
                count--;
            }
        }
    } 

    public boolean doesContain(ArrayList<Integer> temp, int look) { //I created my own method to check if elements were contained in an ArrayList. 
        for(int i = 0; i < temp.size(); i++) { //iterates through the ArrayList parameter temp using index iteration and checks if the element in temp at index i is equal to the int parameter look. If they are equal, true is returned, otherwise false is returned.
            if(temp.get(i) == look) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> freq = new ArrayList<Integer>(Collections.nCopies(13, 0)); //initializes the ArrayList freq to a new ArrayList of 13 elements, each equal to 0
        
        for(Card card: allCards) { //iterates through each card in allCards and checks if the rank value of the card in allCard is within the accepted range (rankVal >= 2 and <= 14). If the rank value of the card is in the accepted range, the rank value is subtracted by 2 so that the rank values are fixed and the smallest rank value is equal to 0 (card of rank 2), allowing them to be used as indices
            int rankVal = Utility.getRankValue(card.getRank());

            if(rankVal >= 2 && rankVal <= 14) {
                int fixedRank = rankVal - 2;
                freq.set(fixedRank, freq.get(fixedRank) + 1); //sets the element at index of rank value of the card in allCard - 2 to the value of the element at index of rank value of the card in allCard - 2 plus 1 (the value is incremented by 1 to increase the frequency)
            } 
        }
        return freq; 
    }

    public ArrayList<Integer> findSuitFrequency(){ //initializes the ArrayList freq to a new ArrayList of 4 elements, each equal to 0
        SortAllCards(); //sorts allCards
        ArrayList<Integer> freq = new ArrayList<Integer>(Collections.nCopies(4, 0)); 
        
        for(Card card: allCards) { //iterates through each card in allCards
            int suitInd = Arrays.asList(suits).indexOf(card.getSuit()); //initializes the int varibale suitInd as the index of the suit of the card in the suits array
            freq.set(suitInd, freq.get(suitInd) + 1); //sets the element at index of suitInd to the value of the element at index suitInd incremented by 1 (the value is incremented by 1 to increase the frequency)
        }
        return freq;
    }

    public String toString(){ //returns the value of hand as a String
        return hand.toString();
    }
}
