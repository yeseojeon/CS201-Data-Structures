/**
 * WordCountMap.java
 * Yeseo Jeon, Jared Arroyo Ruiz
 * 
 * This class consists of a binary search tree that maintains a list of words
 * and their counts.
 */


import java.util.ArrayList;
import java.util.Collections;

public class WordCountMap {

    private TreeNode root;
    
    private class TreeNode {

        private WordCount wordCount;
        private TreeNode left;
        private TreeNode right;

        private TreeNode (String word, int count) {

            this.wordCount = new WordCount(word, count);
            left = null;
            right = null;
        }
    }

    public WordCountMap() {

        root = null; 
    }

    /**
    * Returns true if a word is contained in the WordCountMap
    * and false otherwise.
    @param word that needs to be checked
    */
    public boolean contains(String word) {

        return containsHelper(word, root);
    }

    
    /**
    * Helper for the contains method.
    @param word that needs to be checked
    @param node the first node in the tree.
    */
    public boolean containsHelper(String word, TreeNode node) {
        if (node == null){
            return false;
        } 
        else if (node.wordCount.getWord() == word){
            return true;
        }
        else {
            int comparison = node.wordCount.getWord().compareTo(word);

            if (comparison > 0) {
                return containsHelper(word, node.left);
            } 
            else {
                return containsHelper(word, node.right);
            }
        }
    }

    /**
    * If the specified word is in this WordCountMap, then its count increases by 
    one. If not, the word is added to this map with count 1.
    @param word that needs to be checked.
    */
    public void incrementCount(String word) {
        incrementCountHelper(word, root, root);
    }


    /**
    * Heplper method for the incrementCount method.
    @param word that needs to be checked
    @param node that is added
    @param lastNode that is the previous node.
    */

    public void incrementCountHelper(String word, TreeNode node, TreeNode lastNode){
        if (root == null){
            TreeNode newNode = new TreeNode(word, 1);
            root = newNode;
            return;
        } 
        else if (node == null) {
            TreeNode newNode = new TreeNode(word, 1);
            int comparison = lastNode.wordCount.getWord().compareTo(word);

            if (comparison > 0) {
                lastNode.left = newNode;
            } 
            else {
                lastNode.right = newNode;
            }
        }
        else if (node.wordCount.getWord().equals(word)){
            node.wordCount.incrementCount();
        }
        else {
            int comparison = node.wordCount.getWord().compareTo(word);

            if (comparison > 0) {
                incrementCountHelper(word, node.left, node);
            } 
            else {
                incrementCountHelper(word, node.right, node);
            }
        }
    }


    /**
    * Returns one word of each word sotred as a list, sorted
    by decreasing order.
    */
    public ArrayList<WordCount> getWordCountsByCount() {
        ArrayList<WordCount> newList = new ArrayList<WordCount>();

        getWordCountsByCountHelper(root, newList);

        Collections.sort(newList);
        Collections.reverse(newList);

        return newList;
    }

    /**
    * Helper method for the getWordCountsByCount method
    @param node that is the first node
    @param list that is created inside the method
    */

    public void getWordCountsByCountHelper(TreeNode node, ArrayList<WordCount> list) {
        
        if (node != null) {
            getWordCountsByCountHelper(node.left, list);
            list.add(node.wordCount);
            getWordCountsByCountHelper(node.right,list);
        }        
    }


    /**
    * Returns a list of WordCount objects, one per word stored in this
    * WordCountMap, sorted alphabetically by word.
    */
    public ArrayList<WordCount> getWordCountsByWord() {
        ArrayList<WordCount> newList = new ArrayList<WordCount>();

        getWordCountsByWordHelper(root, newList);

        return newList;
    }


    /**
    * Helper method for the getWordCountsByWord method
    @param node the first node
    @param list that is created in the method.
    */
    public void getWordCountsByWordHelper(TreeNode node, ArrayList<WordCount> list) {
        
        if (node != null) {
            getWordCountsByWordHelper(node.left, list);
            list.add(node.wordCount);
            getWordCountsByWordHelper(node.right,list);
        }
    }
}


