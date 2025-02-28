package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;
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
        sortCards();
    }

    private boolean isRoyalFlush() {

    }

    private boolean isStraightFlush() {

    }

    private boolean isFourOfAKind() {

    }

    private boolean isFullHouse() {

    }

    private boolean isFlush() {

    }

    private boolean isStraight() {
        int count = 1;
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
        sortAllCards();
        if(hand.contains(allCards.get(4))) {
            return true;
        }
        return false;
    }

    public void SortAllCards(){
        allCards.sort(Comparator.comparingInt(eachCard -> Utility.getRankValue(eachCard.getRank())));
    } 

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> freq = new ArrayList<Integer>(Collections.nCopies(13, 0));
        ArrayList<String> temp = Arrays.aslist(ranks);
        SortAllCards();
        int count = 1;
        for(int i = 0; i < allCards.size(); i++) {
            if(Utility.getRankValue(allCards.get(i)) == Utility.getRankValue(allCards.get(i + 1))) {
                count++;
            } else {
                int ind = temp.indexOf(allCards.get(i).getRank());
                freq.add(ind, count);
                count = 1;
            }
        }
        return freq; 
    }

    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> freq = new ArrayList<Integer>(Collections.nCopies(4, 0));
        ArrayList<String> temp = Arrays.aslist(suits);
        SortAllCards();
        int count = 1;
        for(int i = 0; i < allCards.size(); i++) {
            if(allCards.get(i).getSuit().equals(allCards.get(i + 1).getSuit())) {
                count++;
            } else {
                int ind = temp.indexOf(allCards.get(i).getSuit());
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
