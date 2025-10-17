/******************************************************************************
*  File Name:    GUIHumanPlayer.java
*  Author:       Raashtra KC
*  Date:         2025-04-11
*  Language:     Java
*  Notes:        Requires the class files in /lib to compile.
******************************************************************************/

/**
 * GUIHumanPlayer represents a human player using the GUI for move input.
 * It polls the GUIBoard for user input and constructs a Move accordingly.
 */
public class GUIHumanPlayer extends Player
{
	private char Ptype;
	private GUIBoard guiBoard;

	 /**
     * Constructs a GUIHumanPlayer with the given player type.
     * @param type The player's character R or Y
     */
	public GUIHumanPlayer(char type) {
		this.Ptype = type;
	}

	 /**
     * Returns the character representing the player.
     * @return player type.
     */
	public char getPlayerType() {
		return Ptype;
	}

	/**
     * Waits for the player to select a column in the GUI and returns the move.
     * @return A Move object representing the player's move.
     */
	public Move getMove(Board board) { 
		guiBoard.resetSelectedRowAndColumn();
		while(guiBoard.getColumnSelected() == -1){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("Thread sleep interrupted!");
			}
		}
		
		int column = guiBoard.getColumnSelected();
		guiBoard.defaultTitle();
		return new Move(column);
	}

	/**
     * Sets the GUIBoard associated with this player.
     * @param guiBoard The GUIBoard instance.
     */
	public void setGUIBoard(GUIBoard guiBoard) {
		this.guiBoard = guiBoard;
	}
}