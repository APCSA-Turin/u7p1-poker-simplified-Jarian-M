package com.example.project;
import java.util.ArrayList;

public class Card{
    private String rank; //instance variable rank
    private String suit; //instance variable suit

    public Card(String rank, String suit){
        this.rank = rank; //initializes rank
        this.suit = suit; //initializes suit
    }

    public String getRank(){return rank;} //gets rank
    public String getSuit(){return suit;} //gets suit
    
    @Override
    public String toString(){ //returns the value of the card as a String
        return rank + " of " + suit;
    }

}