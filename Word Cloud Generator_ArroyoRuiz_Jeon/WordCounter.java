/**
 * WordCounter.java
 * Yeseo Jeon, Jared Arroyo Ruiz
 * 
 * This class contains the main program and support methods. This class opens
 * a text file, counts all the words the file contains, and produces one of
 * the three outputs.
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class WordCounter {

    /**
    * Our main method that opens the text file .
    */
    public static void main(String arg[]) {

        WordCountMap map = new WordCountMap();
        ArrayList<String> stopWordsList = new ArrayList<String>(82);

        try {
            File stopWords = new File("StopWords.txt");
            Scanner input = new Scanner(stopWords);
    
            while (input.hasNext()) {
                String word = input.next();
                word = word.replaceAll("[^a-zA-Z]","");

                stopWordsList.add(word);
                
            }
        }
                
            catch (Exception e) {
                e.printStackTrace();
            }
            

        try {
        File file = new File(arg[1]);
        Scanner input = new Scanner(file);

        while (input.hasNext()) {
            String word = input.next();
            word = word.replaceAll("[^a-zA-Z]","");
        

            // for (int i = 0; i < stopWordsList.size(); i++){

            //     if (word.equals(stopWordsList.get(i))) {
            //        continue;
            //     }

            // }
            
            map.incrementCount(word);
        }
    }
            
        catch (Exception e) {
            e.printStackTrace();
        }
        

        if (arg[0].equals("alphabetical")) {
            ArrayList<WordCount> newList = map.getWordCountsByWord();
            for (int i = 0; i < newList.size(); i++ ){
                System.out.println(newList.get(i).getWord() + ": " + newList.get(i).getCount());
            }
        }

        if (arg[0].equals("frequency")) {
            ArrayList<WordCount> newList = map.getWordCountsByCount();
            for (int i = 0; i < newList.size(); i++ ){
                System.out.println(newList.get(i).getWord() + ": " + newList.get(i).getCount());
            }
        }

        if (arg[0].equals("cloud")) {

            
            
            int numberOfWords;
            numberOfWords = Integer.parseInt(arg[2]);

            ArrayList<WordCount> newList = map.getWordCountsByCount();
            ArrayList<WordCount> cloudList = new ArrayList<>(newList.size());

            for (int i = 0; i < newList.size(); i++) {

                String word = newList.get(i).getWord();

                if (stopWordsList.contains(word) == false) {

                    cloudList.add(newList.get(i));
                }

            }

            ArrayList<WordCount> newCloudList = new ArrayList<>(numberOfWords);

            for (int i = 0; i <= numberOfWords; i++) {

                newCloudList.add(cloudList.get(i));
            }
            
            System.out.println(WordCloudMaker.getWordCloudHTML("cloud", newCloudList));
            }
        

    }
    }
