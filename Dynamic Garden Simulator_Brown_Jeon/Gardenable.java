// A contract for which methods any class that implements
// the Gardenable interface will need to implement. 
public interface Gardenable {
    // Returns a boolean value describing if the plant is alive or not.
    public boolean isAlive();
    // Takes the next step in a plant's growth or wilting progression. 
    public void step();
    // Prints out the current stage of the plant.
    public void print();
    
}
