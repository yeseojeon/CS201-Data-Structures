import java.util.Random;
/**
 * Garden.java
 * @authors Maddy Brown and Yeseo Jeon
 * Date: 1/16/2023
 * 
 * Template by Kiran Tomlinson
 * 
 * The Garden class initializes a grid (resembling a garden) and randomly 
 * populates it with instances of plants (resembling the garden growing).
 */

 // Constructs a grid filled with Empty objects that plants will eventually grow in.
 public class Garden {
    private Gardenable[][] grid;
    private int rows;
    private int cols;
    private int currentRow;
    private int currentColumn;
    private final double SEED_PROBABILITY = 0.01;
    public Garden(int rows, int cols){
        grid = new Gardenable[rows][cols];
        for(currentRow = 0; currentRow <= rows - 1; currentRow++){
            System.out.print("\n");
            for(currentColumn = 0; currentColumn <= cols - 1; currentColumn++){
                grid[currentRow][currentColumn] = new Empty();
            }
        }
    }
    // Updates the grid depending on the stages of each independent plant's growth.
    public void update(){
        for(currentRow = 0; currentRow <= grid.length - 1; currentRow++){
            System.out.print("\n");
            for(currentColumn = 0; currentColumn <= grid[0].length - 1; currentColumn++){
                if(!grid[currentRow][currentColumn].isAlive()){
                    Random randomGenerator = new Random();
                    if(randomGenerator.nextDouble() < SEED_PROBABILITY){
                        grid[currentRow][currentColumn] = Garden.getRandomGardenable();
                    }
                    grid[currentRow][currentColumn].print();
                }
                else{
                    grid[currentRow][currentColumn].step();
                    grid[currentRow][currentColumn].print(); 
                }
                System.out.print(" ");
            }
        }
    }
    // Returns a random plant of type Gardenable 
    // (each plant has a different likelihood of getting chosen).
    public static Gardenable getRandomGardenable(){
        Gardenable finalPlant;
        Random randomGenerator = new Random();
        double randomNumber = randomGenerator.nextDouble();
        if(0 < randomNumber && randomNumber < 0.25 ){
            Gardenable flower = new Flower();
            finalPlant = flower;
            }
        else if(0.25 <= randomNumber && randomNumber < 0.55 ){
            Gardenable tree = new Tree();
            finalPlant = tree;
            }
        else if(0.55 <= randomNumber && randomNumber < 0.90 ){
            Gardenable shrub = new Shrub();
            finalPlant = shrub;
            }
        else{
            Gardenable grass = new Grass();
            finalPlant = grass;
            }
        return finalPlant;
    }
    // Creates a new Garden object and updates it 100 times. Prints out the number
    // of times each type of plant was constructed throughout the simulation.
    public static void main(String[] args) {
        Garden garden = new Garden(10, 20);

        /*
        The following magic print statements use ANSI escape codes to manipulate the cursor.
        (for a list of all codes, see https://gist.github.com/fnky/458719343aabd01cfb17a3a4f7296797)
        Don't worry about understanding them--they're provided for you to make the Garden print nicely.
        This requires a terminal that supports ANSI escape codes (MacOS Terminal and VS Code both do).
        */
        System.out.print("\033[2J");            // clear the terminal
        System.out.print("\033[?25l");          // hide the cursor
        
        // Simulate the garden for 100 steps. 
        // Feel free to change this for testing, but your submission should run for 100 steps.
        for (int i = 0; i < 100; i++) {
            System.out.print("\033[H");         // jump the cursor to the top left
            System.out.flush();                 // force the display to update

            // Update and display the garden
            garden.update();

            // Don't worry about this try-catch; we'll go over exceptions soon
            try {                               
                Thread.sleep(100);              // Wait for 100 milliseconds. Can change for testing, but not submission
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("\033[?25h");          // re-enable the cursor
        System.out.println();
        System.out.println("Grasses: " + Grass.getCount());
        System.out.println("Flowers: " + Flower.getCount());
        System.out.println("Shrubs: " + Shrub.getCount());
        System.out.println("Trees: " + Tree.getCount());
    }
}
