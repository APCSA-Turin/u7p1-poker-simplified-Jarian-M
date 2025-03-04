package com.example.project;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import com.example.project.Insertion_Sort.*;

public class TestInsertionSort{
    @Test
    public void testInsertionSort_UnsortedArray() {
        int[] arr = {5, 2, 9, 1, 5};
        int[] expected = {1, 2, 5, 5, 9};
        assertArrayEquals(expected, InsertionSort.insertionSort(arr), "Unsorted array should be sorted");
    }

    @Test
    public void testInsertionSort_AlreadySortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        
        assertArrayEquals(expected, InsertionSort.insertionSort(arr), "Already sorted array should remain unchanged");
    }

    @Test
    public void testInsertionSort_ReverseSortedArray() {
        int[] arr = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        
        assertArrayEquals(expected, InsertionSort.insertionSort(arr), "Reverse sorted array should be sorted");
    }

    @Test
    public void testInsertionSort_SingleElementArray() {
        int[] arr = {42};
        int[] expected = {42};
        
        assertArrayEquals(expected, InsertionSort.insertionSort(arr), "Single-element array should remain unchanged");
    }

    @Test
    public void testInsertionSort_EmptyArray() {
        int[] arr = {};
        int[] expected = {};
        
        assertArrayEquals(expected, InsertionSort.insertionSort(arr), "Empty array should remain unchanged");
    }

    @Test
    public void testSelectionSort_Words(){
        ArrayList<String> words = InsertionSort.loadWordsInto(new ArrayList<String>());
        words = InsertionSort.insertionSortWordList(words);
        boolean pass = true;
        for(int i=1; i < words.size();i++){
            if(words.get(i).compareTo(words.get(i-1))<0){ //not in alphabetical order
                pass = false; 
                break;
            }
        }
        assertTrue(pass,"At least one word is not in alphabetical order");
    }

}