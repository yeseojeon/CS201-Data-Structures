import java.util.NoSuchElementException;
/**
 * A Sorted Array List Data Structure.
 * @authors Maddy Brown and Yeseo Jeon
 * Date: 1/19/2023
 */

/**
 * The SortedArrayList class uses arrays to simulate a sorted list.
 * The list is sorted from smallest to largest.
 */
public class SortedArrayList {
    // Here we initialize our instance variables.
    private int[] data;
    private int size;
    private int capacity;
    int placementIndex;
    /**
     * This is the constructor for the SortedArrayList class.
     * It initializes the data array to a given initial size.
     * @param cap the initial size of the data array 
     */
    public SortedArrayList(int cap) {
        // This is an unchecked exception. We handle it in a try/catch block
        // in our main method to verify that the exception is thrown (per
        // Slack instructions), but it's not mandated that we handle
        // the exception because it's unchecked.
        if (cap < 0){
            throw new NegativeArraySizeException("An array cannot have a size of " + cap + ".");
        }
        size = 0;
        capacity = cap;
        data = new int[cap];
    }
    /**
     * The getMin() method returns the smallest value in the sorted array.
     * An exception is thrown if the size of the array is 0.
     * @return the first (smallest) item in the sorted array
     */
    public int getMin(){
        if (size == 0){
            throw new NoSuchElementException("Cannot get the minimum value of a list of size 0.");
        }
        return data[0];
    }
    /**
     * The getMax() method returns the largest value in the sorted array.
     * An exception is thrown if the size of the array is 0.
     * @return the last (largest) item in the sorted array
     */
    public int getMax(){
        if (size == 0){
            throw new NoSuchElementException("Cannot get the maximum value of a list of size 0.");
        }
        return data[size - 1];
    }
    /**
     * The getSize() method returns the number of items
     * currently stored in the SortedArrayList object.
     * @return the size of the sorted array
     */
    public int getSize(){
        return size;
    }
    /**
     * The deleteMin() method deletes the smallest value in the sorted array.
     * An exception is thrown if the size of the array is 0.
     * @return the smallest value in the sorted array
     */
    public int deleteMin(){
        if (size == 0){
            throw new NoSuchElementException("Cannot delete the minimum value of a list of size 0.");
        }
        int minimumValue = data[0];
        for (int i = 0; i < size - 1; i++){
            data[i] = data[i + 1];
        }
        size--;
        return minimumValue;
    }
    /**
     * The deleteMax() method deletes the largest value in the sorted array.
     * An exception is thrown if the size of the array is 0.
     * @return the largest value in the sorted array
     */
    public int deleteMax(){
        if (size == 0){
            throw new NoSuchElementException("Cannot delete the maximum value of a list of size 0.");
        }
        int maximumValue = data[size - 1];
        size--;
        return maximumValue;
    }
    /**
     * The contains(int x) method checks if a certain value is in the
     * SortedArrayList object. If the value is present within the sorted
     * array, it returns true. Otherwise, it returns false.
     * @param x the value that the method searches for
     * @return whether or not the value is in the sorted array
     */
    public boolean contains(int x){
        for (int i = 0; i < size; i++){
            if (data[i] == x){
                return true;
            }
        }
        return false;
    }
    /**
     * The add(int x) method adds a value to the array in numerical order.
     * @param x the value to be added
     */
    public void add(int x){
        // If the array is at its capacity, resize it before
        // adding the value.
        if (size == capacity){
            resizeArray();
        }
        if (size == 0){
            data[0] = x;
        }
        for(int i = 0; i < size; i++){
            if(i == 0 && data[i] > x){
                placementIndex = 0;
            }
            else if(data[i] <= x){
                placementIndex = i + 1;
            }
        }
        for (int k = size - 1; k >= placementIndex; k--){
            data[k + 1] = data[k];
        }
        data[placementIndex] = x;
        size++;
    }
    /**
     * The resizeArray() method expands the array by a factor of two.
     * It creates a new array, copies over the values from the old array,
     * and then assigns the new array to the variable name of the 
     * old array.
     */
    private void resizeArray(){ // have to copy all old values over into new array
        capacity = capacity * 2;
        int[] newArray = new int[capacity];
        for (int i = 0; i < size; i++){
            newArray[i] = data[i];
        }
        data = newArray;
    }
    /**
     * The toString() method converts the SortedArrayList object
     * into an interpretable String object that can be printed.
     * @return a string that describes the SortedArrayList
     */
    public String toString() {
        String val = "[";
        for (int i = 0; i < size; i++) {
            val = val + data[i];
            
            if (i < size - 1) {
                val = val + ", ";
            }
        }
        val = val + "]";
        return val;
    }
    public static void main(String[] args){
        SortedArrayList myArrayList = new SortedArrayList(6);
        // Tests all of our methods on an empty list
        System.out.println(myArrayList.toString());
        try{
            System.out.println("Minimum value: " + myArrayList.getMin());
        } catch (NoSuchElementException e){
            System.out.println("NoSuchElementException: " + e.getMessage());
        }
        try{
            System.out.println("Maximum value: " + myArrayList.getMax());
        } catch (NoSuchElementException e){
            System.out.println("NoSuchElementException: " + e.getMessage());
        }
        try{
            myArrayList.deleteMin();
        } catch (NoSuchElementException e){
            System.out.println("NoSuchElementException: " + e.getMessage());
        }
        try{
            myArrayList.deleteMax();
        } catch (NoSuchElementException e){
            System.out.println("NoSuchElementException: " + e.getMessage());
        }
        System.out.println("Size: " + myArrayList.getSize());
        System.out.println("Contains 2: " + myArrayList.contains(2));
        System.out.println("Contains 0: " + myArrayList.contains(0));
        // Tests adding an element at the beginning of the list
        myArrayList.add(4);
        myArrayList.add(8);
        myArrayList.add(1); // Adds an element at the beginning
        myArrayList.add(5);
        System.out.println(myArrayList.toString());
        System.out.println("Size: " + myArrayList.getSize());
        System.out.println("Contains 4: " + myArrayList.contains(4));
        System.out.println("Contains 0: " + myArrayList.contains(0));
        try{
            System.out.println("Minimum value: " + myArrayList.getMin());
        } catch (NoSuchElementException e){
            System.out.println("NoSuchElementException: " + e.getMessage());
        }
        try{
            System.out.println("Maximum value: " + myArrayList.getMax());
        } catch (NoSuchElementException e){
            System.out.println("NoSuchElementException: " + e.getMessage());
        }
        try{
            System.out.println("Minimum (" + myArrayList.deleteMin() + ") deleted: " + myArrayList.toString());
        } catch (NoSuchElementException e){
            System.out.println("NoSuchElementException: " + e.getMessage());
        }
        try{
            System.out.println("Maximum (" + myArrayList.deleteMax() + ") deleted: " + myArrayList.toString());
        } catch (NoSuchElementException e){
            System.out.println("NoSuchElementException: " + e.getMessage());
        }
        // Tests adding an element at the end of the list (creates a new ArrayList to test)
        SortedArrayList myArrayList2 = new SortedArrayList(6);
        myArrayList2.add(1);
        myArrayList2.add(5); // Adds an element at the current end
        myArrayList2.add(3);
        myArrayList2.add(9); // Adds an element at the current end
        myArrayList2.add(0);
        myArrayList2.add(11); // Adds an element at the end
        myArrayList2.add(2);
        myArrayList2.add(6);
        System.out.println(myArrayList2.toString());
        System.out.println("Size: " + myArrayList2.getSize());
        System.out.println("Contains 9: " + myArrayList2.contains(9));
        System.out.println("Contains 4: " + myArrayList2.contains(4));
        try{
            System.out.println("Minimum value: " + myArrayList2.getMin());
        } catch (NoSuchElementException e){
            System.out.println("NoSuchElementException: " + e.getMessage());
        }
        try{
            System.out.println("Maximum value: " + myArrayList2.getMax());
        } catch (NoSuchElementException e){
            System.out.println("NoSuchElementException: " + e.getMessage());
        }
        try{
            System.out.println("Minimum (" + myArrayList2.deleteMin() + ") deleted: " + myArrayList2.toString());
        } catch (NoSuchElementException e){
            System.out.println("NoSuchElementException: " + e.getMessage());
        }
        try{
            System.out.println("Maximum (" + myArrayList2.deleteMax() + ") deleted: " + myArrayList2.toString());
        } catch (NoSuchElementException e){
            System.out.println("NoSuchElementException: " + e.getMessage());
        }
        // Tests creating an array of negative capacity
        try{
            SortedArrayList myArrayList3 = new SortedArrayList(-2);
            System.out.println(myArrayList3.toString());
        } catch (NegativeArraySizeException e){
            System.out.println("NegativeArraySizeException: " + e.getMessage());
        }
    }
}