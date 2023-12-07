import java.util.Random;
// The Plant class constructs a Plant object and defines the methods
// step(), print(), and isAlive().
public class Plant implements Gardenable {
    private String stages;
    private double growProbability;
    private double wiltProbability;
    private int currentIndex;
    private boolean isWilting;
    // Constructs an object of type Plant and initializes its
    // instance variables. 
    public Plant(String stages, double growProbability, double wiltProbability){
        this.stages = stages;
        this.growProbability = growProbability;
        this.wiltProbability = wiltProbability;
        currentIndex = 0;
        isWilting = false;
    }
    // Updates the plant so that it moves up
    // to the next step in it's growth or wilting progression.
    public void step(){
        Random randomGenerator = new Random();
        if(currentIndex == stages.length() - 1){
            if(randomGenerator.nextDouble() < wiltProbability){
                isWilting = true;
            }
        }
        else if(randomGenerator.nextDouble() < growProbability){
            currentIndex++;
        }
    }
    // Prints out the current state of the plant.
    public void print(){
        if(isWilting == true){
            System.out.print(" ");
        }
        else{
            System.out.print(stages.charAt(currentIndex));
        }
    }
    // Determines and returns whether or not the plant is alive.
    public boolean isAlive(){
        if(isWilting == true){
            return false;
        }
        else{
            return true;
        }
    }
}




