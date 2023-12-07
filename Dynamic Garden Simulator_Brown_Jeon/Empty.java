// Declares the methods that an object of type Empty will have. 
public class Empty implements Gardenable {
    // Empty objects do not step.
    public void step(){
    }
    // Empty objects should print out a blank space.
    public void print(){
        System.out.print(" ");
    }
    // Empty objects are not alive.
    public boolean isAlive(){
        return false;
    }
}
