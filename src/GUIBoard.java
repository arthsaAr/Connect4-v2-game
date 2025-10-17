/******************************************************************************
*  File Name:    GUIBoard.java
*  Author:       Raashtra KC
*  Date:         2025-04-11
*  Language:     Java
*  Notes:        Requires the class files in /lib to compile.
******************************************************************************/

import javax.swing.*;
import java.awt.*;
/**
 * GUIBoard is the graphical representation of the Connect four game board
 * Manages the buttons, display and player interaction.
 */
public class GUIBoard
{
	private JFrame frame;
	private gameButton[][] button;
	private int selectedRow = -1;
	private int selectedCol = -1;
	private Board board;
	private Player pR, pY;
	private final int FRAME_WIDTH = 400;
    private final int FRAME_HEIGHT = 400;

	private String defaultTitle = "Connect Four";

	/**
	 * Sets the window title of the game window
	 * @param title The new title for the main game frame
	 */
	public void setTitle(String title) {
		if(frame != null){
			frame.setTitle(title);
		}
	}	

	/**
	 * Resets the frame title to the default title of connect four
	 */
	public void defaultTitle() {
		frame.setTitle(defaultTitle);
	}

	/**
	 * Gets the row of the selected button.
	 * @return selected row index.
	 */
	public int getRowSelected() { 
		return selectedRow;
	}

	/**
	 * Gets the column of the selected button/by user.
	 * @return selected column index.
	 */
	public int getColumnSelected() { 
		return selectedCol;
	}

	/** 
	 * Resets the selected row and column values to -1(This makes no selection of any of the columns)
	 * */
	public void resetSelectedRowAndColumn() {
		selectedRow = -1;
        selectedCol = -1;
	}

	/**
	 * Sets our final winner and also prints our to the console
	 * @param winner The player who won.
	 */
	public void setWinner(Player winner) {
		System.out.println("Winner: " + winner.getPlayerType());
	}

	/**
	 * Constructs the GUIBoard with buttons for each cell of specific size in the game board.
	 * @param board The board to display
	 * @param title The game window title
	 * @param playerRed Player object for Red
	 * @param playerYellow Player object for Yellow
	 */
	public GUIBoard(Board board, String title, Player playerRed, Player playerYellow) {
		this.board = board;
		this.pR = playerRed;
		this.pY = playerYellow;

		frame = new JFrame(title);		//Creating our main game frame where all the buttons will be added
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Connect Four");
		frame.setLocation(100, 100);
		frame.setLayout(new GridLayout(board.getRows(), board.getColumns()));
		button = new gameButton[board.getRows()][board.getColumns()];

		for(int i=0; i<board.getRows(); i++){
			for(int j=0; j<board.getColumns(); j++){
				gameButton btn = new gameButton(i,j);
				btn.addActionListener(
					e -> {
						gameButton b = (gameButton) e.getSource();
						selectedRow = b.getRow();
						selectedCol = b.getCol();
					}
				);
				button[i][j] = btn;		//initializing each button to the frame
				frame.add(btn);
			}
		}
		frame.pack();
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true); //setting our frame with all the buttons visible to user for playing/gameplay
	}

	/** 
	 * Clears and resets the GUI board for a new game. 
	 **/
	public void reseter() {
		frame.getContentPane().removeAll();		//removing everything to make sure no duplicate boards are on the screen when player choose to replay the game
		frame.repaint();
		frame.revalidate();
		this.board = null;		//setting the default board and button as null, so reinitialize
		this.button = null;
		frame.setVisible(false);	//making the old frame invisible

	}

	/**
	 * Updates the button colors on the GUI to reflect the current board state.
	 * @param board updated board state.
	 */
	public void updateBoard(Board board) {
		for(int i=0; i<board.getRows(); i++){
			for(int j=0; j<board.getColumns(); j++){
				Player current = board.getPlayerAtLocation(i,j);
				if(current != null) {
					button[i][j].setPlayerType(current.getPlayerType());
				} else {
					button[i][j].setPlayerType('.');
				}
				button[i][j].repaint();
			}
		}
	}

	//Not implementing these two methods as per instructions
	//////////////////////////////////////////////
	public void setThickness(float thickness) {
	}

	public void setThickness(double thickness) {
	}	
	//////////////////////////////////////////////
}

/**
 * Represents an individual cell (button) in the Connect Four board.
 */
class gameButton extends JButton {
	private int r;
	private int c;
	private char playerType = '.';

	/**
	 * Constructs a game button with its row and column.
	 * @param row The row of the button.
	 * @param col The column of the button.
	 */
	public gameButton(int row, int col){
		this.r = row;
		this.c = col;
	}

	/**
	 * Gets the row index of the button.
	 * @return Row index.
	 */
	public int getRow() {
		return r;
	}

	/**
	 * Gets the column index of the button.
	 * @return Column index.
	 */
	public int getCol(){
		return c;
	}

	/**
	 * Sets the player type for this button ('R', 'Y', or '.').
	 * @param type The character representing the player.
	 */
	public void setPlayerType(char type){
		this.playerType = type;
	}

	/**
	 * Overrides the paintComponent to draw a colored circle based on player type.
	 * @param g Graphics context used for painting.
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		int diameter = Math.min(getWidth(), getHeight()) - 10;
		int x = (getWidth() - diameter) /2;
		int y = (getHeight() - diameter) / 2;

		if(playerType == 'R'){
			g2.setColor(Color.RED);
		}else if(playerType == 'Y') {
			g2.setColor(Color.YELLOW);
		} else {
			g2.setColor(Color.WHITE);
		}
		g2.fillOval(x,y,diameter, diameter);
		g2.setColor(Color.BLACK);
		g2.drawOval(x, y, diameter, diameter);

	}
 }
