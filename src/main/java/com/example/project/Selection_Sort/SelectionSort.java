package com.example.project.Selection_Sort;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class SelectionSort {

    // PART A. implementing selection sort
    public static int[] selectionSort(int[] elements) {
        for (int j = 0; j < elements.length - 1; j++) {
            int minIndex = j;
            for (int k = j + 1; k < elements.length; k++) {
                if (elements[k] < elements[minIndex]) {
                    minIndex = k;
                }
            }
            int temp = elements[j];
            elements[j] = elements[minIndex];
            elements[minIndex] = temp;
        }

        return elements;
    }


    // PART B. sorting a 1000-word list
    public static ArrayList<String> selectionSortWordList(ArrayList<String> words) {
        for (int j = 0; j < words.size() - 1; j++) {
            int minIndex = j;
            for (int k = j + 1; k < words.size(); k++) {
                if (words.get(k).compareTo(words.get(minIndex)) < 0) {
                    minIndex = k;
                }
            }
            String temp = words.get(j);
            words.set(j, words.get(minIndex));
            words.set(minIndex, temp);
        }
        return words;
    }

    //call this method to load 1000 words into list. Use it to test Part B
    public static ArrayList<String> loadWordsInto(ArrayList<String> wordList) {
        try {
            Scanner input = new Scanner(new File("src/main/java/com/example/project/Selection_Sort/words.txt"));
            while (input.hasNext()) {
                String word = input.next();
                wordList.add(word);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return wordList;
    }
}