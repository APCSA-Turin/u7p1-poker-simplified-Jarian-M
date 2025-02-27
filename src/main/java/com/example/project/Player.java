package com.example.project;
import java.util.ArrayList;
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
        ArrayList<Integer> temp1 = new ArrayList<Integer>();
        ArrayList<String> temp2 = new ArrayList<String>();
        for(int j = 0; j < 5; j++) {
            temp1.add(Utility.getRankValue(allCards.get(j).getRank()));
            temp2.add(allCards.get(j).getSuit());
        }   
        int count = 0;
        for(int temp: temp1) {

        }
        return "Nothing";
    }

    public void SortAllCards(){
        allCards.sort(Comparator.comparingInt(eachCard -> Utility.getRankValue(eachCard.getRank())));
    } 

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> freq = new ArrayList<Integer>(13);
        return new ArrayList<>(); 
    }

    public ArrayList<Integer> findSuitFrequency(){
        return new ArrayList<>(); 
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}
