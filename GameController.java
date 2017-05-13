// Author: Quang-Tri Do
// Student number: 8248005
// Course: ITI 1121-C
// Assignment: 2
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.util.Random;
/**
 * The class <b>GameController</b> is the controller of the game. It implements 
 * the interface ActionListener to be called back when the player makes a move. It computes
 * the next step of the game, and then updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener {

 // ADD YOUR INSTANCE VARIABLES HERE
    private int size;
    private Point start,finalDot;
    private GameView gameView;
    private GameModel gameModel;
    private BoardView boardView;
    private LinkedList<Point> directions;
    private int[][] blocked,targets;
    private Point[][] path;
    private boolean isPath;

     /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param size
     *            the size of the board on which the game will be played
     */
    public GameController(int size) {
// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
        this.size=size;
        gameModel=new GameModel(size);
        gameView=new GameView(gameModel,this);
    }

  
    /**
     * Starts the game
     */
    public void start(){
// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
        boardView=new BoardView(gameModel,this);
    }

 
    /**
     * resets the game
     */
    public void reset(){
// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
        gameModel.reset();
    }

    /**
    *Breadth first search to find shortest path
    *@return Returns the final point of the shortest path, else returns a point of (-2,-2)
    **/
    private Point breadthFirstSearch(Point start){ //bfs goes through each neighboring dot first before going to the neighboring dot's neighboring dot
        LinkedList<Point> queue=new LinkedList<Point>();
        blocked=new int[gameModel.getSize()][gameModel.getSize()];
        fillBlocked();
        fillTargets();
        queue.add(start);
        isPath=true;
        finalDot=new Point(-2,-2);
        path=new Point[gameModel.getSize()][gameModel.getSize()]; // this is the path array where each position contains the x and y values for the previous direction
        blocked[start.getX()][start.getY()]=-1;
        path[start.getX()][start.getY()]=new Point(-1,-1); //assigned to impossible array value to know when to stop the while statement when backtracking through the array
        while(!queue.isEmpty()){
            Point c=queue.removeLast();
            Point[] pathArray=new Point[6];
            if(c.getX()%2==0){ // Every second row is shifted so we need this for the neighboring point coordinates
                pathArray[0]=new Point(c.getX()-1,c.getY()-1);
                pathArray[1]=new Point(c.getX()-1,c.getY());
                pathArray[2]=new Point(c.getX()+1,c.getY()-1);
                pathArray[3]=new Point(c.getX()+1,c.getY());
                pathArray[4]=new Point(c.getX(),c.getY()-1);
                pathArray[5]=new Point(c.getX(),c.getY()+1);
            }
            else{
                pathArray[0]=new Point(c.getX()-1,c.getY());
                pathArray[1]=new Point(c.getX()-1,c.getY()+1);
                pathArray[2]=new Point(c.getX()+1,c.getY());
                pathArray[3]=new Point(c.getX()+1,c.getY()+1);
                pathArray[4]=new Point(c.getX(),c.getY()-1);
                pathArray[5]=new Point(c.getX(),c.getY()+1);
            }
            for(int i=0;i<6;i++){
                if(isNeighboring(pathArray[i])){
                    if(blocked[pathArray[i].getX()][pathArray[i].getY()]!=-1){
                        if(targets[pathArray[i].getX()][pathArray[i].getY()]==-1){
                            path[pathArray[i].getX()][pathArray[i].getY()]=new Point(c.getX(),c.getY());
                            finalDot=pathArray[i];
                            return finalDot; //returns the final dot in the path array so we can backtrack later
                        }
                        else{ //bfs adds the stuff to blocked and then uses path to add trace the parent (thinking about it now wouldn't this override previous points with the same coordinates?)
                            queue.addFirst(pathArray[i]);
                            blocked[pathArray[i].getX()][pathArray[i].getY()]=-1;
                            path[pathArray[i].getX()][pathArray[i].getY()]=new Point(c.getX(),c.getY());
                        }
                    }
                }
            }
        }
        if(queue.size()==0){ //isPath global variable to see if there's a path
            isPath=false;
        }
        return finalDot;
    }
    /**
    *Gets the shortest path
    *@param this is the last point for the shortest path
    *@return Returns the Point that determines the next step
    */
    private Point getNext(Point lastPoint){ //fun part, uses the path array and the last point from bfs to go back through the array to get to the first point
        Point first=new Point(-1,-1);
        Point current=lastPoint;
        directions=new LinkedList<Point>();
        while(!(first.getX()==current.getX() && first.getY()==current.getY())){
            directions.addFirst(current);
            current=path[current.getX()][current.getY()];
        }
        Point nextPoint=(Point) directions.get(1);
        return nextPoint;
    }
    /**
    *Checks to see if the neighboring dot is a valid dot
    *@return true if it is and false otherwise
    */
    private boolean isNeighboring(Point a){
        boolean flag=false;
        if(a.getX()<0 || a.getX()>=gameModel.getSize() || a.getY()<0 || a.getY()>=gameModel.getSize()){
            return false;
        }
        if(gameModel.getCurrentStatus(a.getX(),a.getY())==0){
            flag=true;
        }
        return flag;
    }
    /**
    *Fills the target array with positions that the dot will try to reach
    */
    private void fillTargets(){
        targets=new int[gameModel.getSize()][gameModel.getSize()];
        for(int i=0;i<gameModel.getSize();i++){
            targets[0][i]=-1;
            targets[i][gameModel.getSize()-1]=-1;
            targets[gameModel.getSize()-1][i]=-1;
            targets[gameModel.getSize()-1][0]=-1;
        }
    }
    /**
    *Fills the blocked array with positions that the dot can't pass through
    */
    private void fillBlocked(){
        for(int row=0;row<gameModel.getSize();row++){
            for(int column=0;column<gameModel.getSize();column++){
                if(gameModel.getCurrentStatus(row,column)!=0){
                    blocked[row][column]=-1;
                }
            }
        }
    }
    /**
     * Callback used when the user clicks a button or one of the dots. 
     * Implements the logic of the game
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) {
        
 // REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
        if(e.getSource() instanceof DotButton){
            DotButton src=(DotButton) e.getSource();
            if(gameModel.getCurrentStatus(src.getRow(),src.getColumn())==0){
                gameModel.select(src.getRow(),src.getColumn());
                Point last=breadthFirstSearch(gameModel.getCurrentDot());
                if(isPath==false){
                    gameView.getBoardView().update();
                    JOptionPane.showMessageDialog (null,"You Win, it took "+gameModel.getNumberOfSteps()+" moves", "Results", JOptionPane.INFORMATION_MESSAGE);
                    int p = JOptionPane.showConfirmDialog(null, "Play again?","Circle The Dot",JOptionPane.YES_NO_OPTION);
                        if (p == 1){
                        System.exit(0); 
                    }
                        else{
                            reset();
                            gameView.getBoardView().update();
                        }
                }
                else{
                    Point current=getNext(last);
                    gameModel.setCurrentDot(current.getX(),current.getY());
                    gameView.getBoardView().update();
                    if(current.getX()==0 || current.getX()==gameModel.getSize()-1 || current.getY()==0 || current.getY()==gameModel.getSize()-1){
                        JOptionPane.showMessageDialog (null,"You Lose", "Results", JOptionPane.INFORMATION_MESSAGE);
                        int p = JOptionPane.showConfirmDialog(null, "Play again?","Circle The Dot",JOptionPane.YES_NO_OPTION);
                        if (p == 1){
                            System.exit(0); 
                        }
                        else{
                            reset();
                            gameView.getBoardView().update();
                        }
                    }
                }
            }
        }
        if(e.getSource() instanceof JButton){
            String cmd=e.getActionCommand();
            if(cmd.equals("Reset")){
                reset();
                gameView.getBoardView().update();
            }
            if(cmd.equals("Exit")){
                System.exit(0);
            }
        }
    }
}