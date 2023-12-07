/**
 * This class creates a Tour of Points using a 
 * Linked List implementation.  The points can
 * be inserted into the list using two heuristics.
 * @authors Maddy Brown and Yeseo Jeon
 * @author Layla Oesper, modified code 09-22-2017
 */

public class Tour {

    /** A helper class that defines a single node for use in a tour.
     * A node consists of a Point, representing the location of that
     * city in the tour, and a pointer to the next Node in the tour.
     */
    private class Node {
        private Point p;
        private Node next;
	
        /** Constructor creates a new Node at the given Point newP
         * with an intital next value of null.
         * @param newP - the point to associate with the Node.
         */
        public Node(Point newP) {
            p = newP;
            next = null;
        }

        /** Constructor creates a new Node at the given Point newP
         * with the specified next node.
         * @param newP - the point to associate with the Node.
         * @param nextNode - the nextNode this node should point to.
         */
        public Node(Point newP, Node nextNode) {
            p = newP;
            next = nextNode;
        }
	
        /**
         * Return the Point associated with this Node. 
         * (Same value can also be accessed from a Node object node
         * using node.p)
         * @return The Point object associated with this node.
         */
        public Point getPoint() {
            return p;
        }
        
        /**
         * Return the next Node associated with this Node. 
         * (Same value can also be accessed from a Node object node
         * using node.next)
         * @return The next Node object linked from this node..
         */
	   public Node getNext() {
	       return next;
	   }
          
    } // End Node class
    

    // Tour class Instance variables
    private Node head;
    private int size;
    /**
     * Constructor for the Tour class.  By default sets head to null.
     */
    public Tour() { 
        head = null;
    }

    /**
     * A method that returns a String version of the entire Tour.
     */
    public String toString(){
        String finalString = "";
        Node currentNode = head;
        Point currentNodePoint;
        String currentPointValue;
        while (currentNode != null){
            currentNodePoint = currentNode.getPoint();
            currentPointValue = currentNodePoint.toString();
            finalString = finalString + "\n" + currentPointValue;
            currentNode = currentNode.next;
        }
        return finalString;
    }

    /**
     * Constructs the nodes to points and connects the node points together.
     */
    public void draw(){
        Node currentNode = head;
        Point currentNodePoint;
        Node nextNode;
        Point nextNodePoint;
        Node lastNode = head;
        Point lastNodePoint;
        while (currentNode != null){
            if (currentNode.next == null){
                lastNode = currentNode;
            }
            else{
                currentNodePoint = currentNode.getPoint();
                nextNode = currentNode.next;
                nextNodePoint = nextNode.getPoint();
                currentNodePoint.draw();
                currentNodePoint.drawTo(nextNodePoint); 
            }
            currentNode = currentNode.next;
        }
        lastNodePoint = lastNode.getPoint();
        lastNodePoint.draw();
        lastNodePoint.drawTo(head.getPoint());
    }
    /**
     * Returns the numbers of nodes in the program.
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * Adds the distances between the node points in the tour and returns it.
     * @return
     */
    public double distance(){
        double distanceTotal = 0.0;
        Node firstNode = head;
        Point firstNodePoint = firstNode.getPoint();
        Node currentNode = head;
        Point currentNodePoint;
        Node nextNode;
        Point nextNodePoint;
        while (currentNode != null){
            if (currentNode.next == null){
                currentNodePoint = currentNode.getPoint();
                distanceTotal = distanceTotal + currentNodePoint.distanceTo(firstNodePoint); 
            }
            else{
                currentNodePoint = currentNode.getPoint();
                nextNode = currentNode.next;
                nextNodePoint = nextNode.getPoint();
                distanceTotal = distanceTotal + currentNodePoint.distanceTo(nextNodePoint);
            }
            currentNode = currentNode.next;
        }
        return distanceTotal; 
    }

