// Author: Quang-Tri Do
// Student number: 8248005
// Course: ITI 1121-C
// Assignment: 2
/**
 * The class <b>Point</b> is a simple helper class that starts a 2 dimentional element on a grid
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class Point {

   // ADD YOUR INSTANCE VARIABLES HERE
	private int x,y;
    /**
     * Constructor 
     * 
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     */
    public Point(int x, int y){
// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
    	this.x=x;
    	this.y=y;
    }

    /**
     * Getter method for the attribute x.
     * 
     * @return the value of the attribute x
     */
    public int getX(){
// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
    	return this.x;
    }
    
    /**
     * Getter method for the attribute y.
     * 
     * @return the value of the attribute y
     */
    public int getY(){
// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
    	return this.y;
    }
    /**
     * Setter for x and y.
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     */
    public void reset(int x, int y){
 // REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
    	this.x=x;
    	this.y=y;
  }
 }