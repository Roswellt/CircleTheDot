// Author: Quang-Tri Do
// Student number: 8248005
// Course: ITI 1121-C
// Assignment: 2
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.BorderFactory;
/**
 * The class <b>BoardView</b> provides the current view of the board. It extends
 * <b>JPanel</b> and lays out a two dimensional array of <b>DotButton</b> instances.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class BoardView extends JPanel {

     // ADD YOUR INSTANCE VARIABLES HERE
    private GameModel gameModel;
    private GameController gameController;
    private DotButton[][] board;
    private JPanel[] panelArray;

	/**
     * Constructor used for initializing the board. The action listener for
     * the board's DotButton is the game controller
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public BoardView(GameModel gameModel, GameController gameController) {
// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
        this.gameModel=gameModel;
        this.gameController=gameController;
        panelArray= new JPanel[this.gameModel.getSize()];
        setLayout(new GridLayout(this.gameModel.getSize(),this.gameModel.getSize()));
        board=new DotButton[this.gameModel.getSize()][this.gameModel.getSize()];
        for(int row=0;row<this.gameModel.getSize();row++){
            for(int column=0;column<this.gameModel.getSize();column++){
                board[row][column]= new DotButton(row,column,this.gameModel.getCurrentStatus(row,column));
                board[row][column].addActionListener(this.gameController);
            }
        }
        for(int row=0;row<this.gameModel.getSize();row++){
            panelArray[row]=new JPanel();
            for(int column=0;column<this.gameModel.getSize();column++){
                panelArray[row].add(board[row][column]);
            }
            if(row%2!=0){
                panelArray[row].setBorder(BorderFactory.createEmptyBorder(0, 20,0, 20)); //adds a border to the rows so that the board isn't aligned
            }
            else{
                panelArray[row].setBorder(BorderFactory.createEmptyBorder(0, 0,0, 0));
            }
            panelArray[row].setLayout(new FlowLayout(FlowLayout.LEFT,0,0)); //puts every row of the dotbuttons on it's own instance of jpanel, flowlayout so they are evenly spaced
            add(panelArray[row]); //adds the jpanel rows to the actual board with the gridlayout
        }

    }

 	/**
	 * update the status of the board's DotButton instances based on the current game model
	 */

    public void update(){
  // REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
        for(int row=0;row<this.gameModel.getSize();row++){
            for(int column=0;column<this.gameModel.getSize();column++){
                board[row][column].setType(this.gameModel.getCurrentStatus(row,column));
            }
        }
	}
}