package com.example.project;
import java.util.ArrayList;
import java.util.Scanner;


public class Game{
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){ //determineWinner method with 5 parameters: Player p1, Player p2, String p1Hand, String p2Hand, ArrayList communityCards
        int p1Rank = Utility.getHandRanking(p1Hand); //initializes the int variable p1Rank to the hand ranking of the parameter p1Hand
        int p2Rank = Utility.getHandRanking(p2Hand); //initializes the int variable p2Rank to the hand ranking of the parameter p2Hand
        if(p1Rank > p2Rank) { //if the value of the hand ranking of player 1 is greater than that of player 2, "Player 1 wins!" is returned
            return "Player 1 wins!";
        } else if(p2Rank > p1Rank) { //if the value of the hand ranking of player 2 is greater than that of player 1, "Player 2 wins!" is returned
            return "Player 2 wins!";
        } else {
            p1.SortAllCards(); //sorts the allCards ArrayList of player 1
            p2.SortAllCards(); //sorts the allCards ArrayList of player 2
            for(int i = p1.getAllCards().size() - 1; i >= 0; i--) { //iterates through the allCards ArrayList of player 1 by decrementing by 1 until the index is less than 0.
                int p1CardRank = Utility.getRankValue(p1.getAllCards().get(i).getRank()); //initializes the int variable p1CardRank to the rank value of the rank of the card in the allCards ArrayList of player 1 at index i.
                int p2CardRank = Utility.getRankValue(p2.getAllCards().get(i).getRank()); //initializes the int variable p2CardRank to the rank value of the rank of the card in the allCards ArrayList of player 2 at index i.
                if(p1CardRank > p2CardRank) { //if p1CardRank > p2CardRank, "Player 1 wins!" is returned (both players had the same hand ranking but player 1 had a higher ranking card in their hand)
                    return "Player 1 wins!";
                }
                if(p2CardRank > p1CardRank) { //if p2CardRank > p1CardRank, "Player 2 wins!" is returned (both players had the same hand ranking but player 2 had a higher ranking card in their hand)
                    return "Player 2 wins!";
                }
            }
            return "Tie!"; //"Tie!" is returned if both players had the same hand ranking and card ranking
        }
    }

    public static void play(){ //simulates card playing
        Scanner scan = new Scanner(System.in); //used to take in user input
        Player player = new Player(); //initializes the player
        Deck deck = new Deck(); //initializes the deck
        int cardCount = 0; //initializes the int variable cardCount to 0
        System.out.println("Please Draw Your Cards! Type \"draw\" to Draw your Cards:"); //tells the user to type an input "draw" in order to draw their cards
        String draw = scan.nextLine();
        while(cardCount < 2) { //loops to make sure that the correct input was typed
            if(draw.equals("draw") && cardCount < 2) { //if the correct input is typed, both cards are drawn immediately
                player.addCard(deck.drawCard());
                cardCount++;
            } else {
                System.out.println("Please Type \"draw\" to Draw Your Cards:"); //if the incorrect input is typed, the user is asked to type the correct input and the loops iterates
                draw = scan.nextLine();
            }
        }
        System.out.println();
        System.out.println("Player Hand:\n"); //Informs the user which hand is theirs
        deck.printCard(player.getHand().get(0)); //prints the first card in the user's hand
        deck.printCard(player.getHand().get(1)); //prints the second card in the user's hand
        
        System.out.println();
        System.out.println("CPU Hand:\n"); //Informs the user the cpu's hand
        Player cpu = new Player(); //initializes the second player (the cpu), who will go against player 1
        cpu.addCard(deck.drawCard()); //draws a card for the cpu's hand
        deck.printCard(cpu.getHand().get(0)); //prints the drawn card in the cpu's hand
        cpu.addCard(deck.drawCard()); //draws a second card for the cpu's hand
        deck.printCard(cpu.getHand().get(1)); //prints the second drawn card in the cpu's hand

        ArrayList<Card> communityCards = new ArrayList<>(); //initializes the communityCards ArrayList
        communityCards.add(deck.drawCard()); //draws a card and adds it to the communityCards ArrayList
        System.out.println("Community Cards: "); //Informs the user about where the Community Cards are
        deck.printCard(communityCards.get(0)); //prints the drawn card in the communityCards ArrayList
        communityCards.add(deck.drawCard()); //draws a second card and adds it to the communityCards ArrayList
        deck.printCard(communityCards.get(1)); //prints the second drawn card in the communityCards ArrayList
        communityCards.add(deck.drawCard()); //draws a third card and adds it to the communityCards ArrayList
        deck.printCard(communityCards.get(2)); //prints the third drawn card in the communityCards ArrayList
        
        String handResult1 = player.playHand(communityCards); //initializes the String variable handResult1 as the value of player.playHand(communityCards)
        String handResult2 = cpu.playHand(communityCards); //initializes the String variable handResult2 as the value of cpu.playHand(communityCards)
        System.out.println("Type \"reveal\" to Reveal who won the hand!"); //tells the user to type an input "reveal" in order to reveal whos hand won
        String reveal = scan.nextLine();
        while(!reveal.equals("reveal")) { //if the user's input isn't equal to "reveal," the user is told to type the correct input until the correct input is typed by the user
            System.out.println("Please Type \"reveal\" to Reveal who won the hand!");
            reveal = scan.nextLine();
        }
        if(reveal.equals("reveal")) { //if the correct input is typed, the value of determineWinner(player, cpu, handResult1, handResult2, communityCards) is printed, which will print who won the hand
            System.out.println(determineWinner(player, cpu, handResult1, handResult2, communityCards));
        }  
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //used to take in user input
        play(); //starts the hand ranking game
        System.out.println("Would you like to try again? Type \"again\" if you do! If you don't, type \"stop\""); //when the hand ranking game ends, the user is prompted to either end the game by inputting "stop" or play another round by inputting "again"
        String again = scanner.nextLine();      
        while(!again.equals("again") && !again.equals("stop")) { //if the user types any input other than "stop" or "again," the user is told to type either "again" or "stop" until the correct input is typed
            System.out.println("Please Type \"again\" or Type \"stop\"");
            again = scanner.nextLine(); 
        }
        while(again.equals("again")) { //if the user's input is equal to "again," the game restarts
            play();
            System.out.println("Would you like to try again? Type \"again\" if you do! If you don't, type \"stop\""); //when the game ends the user is prompted once again whether than want to stop or play another round. Thus, the user is able to play continuously and stop whenever they feel like it
            again = scanner.nextLine(); 
            while(!again.equals("again") && !again.equals("stop")) { //uses the same code as before for preventing the user from inputting the incorrect inputs. Only the inputs "again" and "stop" are accepted
                System.out.println("Please Type \"again\" or Type \"stop\"");
                again = scanner.nextLine(); 
            }
            if(again.equals("stop")) { //if the user's input is equal to "stop," the while loop is broken and the program ends
                break;
            } 
        }
        scanner.close();
    }
}