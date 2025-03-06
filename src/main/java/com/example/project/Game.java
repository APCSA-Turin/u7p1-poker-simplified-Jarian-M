package com.example.project;
import java.util.ArrayList;
import java.util.Scanner;


public class Game{
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        int p1Rank = Utility.getHandRanking(p1Hand);
        int p2Rank = Utility.getHandRanking(p2Hand);
        if(p1Rank > p2Rank) {
            return "Player 1 wins!";
        } else if(p2Rank > p1Rank) {
            return "Player 2 wins!";
        } else {
            p1.SortAllCards();
            p2.SortAllCards();
            for(int i = p1.getAllCards().size() - 1; i >= 0; i--) {
                int p1CardRank = Utility.getRankValue(p1.getAllCards().get(i).getRank());
                int p2CardRank = Utility.getRankValue(p2.getAllCards().get(i).getRank());
                if(p1CardRank > p2CardRank) {
                    return "Player 1 wins!";
                }
                if(p2CardRank > p1CardRank) {
                    return "Player 2 wins!";
                }
            }
            return "Tie!";
        }
        //return "Error";
    }

    public static void play(){ //simulate card playing
        Scanner scan = new Scanner(System.in);
        Player player = new Player();
        Deck deck = new Deck();
        int cardCount = 0;
        System.out.println("Please Draw Your Cards! Type \"draw\" to Draw your Cards:");
        String draw = scan.nextLine();
        while(cardCount < 2) {
            if(draw.equals("draw") && cardCount < 2) {
                player.addCard(deck.drawCard());
                cardCount++;
            } else {
                System.out.println("Please Type \"draw\" to Draw Your Cards:");
                draw = scan.nextLine();
            }
        }
        System.out.println();
        System.out.println("Player Hand:\n");
        deck.printCard(player.getHand().get(0));
        deck.printCard(player.getHand().get(1));  
        
        System.out.println();
        System.out.println("CPU Hand:\n");
        Player cpu = new Player();
        cpu.addCard(deck.drawCard());
        deck.printCard(cpu.getHand().get(0));
        cpu.addCard(deck.drawCard());
        deck.printCard(cpu.getHand().get(1));

        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(deck.drawCard());
        System.out.println("Community Cards: ");
        deck.printCard(communityCards.get(0));
        communityCards.add(deck.drawCard());
        deck.printCard(communityCards.get(1));
        communityCards.add(deck.drawCard());
        deck.printCard(communityCards.get(2));
        
        String handResult1 = player.playHand(communityCards);
        String handResult2 = cpu.playHand(communityCards);
        System.out.println("Type \"reveal\" to Reveal who won the hand!");
        String reveal = scan.nextLine();
        while(!reveal.equals("reveal")) {
            System.out.println("Please Type \"reveal\" to Reveal who won the hand!");
            reveal = scan.nextLine();
        }
        if(reveal.equals("reveal")) {
            System.out.println(determineWinner(player, cpu, handResult1, handResult2, communityCards));
        }  
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        play();
        System.out.println("Would you like to try again? Type \"again\" if you do! If you don't, type \"stop\"");
        String again = scanner.nextLine();      
        while(!again.equals("again") && !again.equals("stop")) {
            System.out.println("Please Type \"again\" or Type \"stop\"");
            again = scanner.nextLine(); 
        }
        while(again.equals("again")) {
            play();
            System.out.println("Would you like to try again? Type \"again\" if you do! If you don't, type \"stop\"");
            again = scanner.nextLine(); 
            while(!again.equals("again") && !again.equals("stop")) {
                System.out.println("Please Type \"again\" or Type \"stop\"");
                again = scanner.nextLine(); 
            }
            if(again.equals("stop")) {
                break;
            } 
        }
        scanner.close();
    }
}