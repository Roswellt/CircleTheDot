// Author: Quang-Tri Do
// Student number: 8248005
// Course: ITI 1121-C
// Assignment: 2
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out an instance of  <b>BoardView</b> (the actual game) and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {

 // ADD YOUR INSTANCE VARIABLES HERE
    private GameModel model;
    private GameController gameController;
    private BoardView boardView;
 
    /**
     * Constructor used for initializing the Frame
     * 
     * @param model
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel model, GameController gameController) {
 // REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
        this.model=model;
        this.gameController=gameController;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        JButton reset=new JButton("Reset");
        reset.addActionListener(this.gameController);
        JButton exit=new JButton("Exit");
        exit.addActionListener(this.gameController);
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.WHITE);
        controlPanel.add(reset);
        controlPanel.add(exit);
        add(controlPanel, BorderLayout.SOUTH);
        boardView=new BoardView(model,gameController);
        add(boardView);
        setVisible(true);
        pack();
    }

   /**
     * Getter method for the attribute board.
     * 
     * @return a reference to the BoardView instance
     */

    public BoardView getBoardView(){
 // REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
        return boardView; //no idea why I need this
   }
}