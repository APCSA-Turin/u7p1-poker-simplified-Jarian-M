package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
        allCards.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){   
        for(int i = 0; i < 3; i++) {
            allCards.add(communityCards.get(i));
        }
        SortAllCards();
        if(isRoyalFlush()) return "Royal Flush";
        if(isStraightFlush())return "Straight Flush";
        if(isFourOfAKind()) return "Four of a Kind";
        if(isFullHouse()) return "Full House";
        if(isFlush()) return "Flush";
        if(isStraight()) return "Straight";
        if(isThreeOfAKind()) return "Three of a Kind";
        if(isTwoPair()) return "Two Pair";
        if(isOnePair()) return "A Pair";
        if(isHighCard()) {
            return "High Card";
        } else {
            return "Nothing";
        }
    }

    private boolean isRoyalFlush() {
        SortAllCards();
        if(isStraightFlush() && allCards.get(4).getRank().equals("A")) {
            return true;
        }
        return false;
    }

    private boolean isStraightFlush() {
        if(isStraight() && isFlush()) {
            return true;
        }
        return false;
    }

    private boolean isFourOfAKind() {
        if(findRankingFrequency().contains(4)) {
            return true;
        }
        return false;
    }

    private boolean isFullHouse() {
        if(isThreeOfAKind() && isOnePair()) {
            return true;
        }
        return false;
    }

    private boolean isFlush() {
        boolean sameSuit = true;
        for(int i = 0; i < allCards.size() - 1; i++) {
            if(allCards.get(i).getSuit() != allCards.get(i + 1).getSuit()) {
                sameSuit = false;
            }
        }
        return sameSuit;
    }

    private boolean isStraight() {
        int count = 0;
        for(int i = 0; i < allCards.size() - 1; i++) {
            if(allCards.get(i).getRank() == allCards.get(i + 1).getRank()) {
                count++;
            }
        }
        if(count == 5) {
            return true;
        }
        return false;
    }

    private boolean isThreeOfAKind() {
        if(findRankingFrequency().contains(3)) {
            return true;
        }
        return false;
    }

    private boolean isTwoPair() {
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

    private boolean isOnePair() {
        if(findRankingFrequency().contains(2)) {
            return true;
        }
        return false;
    }

    private boolean isHighCard() {
        SortAllCards();
        if(hand.contains(allCards.get(4))) {
            return true;
        }
        return false;
    }

    public void SortAllCards(){
        //allCards.sort(Comparator.comparingInt(eachCard -> Utility.getRankValue(eachCard.getRank())));
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

    public ArrayList<Integer> findRankingFrequency(){
        SortAllCards();
        ArrayList<Integer> freq = new ArrayList<Integer>(Collections.nCopies(13, 0));
        ArrayList<String> tempRank = new ArrayList<>();
        for(Card card: allCards) {
            tempRank.add(card.getRank());
        }
        int count = 1;
        for(int i = 0; i < allCards.size(); i++) {
            if(Utility.getRankValue(allCards.get(i).getRank()) == Utility.getRankValue(allCards.get(i + 1).getRank())) {
                count++;
            } else {
                int ind = tempRank.indexOf(allCards.get(i).getRank());
                freq.add(ind, count);
                count = 1;
            }
        }
        return freq; 
    }

    public ArrayList<Integer> findSuitFrequency(){
        SortAllCards();
        ArrayList<Integer> freq = new ArrayList<Integer>(Collections.nCopies(4, 0));
        ArrayList<String> tempSuit = new ArrayList<>();
        for(Card card: allCards) {
            tempSuit.add(card.getSuit());
        }
        int count = 1;
        for(int i = 0; i < allCards.size(); i++) {
            if(allCards.get(i).getSuit().equals(allCards.get(i + 1).getSuit())) {
                count++;
            } else {
                int ind = tempSuit.indexOf(allCards.get(i).getSuit());
                freq.add(ind, count);
                count = 1;
            }
        }
        return freq;
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}
