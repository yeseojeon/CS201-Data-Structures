// The Shrub class is a subclass of Plant.
// This class creates a Shrub object and counts how many times
// the Shrub constructor is called.
public class Shrub extends Plant {
    private static int counter;
    // Constructs a Shrub object using super() and adds one
    // to the counter each time a new Shrub object is created.
    public Shrub(){
        super("+#0", 0.3, 0.03);
        counter++;
    }
    // Returns the count of each time a new Shrub is created.
    public static int getCount(){
        return counter;
    }
}