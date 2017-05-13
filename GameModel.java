// Author: Quang-Tri Do
// Student number: 8248005
// Course: ITI 1121-C
// Assignment: 2
import java.util.Random;
/**
 * The class <b>GameModel</b> holds the model, the state of the systems. 
 * It stores the followiung information:
 * - the current location of the blue dot
 * - the state of all the dots on the board (available, selected or 
 *  occupied by the blue dot
 * - the size of the board
 * - the number of steps since the last reset
 *
 * The model provides all of this informations to the other classes trough 
 *  appropriate Getters. 
 * The controller can also update the model through Setters.
 * Finally, the model is also in charge of initializing the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class GameModel {


    /**
     * predefined values to capture the state of a point
     */
 	public static final int AVAILABLE 	= 0;
	public static final int SELECTED 	= 1;
	public static final int DOT 		= 2;

// ADD YOUR INSTANCE VARIABLES HERE
	private Point[][] pointArray;
	private int size,steps;
	private int[][] stateArray;
	private Random r=new Random();

    /**
     * Constructor to initialize the model to a given size of board.
     * 
     * @param size
     *            the size of the board
     */
    public GameModel(int size) {
// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
    	this.size=size;
    	pointArray=new Point[size][size];
    	stateArray=new int[size][size];
    	for(int row=0;row<size;row++){
    		for(int column=0;column<size;column++){
    			pointArray[row][column]=new Point(row,column);
    			if(r.nextInt((10-1)+1)+1==1){
					stateArray[row][column]=SELECTED;
				}
				else{
					stateArray[row][column]=AVAILABLE;
				}
    		}
    	}
    	if(size%2==0){ //puts the blue dot in the center 4 dots or the center 9 dots depending on even or uneven
    		int dotRow=r.nextInt((size/2-(size/2-1))+1)+(size/2-1);
    		int dotColumn=r.nextInt((size/2-(size/2-1))+1)+(size/2-1);
    		stateArray[dotRow][dotColumn]=DOT;
    	}
    	else{
    		int dotRow=r.nextInt(((size/3+2)-size/3)+1)+size/3;
			int dotColumn=r.nextInt(((size/3+2)-size/3)+1)+size/3;
			stateArray[dotRow][dotColumn]=DOT;
    	}
    }


    /**
     * Resets the model to (re)start a game. The previous game (if there is one)
     * is cleared up . The blue dot is positioned as per instructions, and each 
     * dot of the board is either AVAILABLE, or SELECTED (with
     * a probability 1/INITIAL_PROBA). The number of steps is reset.
     */
    public void reset(){
 // REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
    	steps=0;
    	for(int row=0;row<size;row++){
    		for(int column=0;column<size;column++){
    			pointArray[row][column].reset(row,column);
    			if(r.nextInt((10-1)+1)+1==1){
					stateArray[row][column]=SELECTED;
				}
				else{
					stateArray[row][column]=AVAILABLE;
				}
    		}
    	}
    	if(size%2==0){
    		int dotRow=r.nextInt((size/2-(size/2-1))+1)+(size/2-1);
    		int dotColumn=r.nextInt((size/2-(size/2-1))+1)+(size/2-1);
    		stateArray[dotRow][dotColumn]=DOT;
    	}
    	else{
    		int dotRow=r.nextInt(((size/3+2)-size/3)+1)+size/3;
			int dotColumn=r.nextInt(((size/3+2)-size/3)+1)+size/3;
			stateArray[dotRow][dotColumn]=DOT;
    	}
    }


    /**
     * Getter <b>class</b> method for the size of the game
     * 
     * @return the value of the attribute sizeOfGame
     */   
    public  int getSize(){
 // REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
    	return this.size;
   }

    /**
     * returns the current status (AVAILABLE, SELECTED or DOT) of a given dot in the game
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public int getCurrentStatus(int i, int j){
// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
    	return stateArray[i][j];
    }


    /**
     * Sets the status of the dot at coordinate (i,j) to SELECTED, and 
     * increases the number of steps by one
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void select(int i, int j){
// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
    	stateArray[i][j]=SELECTED;
    	steps++;
    }

    /**
     * Puts the blue dot at coordinate (i,j). Clears the previous location 
     * of the blue dot. If the i coordinate is "-1", it means that the blue 
     * dot exits the board (the player lost)
     *
     * @param i
     *            the new x coordinate of the blue dot
     * @param j
     *            the new y coordinate of the blue dot
     */   
    public void setCurrentDot(int i, int j){
// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
    	Point oldDot=getCurrentDot();
    	stateArray[oldDot.getX()][oldDot.getY()]=AVAILABLE;
    	stateArray[i][j]=DOT;

   }

    /**
     * Getter method for the current blue dot
     * 
     * @return the location of the curent blue dot
     */   
    public Point getCurrentDot(){
// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
    	Point dot=new Point(0,0);
    	for(int row=0;row<size;row++){
    		for(int column=0;column<size;column++){
    			if (stateArray[row][column]==DOT){
    				dot.reset(row,column);
    			}
    		}
    	}
    	return dot;
    }

    /**
     * Getter method for the current number of steps
     * 
     * @return the current number of steps
     */   
    public int getNumberOfSteps(){
// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
    	return steps;
    }
}