    /**
     * Loops through all the nodes in the tour and inserts point p after the node point
     * it was closest to.
     * @param p Point p that will be inserted
     */
    public void insertNearest(Point p){ 
        double minDist = Double.MAX_VALUE;
        Node currentNode = head;
        Node insertNode = new Node(p);
        Node nearestNode = null;
        double currentDistance;
        Point currentNodePoint;
        if (head == null){
            currentNode = insertNode;
            head = currentNode;
        }
        else{
            while (currentNode != null){
                currentNodePoint = currentNode.getPoint();
                currentDistance = currentNodePoint.distanceTo(p);
                if (currentDistance < minDist){
                    nearestNode = currentNode;
                    minDist = currentDistance;
                }
                currentNode = currentNode.next;
            }
        insertNode.next = nearestNode.next;
        nearestNode.next = insertNode;
        }
        size++;
    }

    /**
     * Loops through all the nodes in the linked list and inserts Point P after the node that results 
     * in the smallest current total distance in tour.
     * @param p Point p that will be inserted
     */
    public void insertSmallest(Point p){ 
        double smallestDist = Double.MAX_VALUE;
        Node currentNode = head;
        Point currentPoint;
        Node insertNode = new Node(p);
        Point insertNodePoint = insertNode.getPoint();
        Node smallestDistanceNode = null;
        double currentTotalDistance = 0.0;
        double newDistance;
        Node nextNode;
        Point nextPoint;
        Point headPoint;
        if (head == null){
            head = insertNode;
            currentNode = insertNode;
        }
        else {
            while (currentNode != null){
                if (currentNode == head && currentNode.next == null){
                    smallestDistanceNode = currentNode;
                    Point currentNodePoint = currentNode.getPoint();
                    newDistance = currentNodePoint.distanceTo(insertNodePoint);
                }
                else if (currentNode.next == null) {
                    currentPoint = currentNode.getPoint();
                    headPoint = head.getPoint();
                    newDistance = currentTotalDistance + currentPoint.distanceTo(p) + p.distanceTo(headPoint) - currentPoint.distanceTo(headPoint);
                    if (newDistance < smallestDist){
                        smallestDist = newDistance;
                        smallestDistanceNode = currentNode;
                    }  
                }
                else{
                    currentPoint = currentNode.getPoint();
                    nextNode = currentNode.next;
                    nextPoint = nextNode.getPoint();
                    newDistance = currentTotalDistance - currentPoint.distanceTo(nextPoint) + currentPoint.distanceTo(p) + p.distanceTo(nextPoint); 
                    if (newDistance < smallestDist){
                        smallestDist = newDistance;
                        smallestDistanceNode = currentNode;
                    }
                }
                currentNode = currentNode.next; 
            }
        insertNode.next = smallestDistanceNode.next;
        smallestDistanceNode.next = insertNode;
        }
        size++;
    }
    public static void main(String[] args) {
        /* Use your main() function to test your code as you write it. 
         * This main() will not actually be run once you have the entire
         * Tour class complete, instead you will run the NearestInsertion
         * and SmallestInsertion programs which call the functions in this 
         * class. 
         */
        
        
        //One example test could be the follow (uncomment to run):
        Tour tour = new Tour();
        Point p = new Point(0,0);
        tour.insertNearest(p);
        p = new Point(0,100);
        tour.insertNearest(p);
        p = new Point(100, 100);
        tour.insertNearest(p);
        System.out.println("Tour distance = "+tour.distance());
        System.out.println("Number of points = "+tour.size());
        System.out.println(tour);
    
         

        // the tour size should be 3 and the distance 341.42 (don't forget to include the trip back
        // to the original point)
    
        // uncomment the following section to draw the tour, setting w and h to the max x and y 
        // values that occur in your tour points
	
        
        int w = 100 ; //Set this value to the max that x can take on
        int h = 100 ; //Set this value to the max that y can take on
        StdDraw.setCanvasSize(w, h);
        StdDraw.setXscale(0, w);
        StdDraw.setYscale(0, h);
        StdDraw.setPenRadius(.005);
        tour.draw(); 
    }
}