package com.example.project;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;


public class Game{
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        int p1Rank = Utility.getHandRanking(p1Hand);
        int p2Rank = Utility.getHandRanking(p2Hand);
        if(p1Rank > p2Rank) {
            return "Player 1 Wins!";
        } else if(p2Rank > p1Rank) {
            return "Player 2 Wins!";
        } else {
            p1.SortAllCards();
            p2.SortAllCards();
            for(int i = p1.getAllCards().size() - 1; i >= 0; i--) {
                int p1CardRank = Utility.getRankValue(p1.getAllCards().get(i).getRank());
                int p2CardRank = Utility.getRankValue(p2.getAllCards().get(i).getRank());
                if(p1CardRank > p2CardRank) {
                    return "Player 1 Wins!";
                }
                if(p2CardRank > p1CardRank) {
                    return "Player 2 Wins!";
                }
            }
            return "Tie!";
        }
        //return "Error";
    }

    public static void play(){ //simulate card playing
        Player player = new Player();
        player.addCard(new Card("A", "♠"));
        player.addCard(new Card("6", "♦"));
        
        // Community Cards
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("5", "♣"));
        communityCards.add(new Card("2", "♠"));
        communityCards.add(new Card("3", "♠"));
        
        player.playHand(communityCards);
        String handResult = player.playHand(communityCards);
        determineWinner(player, player, handResult, handResult, communityCards);
    }

    public static void main(String[] args) {
        play();
    }
        
        

}