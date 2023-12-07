// The Tree class is a subclass of Plant.
// This class creates a Tree object and counts how many times
// the Tree constructor is called.
public class Tree extends Plant {
    private static int counter;
    // Constructs a Tree object using super() and adds one
    // to the counter each time a new Tree object is created.
    public Tree(){
        super(".,|T", 0.02, 0.01);
        counter++;
    }
    // Returns the count of each time a new Tree is created.
    public static int getCount(){
        return counter;
    }
}
