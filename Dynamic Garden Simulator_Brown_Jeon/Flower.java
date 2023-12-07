// The Flower class is a subclass of Plant.
// This class creates a Flower object and counts how many times
// the Flower constructor is called.
public class Flower extends Plant {
    private static int counter;
    // Constructs a Flower object using super() and adds one
    // to the counter each time a new Flower object is created.
    public Flower(){
        super(".a@", 0.1, 0.1);
        counter++;
    }
    // Returns the count of each time a new Flower is created.
    public static int getCount(){
        return counter;
    }
}
