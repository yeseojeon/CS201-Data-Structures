// The Grass class is a subclass of Plant.
// This class creates a Grass object and counts how many times
// the Grass constructor is called.
public class Grass extends Plant {
    private static int counter;
    // Constructs a Grass object using super() and adds one
    // to the counter each time a new Grass object is created.
    public Grass(){
        super(":]", 0.2, 0.02);
        counter++;
    }
    // Returns the count of each time a new Grass is created.
    public static int getCount(){
        return counter;
    }
}