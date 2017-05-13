// Author: Quang-Tri Do
// Student number: 8248005
// Course: ITI 1121-C
// Assignment: 2
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.Border;

import java.awt.Color;

/**
 * In the application <b>Circle the dot</b>, a <b>DotButton</b> is a specialized type of
 * <b>JButton</b> that represents a dot in the game. It uses different icons to
 * visually reflect its state: a blue icon if the blue dot is currently on this location
 * an orange icon is the dot has been selected and a grey icon otherwise.
 * 
 * The icon images are stored in a subdirectory ``data''. They are:
 * data/ball-0.png => grey icon
 * data/ball-1.png => orange icon
 * data/ball-2.png => blue icon
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class DotButton extends JButton {
    private int row,column,type;

    // ADD YOUR INSTANCE VARIABLES HERE
    /**
     * A an array is used to cache all the images. Since the images are not
     * modified. All the cells that display the same image reuse the same
     * <b>ImageIcon</b> object. Notice the use of the keyword <b>static</b>.
     * From Puzzler
     */

    private static final ImageIcon[] icons = new ImageIcon[3];
    /**
     * Constructor used for initializing a cell of a specified type.
     * 
     * @param row
     *            the row of this Cell
     * @param column
     *            the column of this Cell
     * @param type
     *            specifies the type of this cell
     */

    public DotButton(int row, int column, int type) {
// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
        this.row=row;
        this.column=column;
        this.type=type;
        setBackground(Color.WHITE);
        setIcon(getImageIcon());
        Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
        setBorder(emptyBorder);
        setBorderPainted(false);
    }
/**
     * Determine the image to use based on the cell type. Uses
     * <b>getResource</b> to locate the image file, either on the file system or
     * the .jar file. Implements a caching mechanism.
     * From Puzzler
     *
     * @return the image to be displayed by the button
     */
    private ImageIcon getImageIcon() { //copied from puzzler, assigns the image icons to the dotbuttons
        int id;
        id = type;
        if (icons[id] == null) {
            String strId = Integer.toString(id);
            icons[id] = new ImageIcon("data/ball-" + strId + ".png");
        }
        return icons[id];
    }

    /**
     * Changes the cell type of this cell. The image is updated accordingly.
     * 
     * @param type
     *            the type to set
     */

    public void setType(int type) {
// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
        this.type=type;
        this.setIcon(getImageIcon());
    }


 
    /**
     * Getter method for the attribute row.
     * 
     * @return the value of the attribute row
     */

    public int getRow() {
// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
        return this.row;
    }

    /**
     * Getter method for the attribute column.
     * 
     * @return the value of the attribute column
     */

    public int getColumn() {
// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
        return this.column;
    }
}